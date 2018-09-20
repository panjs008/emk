package com.emk.storage.enquiry.service;

import com.emk.storage.enquiry.entity.EmkEnquiryEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkEnquiryServiceI
        extends CommonService {
    public abstract void delete(EmkEnquiryEntity paramEmkEnquiryEntity)
            throws Exception;

    public abstract Serializable save(EmkEnquiryEntity paramEmkEnquiryEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkEnquiryEntity paramEmkEnquiryEntity)
            throws Exception;
}
