package com.zysic.hfct.optlog.annotation;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.zysic.hfct.core.helper.IPHelper;
import com.zysic.hfct.optlog.entity.OptLogDO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZHANGYONG797
 * @date 2021-5-20
 */

@Slf4j
@Aspect
@Component
@Order(1002)
public class LogAspect {

    @Autowired
    private LogTaskFactory logTaskFactory;

    @Autowired
    private UserApplicationContext userApplicationContext;

    @Pointcut("@annotation(com.zysic.hfct.optlog.annotation.OptLog)")
    public void OptLog() {}

//    @AfterThrowing("BusinessLog()")
//    public Object AfterThrowing(ProceedingJoinPoint point) throws Throwable{
//        return handler(point);
//    }

    @Around("OptLog()")
    public Object OptLog(ProceedingJoinPoint point) throws Throwable{
        return handler(point);
    }


    private Object handler(ProceedingJoinPoint point) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        UserPracParam userPracParam = UserApplicationContext.getUserPracParam();
        String userId = "未登录";
        String userName = "未登录";
        String userType = "未知";
        if (userPracParam !=null){
            userId = userPracParam.getAccountNo();
            userName = userPracParam.getUserName();
            userType = userPracParam.getUserType();
        }

        OptLogDO operationLog = new OptLogDO();


        Date startTime = new Date();   //获取开始时间
        Object proceed = point.proceed();
        Date endTime = new Date(); //获取结束时间

        if (proceed instanceof R){
            operationLog.setResultCode(((R) proceed).getCode()+"");
        }else if ( proceed instanceof Result){
            operationLog.setResultCode(((Result) proceed).getCode());
        } else if (proceed instanceof WrapperResponse){
            operationLog.setResultCode(((WrapperResponse) proceed).getCode()+"");
        }

        operationLog.setOutParam(JSONObject.toJSONString(proceed));

        StringBuffer inParam = new StringBuffer("请求参数：");
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())){
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String,String> stringMap = new HashMap<>();
            parameterMap.forEach((key,value)->stringMap.put(key, Arrays.stream(value).collect(Collectors.joining(","))));
            inParam.append(JSONObject.toJSONString(stringMap));
        }else {
            Object[] objects = point.getArgs();
            Arrays.stream(objects).forEach(object->inParam.append(object.toString()));
        }

        String ip = IPHelper.getClientIpAddress();
        log.info("ip:{}",ip);
        log.info("inParam:{}",inParam);
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取操作名称
        OptLog annotation = currentMethod.getAnnotation(OptLog.class);
        String businessName = annotation.value();
        //如果涉及到修改,比对变化
        operationLog.setOperationLogId(UUID.randomUUID().toString());
        operationLog.setUserId(userId);
        operationLog.setUserType(userType);
        operationLog.setIp(ip);
        operationLog.setRequestUrl(url);
        operationLog.setRequestUrlExplain(businessName);
        operationLog.setStartDate(startTime);
        operationLog.setInParam(inParam.toString());
        operationLog.setEndDate(endTime);
        operationLog.setCreatedDate(new Date());
        operationLog.setCreatedBy(userName);
        operationLog.setUpdatedDate(new Date());
        operationLog.setUpdatedBy(userName);
        LogManager.me().executeLog(logTaskFactory.businessLog(operationLog));
        return proceed;
    }


}
