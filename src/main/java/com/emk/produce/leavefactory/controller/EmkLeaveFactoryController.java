package com.emk.produce.leavefactory.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.leavefactory.entity.EmkLeaveFactoryEntity;
import com.emk.produce.leavefactory.service.EmkLeaveFactoryServiceI;
import com.emk.util.ParameterUtil;
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
import javax.servlet.http.HttpSession;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
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

@Api(value = "EmkLeaveFactory", description = "离厂通知单", tags = {"emkLeaveFactoryController"})
@Controller
@RequestMapping({"/emkLeaveFactoryController"})
public class EmkLeaveFactoryController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkLeaveFactoryController.class);
    @Autowired
    private EmkLeaveFactoryServiceI emkLeaveFactoryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/leavefactory/emkLeaveFactoryList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkLeaveFactoryEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkLeaveFactory, request.getParameterMap());


        cq.add();
        this.emkLeaveFactoryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkLeaveFactory = (EmkLeaveFactoryEntity) this.systemService.getEntity(EmkLeaveFactoryEntity.class, emkLeaveFactory.getId());
        message = "离厂通知单删除成功";
        try {
            this.emkLeaveFactoryService.delete(emkLeaveFactory);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "离厂通知单删除失败";
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
        message = "离厂通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkLeaveFactoryEntity emkLeaveFactory = (EmkLeaveFactoryEntity) this.systemService.getEntity(EmkLeaveFactoryEntity.class, id);


                this.emkLeaveFactoryService.delete(emkLeaveFactory);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "离厂通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "离厂通知单添加成功";
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_leave_factory where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
            emkLeaveFactory.setLeaveFactoryNo("L" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
            this.emkLeaveFactoryService.save(emkLeaveFactory);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "离厂通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "离厂通知单更新成功";
        EmkLeaveFactoryEntity t = (EmkLeaveFactoryEntity) this.emkLeaveFactoryService.get(EmkLeaveFactoryEntity.class, emkLeaveFactory.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkLeaveFactory, t);
            this.emkLeaveFactoryService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "离厂通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkLeaveFactory.getId())) {
            emkLeaveFactory = (EmkLeaveFactoryEntity) this.emkLeaveFactoryService.getEntity(EmkLeaveFactoryEntity.class, emkLeaveFactory.getId());
            req.setAttribute("emkLeaveFactoryPage", emkLeaveFactory);
        }
        return new ModelAndView("com/emk/produce/leavefactory/emkLeaveFactory-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkLeaveFactory.getId())) {
            emkLeaveFactory = (EmkLeaveFactoryEntity) this.emkLeaveFactoryService.getEntity(EmkLeaveFactoryEntity.class, emkLeaveFactory.getId());
            req.setAttribute("emkLeaveFactoryPage", emkLeaveFactory);
        }
        return new ModelAndView("com/emk/produce/leavefactory/emkLeaveFactory-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkLeaveFactoryController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkLeaveFactoryEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkLeaveFactory, request.getParameterMap());
        List<EmkLeaveFactoryEntity> emkLeaveFactorys = this.emkLeaveFactoryService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "离厂通知单");
        modelMap.put("entity", EmkLeaveFactoryEntity.class);
        modelMap.put("params", new ExportParams("离厂通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkLeaveFactorys);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkLeaveFactoryEntity emkLeaveFactory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "离厂通知单");
        modelMap.put("entity", EmkLeaveFactoryEntity.class);
        modelMap.put("params", new ExportParams("离厂通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "离厂通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkLeaveFactoryEntity>> list() {
        List<EmkLeaveFactoryEntity> listEmkLeaveFactorys = this.emkLeaveFactoryService.getList(EmkLeaveFactoryEntity.class);
        return Result.success(listEmkLeaveFactorys);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取离厂通知单信息", notes = "根据ID获取离厂通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkLeaveFactoryEntity task = (EmkLeaveFactoryEntity) this.emkLeaveFactoryService.get(EmkLeaveFactoryEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取离厂通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建离厂通知单")
    public ResponseMessage<?> create(@ApiParam(name = "离厂通知单对象") @RequestBody EmkLeaveFactoryEntity emkLeaveFactory, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkLeaveFactoryEntity>> failures = this.validator.validate(emkLeaveFactory, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkLeaveFactoryService.save(emkLeaveFactory);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("离厂通知单信息保存失败");
        }
        return Result.success(emkLeaveFactory);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新离厂通知单", notes = "更新离厂通知单")
    public ResponseMessage<?> update(@ApiParam(name = "离厂通知单对象") @RequestBody EmkLeaveFactoryEntity emkLeaveFactory) {
        Set<ConstraintViolation<EmkLeaveFactoryEntity>> failures = this.validator.validate(emkLeaveFactory, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkLeaveFactoryService.saveOrUpdate(emkLeaveFactory);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新离厂通知单信息失败");
        }
        return Result.success("更新离厂通知单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除离厂通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkLeaveFactoryService.deleteEntityById(EmkLeaveFactoryEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("离厂通知单删除失败");
        }
        return Result.success();
    }
}
