package com.zysic.hfct.core.errcode;

public enum BusinessErrorCode implements IErrorCode{
    UNKNOWN(-1,"未知异常"),
    SUCCESS(0,"成功"),
    CUSTOMIZE(1,"自定义异常")
    ;
    private int errcode;
    private String errmsg;

    BusinessErrorCode(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }


    @Override
    public int getErrcode() {
        return this.errcode;
    }

    @Override
    public String getErrmsg() {
        return this.errmsg;
    }
}
