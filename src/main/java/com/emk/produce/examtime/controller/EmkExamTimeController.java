package com.emk.produce.examtime.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.examtime.entity.EmkExamTimeEntity;
import com.emk.produce.examtime.service.EmkExamTimeServiceI;
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

@Api(value = "EmkExamTime", description = "预计验货时间表", tags = {"emkExamTimeController"})
@Controller
@RequestMapping({"/emkExamTimeController"})
public class EmkExamTimeController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkExamTimeController.class);
    @Autowired
    private EmkExamTimeServiceI emkExamTimeService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/examtime/emkExamTimeList");
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
        return new ModelAndView("com/emk/produce/examtime/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkExamTimeEntity emkExamTime, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkExamTimeEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkExamTime, request.getParameterMap());


        cq.add();
        this.emkExamTimeService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkExamTimeEntity emkExamTime, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkExamTime = (EmkExamTimeEntity) this.systemService.getEntity(EmkExamTimeEntity.class, emkExamTime.getId());
        message = "预计验货时间表删除成功";
        try {
            this.emkExamTimeService.delete(emkExamTime);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "预计验货时间表删除失败";
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
        message = "预计验货时间表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkExamTimeEntity emkExamTime = (EmkExamTimeEntity) this.systemService.getEntity(EmkExamTimeEntity.class, id);


                this.emkExamTimeService.delete(emkExamTime);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "预计验货时间表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkExamTimeEntity emkExamTime, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "预计验货时间表添加成功";
        try {
            this.emkExamTimeService.save(emkExamTime);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkExamTime.getId());
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
            message = "预计验货时间表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkExamTimeEntity emkExamTime, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "预计验货时间表更新成功";
        EmkExamTimeEntity t = (EmkExamTimeEntity) this.emkExamTimeService.get(EmkExamTimeEntity.class, emkExamTime.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkExamTime, t);
            this.emkExamTimeService.saveOrUpdate(t);

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
            message = "预计验货时间表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkExamTimeEntity emkExamTime, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkExamTime.getId())) {
            emkExamTime = (EmkExamTimeEntity) this.emkExamTimeService.getEntity(EmkExamTimeEntity.class, emkExamTime.getId());
            req.setAttribute("emkExamTimePage", emkExamTime);
        }
        return new ModelAndView("com/emk/produce/examtime/emkExamTime-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkExamTimeEntity emkExamTime, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkExamTime.getId())) {
            emkExamTime = (EmkExamTimeEntity) this.emkExamTimeService.getEntity(EmkExamTimeEntity.class, emkExamTime.getId());
            req.setAttribute("emkExamTimePage", emkExamTime);
        }
        return new ModelAndView("com/emk/produce/examtime/emkExamTime-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkExamTimeController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkExamTimeEntity emkExamTime, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkExamTimeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkExamTime, request.getParameterMap());
        List<EmkExamTimeEntity> emkExamTimes = this.emkExamTimeService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "预计验货时间表");
        modelMap.put("entity", EmkExamTimeEntity.class);
        modelMap.put("params", new ExportParams("预计验货时间表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkExamTimes);
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "预计验货时间表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkExamTimeEntity>> list() {
        List<EmkExamTimeEntity> listEmkExamTimes = this.emkExamTimeService.getList(EmkExamTimeEntity.class);
        return Result.success(listEmkExamTimes);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取预计验货时间表信息", notes = "根据ID获取预计验货时间表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkExamTimeEntity task = (EmkExamTimeEntity) this.emkExamTimeService.get(EmkExamTimeEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取预计验货时间表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建预计验货时间表")
    public ResponseMessage<?> create(@ApiParam(name = "预计验货时间表对象") @RequestBody EmkExamTimeEntity emkExamTime, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkExamTimeEntity>> failures = this.validator.validate(emkExamTime, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkExamTimeService.save(emkExamTime);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("预计验货时间表信息保存失败");
        }
        return Result.success(emkExamTime);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新预计验货时间表", notes = "更新预计验货时间表")
    public ResponseMessage<?> update(@ApiParam(name = "预计验货时间表对象") @RequestBody EmkExamTimeEntity emkExamTime) {
        Set<ConstraintViolation<EmkExamTimeEntity>> failures = this.validator.validate(emkExamTime, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkExamTimeService.saveOrUpdate(emkExamTime);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新预计验货时间表信息失败");
        }
        return Result.success("更新预计验货时间表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除预计验货时间表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkExamTimeService.deleteEntityById(EmkExamTimeEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("预计验货时间表删除失败");
        }
        return Result.success();
    }
}
