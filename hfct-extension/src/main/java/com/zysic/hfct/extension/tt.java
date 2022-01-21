package com.zysic.hfct.extension;

import com.zysic.hfct.core.errcode.BusinessErrorCode;
import com.zysic.hfct.extension.response.ResponseResult;

public class tt {

    public static void main(String[] args) {
        System.out.println(ResponseResult.error(BusinessErrorCode.UNKNOWN).errmsg("22222"));
    }
}
