package com.service.customtrace.controller;

import com.service.custom.entity.YmkCustomEntity;
import com.service.customcontact.entity.YmkCustomContactEntity;
import com.service.customtrace.entity.YmkCustomTraceEntity;
import com.service.customtrace.service.YmkCustomTraceServiceI;

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
@RequestMapping({"/ymkCustomTraceController"})
public class YmkCustomTraceController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomTraceController.class);
    @Autowired
    private YmkCustomTraceServiceI ymkCustomTraceService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/customtrace/ymkCustomTraceList");
    }

    @RequestMapping(params = {"list1"})
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/service/customtrace/ymkCustomTraceList1");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomTraceEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustomTrace, request.getParameterMap());


        cq.add();
        this.ymkCustomTraceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustomTrace = (YmkCustomTraceEntity) this.systemService.getEntity(YmkCustomTraceEntity.class, ymkCustomTrace.getId());
        message = "客户跟进表删除成功";
        try {
            this.ymkCustomTraceService.delete(ymkCustomTrace);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户跟进表删除失败";
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
        message = "客户跟进表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomTraceEntity ymkCustomTrace = (YmkCustomTraceEntity) this.systemService.getEntity(YmkCustomTraceEntity.class, id);


                this.ymkCustomTraceService.delete(ymkCustomTrace);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户跟进表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户跟进表添加成功";
        try {
            YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomTrace.getCusId());
            ymkCustomTrace.setCusName(customEntity.getCusName());

            YmkCustomContactEntity customContactEntity = (YmkCustomContactEntity) this.systemService.getEntity(YmkCustomContactEntity.class, ymkCustomTrace.getContactId());
            ymkCustomTrace.setContactName(customContactEntity.getUserName());

            this.ymkCustomTraceService.save(ymkCustomTrace);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户跟进表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户跟进表更新成功";
        YmkCustomTraceEntity t = (YmkCustomTraceEntity) this.ymkCustomTraceService.get(YmkCustomTraceEntity.class, ymkCustomTrace.getId());
        try {
            if ((ymkCustomTrace.getCusName() != null) && (!ymkCustomTrace.getCusName().isEmpty())) {
                Map<String, Object> custom = this.systemService.findOneForJdbc("select a.id ,a.cus_name fname from ymk_custom a where 1=1 and cus_name=?", new Object[]{ymkCustomTrace.getCusName()});
                ymkCustomTrace.setCusId(custom.get("id").toString());
            }
            YmkCustomContactEntity customContactEntity = (YmkCustomContactEntity) this.systemService.getEntity(YmkCustomContactEntity.class, ymkCustomTrace.getContactId());
            ymkCustomTrace.setContactName(customContactEntity.getUserName());
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomTrace, t);
            this.ymkCustomTraceService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户跟进表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest req) {
        YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomTrace.getCusId());
        req.setAttribute("cusName", customEntity.getCusName());
        if (StringUtil.isNotEmpty(ymkCustomTrace.getId())) {
            ymkCustomTrace = (YmkCustomTraceEntity) this.ymkCustomTraceService.getEntity(YmkCustomTraceEntity.class, ymkCustomTrace.getId());
            req.setAttribute("ymkCustomTracePage", ymkCustomTrace);
        }
        return new ModelAndView("com/service/customtrace/ymkCustomTrace-add");
    }

    @RequestMapping(params = {"goAdd1"})
    public ModelAndView goAdd1(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest req) {
        return new ModelAndView("com/service/customtrace/ymkCustomTrace-add1");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomTrace.getId())) {
            ymkCustomTrace = (YmkCustomTraceEntity) this.ymkCustomTraceService.getEntity(YmkCustomTraceEntity.class, ymkCustomTrace.getId());
            req.setAttribute("ymkCustomTracePage", ymkCustomTrace);

            List<YmkCustomContactEntity> customContactEntities = this.systemService.findHql("from YmkCustomContactEntity where customId=?", new Object[]{ymkCustomTrace.getCusId()});
            req.setAttribute("customContactEntities", customContactEntities);
        }
        return new ModelAndView("com/service/customtrace/ymkCustomTrace-update");
    }

    @RequestMapping(params = {"goUpdate1"})
    public ModelAndView goUpdate1(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomTrace.getId())) {
            ymkCustomTrace = (YmkCustomTraceEntity) this.ymkCustomTraceService.getEntity(YmkCustomTraceEntity.class, ymkCustomTrace.getId());
            req.setAttribute("ymkCustomTracePage", ymkCustomTrace);

            List<YmkCustomContactEntity> customContactEntities = this.systemService.findHql("from YmkCustomContactEntity", new Object[0]);
            req.setAttribute("customContactEntities", customContactEntities);
        }
        return new ModelAndView("com/service/customtrace/ymkCustomTrace-update1");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomTraceController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomTraceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustomTrace, request.getParameterMap());
        List<YmkCustomTraceEntity> ymkCustomTraces = this.ymkCustomTraceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "客户跟进表");
        modelMap.put("entity", YmkCustomTraceEntity.class);
        modelMap.put("params", new ExportParams("客户跟进表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustomTraces);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomTraceEntity ymkCustomTrace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "客户跟进表");
        modelMap.put("entity", YmkCustomTraceEntity.class);
        modelMap.put("params", new ExportParams("客户跟进表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomTraceEntity> list() {
        List<YmkCustomTraceEntity> listYmkCustomTraces = this.ymkCustomTraceService.getList(YmkCustomTraceEntity.class);
        return listYmkCustomTraces;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomTraceEntity task = (YmkCustomTraceEntity) this.ymkCustomTraceService.get(YmkCustomTraceEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomTraceEntity ymkCustomTrace, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomTraceEntity>> failures = this.validator.validate(ymkCustomTrace, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomTraceService.save(ymkCustomTrace);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustomTrace.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomTraceController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomTraceEntity ymkCustomTrace) {
        Set<ConstraintViolation<YmkCustomTraceEntity>> failures = this.validator.validate(ymkCustomTrace, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomTraceService.saveOrUpdate(ymkCustomTrace);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomTraceService.deleteEntityById(YmkCustomTraceEntity.class, id);
    }
}
