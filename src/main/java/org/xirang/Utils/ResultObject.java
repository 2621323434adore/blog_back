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

    public ResultObject setData(Object data) {
        put("data",data);
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
