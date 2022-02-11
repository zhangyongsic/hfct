package com.zysic.hfct.service;

import com.zysic.hfct.datalog.HisType;
import com.zysic.hfct.datalog.annotation.DataLog;
import com.zysic.hfct.entity.PsnBiz;
import org.springframework.stereotype.Service;

@Service
public class PsnService {

    @DataLog(value = HisType.UPDATE)
    public void update(PsnBiz psnBiz){
        psnBiz.getRoot().updateById();
    }
}
