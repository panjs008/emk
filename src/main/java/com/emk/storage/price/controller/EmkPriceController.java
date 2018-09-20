package com.emk.storage.price.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.price.service.EmkPriceServiceI;
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

@Api(value = "EmkPrice", description = "报价单", tags = {"emkPriceController"})
@Controller
@RequestMapping({"/emkPriceController"})
public class EmkPriceController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkPriceController.class);
    @Autowired
    private EmkPriceServiceI emkPriceService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/price/emkPriceList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkPriceEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkPrice, request.getParameterMap());


        cq.add();
        this.emkPriceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPrice = (EmkPriceEntity) this.systemService.getEntity(EmkPriceEntity.class, emkPrice.getId());
        message = "报价单删除成功";
        try {
            this.emkPriceService.delete(emkPrice);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单删除失败";
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
        message = "报价单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkPriceEntity emkPrice = (EmkPriceEntity) this.systemService.getEntity(EmkPriceEntity.class, id);


                this.emkPriceService.delete(emkPrice);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单添加成功";
        try {
            emkPrice.setPirceNo(emkPrice.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
            this.emkPriceService.save(emkPrice);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单更新成功";
        EmkPriceEntity t = (EmkPriceEntity) this.emkPriceService.get(EmkPriceEntity.class, emkPrice.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkPrice, t);
            this.emkPriceService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkPriceEntity emkPrice, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = (EmkPriceEntity) this.emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = (EmkPriceEntity) this.emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkPriceController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkPriceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkPrice, request.getParameterMap());
        List<EmkPriceEntity> emkPrices = this.emkPriceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "报价单");
        modelMap.put("entity", EmkPriceEntity.class);
        modelMap.put("params", new ExportParams("报价单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkPrices);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "报价单");
        modelMap.put("entity", EmkPriceEntity.class);
        modelMap.put("params", new ExportParams("报价单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "报价单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkPriceEntity>> list() {
        List<EmkPriceEntity> listEmkPrices = this.emkPriceService.getList(EmkPriceEntity.class);
        return Result.success(listEmkPrices);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取报价单信息", notes = "根据ID获取报价单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPriceEntity task = (EmkPriceEntity) this.emkPriceService.get(EmkPriceEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取报价单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建报价单")
    public ResponseMessage<?> create(@ApiParam(name = "报价单对象") @RequestBody EmkPriceEntity emkPrice, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPriceEntity>> failures = this.validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPriceService.save(emkPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价单信息保存失败");
        }
        return Result.success(emkPrice);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新报价单", notes = "更新报价单")
    public ResponseMessage<?> update(@ApiParam(name = "报价单对象") @RequestBody EmkPriceEntity emkPrice) {
        Set<ConstraintViolation<EmkPriceEntity>> failures = this.validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPriceService.saveOrUpdate(emkPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新报价单信息失败");
        }
        return Result.success("更新报价单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除报价单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkPriceService.deleteEntityById(EmkPriceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价单删除失败");
        }
        return Result.success();
    }
}
