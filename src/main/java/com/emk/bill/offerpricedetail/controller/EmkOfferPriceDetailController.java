package com.emk.bill.offerpricedetail.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.offerpricedetail.entity.EmkOfferPriceDetailEntity;
import com.emk.bill.offerpricedetail.service.EmkOfferPriceDetailServiceI;
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

@Api(value = "EmkOfferPriceDetail", description = "报价方案明细", tags = {"emkOfferPriceDetailController"})
@Controller
@RequestMapping({"/emkOfferPriceDetailController"})
public class EmkOfferPriceDetailController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkOfferPriceDetailController.class);
    @Autowired
    private EmkOfferPriceDetailServiceI emkOfferPriceDetailService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/offerpricedetail/emkOfferPriceDetailList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkOfferPriceDetailEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkOfferPriceDetail, request.getParameterMap());


        cq.add();
        this.emkOfferPriceDetailService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkOfferPriceDetail = (EmkOfferPriceDetailEntity) this.systemService.getEntity(EmkOfferPriceDetailEntity.class, emkOfferPriceDetail.getId());
        message = "报价方案明细删除成功";
        try {
            this.emkOfferPriceDetailService.delete(emkOfferPriceDetail);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价方案明细删除失败";
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
        message = "报价方案明细删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkOfferPriceDetailEntity emkOfferPriceDetail = (EmkOfferPriceDetailEntity) this.systemService.getEntity(EmkOfferPriceDetailEntity.class, id);


                this.emkOfferPriceDetailService.delete(emkOfferPriceDetail);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价方案明细删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价方案明细添加成功";
        try {
            this.emkOfferPriceDetailService.save(emkOfferPriceDetail);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价方案明细添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价方案明细更新成功";
        EmkOfferPriceDetailEntity t = (EmkOfferPriceDetailEntity) this.emkOfferPriceDetailService.get(EmkOfferPriceDetailEntity.class, emkOfferPriceDetail.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkOfferPriceDetail, t);
            this.emkOfferPriceDetailService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价方案明细更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkOfferPriceDetail.getId())) {
            emkOfferPriceDetail = (EmkOfferPriceDetailEntity) this.emkOfferPriceDetailService.getEntity(EmkOfferPriceDetailEntity.class, emkOfferPriceDetail.getId());
            req.setAttribute("emkOfferPriceDetailPage", emkOfferPriceDetail);
        }
        return new ModelAndView("com/emk/bill/offerpricedetail/emkOfferPriceDetail-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkOfferPriceDetail.getId())) {
            emkOfferPriceDetail = (EmkOfferPriceDetailEntity) this.emkOfferPriceDetailService.getEntity(EmkOfferPriceDetailEntity.class, emkOfferPriceDetail.getId());
            req.setAttribute("emkOfferPriceDetailPage", emkOfferPriceDetail);
        }
        return new ModelAndView("com/emk/bill/offerpricedetail/emkOfferPriceDetail-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkOfferPriceDetailController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkOfferPriceDetailEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkOfferPriceDetail, request.getParameterMap());
        List<EmkOfferPriceDetailEntity> emkOfferPriceDetails = this.emkOfferPriceDetailService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "报价方案明细");
        modelMap.put("entity", EmkOfferPriceDetailEntity.class);
        modelMap.put("params", new ExportParams("报价方案明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkOfferPriceDetails);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkOfferPriceDetailEntity emkOfferPriceDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "报价方案明细");
        modelMap.put("entity", EmkOfferPriceDetailEntity.class);
        modelMap.put("params", new ExportParams("报价方案明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "报价方案明细列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkOfferPriceDetailEntity>> list() {
        List<EmkOfferPriceDetailEntity> listEmkOfferPriceDetails = this.emkOfferPriceDetailService.getList(EmkOfferPriceDetailEntity.class);
        return Result.success(listEmkOfferPriceDetails);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取报价方案明细信息", notes = "根据ID获取报价方案明细信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkOfferPriceDetailEntity task = (EmkOfferPriceDetailEntity) this.emkOfferPriceDetailService.get(EmkOfferPriceDetailEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取报价方案明细信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建报价方案明细")
    public ResponseMessage<?> create(@ApiParam(name = "报价方案明细对象") @RequestBody EmkOfferPriceDetailEntity emkOfferPriceDetail, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkOfferPriceDetailEntity>> failures = this.validator.validate(emkOfferPriceDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkOfferPriceDetailService.save(emkOfferPriceDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价方案明细信息保存失败");
        }
        return Result.success(emkOfferPriceDetail);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新报价方案明细", notes = "更新报价方案明细")
    public ResponseMessage<?> update(@ApiParam(name = "报价方案明细对象") @RequestBody EmkOfferPriceDetailEntity emkOfferPriceDetail) {
        Set<ConstraintViolation<EmkOfferPriceDetailEntity>> failures = this.validator.validate(emkOfferPriceDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkOfferPriceDetailService.saveOrUpdate(emkOfferPriceDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新报价方案明细信息失败");
        }
        return Result.success("更新报价方案明细信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除报价方案明细")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkOfferPriceDetailService.deleteEntityById(EmkOfferPriceDetailEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价方案明细删除失败");
        }
        return Result.success();
    }
}
