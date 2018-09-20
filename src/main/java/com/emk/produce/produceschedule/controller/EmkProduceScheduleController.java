package com.emk.produce.produceschedule.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.produceschedule.entity.EmkProduceScheduleEntity;
import com.emk.produce.produceschedule.service.EmkProduceScheduleServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
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

@Api(value = "EmkProduceSchedule", description = "采购生产表", tags = {"emkProduceScheduleController"})
@Controller
@RequestMapping({"/emkProduceScheduleController"})
public class EmkProduceScheduleController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProduceScheduleController.class);
    @Autowired
    private EmkProduceScheduleServiceI emkProduceScheduleService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceScheduleList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/produceschedule/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProduceScheduleEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProduceSchedule, request.getParameterMap());


        cq.add();
        this.emkProduceScheduleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProduceSchedule = (EmkProduceScheduleEntity) this.systemService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
        message = "采购生产表删除成功";
        try {
            this.emkProduceScheduleService.delete(emkProduceSchedule);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表删除失败";
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
        message = "采购生产表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProduceScheduleEntity emkProduceSchedule = (EmkProduceScheduleEntity) this.systemService.getEntity(EmkProduceScheduleEntity.class, id);


                this.emkProduceScheduleService.delete(emkProduceSchedule);
                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkProduceSchedule.getId()});
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购生产表添加成功";
        try {
            this.emkProduceScheduleService.save(emkProduceSchedule);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkProduceSchedule.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购生产表更新成功";
        EmkProduceScheduleEntity t = (EmkProduceScheduleEntity) this.emkProduceScheduleService.get(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProduceSchedule, t);
            this.emkProduceScheduleService.saveOrUpdate(t);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{t.getId()});
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkProduceSchedule.getId())) {
            emkProduceSchedule = (EmkProduceScheduleEntity) this.emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
            req.setAttribute("emkProduceSchedulePage", emkProduceSchedule);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkProduceSchedule.getId())) {
            emkProduceSchedule = (EmkProduceScheduleEntity) this.emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
            req.setAttribute("emkProduceSchedulePage", emkProduceSchedule);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProduceScheduleController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProduceScheduleEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProduceSchedule, request.getParameterMap());
        List<EmkProduceScheduleEntity> emkProduceSchedules = this.emkProduceScheduleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "采购生产表");
        modelMap.put("entity", EmkProduceScheduleEntity.class);
        modelMap.put("params", new ExportParams("采购生产表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProduceSchedules);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "采购生产表");
        modelMap.put("entity", EmkProduceScheduleEntity.class);
        modelMap.put("params", new ExportParams("采购生产表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "采购生产表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProduceScheduleEntity>> list() {
        List<EmkProduceScheduleEntity> listEmkProduceSchedules = this.emkProduceScheduleService.getList(EmkProduceScheduleEntity.class);
        return Result.success(listEmkProduceSchedules);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取采购生产表信息", notes = "根据ID获取采购生产表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProduceScheduleEntity task = (EmkProduceScheduleEntity) this.emkProduceScheduleService.get(EmkProduceScheduleEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取采购生产表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建采购生产表")
    public ResponseMessage<?> create(@ApiParam(name = "采购生产表对象") @RequestBody EmkProduceScheduleEntity emkProduceSchedule, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProduceScheduleEntity>> failures = this.validator.validate(emkProduceSchedule, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProduceScheduleService.save(emkProduceSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购生产表信息保存失败");
        }
        return Result.success(emkProduceSchedule);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新采购生产表", notes = "更新采购生产表")
    public ResponseMessage<?> update(@ApiParam(name = "采购生产表对象") @RequestBody EmkProduceScheduleEntity emkProduceSchedule) {
        Set<ConstraintViolation<EmkProduceScheduleEntity>> failures = this.validator.validate(emkProduceSchedule, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProduceScheduleService.saveOrUpdate(emkProduceSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新采购生产表信息失败");
        }
        return Result.success("更新采购生产表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除采购生产表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProduceScheduleService.deleteEntityById(EmkProduceScheduleEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购生产表删除失败");
        }
        return Result.success();
    }
}
