package com.emk.storage.sampletotal.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.sampletotal.entity.EmkSampleTotalEntity;
import com.emk.storage.sampletotal.service.EmkSampleTotalServiceI;
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

@Api(value = "EmkSampleTotal", description = "样品数量", tags = {"emkSampleTotalController"})
@Controller
@RequestMapping({"/emkSampleTotalController"})
public class EmkSampleTotalController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleTotalController.class);
    @Autowired
    private EmkSampleTotalServiceI emkSampleTotalService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sampletotal/emkSampleTotalList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleTotalEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkSampleTotal, request.getParameterMap());


        cq.add();
        this.emkSampleTotalService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleTotal = (EmkSampleTotalEntity) this.systemService.getEntity(EmkSampleTotalEntity.class, emkSampleTotal.getId());
        message = "样品数量删除成功";
        try {
            this.emkSampleTotalService.delete(emkSampleTotal);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品数量删除失败";
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
        message = "样品数量删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleTotalEntity emkSampleTotal = (EmkSampleTotalEntity) this.systemService.getEntity(EmkSampleTotalEntity.class, id);


                this.emkSampleTotalService.delete(emkSampleTotal);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品数量删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品数量添加成功";
        try {
            this.emkSampleTotalService.save(emkSampleTotal);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品数量添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品数量更新成功";
        EmkSampleTotalEntity t = (EmkSampleTotalEntity) this.emkSampleTotalService.get(EmkSampleTotalEntity.class, emkSampleTotal.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleTotal, t);
            this.emkSampleTotalService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品数量更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleTotal.getId())) {
            emkSampleTotal = (EmkSampleTotalEntity) this.emkSampleTotalService.getEntity(EmkSampleTotalEntity.class, emkSampleTotal.getId());
            req.setAttribute("emkSampleTotalPage", emkSampleTotal);
        }
        return new ModelAndView("com/emk/storage/sampletotal/emkSampleTotal-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleTotal.getId())) {
            emkSampleTotal = (EmkSampleTotalEntity) this.emkSampleTotalService.getEntity(EmkSampleTotalEntity.class, emkSampleTotal.getId());
            req.setAttribute("emkSampleTotalPage", emkSampleTotal);
        }
        return new ModelAndView("com/emk/storage/sampletotal/emkSampleTotal-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleTotalController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleTotalEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleTotal, request.getParameterMap());
        List<EmkSampleTotalEntity> emkSampleTotals = this.emkSampleTotalService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品数量");
        modelMap.put("entity", EmkSampleTotalEntity.class);
        modelMap.put("params", new ExportParams("样品数量列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleTotals);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkSampleTotalEntity emkSampleTotal, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品数量");
        modelMap.put("entity", EmkSampleTotalEntity.class);
        modelMap.put("params", new ExportParams("样品数量列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品数量列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleTotalEntity>> list() {
        List<EmkSampleTotalEntity> listEmkSampleTotals = this.emkSampleTotalService.getList(EmkSampleTotalEntity.class);
        return Result.success(listEmkSampleTotals);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品数量信息", notes = "根据ID获取样品数量信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleTotalEntity task = (EmkSampleTotalEntity) this.emkSampleTotalService.get(EmkSampleTotalEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品数量信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建样品数量")
    public ResponseMessage<?> create(@ApiParam(name = "样品数量对象") @RequestBody EmkSampleTotalEntity emkSampleTotal, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleTotalEntity>> failures = this.validator.validate(emkSampleTotal, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleTotalService.save(emkSampleTotal);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品数量信息保存失败");
        }
        return Result.success(emkSampleTotal);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新样品数量", notes = "更新样品数量")
    public ResponseMessage<?> update(@ApiParam(name = "样品数量对象") @RequestBody EmkSampleTotalEntity emkSampleTotal) {
        Set<ConstraintViolation<EmkSampleTotalEntity>> failures = this.validator.validate(emkSampleTotal, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleTotalService.saveOrUpdate(emkSampleTotal);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品数量信息失败");
        }
        return Result.success("更新样品数量信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品数量")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkSampleTotalService.deleteEntityById(EmkSampleTotalEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品数量删除失败");
        }
        return Result.success();
    }
}
