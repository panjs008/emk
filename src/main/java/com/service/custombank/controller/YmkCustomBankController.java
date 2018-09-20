package com.service.custombank.controller;

import com.service.custom.entity.YmkCustomEntity;
import com.service.custombank.entity.YmkCustomBankEntity;
import com.service.custombank.service.YmkCustomBankServiceI;

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
@RequestMapping({"/ymkCustomBankController"})
public class YmkCustomBankController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomBankController.class);
    @Autowired
    private YmkCustomBankServiceI ymkCustomBankService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/custombank/ymkCustomBankList");
    }

    @RequestMapping(params = {"list1"})
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/service/custombank/ymkCustomBankList1");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomBankEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustomBank, request.getParameterMap());


        cq.add();
        this.ymkCustomBankService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustomBank = (YmkCustomBankEntity) this.systemService.getEntity(YmkCustomBankEntity.class, ymkCustomBank.getId());
        message = "银行账户表删除成功";
        try {
            this.ymkCustomBankService.delete(ymkCustomBank);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "银行账户表删除失败";
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
        message = "银行账户表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomBankEntity ymkCustomBank = (YmkCustomBankEntity) this.systemService.getEntity(YmkCustomBankEntity.class, id);


                this.ymkCustomBankService.delete(ymkCustomBank);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "银行账户表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "银行账户表添加成功";
        try {
            YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomBank.getCustomId());
            ymkCustomBank.setCustomName(customEntity.getCusName());

            this.ymkCustomBankService.save(ymkCustomBank);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "银行账户表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "银行账户表更新成功";
        YmkCustomBankEntity t = (YmkCustomBankEntity) this.ymkCustomBankService.get(YmkCustomBankEntity.class, ymkCustomBank.getId());
        try {
            YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomBank.getCustomId());
            ymkCustomBank.setCustomName(customEntity.getCusName());
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomBank, t);
            this.ymkCustomBankService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "银行账户表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomBankEntity ymkCustomBank, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomBank.getId())) {
            ymkCustomBank = (YmkCustomBankEntity) this.ymkCustomBankService.getEntity(YmkCustomBankEntity.class, ymkCustomBank.getId());
            req.setAttribute("ymkCustomBankPage", ymkCustomBank);
        }
        return new ModelAndView("com/service/custombank/ymkCustomBank-add");
    }

    @RequestMapping(params = {"goAdd1"})
    public ModelAndView goAdd1(YmkCustomBankEntity ymkCustomBank, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomBank.getId())) {
            ymkCustomBank = (YmkCustomBankEntity) this.ymkCustomBankService.getEntity(YmkCustomBankEntity.class, ymkCustomBank.getId());
            req.setAttribute("ymkCustomBankPage", ymkCustomBank);
        }
        return new ModelAndView("com/service/custombank/ymkCustomBank-add1");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomBankEntity ymkCustomBank, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomBank.getId())) {
            ymkCustomBank = (YmkCustomBankEntity) this.ymkCustomBankService.getEntity(YmkCustomBankEntity.class, ymkCustomBank.getId());
            req.setAttribute("ymkCustomBankPage", ymkCustomBank);
        }
        return new ModelAndView("com/service/custombank/ymkCustomBank-update");
    }

    @RequestMapping(params = {"goUpdate1"})
    public ModelAndView goUpdate1(YmkCustomBankEntity ymkCustomBank, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomBank.getId())) {
            ymkCustomBank = (YmkCustomBankEntity) this.ymkCustomBankService.getEntity(YmkCustomBankEntity.class, ymkCustomBank.getId());
            req.setAttribute("ymkCustomBankPage", ymkCustomBank);
        }
        return new ModelAndView("com/service/custombank/ymkCustomBank-update1");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomBankController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomBankEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustomBank, request.getParameterMap());
        List<YmkCustomBankEntity> ymkCustomBanks = this.ymkCustomBankService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "银行账户表");
        modelMap.put("entity", YmkCustomBankEntity.class);
        modelMap.put("params", new ExportParams("银行账户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustomBanks);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomBankEntity ymkCustomBank, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "银行账户表");
        modelMap.put("entity", YmkCustomBankEntity.class);
        modelMap.put("params", new ExportParams("银行账户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomBankEntity> list() {
        List<YmkCustomBankEntity> listYmkCustomBanks = this.ymkCustomBankService.getList(YmkCustomBankEntity.class);
        return listYmkCustomBanks;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomBankEntity task = (YmkCustomBankEntity) this.ymkCustomBankService.get(YmkCustomBankEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomBankEntity ymkCustomBank, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomBankEntity>> failures = this.validator.validate(ymkCustomBank, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomBankService.save(ymkCustomBank);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustomBank.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomBankController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomBankEntity ymkCustomBank) {
        Set<ConstraintViolation<YmkCustomBankEntity>> failures = this.validator.validate(ymkCustomBank, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomBankService.saveOrUpdate(ymkCustomBank);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomBankService.deleteEntityById(YmkCustomBankEntity.class, id);
    }
}
