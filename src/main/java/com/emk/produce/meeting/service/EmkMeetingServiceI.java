package com.emk.produce.meeting.service;

import com.emk.produce.meeting.entity.EmkMeetingEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMeetingServiceI
        extends CommonService {
    public abstract void delete(EmkMeetingEntity paramEmkMeetingEntity)
            throws Exception;

    public abstract Serializable save(EmkMeetingEntity paramEmkMeetingEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMeetingEntity paramEmkMeetingEntity)
            throws Exception;
}
