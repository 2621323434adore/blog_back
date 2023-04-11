package org.xirang.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xirang.Entity.XiRangUser;
import org.xirang.Mapper.UserMapper;
import org.xirang.Service.UserService;

@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, XiRangUser> implements UserService {
    @Autowired
    UserMapper userMapper;

    public XiRangUser login(String username, String password) {
        QueryWrapper<XiRangUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "name", "gender", "birth_date", "user_date", "email", "qq", "mobile", "label");
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        XiRangUser xiRangUser = getOne(queryWrapper);
        return xiRangUser;
    }

        public Boolean findUser(String username, long id) {
        QueryWrapper<XiRangUser> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username")
                .eq("username", username)
                .eq("id", id);
        XiRangUser one = getOne(wrapper);
        if (one == null) {
            return false;
        } else {
            return true;
        }
    }
    public XiRangUser createUser(String username, String password , String name, String email, String qq, String phone, char gender, String birth_date, String user_date, String label){
        XiRangUser xiRangUser = new XiRangUser(0L,username, name,password,gender,birth_date,user_date,label, email, qq, phone,"N");
        boolean save = save(xiRangUser);
        if(save){
            return xiRangUser;
        }else {
            return null;
        }
    }
}
