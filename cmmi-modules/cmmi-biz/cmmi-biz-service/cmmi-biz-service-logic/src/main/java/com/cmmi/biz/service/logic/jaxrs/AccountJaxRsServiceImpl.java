/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.cmmi.biz.service.logic.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cmmi.common.service.facade.jaxrs.AccountJaxRsService;
import com.cmmi.common.service.response.jaxrs.dto.UserDTO;
import com.cmmi.core.domain.account.AccountDomain;
import com.cmmi.core.persistant.po.account.UserPO;

@Path("/user")
public class AccountJaxRsServiceImpl  implements AccountJaxRsService{

    private static Logger logger = LoggerFactory.getLogger(AccountJaxRsServiceImpl.class);

    @Autowired
    @Qualifier("accountDomain")
    private AccountDomain accountDomain;

    @GET
    @Path("/{id}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public UserDTO getAsXml(@PathParam("id") Integer id) {
        UserPO userPo = accountDomain.getUser(id);
        if (userPo == null) {
            String message = "用户不存在(id:" + id + ")";
            throw buildException(Status.NOT_FOUND, message);
        }
        return bindDTO(userPo);
    }

    @GET
    @Path("/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getAsJson(@PathParam("id") Integer id) {
        UserPO userPo = accountDomain.getUser(id);
        if (userPo == null) {
            String message = "用户不存在(id:" + id + ")";
            throw buildException(Status.NOT_FOUND, message);
        }
        return bindDTO(userPo);
    }

    private UserDTO bindDTO(UserPO user) {
        UserDTO userDto = new UserDTO();

        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private WebApplicationException buildException(Status status, String message) {
        return new WebApplicationException(Response.status(status).entity(message)
            .type(MediaType.TEXT_PLAIN).build());
    }

    
}