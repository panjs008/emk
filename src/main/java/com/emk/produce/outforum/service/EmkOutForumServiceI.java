package com.emk.produce.outforum.service;

import com.emk.produce.outforum.entity.EmkOutForumEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkOutForumServiceI
        extends CommonService {
    public abstract void delete(EmkOutForumEntity paramEmkOutForumEntity)
            throws Exception;

    public abstract Serializable save(EmkOutForumEntity paramEmkOutForumEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkOutForumEntity paramEmkOutForumEntity)
            throws Exception;
}
