package com.zysic.hfct.core.errcode;

import lombok.Data;

/**
 * 自定义错误
 */
@Data
public class CustomizeErrorCode implements IErrorCode{

    private int errcode;
    private String errmsg;

    public CustomizeErrorCode(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

}
