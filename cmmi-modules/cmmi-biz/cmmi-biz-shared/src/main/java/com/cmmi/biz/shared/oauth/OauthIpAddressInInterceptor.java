/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.cmmi.biz.shared.oauth;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

/**
 * Reason:	 soap IP地址验证. 
 * 
 * @author chenlg
 * @version $Id: OauthIpAddressInInterceptor.java, v 0.1 2014年2月27日 上午11:17:42 chenlg Exp $
 * @since    JDK 1.7
 * @see
 */
public class OauthIpAddressInInterceptor extends AbstractPhaseInterceptor<Message> {

    public OauthIpAddressInInterceptor() {
        super(Phase.RECEIVE);
    }

    public void handleMessage(Message message) throws Fault {
        /*
         * 获取request 对象
         */
        HttpServletRequest request = (HttpServletRequest) message
            .get(AbstractHTTPDestination.HTTP_REQUEST);
        /*
         *通过一个IpAddressConfig对象，从XML文件中读取预先设置的允许和拒绝的IP地址，这些值也可以来自数据库   
         */
        
        /*
         *  取客户端IP地址  
         */
        String ipAddress = request.getRemoteAddr();  
        /*
         * 先处理拒绝访问的地址   
         */
        System.out.println("客户端调用的地址:" + ipAddress);
        // throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is denied"));  
        /*
         * 如果允许访问的集合非空，继续处理，否则认为全部IP地址均合法  
         */ 
        //throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is not allowed"));
    }

}