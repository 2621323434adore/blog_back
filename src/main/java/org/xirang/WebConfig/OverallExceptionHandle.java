package org.xirang.WebConfig;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.xirang.Utils.ResultCode;
import org.xirang.Utils.ResultObject;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class OverallExceptionHandle {   //注册全局异常处理器
    @ExceptionHandler({NullPointerException.class
    })
    @ResponseBody
    public ResultObject overallExceptionHandle() {
        ResultObject object = new ResultObject();
        object.setResultCode(ResultCode.ERROR).setMsg("请求发送错误！请重新尝试");
        return object;
    }

    @ExceptionHandler({HttpClientErrorException.NotFound.class})
    @ResponseBody
    public ResultObject NoTFoundHandleHandel(Exception e) {
        ResultObject object = new ResultObject();
        object.setResultCode(ResultCode.NotFoundException);
        object.setMsg("出错了！什么都没有找到");
        return object;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultObject MissingRequestParameterExceptionHandle(HttpServletRequest request) {
        ResultObject object = new ResultObject();
        object.setResultCode(ResultCode.WARNING);
        object.setMsg("你的请求参数不完整！请重试");
        return object;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultObject HttpRequestMethodNotSupportedExceptionHandle(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        ResultObject object = new ResultObject();
        object.setResultCode(ResultCode.WARNING);
        object.setMsg("出错了！你的请求方式不正确.");
        return object;
    }
@ExceptionHandler({NotLoginException.class})
@ResponseBody
public ResultObject HttpLoginExceptionHandle() {
    ResultObject object = new ResultObject();
    object.setResultCode(ResultCode.NotUserAuth);
    object.setMsg("您还未登录，请登录后重试");
    return object;
}
@ExceptionHandler({CannotGetJdbcConnectionException.class})
@ResponseBody
public ResultObject CannotGetJdbcConnectionExceptionHandle() {
    ResultObject object = new ResultObject();
    object.setResultCode(ResultCode.FAILED);
    object.setMsg("网络连接超时！请刷新页面后重试。（我们将不保存你当前的表单数据）");
    return object;
}
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultObject GeneralExceptionHandle() {
        ResultObject object = new ResultObject();
        object.setResultCode(ResultCode.FAILED);
        object.setMsg("请求失败！请稍后重试");
        return object;
    }
}
