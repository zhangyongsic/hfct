package com.zysic.hfct.datalog.annotation;

import com.zysic.hfct.datalog.HisType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 数据日志
 * @author ZHANGYONG
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataLog {
    HisType value() default HisType.CREATE;
}
