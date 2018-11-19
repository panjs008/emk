package com.emk.bill.materialrequired.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.materialrequired.service.EmkMaterialRequiredServiceI;
import com.emk.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
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

@Api(value = "EmkMaterialRequired", description = "面料采购需求单", tags = "emkMaterialRequiredController")
@Controller
@RequestMapping("/emkMaterialRequiredController")
public class EmkMaterialRequiredController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialRequiredController.class);
    @Autowired
    private EmkMaterialRequiredServiceI emkMaterialRequiredService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList");
    }

    @RequestMapping(params = "list2")
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList2");
    }

    @RequestMapping(params = "list3")
    public ModelAndView list3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList3");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialRequiredEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkMaterialRequired, request.getParameterMap());


        cq.add();
        this.emkMaterialRequiredService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialRequired = (EmkMaterialRequiredEntity) this.systemService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
        message = "面料采购需求单删除成功";
        try {
            this.emkMaterialRequiredService.delete(emkMaterialRequired);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料采购需求单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialRequiredEntity emkMaterialRequired = (EmkMaterialRequiredEntity) this.systemService.getEntity(EmkMaterialRequiredEntity.class, id);
                this.systemService.executeSql("delete from emk_sample_detail where SAMPLE_ID=?", id);

                this.emkMaterialRequiredService.delete(emkMaterialRequired);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料采购需求单添加成功";
        try {
            this.emkMaterialRequiredService.save(emkMaterialRequired);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料采购需求单更新成功";
        EmkMaterialRequiredEntity t = (EmkMaterialRequiredEntity) this.emkMaterialRequiredService.get(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialRequired, t);
            this.emkMaterialRequiredService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
        Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_material_required where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
        req.setAttribute("materialNo","CG"+ DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = (EmkMaterialRequiredEntity) this.emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);
        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = (EmkMaterialRequiredEntity) this.emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);
        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialRequiredController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialRequiredEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialRequired, request.getParameterMap());
        List<EmkMaterialRequiredEntity> emkMaterialRequireds = this.emkMaterialRequiredService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "面料采购需求单");
        modelMap.put("entity", EmkMaterialRequiredEntity.class);
        modelMap.put("params", new ExportParams("面料采购需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialRequireds);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "面料采购需求单");
        modelMap.put("entity", EmkMaterialRequiredEntity.class);
        modelMap.put("params", new ExportParams("面料采购需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "面料采购需求单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialRequiredEntity>> list() {
        List<EmkMaterialRequiredEntity> listEmkMaterialRequireds = this.emkMaterialRequiredService.getList(EmkMaterialRequiredEntity.class);
        return Result.success(listEmkMaterialRequireds);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取面料采购需求单信息", notes = "根据ID获取面料采购需求单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialRequiredEntity task = (EmkMaterialRequiredEntity) this.emkMaterialRequiredService.get(EmkMaterialRequiredEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取面料采购需求单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建面料采购需求单")
    public ResponseMessage<?> create(@ApiParam(name = "面料采购需求单对象") @RequestBody EmkMaterialRequiredEntity emkMaterialRequired, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialRequiredEntity>> failures = this.validator.validate(emkMaterialRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialRequiredService.save(emkMaterialRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料采购需求单信息保存失败");
        }
        return Result.success(emkMaterialRequired);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新面料采购需求单", notes = "更新面料采购需求单")
    public ResponseMessage<?> update(@ApiParam(name = "面料采购需求单对象") @RequestBody EmkMaterialRequiredEntity emkMaterialRequired) {
        Set<ConstraintViolation<EmkMaterialRequiredEntity>> failures = this.validator.validate(emkMaterialRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialRequiredService.saveOrUpdate(emkMaterialRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新面料采购需求单信息失败");
        }
        return Result.success("更新面料采购需求单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除面料采购需求单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMaterialRequiredService.deleteEntityById(EmkMaterialRequiredEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料采购需求单删除失败");
        }
        return Result.success();
    }
}
