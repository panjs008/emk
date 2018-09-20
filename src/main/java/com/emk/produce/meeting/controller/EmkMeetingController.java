package com.emk.produce.meeting.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.meeting.entity.EmkMeetingEntity;
import com.emk.produce.meeting.service.EmkMeetingServiceI;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkMeeting", description = "产前会议通知表", tags = {"emkMeetingController"})
@Controller
@RequestMapping({"/emkMeetingController"})
public class EmkMeetingController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMeetingController.class);
    @Autowired
    private EmkMeetingServiceI emkMeetingService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/meeting/emkMeetingList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkMeetingEntity emkMeeting, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMeetingEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkMeeting, request.getParameterMap());


        cq.add();
        this.emkMeetingService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkMeetingEntity emkMeeting, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMeeting = (EmkMeetingEntity) this.systemService.getEntity(EmkMeetingEntity.class, emkMeeting.getId());
        message = "产前会议通知表删除成功";
        try {
            this.emkMeetingService.delete(emkMeeting);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产前会议通知表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doBatchDel"})
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产前会议通知表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMeetingEntity emkMeeting = (EmkMeetingEntity) this.systemService.getEntity(EmkMeetingEntity.class, id);


                this.emkMeetingService.delete(emkMeeting);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "产前会议通知表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkMeetingEntity emkMeeting, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产前会议通知表添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkMeeting.setPartUserIds(map.get("userName").toString());
            emkMeeting.setPartUsers(map.get("realName").toString());
            this.emkMeetingService.save(emkMeeting);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产前会议通知表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkMeetingEntity emkMeeting, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产前会议通知表更新成功";
        EmkMeetingEntity t = (EmkMeetingEntity) this.emkMeetingService.get(EmkMeetingEntity.class, emkMeeting.getId());
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkMeeting.setPartUserIds(map.get("userName").toString());
            emkMeeting.setPartUsers(map.get("realName").toString());
            MyBeanUtils.copyBeanNotNull2Bean(emkMeeting, t);
            this.emkMeetingService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产前会议通知表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkMeetingEntity emkMeeting, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());
        Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_test where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
        req.setAttribute("meetingNo", "HY" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
        if (StringUtil.isNotEmpty(emkMeeting.getId())) {
            emkMeeting = (EmkMeetingEntity) this.emkMeetingService.getEntity(EmkMeetingEntity.class, emkMeeting.getId());
            req.setAttribute("emkMeetingPage", emkMeeting);
        }
        return new ModelAndView("com/emk/produce/meeting/emkMeeting-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkMeetingEntity emkMeeting, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMeeting.getId())) {
            emkMeeting = (EmkMeetingEntity) this.emkMeetingService.getEntity(EmkMeetingEntity.class, emkMeeting.getId());
            req.setAttribute("emkMeetingPage", emkMeeting);
        }
        return new ModelAndView("com/emk/produce/meeting/emkMeeting-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMeetingController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkMeetingEntity emkMeeting, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMeetingEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMeeting, request.getParameterMap());
        List<EmkMeetingEntity> emkMeetings = this.emkMeetingService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "产前会议通知表");
        modelMap.put("entity", EmkMeetingEntity.class);
        modelMap.put("params", new ExportParams("产前会议通知表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMeetings);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkMeetingEntity emkMeeting, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "产前会议通知表");
        modelMap.put("entity", EmkMeetingEntity.class);
        modelMap.put("params", new ExportParams("产前会议通知表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "产前会议通知表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMeetingEntity>> list() {
        List<EmkMeetingEntity> listEmkMeetings = this.emkMeetingService.getList(EmkMeetingEntity.class);
        return Result.success(listEmkMeetings);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取产前会议通知表信息", notes = "根据ID获取产前会议通知表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMeetingEntity task = (EmkMeetingEntity) this.emkMeetingService.get(EmkMeetingEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取产前会议通知表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建产前会议通知表")
    public ResponseMessage<?> create(@ApiParam(name = "产前会议通知表对象") @RequestBody EmkMeetingEntity emkMeeting, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMeetingEntity>> failures = this.validator.validate(emkMeeting, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMeetingService.save(emkMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("产前会议通知表信息保存失败");
        }
        return Result.success(emkMeeting);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新产前会议通知表", notes = "更新产前会议通知表")
    public ResponseMessage<?> update(@ApiParam(name = "产前会议通知表对象") @RequestBody EmkMeetingEntity emkMeeting) {
        Set<ConstraintViolation<EmkMeetingEntity>> failures = this.validator.validate(emkMeeting, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMeetingService.saveOrUpdate(emkMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新产前会议通知表信息失败");
        }
        return Result.success("更新产前会议通知表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除产前会议通知表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMeetingService.deleteEntityById(EmkMeetingEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("产前会议通知表删除失败");
        }
        return Result.success();
    }
}
