package com.emk.bill.clause.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.clause.entity.EmkClauseEntity;
import com.emk.bill.clause.service.EmkClauseServiceI;
import com.emk.util.ParameterUtil;
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
import javax.servlet.http.HttpSession;
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

@Api(value = "EmkClause", description = "合同条款表", tags = {"emkClauseController"})
@Controller
@RequestMapping({"/emkClauseController"})
public class EmkClauseController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkClauseController.class);
    @Autowired
    private EmkClauseServiceI emkClauseService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/clause/emkClauseList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkClauseEntity emkClause, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkClauseEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkClause, request.getParameterMap());


        cq.add();
        this.emkClauseService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkClauseEntity emkClause, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkClause = (EmkClauseEntity) this.systemService.getEntity(EmkClauseEntity.class, emkClause.getId());
        message = "合同条款表删除成功";
        try {
            this.emkClauseService.delete(emkClause);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "合同条款表删除失败";
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
        message = "合同条款表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkClauseEntity emkClause = (EmkClauseEntity) this.systemService.getEntity(EmkClauseEntity.class, id);


                this.emkClauseService.delete(emkClause);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "合同条款表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkClauseEntity emkClause, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "合同条款表添加成功";
        try {
            this.emkClauseService.save(emkClause);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "合同条款表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkClauseEntity emkClause, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "合同条款表更新成功";
        EmkClauseEntity t = (EmkClauseEntity) this.emkClauseService.get(EmkClauseEntity.class, emkClause.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkClause, t);
            this.emkClauseService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "合同条款表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"findClauseList"})
    @ResponseBody
    public AjaxJson findClauseList(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");

        String sql = "select a.clause_num,a.clause_content from emk_clause a where 1=1 and sys_org_code='" + user.getCurrentDepart().getOrgCode() + "' ";


        List<Map<String, Object>> temp = this.systemService.findForJdbc(sql, new Object[0]);
        if ((temp != null) && (temp.size() > 0)) {
            j.setObj(temp);
        }
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkClauseEntity emkClause, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkClause.getId())) {
            emkClause = (EmkClauseEntity) this.emkClauseService.getEntity(EmkClauseEntity.class, emkClause.getId());
            req.setAttribute("emkClausePage", emkClause);
        }
        return new ModelAndView("com/emk/bill/clause/emkClause-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkClauseEntity emkClause, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkClause.getId())) {
            emkClause = (EmkClauseEntity) this.emkClauseService.getEntity(EmkClauseEntity.class, emkClause.getId());
            req.setAttribute("emkClausePage", emkClause);
        }
        return new ModelAndView("com/emk/bill/clause/emkClause-update");
    }

    @RequestMapping(params = {"goClauseUpdate"})
    public ModelAndView goClauseUpdate(EmkClauseEntity emkClause, HttpServletRequest req) {
        Map clauseMap = this.systemService.findOneForJdbc("select * from emk_clause where clause_num=?", new Object[]{emkClause.getClauseNum()});
        emkClause = (EmkClauseEntity) this.emkClauseService.getEntity(EmkClauseEntity.class, clauseMap.get("id").toString());
        req.setAttribute("emkClausePage", emkClause);
        return new ModelAndView("com/emk/bill/clause/emkClause-update2");
    }

    @RequestMapping(params = {"getClause"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public AjaxJson getClause(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        Map param = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            Map clauseMap = this.systemService.findOneForJdbc("SELECT clause_content FROM emk_clause WHERE clause_num=?", new Object[]{param.get("clause_num")});
            String dataItems = "";
            try {
                j.setObj(clauseMap.get("clause_content"));
            } catch (Exception e) {
                logger.error(ExceptionUtil.getExceptionMessage(e));
            }
        } catch (Exception e) {
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return j;
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkClauseController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkClauseEntity emkClause, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkClauseEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkClause, request.getParameterMap());
        List<EmkClauseEntity> emkClauses = this.emkClauseService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "合同条款表");
        modelMap.put("entity", EmkClauseEntity.class);
        modelMap.put("params", new ExportParams("合同条款表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkClauses);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkClauseEntity emkClause, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "合同条款表");
        modelMap.put("entity", EmkClauseEntity.class);
        modelMap.put("params", new ExportParams("合同条款表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "合同条款表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkClauseEntity>> list() {
        List<EmkClauseEntity> listEmkClauses = this.emkClauseService.getList(EmkClauseEntity.class);
        return Result.success(listEmkClauses);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取合同条款表信息", notes = "根据ID获取合同条款表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkClauseEntity task = (EmkClauseEntity) this.emkClauseService.get(EmkClauseEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取合同条款表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建合同条款表")
    public ResponseMessage<?> create(@ApiParam(name = "合同条款表对象") @RequestBody EmkClauseEntity emkClause, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkClauseEntity>> failures = this.validator.validate(emkClause, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkClauseService.save(emkClause);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("合同条款表信息保存失败");
        }
        return Result.success(emkClause);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新合同条款表", notes = "更新合同条款表")
    public ResponseMessage<?> update(@ApiParam(name = "合同条款表对象") @RequestBody EmkClauseEntity emkClause) {
        Set<ConstraintViolation<EmkClauseEntity>> failures = this.validator.validate(emkClause, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkClauseService.saveOrUpdate(emkClause);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新合同条款表信息失败");
        }
        return Result.success("更新合同条款表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除合同条款表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkClauseService.deleteEntityById(EmkClauseEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("合同条款表删除失败");
        }
        return Result.success();
    }
}
