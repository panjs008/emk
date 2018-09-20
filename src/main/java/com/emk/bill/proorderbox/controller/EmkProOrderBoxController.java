package com.emk.bill.proorderbox.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.bill.proorderbox.service.EmkProOrderBoxServiceI;
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

@Api(value = "EmkProOrderBox", description = "纸箱尺寸表", tags = {"emkProOrderBoxController"})
@Controller
@RequestMapping({"/emkProOrderBoxController"})
public class EmkProOrderBoxController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderBoxController.class);
    @Autowired
    private EmkProOrderBoxServiceI emkProOrderBoxService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorderbox/emkProOrderBoxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderBoxEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProOrderBox, request.getParameterMap());


        cq.add();
        this.emkProOrderBoxService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrderBox = (EmkProOrderBoxEntity) this.systemService.getEntity(EmkProOrderBoxEntity.class, emkProOrderBox.getId());
        message = "纸箱尺寸表删除成功";
        try {
            this.emkProOrderBoxService.delete(emkProOrderBox);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "纸箱尺寸表删除失败";
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
        message = "纸箱尺寸表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderBoxEntity emkProOrderBox = (EmkProOrderBoxEntity) this.systemService.getEntity(EmkProOrderBoxEntity.class, id);


                this.emkProOrderBoxService.delete(emkProOrderBox);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "纸箱尺寸表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "纸箱尺寸表添加成功";
        try {
            this.emkProOrderBoxService.save(emkProOrderBox);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "纸箱尺寸表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "纸箱尺寸表更新成功";
        EmkProOrderBoxEntity t = (EmkProOrderBoxEntity) this.emkProOrderBoxService.get(EmkProOrderBoxEntity.class, emkProOrderBox.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrderBox, t);
            this.emkProOrderBoxService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "纸箱尺寸表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderBox.getId())) {
            emkProOrderBox = (EmkProOrderBoxEntity) this.emkProOrderBoxService.getEntity(EmkProOrderBoxEntity.class, emkProOrderBox.getId());
            req.setAttribute("emkProOrderBoxPage", emkProOrderBox);
        }
        return new ModelAndView("com/emk/bill/proorderbox/emkProOrderBox-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderBox.getId())) {
            emkProOrderBox = (EmkProOrderBoxEntity) this.emkProOrderBoxService.getEntity(EmkProOrderBoxEntity.class, emkProOrderBox.getId());
            req.setAttribute("emkProOrderBoxPage", emkProOrderBox);
        }
        return new ModelAndView("com/emk/bill/proorderbox/emkProOrderBox-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderBoxController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderBoxEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrderBox, request.getParameterMap());
        List<EmkProOrderBoxEntity> emkProOrderBoxs = this.emkProOrderBoxService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "纸箱尺寸表");
        modelMap.put("entity", EmkProOrderBoxEntity.class);
        modelMap.put("params", new ExportParams("纸箱尺寸表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrderBoxs);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProOrderBoxEntity emkProOrderBox, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "纸箱尺寸表");
        modelMap.put("entity", EmkProOrderBoxEntity.class);
        modelMap.put("params", new ExportParams("纸箱尺寸表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "纸箱尺寸表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProOrderBoxEntity>> list() {
        List<EmkProOrderBoxEntity> listEmkProOrderBoxs = this.emkProOrderBoxService.getList(EmkProOrderBoxEntity.class);
        return Result.success(listEmkProOrderBoxs);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取纸箱尺寸表信息", notes = "根据ID获取纸箱尺寸表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderBoxEntity task = (EmkProOrderBoxEntity) this.emkProOrderBoxService.get(EmkProOrderBoxEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取纸箱尺寸表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建纸箱尺寸表")
    public ResponseMessage<?> create(@ApiParam(name = "纸箱尺寸表对象") @RequestBody EmkProOrderBoxEntity emkProOrderBox, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderBoxEntity>> failures = this.validator.validate(emkProOrderBox, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderBoxService.save(emkProOrderBox);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("纸箱尺寸表信息保存失败");
        }
        return Result.success(emkProOrderBox);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新纸箱尺寸表", notes = "更新纸箱尺寸表")
    public ResponseMessage<?> update(@ApiParam(name = "纸箱尺寸表对象") @RequestBody EmkProOrderBoxEntity emkProOrderBox) {
        Set<ConstraintViolation<EmkProOrderBoxEntity>> failures = this.validator.validate(emkProOrderBox, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderBoxService.saveOrUpdate(emkProOrderBox);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新纸箱尺寸表信息失败");
        }
        return Result.success("更新纸箱尺寸表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除纸箱尺寸表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProOrderBoxService.deleteEntityById(EmkProOrderBoxEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("纸箱尺寸表删除失败");
        }
        return Result.success();
    }
}
