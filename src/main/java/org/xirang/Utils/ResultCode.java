package org.xirang.Utils;

public enum ResultCode implements BaseErrorInfoInterface{
    SUCCESS(200, "Success"),
    FAILED(400,"Failed"),
    ERROR(500,"Error"),
    WARNING(403,"Warning"),
    NotFoundException(404,"NotFoundException"),
    NotUserAuth(401,"NotUserAuth"),
    RequestNotSatisfiable(417,"Msg");
    /* 错误码
           */

    private final int resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ResultCode(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
