package com.service.customfrom.controller;

import com.service.customfrom.entity.YmkCustomFromEntity;
import com.service.customfrom.service.YmkCustomFromServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping({"/ymkCustomFromController"})
public class YmkCustomFromController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomFromController.class);
    @Autowired
    private YmkCustomFromServiceI ymkCustomFromService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/customfrom/ymkCustomFromList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomFromEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustomFrom, request.getParameterMap());
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            cq.eq("sysOrgCode", user.getCurrentDepart().getOrgCode());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.ymkCustomFromService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustomFrom = (YmkCustomFromEntity) this.systemService.getEntity(YmkCustomFromEntity.class, ymkCustomFrom.getId());
        message = "客户来源表删除成功";
        try {
            this.ymkCustomFromService.delete(ymkCustomFrom);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户来源表删除失败";
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
        message = "客户来源表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomFromEntity ymkCustomFrom = (YmkCustomFromEntity) this.systemService.getEntity(YmkCustomFromEntity.class, id);


                this.ymkCustomFromService.delete(ymkCustomFrom);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户来源表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"findSelectList"})
    @ResponseBody
    public AjaxJson findSelectList(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");

        String sql = "select a.form_code ,a.from_name fname from ymk_custom_from a where 1=1 and sys_org_code='" + user.getCurrentDepart().getOrgCode() + "' ";
        if (((ymkCustomFrom.getFormCode() == null) || (ymkCustomFrom.getFormCode().isEmpty())) || (


                (ymkCustomFrom.getFromName() != null) && (!ymkCustomFrom.getFromName().isEmpty()))) {
            sql = sql + " and (a.FORM_CODE like '%" + ymkCustomFrom.getFormCode() + "%'  ";
            sql = sql + "  or a.FROM_NAME like '%" + ymkCustomFrom.getFromName() + "%') order by id asc ";
        }
        List<Map<String, Object>> temp = this.systemService.findForJdbc(sql, new Object[0]);
        if ((temp != null) && (temp.size() > 0)) {
            j.setObj(temp);
        }
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户来源表添加成功";
        try {
            this.ymkCustomFromService.save(ymkCustomFrom);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户来源表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户来源表更新成功";
        YmkCustomFromEntity t = (YmkCustomFromEntity) this.ymkCustomFromService.get(YmkCustomFromEntity.class, ymkCustomFrom.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomFrom, t);
            this.ymkCustomFromService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户来源表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomFrom.getId())) {
            ymkCustomFrom = (YmkCustomFromEntity) this.ymkCustomFromService.getEntity(YmkCustomFromEntity.class, ymkCustomFrom.getId());
            req.setAttribute("ymkCustomFromPage", ymkCustomFrom);
        }
        return new ModelAndView("com/service/customfrom/ymkCustomFrom-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomFrom.getId())) {
            ymkCustomFrom = (YmkCustomFromEntity) this.ymkCustomFromService.getEntity(YmkCustomFromEntity.class, ymkCustomFrom.getId());
            req.setAttribute("ymkCustomFromPage", ymkCustomFrom);
        }
        return new ModelAndView("com/service/customfrom/ymkCustomFrom-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomFromController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomFromEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustomFrom, request.getParameterMap());
        List<YmkCustomFromEntity> ymkCustomFroms = this.ymkCustomFromService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "客户来源表");
        modelMap.put("entity", YmkCustomFromEntity.class);
        modelMap.put("params", new ExportParams("客户来源表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustomFroms);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomFromEntity ymkCustomFrom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "客户来源表");
        modelMap.put("entity", YmkCustomFromEntity.class);
        modelMap.put("params", new ExportParams("客户来源表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomFromEntity> list() {
        List<YmkCustomFromEntity> listYmkCustomFroms = this.ymkCustomFromService.getList(YmkCustomFromEntity.class);
        return listYmkCustomFroms;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomFromEntity task = (YmkCustomFromEntity) this.ymkCustomFromService.get(YmkCustomFromEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomFromEntity ymkCustomFrom, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomFromEntity>> failures = this.validator.validate(ymkCustomFrom, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomFromService.save(ymkCustomFrom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustomFrom.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomFromController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomFromEntity ymkCustomFrom) {
        Set<ConstraintViolation<YmkCustomFromEntity>> failures = this.validator.validate(ymkCustomFrom, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomFromService.saveOrUpdate(ymkCustomFrom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomFromService.deleteEntityById(YmkCustomFromEntity.class, id);
    }
}
