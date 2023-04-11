package org.xirang.Service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import org.xirang.Service.UserTokenService;
import org.xirang.Utils.PasswordUtil;

import java.util.List;

@Component
public class UserTokenImpl implements UserTokenService {
    @Override
    public boolean isAuthenticated(String userToken) {
       // Object token = StpUtil.getLoginIdByToken(userToken);
        List<String> list = StpUtil.searchTokenValue(userToken, 0, -1, true);
        if(list.size() == 0) {
            return false;
        }
        for (String token : list) {
            String token1 = PasswordUtil.verifyToken(token);
            if(token1.equals(userToken)) {
            break;
            }else {
                return  false;
            }
        }
        return true;
    }
}
