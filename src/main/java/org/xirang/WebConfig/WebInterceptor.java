package org.xirang.WebConfig;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xirang.Utils.PasswordUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {
    protected boolean status = false;
    /**
     * 这个方法在HandlerMapping找到对应的处理对象,但还没有请求这个对象时处理
     * 也就是在Controller的方法之前执行
     *
     * @param request
     * @param response
     * @param handler  这个参数就是代码着Controller的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("接收到请求："+request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("xirang_token")) {
                List<String> list = StpUtil.searchTokenValue(cookie.getValue(), 0, -1, true);
                if(list.size() == 0) {
                    throw new NotLoginException("用户未登录", NotLoginException.DEFAULT_MESSAGE, NotLoginException.NOT_TOKEN);
                }
                for (String value : list) {
                    String token = PasswordUtil.verifyToken(value);
                    if (token.equals(cookie.getValue())) {
                        System.out.println("用户已经登录: " + cookie.getValue());
                       status = true;
                    }
                }
            }
        }
        return status;
    }

    /**
     * 在Handler/controller的方法处理完逻辑,渲染视图之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("数据响应成功");
    }

    /**
     * 在视图渲染完之后执行,常用于资源清理等等
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        status = false;
    }
}
