package com.emk.storage.samplerequired.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.samplerequired.service.EmkSampleRequiredServiceI;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
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

@Api(value = "EmkSampleRequired", description = "样品需求单", tags = {"emkSampleRequiredController"})
@Controller
@RequestMapping({"/emkSampleRequiredController"})
public class EmkSampleRequiredController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleRequiredController.class);
    @Autowired
    private EmkSampleRequiredServiceI emkSampleRequiredService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequiredList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRequiredEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkSampleRequired, request.getParameterMap());


        cq.add();
        this.emkSampleRequiredService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleRequired = (EmkSampleRequiredEntity) this.systemService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        message = "样品需求单删除成功";
        try {
            this.emkSampleRequiredService.delete(emkSampleRequired);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单删除失败";
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
        message = "样品需求单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleRequiredEntity emkSampleRequired = (EmkSampleRequiredEntity) this.systemService.getEntity(EmkSampleRequiredEntity.class, id);


                this.emkSampleRequiredService.delete(emkSampleRequired);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(REQUIRED_NO, 3)),0)+1 AS signed) orderNum from emk_sample_required");

            emkSampleRequired.setRequiredNo("YPXP" + emkSampleRequired.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            this.emkSampleRequiredService.save(emkSampleRequired);

            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
            }
            samplePriceEntity.setBz(map.get("pbz").toString());
            samplePriceEntity.setEnquiryId(emkSampleRequired.getId());
            samplePriceEntity.setState(map.get("pstate").toString());
            this.systemService.save(samplePriceEntity);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单更新成功";
        EmkSampleRequiredEntity t = (EmkSampleRequiredEntity) this.emkSampleRequiredService.get(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());

            MyBeanUtils.copyBeanNotNull2Bean(emkSampleRequired, t);
            this.emkSampleRequiredService.saveOrUpdate(t);

            this.systemService.executeSql("delete from emk_sample_price where ENQUIRY_ID=?", new Object[]{emkSampleRequired.getId()});
            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
            }
            samplePriceEntity.setBz(map.get("pbz").toString());
            samplePriceEntity.setEnquiryId(emkSampleRequired.getId());
            samplePriceEntity.setState(map.get("pstate").toString());
            this.systemService.save(samplePriceEntity);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = (EmkSampleRequiredEntity) this.emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = (EmkSampleRequiredEntity) this.emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkSampleRequired.getYsDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            req.setAttribute("levelDays",day);
            EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity) this.systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkSampleRequired.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleRequiredController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRequiredEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleRequired, request.getParameterMap());
        List<EmkSampleRequiredEntity> emkSampleRequireds = this.emkSampleRequiredService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品需求单");
        modelMap.put("entity", EmkSampleRequiredEntity.class);
        modelMap.put("params", new ExportParams("样品需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleRequireds);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品需求单");
        modelMap.put("entity", EmkSampleRequiredEntity.class);
        modelMap.put("params", new ExportParams("样品需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品需求单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleRequiredEntity>> list() {
        List<EmkSampleRequiredEntity> listEmkSampleRequireds = this.emkSampleRequiredService.getList(EmkSampleRequiredEntity.class);
        return Result.success(listEmkSampleRequireds);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品需求单信息", notes = "根据ID获取样品需求单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleRequiredEntity task = (EmkSampleRequiredEntity) this.emkSampleRequiredService.get(EmkSampleRequiredEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品需求单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建样品需求单")
    public ResponseMessage<?> create(@ApiParam(name = "样品需求单对象") @RequestBody EmkSampleRequiredEntity emkSampleRequired, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleRequiredEntity>> failures = this.validator.validate(emkSampleRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleRequiredService.save(emkSampleRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品需求单信息保存失败");
        }
        return Result.success(emkSampleRequired);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新样品需求单", notes = "更新样品需求单")
    public ResponseMessage<?> update(@ApiParam(name = "样品需求单对象") @RequestBody EmkSampleRequiredEntity emkSampleRequired) {
        Set<ConstraintViolation<EmkSampleRequiredEntity>> failures = this.validator.validate(emkSampleRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleRequiredService.saveOrUpdate(emkSampleRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品需求单信息失败");
        }
        return Result.success("更新样品需求单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品需求单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkSampleRequiredService.deleteEntityById(EmkSampleRequiredEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品需求单删除失败");
        }
        return Result.success();
    }
}
