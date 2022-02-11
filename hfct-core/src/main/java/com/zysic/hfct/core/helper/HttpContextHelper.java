package com.zysic.hfct.core.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpContextHelper {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    public static Cookie[] getCookies(){
        return getRequest().getCookies();
    }

    public static String getParameter(String key){
        return  getRequest().getParameter(key);
    }

    public static String getHeader(String key){
        return  getRequest().getHeader(key);
    }

    public static String getCookie(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }
        Cookie[] cookies = getCookies();
        if (cookies!=null && cookies.length>0){
            for (Cookie cookie:cookies){
                if (key.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }




}
