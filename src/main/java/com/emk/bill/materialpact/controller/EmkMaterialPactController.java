package com.emk.bill.materialpact.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialpact.service.EmkMaterialPactServiceI;
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

@Api(value = "EmkMaterialPact", description = "面料预采购合同", tags = {"emkMaterialPactController"})
@Controller
@RequestMapping({"/emkMaterialPactController"})
public class EmkMaterialPactController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialPactController.class);
    @Autowired
    private EmkMaterialPactServiceI emkMaterialPactService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList");
    }

    @RequestMapping(params = {"list2"})
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList2");
    }

    @RequestMapping(params = {"list3"})
    public ModelAndView list3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList3");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialPactEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkMaterialPact, request.getParameterMap());


        cq.add();
        this.emkMaterialPactService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialPact = (EmkMaterialPactEntity) this.systemService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
        message = "面料预采购合同删除成功";
        try {
            this.emkMaterialPactService.delete(emkMaterialPact);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同删除失败";
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
        message = "面料预采购合同删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialPactEntity emkMaterialPact = (EmkMaterialPactEntity) this.systemService.getEntity(EmkMaterialPactEntity.class, id);


                this.emkMaterialPactService.delete(emkMaterialPact);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料预采购合同添加成功";
        try {
            this.emkMaterialPactService.save(emkMaterialPact);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料预采购合同更新成功";
        EmkMaterialPactEntity t = (EmkMaterialPactEntity) this.emkMaterialPactService.get(EmkMaterialPactEntity.class, emkMaterialPact.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialPact, t);
            this.emkMaterialPactService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            emkMaterialPact = (EmkMaterialPactEntity) this.emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            emkMaterialPact = (EmkMaterialPactEntity) this.emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialPactController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialPactEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialPact, request.getParameterMap());
        List<EmkMaterialPactEntity> emkMaterialPacts = this.emkMaterialPactService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "面料预采购合同");
        modelMap.put("entity", EmkMaterialPactEntity.class);
        modelMap.put("params", new ExportParams("面料预采购合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialPacts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "面料预采购合同");
        modelMap.put("entity", EmkMaterialPactEntity.class);
        modelMap.put("params", new ExportParams("面料预采购合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "面料预采购合同列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialPactEntity>> list() {
        List<EmkMaterialPactEntity> listEmkMaterialPacts = this.emkMaterialPactService.getList(EmkMaterialPactEntity.class);
        return Result.success(listEmkMaterialPacts);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取面料预采购合同信息", notes = "根据ID获取面料预采购合同信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialPactEntity task = (EmkMaterialPactEntity) this.emkMaterialPactService.get(EmkMaterialPactEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取面料预采购合同信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建面料预采购合同")
    public ResponseMessage<?> create(@ApiParam(name = "面料预采购合同对象") @RequestBody EmkMaterialPactEntity emkMaterialPact, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialPactEntity>> failures = this.validator.validate(emkMaterialPact, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialPactService.save(emkMaterialPact);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料预采购合同信息保存失败");
        }
        return Result.success(emkMaterialPact);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新面料预采购合同", notes = "更新面料预采购合同")
    public ResponseMessage<?> update(@ApiParam(name = "面料预采购合同对象") @RequestBody EmkMaterialPactEntity emkMaterialPact) {
        Set<ConstraintViolation<EmkMaterialPactEntity>> failures = this.validator.validate(emkMaterialPact, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialPactService.saveOrUpdate(emkMaterialPact);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新面料预采购合同信息失败");
        }
        return Result.success("更新面料预采购合同信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除面料预采购合同")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMaterialPactService.deleteEntityById(EmkMaterialPactEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料预采购合同删除失败");
        }
        return Result.success();
    }
}
