package com.emk.bill.materialcontractdetail.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;
import com.emk.bill.materialcontractdetail.service.EmkMaterialContractDetailServiceI;
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

@Api(value = "EmkMaterialContractDetail", description = "采购单明细", tags = {"emkMaterialContractDetailController"})
@Controller
@RequestMapping({"/emkMaterialContractDetailController"})
public class EmkMaterialContractDetailController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialContractDetailController.class);
    @Autowired
    private EmkMaterialContractDetailServiceI emkMaterialContractDetailService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialcontractdetail/emkMaterialContractDetailList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialContractDetailEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkMaterialContractDetail, request.getParameterMap());


        cq.add();
        this.emkMaterialContractDetailService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialContractDetail = (EmkMaterialContractDetailEntity) this.systemService.getEntity(EmkMaterialContractDetailEntity.class, emkMaterialContractDetail.getId());
        message = "采购单明细删除成功";
        try {
            this.emkMaterialContractDetailService.delete(emkMaterialContractDetail);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购单明细删除失败";
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
        message = "采购单明细删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialContractDetailEntity emkMaterialContractDetail = (EmkMaterialContractDetailEntity) this.systemService.getEntity(EmkMaterialContractDetailEntity.class, id);


                this.emkMaterialContractDetailService.delete(emkMaterialContractDetail);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购单明细删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购单明细添加成功";
        try {
            this.emkMaterialContractDetailService.save(emkMaterialContractDetail);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购单明细添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购单明细更新成功";
        EmkMaterialContractDetailEntity t = (EmkMaterialContractDetailEntity) this.emkMaterialContractDetailService.get(EmkMaterialContractDetailEntity.class, emkMaterialContractDetail.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialContractDetail, t);
            this.emkMaterialContractDetailService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购单明细更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContractDetail.getId())) {
            emkMaterialContractDetail = (EmkMaterialContractDetailEntity) this.emkMaterialContractDetailService.getEntity(EmkMaterialContractDetailEntity.class, emkMaterialContractDetail.getId());
            req.setAttribute("emkMaterialContractDetailPage", emkMaterialContractDetail);
        }
        return new ModelAndView("com/emk/bill/materialcontractdetail/emkMaterialContractDetail-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContractDetail.getId())) {
            emkMaterialContractDetail = (EmkMaterialContractDetailEntity) this.emkMaterialContractDetailService.getEntity(EmkMaterialContractDetailEntity.class, emkMaterialContractDetail.getId());
            req.setAttribute("emkMaterialContractDetailPage", emkMaterialContractDetail);
        }
        return new ModelAndView("com/emk/bill/materialcontractdetail/emkMaterialContractDetail-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialContractDetailController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialContractDetailEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialContractDetail, request.getParameterMap());
        List<EmkMaterialContractDetailEntity> emkMaterialContractDetails = this.emkMaterialContractDetailService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "采购单明细");
        modelMap.put("entity", EmkMaterialContractDetailEntity.class);
        modelMap.put("params", new ExportParams("采购单明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialContractDetails);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkMaterialContractDetailEntity emkMaterialContractDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "采购单明细");
        modelMap.put("entity", EmkMaterialContractDetailEntity.class);
        modelMap.put("params", new ExportParams("采购单明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "采购单明细列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialContractDetailEntity>> list() {
        List<EmkMaterialContractDetailEntity> listEmkMaterialContractDetails = this.emkMaterialContractDetailService.getList(EmkMaterialContractDetailEntity.class);
        return Result.success(listEmkMaterialContractDetails);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取采购单明细信息", notes = "根据ID获取采购单明细信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialContractDetailEntity task = (EmkMaterialContractDetailEntity) this.emkMaterialContractDetailService.get(EmkMaterialContractDetailEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取采购单明细信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建采购单明细")
    public ResponseMessage<?> create(@ApiParam(name = "采购单明细对象") @RequestBody EmkMaterialContractDetailEntity emkMaterialContractDetail, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialContractDetailEntity>> failures = this.validator.validate(emkMaterialContractDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialContractDetailService.save(emkMaterialContractDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购单明细信息保存失败");
        }
        return Result.success(emkMaterialContractDetail);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新采购单明细", notes = "更新采购单明细")
    public ResponseMessage<?> update(@ApiParam(name = "采购单明细对象") @RequestBody EmkMaterialContractDetailEntity emkMaterialContractDetail) {
        Set<ConstraintViolation<EmkMaterialContractDetailEntity>> failures = this.validator.validate(emkMaterialContractDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialContractDetailService.saveOrUpdate(emkMaterialContractDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新采购单明细信息失败");
        }
        return Result.success("更新采购单明细信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除采购单明细")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMaterialContractDetailService.deleteEntityById(EmkMaterialContractDetailEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购单明细删除失败");
        }
        return Result.success();
    }
}
