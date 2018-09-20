package com.emk.storage.sampleprice.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.storage.sampleprice.service.EmkSamplePriceServiceI;
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

@Api(value = "EmkSamplePrice", description = "打样费", tags = {"emkSamplePriceController"})
@Controller
@RequestMapping({"/emkSamplePriceController"})
public class EmkSamplePriceController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSamplePriceController.class);
    @Autowired
    private EmkSamplePriceServiceI emkSamplePriceService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sampleprice/emkSamplePriceList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSamplePriceEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkSamplePrice, request.getParameterMap());


        cq.add();
        this.emkSamplePriceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSamplePrice = (EmkSamplePriceEntity) this.systemService.getEntity(EmkSamplePriceEntity.class, emkSamplePrice.getId());
        message = "打样费删除成功";
        try {
            this.emkSamplePriceService.delete(emkSamplePrice);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "打样费删除失败";
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
        message = "打样费删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSamplePriceEntity emkSamplePrice = (EmkSamplePriceEntity) this.systemService.getEntity(EmkSamplePriceEntity.class, id);


                this.emkSamplePriceService.delete(emkSamplePrice);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "打样费删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "打样费添加成功";
        try {
            this.emkSamplePriceService.save(emkSamplePrice);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "打样费添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "打样费更新成功";
        EmkSamplePriceEntity t = (EmkSamplePriceEntity) this.emkSamplePriceService.get(EmkSamplePriceEntity.class, emkSamplePrice.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSamplePrice, t);
            this.emkSamplePriceService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "打样费更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSamplePrice.getId())) {
            emkSamplePrice = (EmkSamplePriceEntity) this.emkSamplePriceService.getEntity(EmkSamplePriceEntity.class, emkSamplePrice.getId());
            req.setAttribute("emkSamplePricePage", emkSamplePrice);
        }
        return new ModelAndView("com/emk/storage/sampleprice/emkSamplePrice-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSamplePrice.getId())) {
            emkSamplePrice = (EmkSamplePriceEntity) this.emkSamplePriceService.getEntity(EmkSamplePriceEntity.class, emkSamplePrice.getId());
            req.setAttribute("emkSamplePricePage", emkSamplePrice);
        }
        return new ModelAndView("com/emk/storage/sampleprice/emkSamplePrice-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSamplePriceController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSamplePriceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSamplePrice, request.getParameterMap());
        List<EmkSamplePriceEntity> emkSamplePrices = this.emkSamplePriceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "打样费");
        modelMap.put("entity", EmkSamplePriceEntity.class);
        modelMap.put("params", new ExportParams("打样费列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSamplePrices);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkSamplePriceEntity emkSamplePrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "打样费");
        modelMap.put("entity", EmkSamplePriceEntity.class);
        modelMap.put("params", new ExportParams("打样费列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "打样费列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSamplePriceEntity>> list() {
        List<EmkSamplePriceEntity> listEmkSamplePrices = this.emkSamplePriceService.getList(EmkSamplePriceEntity.class);
        return Result.success(listEmkSamplePrices);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取打样费信息", notes = "根据ID获取打样费信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSamplePriceEntity task = (EmkSamplePriceEntity) this.emkSamplePriceService.get(EmkSamplePriceEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取打样费信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建打样费")
    public ResponseMessage<?> create(@ApiParam(name = "打样费对象") @RequestBody EmkSamplePriceEntity emkSamplePrice, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSamplePriceEntity>> failures = this.validator.validate(emkSamplePrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSamplePriceService.save(emkSamplePrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("打样费信息保存失败");
        }
        return Result.success(emkSamplePrice);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新打样费", notes = "更新打样费")
    public ResponseMessage<?> update(@ApiParam(name = "打样费对象") @RequestBody EmkSamplePriceEntity emkSamplePrice) {
        Set<ConstraintViolation<EmkSamplePriceEntity>> failures = this.validator.validate(emkSamplePrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSamplePriceService.saveOrUpdate(emkSamplePrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新打样费信息失败");
        }
        return Result.success("更新打样费信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除打样费")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkSamplePriceService.deleteEntityById(EmkSamplePriceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("打样费删除失败");
        }
        return Result.success();
    }
}
