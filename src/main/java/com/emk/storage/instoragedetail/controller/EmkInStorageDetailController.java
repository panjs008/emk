package com.emk.storage.instoragedetail.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.instoragedetail.entity.EmkInStorageDetailEntity;
import com.emk.storage.instoragedetail.service.EmkInStorageDetailServiceI;
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

@Api(value = "EmkInStorageDetail", description = "入库表明细表", tags = {"emkInStorageDetailController"})
@Controller
@RequestMapping({"/emkInStorageDetailController"})
public class EmkInStorageDetailController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkInStorageDetailController.class);
    @Autowired
    private EmkInStorageDetailServiceI emkInStorageDetailService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/instoragedetail/emkInStorageDetailList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkInStorageDetailEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkInStorageDetail, request.getParameterMap());


        cq.add();
        this.emkInStorageDetailService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkInStorageDetail = (EmkInStorageDetailEntity) this.systemService.getEntity(EmkInStorageDetailEntity.class, emkInStorageDetail.getId());
        message = "入库表明细表删除成功";
        try {
            this.emkInStorageDetailService.delete(emkInStorageDetail);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "入库表明细表删除失败";
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
        message = "入库表明细表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkInStorageDetailEntity emkInStorageDetail = (EmkInStorageDetailEntity) this.systemService.getEntity(EmkInStorageDetailEntity.class, id);


                this.emkInStorageDetailService.delete(emkInStorageDetail);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "入库表明细表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "入库表明细表添加成功";
        try {
            this.emkInStorageDetailService.save(emkInStorageDetail);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "入库表明细表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "入库表明细表更新成功";
        EmkInStorageDetailEntity t = (EmkInStorageDetailEntity) this.emkInStorageDetailService.get(EmkInStorageDetailEntity.class, emkInStorageDetail.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkInStorageDetail, t);
            this.emkInStorageDetailService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "入库表明细表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkInStorageDetail.getId())) {
            emkInStorageDetail = (EmkInStorageDetailEntity) this.emkInStorageDetailService.getEntity(EmkInStorageDetailEntity.class, emkInStorageDetail.getId());
            req.setAttribute("emkInStorageDetailPage", emkInStorageDetail);
        }
        return new ModelAndView("com/emk/storage/instoragedetail/emkInStorageDetail-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkInStorageDetail.getId())) {
            emkInStorageDetail = (EmkInStorageDetailEntity) this.emkInStorageDetailService.getEntity(EmkInStorageDetailEntity.class, emkInStorageDetail.getId());
            req.setAttribute("emkInStorageDetailPage", emkInStorageDetail);
        }
        return new ModelAndView("com/emk/storage/instoragedetail/emkInStorageDetail-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkInStorageDetailController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkInStorageDetailEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkInStorageDetail, request.getParameterMap());
        List<EmkInStorageDetailEntity> emkInStorageDetails = this.emkInStorageDetailService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "入库表明细表");
        modelMap.put("entity", EmkInStorageDetailEntity.class);
        modelMap.put("params", new ExportParams("入库表明细表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkInStorageDetails);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkInStorageDetailEntity emkInStorageDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "入库表明细表");
        modelMap.put("entity", EmkInStorageDetailEntity.class);
        modelMap.put("params", new ExportParams("入库表明细表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "入库表明细表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkInStorageDetailEntity>> list() {
        List<EmkInStorageDetailEntity> listEmkInStorageDetails = this.emkInStorageDetailService.getList(EmkInStorageDetailEntity.class);
        return Result.success(listEmkInStorageDetails);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取入库表明细表信息", notes = "根据ID获取入库表明细表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkInStorageDetailEntity task = (EmkInStorageDetailEntity) this.emkInStorageDetailService.get(EmkInStorageDetailEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取入库表明细表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建入库表明细表")
    public ResponseMessage<?> create(@ApiParam(name = "入库表明细表对象") @RequestBody EmkInStorageDetailEntity emkInStorageDetail, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkInStorageDetailEntity>> failures = this.validator.validate(emkInStorageDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkInStorageDetailService.save(emkInStorageDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("入库表明细表信息保存失败");
        }
        return Result.success(emkInStorageDetail);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新入库表明细表", notes = "更新入库表明细表")
    public ResponseMessage<?> update(@ApiParam(name = "入库表明细表对象") @RequestBody EmkInStorageDetailEntity emkInStorageDetail) {
        Set<ConstraintViolation<EmkInStorageDetailEntity>> failures = this.validator.validate(emkInStorageDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkInStorageDetailService.saveOrUpdate(emkInStorageDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新入库表明细表信息失败");
        }
        return Result.success("更新入库表明细表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除入库表明细表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkInStorageDetailService.deleteEntityById(EmkInStorageDetailEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("入库表明细表删除失败");
        }
        return Result.success();
    }
}
