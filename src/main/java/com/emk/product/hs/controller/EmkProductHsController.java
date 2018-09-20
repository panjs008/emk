package com.emk.product.hs.controller;

import com.emk.product.hs.entity.EmkProductHsEntity;
import com.emk.product.hs.service.EmkProductHsServiceI;

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
@RequestMapping({"/emkProductHsController"})
public class EmkProductHsController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProductHsController.class);
    @Autowired
    private EmkProductHsServiceI emkProductHsService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/hs/emkProductHsList");
    }

    @RequestMapping(params = {"select"})
    public ModelAndView select(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/hs/emkProductHsList-select");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProductHsEntity emkProductHs, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductHsEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProductHs, request.getParameterMap());


        cq.add();
        this.emkProductHsService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProductHsEntity emkProductHs, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProductHs = (EmkProductHsEntity) this.systemService.getEntity(EmkProductHsEntity.class, emkProductHs.getId());
        message = "海关编码表删除成功";
        try {
            this.emkProductHsService.delete(emkProductHs);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "海关编码表删除失败";
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
        message = "海关编码表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProductHsEntity emkProductHs = (EmkProductHsEntity) this.systemService.getEntity(EmkProductHsEntity.class, id);


                this.emkProductHsService.delete(emkProductHs);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "海关编码表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProductHsEntity emkProductHs, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "海关编码表添加成功";
        try {
            this.emkProductHsService.save(emkProductHs);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "海关编码表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProductHsEntity emkProductHs, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "海关编码表更新成功";
        EmkProductHsEntity t = (EmkProductHsEntity) this.emkProductHsService.get(EmkProductHsEntity.class, emkProductHs.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProductHs, t);
            this.emkProductHsService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "海关编码表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProductHsEntity emkProductHs, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductHs.getId())) {
            emkProductHs = (EmkProductHsEntity) this.emkProductHsService.getEntity(EmkProductHsEntity.class, emkProductHs.getId());
            req.setAttribute("emkProductHsPage", emkProductHs);
        }
        return new ModelAndView("com/emk/product/hs/emkProductHs-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProductHsEntity emkProductHs, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductHs.getId())) {
            emkProductHs = (EmkProductHsEntity) this.emkProductHsService.getEntity(EmkProductHsEntity.class, emkProductHs.getId());
            req.setAttribute("emkProductHsPage", emkProductHs);
        }
        return new ModelAndView("com/emk/product/hs/emkProductHs-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProductHsController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProductHsEntity emkProductHs, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductHsEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProductHs, request.getParameterMap());
        List<EmkProductHsEntity> emkProductHss = this.emkProductHsService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "海关编码表");
        modelMap.put("entity", EmkProductHsEntity.class);
        modelMap.put("params", new ExportParams("海关编码表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProductHss);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProductHsEntity emkProductHs, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "海关编码表");
        modelMap.put("entity", EmkProductHsEntity.class);
        modelMap.put("params", new ExportParams("海关编码表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<EmkProductHsEntity> list() {
        List<EmkProductHsEntity> listEmkProductHss = this.emkProductHsService.getList(EmkProductHsEntity.class);
        return listEmkProductHss;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        EmkProductHsEntity task = (EmkProductHsEntity) this.emkProductHsService.get(EmkProductHsEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody EmkProductHsEntity emkProductHs, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProductHsEntity>> failures = this.validator.validate(emkProductHs, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.emkProductHsService.save(emkProductHs);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = emkProductHs.getId();
        URI uri = uriBuilder.path("/rest/emkProductHsController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody EmkProductHsEntity emkProductHs) {
        Set<ConstraintViolation<EmkProductHsEntity>> failures = this.validator.validate(emkProductHs, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.emkProductHsService.saveOrUpdate(emkProductHs);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.emkProductHsService.deleteEntityById(EmkProductHsEntity.class, id);
    }
}
