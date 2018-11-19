package com.emk.storage.sampleran.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.sampleran.service.EmkSampleRanServiceI;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

@Api(value = "EmkSampleRan", description = "样品染色表", tags = {"emkSampleRanController"})
@Controller
@RequestMapping({"/emkSampleRanController"})
public class EmkSampleRanController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleRanController.class);
    @Autowired
    private EmkSampleRanServiceI emkSampleRanService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sampleran/emkSampleRanList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkSampleRanEntity emkSampleRan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRanEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkSampleRan, request.getParameterMap());


        cq.add();
        this.emkSampleRanService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkSampleRanEntity emkSampleRan, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleRan = (EmkSampleRanEntity) this.systemService.getEntity(EmkSampleRanEntity.class, emkSampleRan.getId());
        message = "样品染色表删除成功";
        try {
            this.emkSampleRanService.delete(emkSampleRan);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品染色表删除失败";
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
        message = "样品染色表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleRanEntity emkSampleRan = (EmkSampleRanEntity) this.systemService.getEntity(EmkSampleRanEntity.class, id);


                this.emkSampleRanService.delete(emkSampleRan);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品染色表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkSampleRanEntity emkSampleRan, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品染色表添加成功";
        try {
            this.emkSampleRanService.save(emkSampleRan);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品染色表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleRanEntity emkSampleRan, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品染色表更新成功";
        EmkSampleRanEntity t = (EmkSampleRanEntity) this.emkSampleRanService.get(EmkSampleRanEntity.class, emkSampleRan.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleRan, t);
            this.emkSampleRanService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品染色表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkSampleRanEntity emkSampleRan, HttpServletRequest req, String type) {
        if (type.equals("sample")) {
            EmkSampleEntity emkSampleEntity = (EmkSampleEntity) this.systemService.getEntity(EmkSampleEntity.class, emkSampleRan.getSampleId());
            req.setAttribute("emkSampleEntity", emkSampleEntity);
        } else if (type.equals("samplerequired")) {
            EmkSampleRequiredEntity emkSampleEntity = (EmkSampleRequiredEntity) this.systemService.getEntity(EmkSampleRequiredEntity.class, emkSampleRan.getSampleId());
            req.setAttribute("emkSampleEntity", emkSampleEntity);
        } else if (type.equals("price")) {
            EmkPriceEntity emkSampleEntity = (EmkPriceEntity) this.systemService.getEntity(EmkPriceEntity.class, emkSampleRan.getSampleId());
            req.setAttribute("emkSampleEntity", emkSampleEntity);
        }
        if (StringUtil.isNotEmpty(emkSampleRan.getId())) {
            emkSampleRan = (EmkSampleRanEntity) this.emkSampleRanService.getEntity(EmkSampleRanEntity.class, emkSampleRan.getId());
            req.setAttribute("emkSampleRanPage", emkSampleRan);
        }
        return new ModelAndView("com/emk/storage/sampleran/emkSampleRan-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkSampleRanEntity emkSampleRan, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRan.getId())) {
            emkSampleRan = (EmkSampleRanEntity) this.emkSampleRanService.getEntity(EmkSampleRanEntity.class, emkSampleRan.getId());
            req.setAttribute("emkSampleRanPage", emkSampleRan);
        }
        return new ModelAndView("com/emk/storage/sampleran/emkSampleRan-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleRanController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkSampleRanEntity emkSampleRan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRanEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleRan, request.getParameterMap());
        List<EmkSampleRanEntity> emkSampleRans = this.emkSampleRanService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品染色表");
        modelMap.put("entity", EmkSampleRanEntity.class);
        modelMap.put("params", new ExportParams("样品染色表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleRans);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkSampleRanEntity emkSampleRan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品染色表");
        modelMap.put("entity", EmkSampleRanEntity.class);
        modelMap.put("params", new ExportParams("样品染色表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品染色表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleRanEntity>> list() {
        List<EmkSampleRanEntity> listEmkSampleRans = this.emkSampleRanService.getList(EmkSampleRanEntity.class);
        return Result.success(listEmkSampleRans);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品染色表信息", notes = "根据ID获取样品染色表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleRanEntity task = (EmkSampleRanEntity) this.emkSampleRanService.get(EmkSampleRanEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品染色表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建样品染色表")
    public ResponseMessage<?> create(@ApiParam(name = "样品染色表对象") @RequestBody EmkSampleRanEntity emkSampleRan, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleRanEntity>> failures = this.validator.validate(emkSampleRan, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleRanService.save(emkSampleRan);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品染色表信息保存失败");
        }
        return Result.success(emkSampleRan);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新样品染色表", notes = "更新样品染色表")
    public ResponseMessage<?> update(@ApiParam(name = "样品染色表对象") @RequestBody EmkSampleRanEntity emkSampleRan) {
        Set<ConstraintViolation<EmkSampleRanEntity>> failures = this.validator.validate(emkSampleRan, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleRanService.saveOrUpdate(emkSampleRan);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品染色表信息失败");
        }
        return Result.success("更新样品染色表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品染色表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkSampleRanService.deleteEntityById(EmkSampleRanEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品染色表删除失败");
        }
        return Result.success();
    }
}
