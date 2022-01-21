package com.zysic.hfct.extension.response;

import com.zysic.hfct.core.errcode.BusinessErrorCode;
import com.zysic.hfct.core.errcode.IErrorCode;
import com.zysic.hfct.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: zhang yong
 */

@Slf4j
@ResponseBody
@ControllerAdvice
public class ResponseExceptionHandler {

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException ex) {
        IErrorCode errorCode = ex.getErrorCode();
        log.error("错误信息 BusinessException errcode：{}，errmsg：{}",errorCode.getErrcode(),errorCode.getErrmsg());
        return ResponseResult.error(ex.getErrorCode());
    }

    /**
     * 未知异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleBusinessException(Exception ex) {
        ex.printStackTrace();
        log.error("错误信息 Exception errmsg：{}",ex.getMessage());
        return ResponseResult.error(BusinessErrorCode.UNKNOWN).errmsg(ex.getMessage());
    }
}
