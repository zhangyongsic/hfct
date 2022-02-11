package com.zysic.hfct.optlog.annotation;


import com.zysic.hfct.optlog.entity.OptLogDO;
import com.zysic.hfct.optlog.service.OptLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Slf4j
@Component
public class LogTaskFactory {

    @Autowired
    OptLogService businessLogService;

    public TimerTask businessLog(final OptLogDO businessLogDO) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    businessLogService.save(businessLogDO);
                } catch (Exception e) {
                    log.error("创建业务日志异常!", e);
                }
            }
        };
    }

}
