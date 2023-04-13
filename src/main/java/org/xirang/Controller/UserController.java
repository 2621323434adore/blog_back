package org.xirang.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xirang.Entity.XiRangUser;
import org.xirang.Service.Impl.UserServiceImpl;
import org.xirang.Service.Impl.UserTokenImpl;
import org.xirang.Utils.PasswordUtil;
import org.xirang.Utils.ResultCode;
import org.xirang.Utils.ResultObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController()
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    UserTokenImpl userToken;

    @CrossOrigin("*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    /**
    @Param loginInfo  获取前端提交的数据 （前端使用FormData提交的表单数据）
    Login（） 进行用户认证
     */
    public SaResult Login(@RequestParam Map<String, String> loginInfo, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");   //返回跨域响应头
        response.setHeader("Access-Control-Allow-Methods", "POST"); //设置请求方式应该为POST
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");
        String encode = PasswordUtil.encode(password);     //对密码做md5转换
        XiRangUser login = userServiceImpl.login(username, encode); //进行数据查询查看账户和密码是否一致
        if (login != null) {   //查询一致 数据将不为空
            StpUtil.login(login.getId());  //利用sa-token对用户进行认证
            SaResult.ok();  //完成用户认证
        } else {
            SaResult.error("登录失败！");   //用户未查询到就返回登录失败
        }
        return SaResult.data(StpUtil.getTokenInfo()).set("UserInfo", login);
        //data 设置用户token 并交给SaResult工具返回
        //set 设置用户详情信息
    }


    /**
    * @param username
    * @param password
    * @param name
    * @param email
    * @param qq
    * @param phone
    * @param gender
     * .....
     * 指定用户数据
    * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultObject registerUser(@RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String qq,
                                     @RequestParam String phone,
                                     @RequestParam(defaultValue = "男") char gender,
                                     @RequestParam String birth_date,
                                     @RequestParam String user_date,
                                     @RequestParam String label) {
        ResultObject object = new ResultObject();
        //利用StrUtil工具类对数据做判空处理
        if (StrUtil.isEmpty(username) && StrUtil.isEmpty(password) && StrUtil.isEmpty(email) && StrUtil.isEmpty(qq) && StrUtil.isEmpty(birth_date)) {
            object.setMsg("数据资料不完整，请检查表单后再试").setResultCode(ResultCode.WARNING);
            return object;
        } else {
            //如果数据不为空 就创建用户对象
            XiRangUser user = userServiceImpl.createUser(username, PasswordUtil.encode(password), name, email, qq, phone, gender, birth_date, user_date, label);
          //类对象返回失败
            if (user == null) {
                object.setMsg("注册失败，请重新尝试！").setResultCode(ResultCode.WARNING);
                return object;
            } else {
                object.setMsg("注册成功，快去登录吧！").setResultCode(ResultCode.SUCCESS);
                return object;
            }
        }
    }


    /**
     * findUser  当用户重新进入页面后 前端将会重新检查用户的token是否过期、用户的登录状态是否正常
     * */
    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public ResultObject findUser(@RequestParam String username, @RequestParam Long id, @RequestParam String token, HttpServletResponse response) {
        ResultObject object = new ResultObject();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        boolean authStatus = userToken.isAuthenticated(token);   //根据token查询用户的token是否过期
        boolean userStatus = userServiceImpl.findUser(username, id); //根据用户的用户名与用户id 查询用户是否纯在，防止恶意修改用户数据
        if (authStatus && userStatus) {   //如果token 未过期，用户存在则保留用户token信息
            object.setResultCode(ResultCode.SUCCESS).setMsg("用户已登录").setData("y");
        } else if (userStatus) { //如果用户信息为存在，则将用户强制下线
            object.setResultCode(ResultCode.SUCCESS).setMsg("身份验证已过期，请重新登录！");
            StpUtil.logout(id);
        } else {
            // 所有情况都不存在 则抛出未登录
            object.setResultCode(ResultCode.NotUserAuth).setMsg("你还未登录，请登录后尝试");
        }
        return object;
    }


    /**
     * 用户自己退出登录
    * */
    @RequestMapping("/logout")
    public ResultObject Logout(@RequestParam Long id, @RequestParam String token) {
        ResultObject resultObject = new ResultObject();
        try {
            // StpUtil.logout(10001);
            StpUtil.logoutByTokenValue("token");
            resultObject.setMsg("退出成功").setResultCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            resultObject.setResultCode(ResultCode.NotFoundException).setMsg("注销失败！！");
        }
        return resultObject;
    }

    /**
    * 用户注册时将采用该方法使得唯一值不重复
    * */
    @RequestMapping("/verifyProp")
    public ResultObject VerifyProp(@RequestParam String key, @RequestParam String value) {
        ResultObject object = new ResultObject();
        switch (key) {
            case "username": {
                boolean status = userServiceImpl.findUserByUsername(value);
                if (status) {
                    object.setResultCode(ResultCode.SUCCESS).setData("Y");
                } else {
                    object.setResultCode(ResultCode.SUCCESS).setData("N");
                }
                break;
            }
            case "email": {
                boolean status = userServiceImpl.findUserByEmail(value);
                if (status) {
                    object.setResultCode(ResultCode.SUCCESS).setData("Y");
                } else {
                    object.setResultCode(ResultCode.SUCCESS).setData("N");
                }
                break;
            }
            case "qq": {
                boolean status = userServiceImpl.findUserByQQ(value);
                if (status) {
                    object.setResultCode(ResultCode.SUCCESS).setData("Y");
                } else {
                    object.setResultCode(ResultCode.SUCCESS).setData("N");
                }
                break;
            }
        }
        return object;
    }
}
