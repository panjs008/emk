package com.emk.storage.enquirydetail.service;

import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkEnquiryDetailServiceI
        extends CommonService {
    public abstract void delete(EmkEnquiryDetailEntity paramEmkEnquiryDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkEnquiryDetailEntity paramEmkEnquiryDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkEnquiryDetailEntity paramEmkEnquiryDetailEntity)
            throws Exception;
}
