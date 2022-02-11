package com.zysic.hfct.datalog.aspect;

import com.zysic.hfct.datalog.HisType;
import com.zysic.hfct.datalog.annotation.DataLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataLogAspect {

    @Pointcut("@annotation(com.zysic.hfct.datalog.annotation.DataLog)")
    public void DataLog() {}

    @After("DataLog()")
    public void doAfter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataLog declaredAnnotation = signature.getMethod().getDeclaredAnnotation(DataLog.class);
        HisType value = declaredAnnotation.value();
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof DataLogBiz) {
                    ((DataLogBiz<?, ?>) arg).setType(value.name());
                    ((DataLogBiz<?, ?>) arg).saveHist();
                }
            }
        }
    }
}
