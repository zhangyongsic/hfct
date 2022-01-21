package com.zysic.hfct.extension.response;

import com.zysic.hfct.core.errcode.BusinessErrorCode;
import com.zysic.hfct.core.errcode.IErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("返回对象")
public class ResponseResult<T> {
    @ApiModelProperty("错误码(0：成功、-1：未知异常)")
    private int errcode;
    @ApiModelProperty("错误信息")
    private String errmsg;
    @ApiModelProperty("数据")
    private T data;

    private ResponseResult(IErrorCode errorCode){
        this.errcode = errorCode.getErrcode();
        this.errmsg = errorCode.getErrmsg();
    }

    private ResponseResult(IErrorCode errorCode, T data){
        this(errorCode);
        this.data = data;
    }

    private ResponseResult(T data){
        this(BusinessErrorCode.SUCCESS,data);
    }

    public static <T> ResponseResult ok(T data){
        return new ResponseResult(data);
    }

    public static <T> ResponseResult error(IErrorCode errorCode){
        return new ResponseResult(errorCode);
    }

    public ResponseResult errmsg(String errmsg){
        this.setErrmsg(errmsg);
        return this;
    }

    public ResponseResult errcode(int errcode){
        this.setErrcode(errcode);
        return this;
    }
}
