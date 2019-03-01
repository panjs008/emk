package com.emk.storage.supplier.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.supplier.entity.EmkSupplierEntity;
import com.emk.storage.supplier.entity.EmkSupplierEntity2;
import com.emk.storage.supplier.service.EmkSupplierServiceI;
import com.emk.util.*;
import com.service.custom.entity.YmkCustomEntity;
import com.service.custom.entity.YmkCustomEntityA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
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
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
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

@Api(value = "EmkSupplier", description = "供应商管理", tags = "emkSupplierController")
@Controller
@RequestMapping("/emkSupplierController")
public class EmkSupplierController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSupplierController.class);
    @Autowired
    private EmkSupplierServiceI emkSupplierService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/supplier/emkSupplierList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSupplierEntity emkSupplier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSupplierEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSupplier, request.getParameterMap());

        cq.add();
        emkSupplierService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSupplierEntity emkSupplier, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSupplier = (EmkSupplierEntity) systemService.getEntity(EmkSupplierEntity.class, emkSupplier.getId());
        message = "供应商管理删除成功";
        try {
            emkSupplierService.delete(emkSupplier);
            WebFileUtils.delete(request.getRealPath("/")+emkSupplier.getLicenceUrl());
            WebFileUtils.delete(request.getRealPath("/")+emkSupplier.getFactoryInfoUrl());

            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "供应商管理删除失败";
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
        message = "供应商管理删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSupplierEntity emkSupplier = (EmkSupplierEntity) systemService.getEntity(EmkSupplierEntity.class, id);
                emkSupplierService.delete(emkSupplier);
                WebFileUtils.delete(request.getRealPath("/")+emkSupplier.getLicenceUrl());
                WebFileUtils.delete(request.getRealPath("/")+emkSupplier.getFactoryInfoUrl());
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "供应商管理删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSupplierEntity emkSupplier, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "供应商管理添加成功";
        try {
            emkSupplierService.save(emkSupplier);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "供应商管理添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSupplierEntity emkSupplier, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "供应商管理更新成功";
        EmkSupplierEntity t = (EmkSupplierEntity) emkSupplierService.get(EmkSupplierEntity.class, emkSupplier.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSupplier, t);
            emkSupplierService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "供应商管理更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doPrintPDF")
    public String doPrintPDF(String ids, EmkSupplierEntity supplierEntity, HttpServletRequest request, HttpServletResponse response) {
        String message = null;

        try {
            for (String id : ids.split(",")) {
                String fileName = "c:\\PDF\\"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                File file = new File(fileName);
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EmkSupplierEntity t = (EmkSupplierEntity) emkSupplierService.get(EmkSupplierEntity.class, id);
                EmkSupplierEntity2 emkSupplierEntity2 = new EmkSupplierEntity2();
                MyBeanUtils.copyBeanNotNull2Bean(t,emkSupplierEntity2);

                Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='gyslx' and typecode=?",t.getSupplierCode());
                if(Utils.notEmpty(type)){
                    emkSupplierEntity2.setSupplierType(type.get("typename").toString());
                }

                if(Utils.notEmpty(t.getLicenceUrl())){
                    emkSupplierEntity2.setLicenceUrl(request.getRealPath("/")+t.getLicenceUrl());
                }
                if(Utils.notEmpty(t.getFactoryInfoUrl())){
                    emkSupplierEntity2.setFactoryInfoUrl(request.getRealPath("/")+t.getFactoryInfoUrl());
                }

                new createPdf(file).generateEmkSupplierPDF(emkSupplierEntity2);
                String fFileName = "c:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                WaterMark.waterMark(fileName,fFileName, "客户档案");
                file.delete();
                WebFileUtils.downLoad(fFileName,response,false);
                file = new File(fFileName);
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "客户档案导出PDF失败";
            throw new BusinessException(e.getMessage());
        }
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSupplierEntity emkSupplier, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSupplier.getId())) {
            emkSupplier = (EmkSupplierEntity) emkSupplierService.getEntity(EmkSupplierEntity.class, emkSupplier.getId());
            req.setAttribute("emkSupplierPage", emkSupplier);
        }
        return new ModelAndView("com/emk/storage/supplier/emkSupplier-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSupplierEntity emkSupplier, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSupplier.getId())) {
            emkSupplier = (EmkSupplierEntity) emkSupplierService.getEntity(EmkSupplierEntity.class, emkSupplier.getId());
            req.setAttribute("emkSupplierPage", emkSupplier);

            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSupplier);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString())-5;
                b = Double.parseDouble(countMap.get("Colums").toString())-5;
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/supplier/emkSupplier-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSupplierController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSupplierEntity emkSupplier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSupplierEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSupplier, request.getParameterMap());
        List<EmkSupplierEntity> emkSuppliers = emkSupplierService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "供应商管理");
        modelMap.put("entity", EmkSupplierEntity.class);
        modelMap.put("params", new ExportParams("供应商管理列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSuppliers);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSupplierEntity emkSupplier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "供应商管理");
        modelMap.put("entity", EmkSupplierEntity.class);
        modelMap.put("params", new ExportParams("供应商管理列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "供应商管理列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSupplierEntity>> list() {
        List<EmkSupplierEntity> listEmkSuppliers = emkSupplierService.getList(EmkSupplierEntity.class);
        return Result.success(listEmkSuppliers);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取供应商管理信息", notes = "根据ID获取供应商管理信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSupplierEntity task = (EmkSupplierEntity) emkSupplierService.get(EmkSupplierEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取供应商管理信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建供应商管理")
    public ResponseMessage<?> create(@ApiParam(name = "供应商管理对象") @RequestBody EmkSupplierEntity emkSupplier, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSupplierEntity>> failures = validator.validate(emkSupplier, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSupplierService.save(emkSupplier);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("供应商管理信息保存失败");
        }
        return Result.success(emkSupplier);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新供应商管理", notes = "更新供应商管理")
    public ResponseMessage<?> update(@ApiParam(name = "供应商管理对象") @RequestBody EmkSupplierEntity emkSupplier) {
        Set<ConstraintViolation<EmkSupplierEntity>> failures = validator.validate(emkSupplier, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSupplierService.saveOrUpdate(emkSupplier);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新供应商管理信息失败");
        }
        return Result.success("更新供应商管理信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除供应商管理")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSupplierService.deleteEntityById(EmkSupplierEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("供应商管理删除失败");
        }
        return Result.success();
    }
}
