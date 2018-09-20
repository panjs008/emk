package com.service.customalert.controller;

import com.service.custom.entity.YmkCustomEntity;
import com.service.customalert.entity.YmkCustomAlertEntity;
import com.service.customalert.service.YmkCustomAlertServiceI;

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
@RequestMapping({"/ymkCustomAlertController"})
public class YmkCustomAlertController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomAlertController.class);
    @Autowired
    private YmkCustomAlertServiceI ymkCustomAlertService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/customalert/ymkCustomAlertList");
    }

    @RequestMapping(params = {"list1"})
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/service/customalert/ymkCustomAlertList1");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomAlertEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustomAlert, request.getParameterMap());


        cq.add();
        this.ymkCustomAlertService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustomAlert = (YmkCustomAlertEntity) this.systemService.getEntity(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
        message = "客户提醒表删除成功";
        try {
            this.ymkCustomAlertService.delete(ymkCustomAlert);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户提醒表删除失败";
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
        message = "客户提醒表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomAlertEntity ymkCustomAlert = (YmkCustomAlertEntity) this.systemService.getEntity(YmkCustomAlertEntity.class, id);


                this.ymkCustomAlertService.delete(ymkCustomAlert);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户提醒表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户提醒表添加成功";
        try {
            ymkCustomAlert.setState("0");
            if ((ymkCustomAlert.getCustomName() != null) && (!ymkCustomAlert.getCustomName().isEmpty())) {
                Map<String, Object> custom = this.systemService.findOneForJdbc("select a.id ,a.cus_name fname from ymk_custom a where 1=1 and cus_name=?", new Object[]{ymkCustomAlert.getCustomName()});
                ymkCustomAlert.setCustomId(custom.get("id").toString());
            } else {
                YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomAlert.getCustomId());
                ymkCustomAlert.setCustomName(customEntity.getCusName());
            }
            this.ymkCustomAlertService.save(ymkCustomAlert);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户提醒表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doOver"})
    @ResponseBody
    public AjaxJson doOver(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户提醒表完成";
        YmkCustomAlertEntity t = (YmkCustomAlertEntity) this.ymkCustomAlertService.get(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
        try {
            ymkCustomAlert.setState("1");
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomAlert, t);
            this.ymkCustomAlertService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户提醒表完成失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户提醒表更新成功";
        YmkCustomAlertEntity t = (YmkCustomAlertEntity) this.ymkCustomAlertService.get(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
        try {
            if ((ymkCustomAlert.getCustomId() == null) && (!ymkCustomAlert.getCustomId().isEmpty())) {
                Map<String, Object> custom = this.systemService.findOneForJdbc("select a.id ,a.cus_name fname from ymk_custom a where 1=1 and cus_name=?", new Object[]{ymkCustomAlert.getCustomName()});
                ymkCustomAlert.setCustomId(custom.get("id").toString());
            }
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomAlert, t);
            this.ymkCustomAlertService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户提醒表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomAlert.getId())) {
            ymkCustomAlert = (YmkCustomAlertEntity) this.ymkCustomAlertService.getEntity(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
            req.setAttribute("ymkCustomAlertPage", ymkCustomAlert);
        }
        return new ModelAndView("com/service/customalert/ymkCustomAlert-add");
    }

    @RequestMapping(params = {"goAdd1"})
    public ModelAndView goAdd1(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomAlert.getId())) {
            ymkCustomAlert = (YmkCustomAlertEntity) this.ymkCustomAlertService.getEntity(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
            req.setAttribute("ymkCustomAlertPage", ymkCustomAlert);
        }
        return new ModelAndView("com/service/customalert/ymkCustomAlert-add1");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomAlert.getId())) {
            ymkCustomAlert = (YmkCustomAlertEntity) this.ymkCustomAlertService.getEntity(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
            req.setAttribute("ymkCustomAlertPage", ymkCustomAlert);
        }
        return new ModelAndView("com/service/customalert/ymkCustomAlert-update");
    }

    @RequestMapping(params = {"goUpdate1"})
    public ModelAndView goUpdate1(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomAlert.getId())) {
            ymkCustomAlert = (YmkCustomAlertEntity) this.ymkCustomAlertService.getEntity(YmkCustomAlertEntity.class, ymkCustomAlert.getId());
            req.setAttribute("ymkCustomAlertPage", ymkCustomAlert);
        }
        return new ModelAndView("com/service/customalert/ymkCustomAlert-update1");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomAlertController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomAlertEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustomAlert, request.getParameterMap());
        List<YmkCustomAlertEntity> ymkCustomAlerts = this.ymkCustomAlertService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "客户提醒表");
        modelMap.put("entity", YmkCustomAlertEntity.class);
        modelMap.put("params", new ExportParams("客户提醒表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustomAlerts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomAlertEntity ymkCustomAlert, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "客户提醒表");
        modelMap.put("entity", YmkCustomAlertEntity.class);
        modelMap.put("params", new ExportParams("客户提醒表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomAlertEntity> list() {
        List<YmkCustomAlertEntity> listYmkCustomAlerts = this.ymkCustomAlertService.getList(YmkCustomAlertEntity.class);
        return listYmkCustomAlerts;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomAlertEntity task = (YmkCustomAlertEntity) this.ymkCustomAlertService.get(YmkCustomAlertEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomAlertEntity ymkCustomAlert, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomAlertEntity>> failures = this.validator.validate(ymkCustomAlert, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomAlertService.save(ymkCustomAlert);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustomAlert.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomAlertController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomAlertEntity ymkCustomAlert) {
        Set<ConstraintViolation<YmkCustomAlertEntity>> failures = this.validator.validate(ymkCustomAlert, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomAlertService.saveOrUpdate(ymkCustomAlert);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomAlertService.deleteEntityById(YmkCustomAlertEntity.class, id);
    }
}
