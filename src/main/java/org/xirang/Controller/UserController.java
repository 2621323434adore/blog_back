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
    public SaResult Login(@RequestParam Map<String, String> loginInfo, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");
        String encode = PasswordUtil.encode(password);
        XiRangUser login = userServiceImpl.login(username, encode);
        if (login != null) {
            StpUtil.login(login.getId());
            SaResult.ok();
        } else {
            SaResult.error("登录失败！");
        }
        return SaResult.data(StpUtil.getTokenInfo()).set("UserInfo", login);
    }

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
        if (StrUtil.isEmpty(username) && StrUtil.isEmpty(password) && StrUtil.isEmpty(email) && StrUtil.isEmpty(qq) && StrUtil.isEmpty(birth_date)) {
            object.setMsg("数据资料不完整，请检查表单后再试").setResultCode(ResultCode.WARNING);
            return object;
        } else {
            XiRangUser user = userServiceImpl.createUser(username, PasswordUtil.encode(password), name, email, qq, phone, gender, birth_date, user_date, label);
            if (user == null) {
                object.setMsg("注册失败，请重新尝试！").setResultCode(ResultCode.WARNING);
                return object;
            } else {
                object.setMsg("注册成功，快去登录吧！").setResultCode(ResultCode.SUCCESS);
                return object;
            }
        }
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public ResultObject findUser(@RequestParam String username, @RequestParam Long id, @RequestParam String token, HttpServletResponse response) {
        ResultObject object = new ResultObject();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        boolean authStatus = userToken.isAuthenticated(token);
        boolean userStatus = userServiceImpl.findUser(username, id);
        if (authStatus && userStatus) {
            object.setResultCode(ResultCode.SUCCESS).setMsg("用户已登录").setData("y");
        } else if (userStatus) {
            object.setResultCode(ResultCode.SUCCESS).setMsg("身份验证已过期，请重新登录！");
            StpUtil.logout(id);
        } else {
            object.setResultCode(ResultCode.NotUserAuth).setMsg("你还未登录，请登录后尝试");
        }
        return object;
    }

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
