package org.xirang.Utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class ResultObject extends HashMap<String,Object> {
//       public static final int success = 200;
//       public static final int failed = 400;
//       public static final int error = 500;
//       public static final int warning = 403;
//       public static final int NotFoundException = 404;
//       public static final int NotUserAuth = 401;
//       public static final int RequestNotSatisfiable = 417;

    public ResultObject setData(String key ,Object data) {
        if(data !=null){
            put(key,data);
        }else {
            put(key,"数据请求失败");
        }
        return this;
    }
    public ResultObject setData(Object data) {
        if(data !=null){
            put("data",data);
        }else {
            put("data","数据请求失败");
        }
        return this;
    }

    public ResultObject setMsg(String msg){
        put("msg",msg);
        return this;
    }
    public ResultObject setResultCode(ResultCode resultCode){
        put("ResponseStatus",resultCode.getResultMsg());
        put("ResponseCode",resultCode.getResultCode());
        return this;
    }
}
