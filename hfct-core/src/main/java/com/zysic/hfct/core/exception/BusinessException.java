package com.zysic.hfct.core.exception;

import com.zysic.hfct.core.errcode.BusinessErrorCode;
import com.zysic.hfct.core.errcode.CustomizeErrorCode;
import com.zysic.hfct.core.errcode.IErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang yong
 *
 *
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException  {
    private static final long serialVersionUID = 3583566093089790852L;
    private IErrorCode errorCode;

    public static final BusinessException SUCCESS = new BusinessException(BusinessErrorCode.SUCCESS);
    public static final BusinessException UNKNOWN = new BusinessException(BusinessErrorCode.UNKNOWN);


    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getErrmsg());
        this.errorCode = errorCode;
    }

    public BusinessException(int errcode, String errmsg) {
        super(errmsg);
        this.errorCode = new CustomizeErrorCode(errcode, errmsg);
    }


}
