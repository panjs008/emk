package com.emk.product.productinfo.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.product.productinfo.entity.EmkProductInfoEntity;
import com.emk.product.productinfo.service.EmkProductInfoServiceI;
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

@Api(value = "EmkProductInfo", description = "产品表", tags = {"emkProductInfoController"})
@Controller
@RequestMapping({"/emkProductInfoController"})
public class EmkProductInfoController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProductInfoController.class);
    @Autowired
    private EmkProductInfoServiceI emkProductInfoService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/productinfo/emkProductInfoList");
    }

    @RequestMapping(params = {"proSelect"})
    public ModelAndView proSelect(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/productinfo/emkProductInfoList-select");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProductInfoEntity emkProductInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductInfoEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProductInfo, request.getParameterMap());


        cq.add();
        this.emkProductInfoService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProductInfoEntity emkProductInfo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProductInfo = (EmkProductInfoEntity) this.systemService.getEntity(EmkProductInfoEntity.class, emkProductInfo.getId());
        message = "产品表删除成功";
        try {
            this.emkProductInfoService.delete(emkProductInfo);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品表删除失败";
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
        message = "产品表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProductInfoEntity emkProductInfo = (EmkProductInfoEntity) this.systemService.getEntity(EmkProductInfoEntity.class, id);


                this.emkProductInfoService.delete(emkProductInfo);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProductInfoEntity emkProductInfo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产品表添加成功";
        try {
            this.emkProductInfoService.save(emkProductInfo);

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProductInfoEntity emkProductInfo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产品表更新成功";
        EmkProductInfoEntity t = (EmkProductInfoEntity) this.emkProductInfoService.get(EmkProductInfoEntity.class, emkProductInfo.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProductInfo, t);
            this.emkProductInfoService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProductInfoEntity emkProductInfo, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductInfo.getId())) {
            emkProductInfo = (EmkProductInfoEntity) this.emkProductInfoService.getEntity(EmkProductInfoEntity.class, emkProductInfo.getId());
            req.setAttribute("emkProductInfoPage", emkProductInfo);
        }
        return new ModelAndView("com/emk/product/productinfo/emkProductInfo-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProductInfoEntity emkProductInfo, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductInfo.getId())) {
            emkProductInfo = (EmkProductInfoEntity) this.emkProductInfoService.getEntity(EmkProductInfoEntity.class, emkProductInfo.getId());
            req.setAttribute("emkProductInfoPage", emkProductInfo);
            if (emkProductInfo.getProType() != null) {
                Map protype = this.systemService.findOneForJdbc("select * from emk_product_type where id=?", new Object[]{emkProductInfo.getProType()});
                req.setAttribute("proTypeName", protype.get("content"));
            }
        }
        return new ModelAndView("com/emk/product/productinfo/emkProductInfo-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProductInfoController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProductInfoEntity emkProductInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductInfoEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProductInfo, request.getParameterMap());
        List<EmkProductInfoEntity> emkProductInfos = this.emkProductInfoService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "产品表");
        modelMap.put("entity", EmkProductInfoEntity.class);
        modelMap.put("params", new ExportParams("产品表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProductInfos);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProductInfoEntity emkProductInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "产品表");
        modelMap.put("entity", EmkProductInfoEntity.class);
        modelMap.put("params", new ExportParams("产品表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "产品表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProductInfoEntity>> list() {
        List<EmkProductInfoEntity> listEmkProductInfos = this.emkProductInfoService.getList(EmkProductInfoEntity.class);
        return Result.success(listEmkProductInfos);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取产品表信息", notes = "根据ID获取产品表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProductInfoEntity task = (EmkProductInfoEntity) this.emkProductInfoService.get(EmkProductInfoEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取产品表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建产品表")
    public ResponseMessage<?> create(@ApiParam(name = "产品表对象") @RequestBody EmkProductInfoEntity emkProductInfo, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProductInfoEntity>> failures = this.validator.validate(emkProductInfo, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProductInfoService.save(emkProductInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("产品表信息保存失败");
        }
        return Result.success(emkProductInfo);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新产品表", notes = "更新产品表")
    public ResponseMessage<?> update(@ApiParam(name = "产品表对象") @RequestBody EmkProductInfoEntity emkProductInfo) {
        Set<ConstraintViolation<EmkProductInfoEntity>> failures = this.validator.validate(emkProductInfo, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProductInfoService.saveOrUpdate(emkProductInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新产品表信息失败");
        }
        return Result.success("更新产品表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除产品表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProductInfoService.deleteEntityById(EmkProductInfoEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("产品表删除失败");
        }
        return Result.success();
    }
}
