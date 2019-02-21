package com.emk.product.product.controller;

import com.emk.product.hs.entity.EmkProductHsEntity;
import com.emk.product.product.entity.EmkProductEntity;
import com.emk.product.product.service.EmkProductServiceI;

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
@RequestMapping("/emkProductController")
public class EmkProductController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProductController.class);
    @Autowired
    private EmkProductServiceI emkProductService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList");
    }

    @RequestMapping(params = "list1")
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList1");
    }

    @RequestMapping(params = "list2")
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList2");
    }

    @RequestMapping(params = "proSelect")
    public ModelAndView proSelect(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList-select");
    }
    @RequestMapping(params = "proSelect2")
    public ModelAndView proSelect2(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList-select2");
    }@RequestMapping(params = "proSelect3")
    public ModelAndView proSelect3(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/product/emkProductList-select3");
    }
    @RequestMapping(params = "datagrid")
    public void datagrid(EmkProductEntity emkProduct, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProduct, request.getParameterMap());


        cq.add();
        emkProductService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkProductEntity emkProduct, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProduct = (EmkProductEntity) systemService.getEntity(EmkProductEntity.class, emkProduct.getId());
        message = "删除成功";
        try {
            emkProductService.delete(emkProduct);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "删除失败";
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
        message = "删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProductEntity emkProduct = (EmkProductEntity) systemService.getEntity(EmkProductEntity.class, id);


                emkProductService.delete(emkProduct);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkProductEntity emkProduct, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "添加成功";
        try {
            emkProductService.save(emkProduct);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkProductEntity emkProduct, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "更新成功";
        Map param = request.getParameterMap();
        EmkProductEntity t = (EmkProductEntity) emkProductService.get(EmkProductEntity.class, emkProduct.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProduct, t);
            emkProductService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkProductEntity emkProduct, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProduct.getId())) {
            emkProduct = (EmkProductEntity) emkProductService.getEntity(EmkProductEntity.class, emkProduct.getId());
            req.setAttribute("emkProductPage", emkProduct);
        }
        return new ModelAndView("com/emk/product/product/emkProduct-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkProductEntity emkProduct, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProduct.getId())) {
            emkProduct = (EmkProductEntity) emkProductService.getEntity(EmkProductEntity.class, emkProduct.getId());
            req.setAttribute("emkProductPage", emkProduct);

            Map protype = systemService.findOneForJdbc("select * from emk_product_type where id=?", emkProduct.getProType());
            if(protype != null){
                req.setAttribute("proTypeName", protype.get("content"));
                if (emkProduct.getHsId() != null) {
                    EmkProductHsEntity productHsEntity = (EmkProductHsEntity) systemService.getEntity(EmkProductHsEntity.class, emkProduct.getHsId());
                    req.setAttribute("productHsEntity", productHsEntity);
                }
            }
        }
        return new ModelAndView("com/emk/product/product/emkProduct-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProductController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkProductEntity emkProduct, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProduct, request.getParameterMap());
        List<EmkProductEntity> emkProducts = emkProductService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "");
        modelMap.put("entity", EmkProductEntity.class);
        modelMap.put("params", new ExportParams("列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProducts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkProductEntity emkProduct, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "");
        modelMap.put("entity", EmkProductEntity.class);
        modelMap.put("params", new ExportParams("列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<EmkProductEntity> list() {
        List<EmkProductEntity> listEmkProducts = emkProductService.getList(EmkProductEntity.class);
        return listEmkProducts;
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        EmkProductEntity task = (EmkProductEntity) emkProductService.get(EmkProductEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody EmkProductEntity emkProduct, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProductEntity>> failures = validator.validate(emkProduct, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            emkProductService.save(emkProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = emkProduct.getId();
        URI uri = uriBuilder.path("/rest/emkProductController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody EmkProductEntity emkProduct) {
        Set<ConstraintViolation<EmkProductEntity>> failures = validator.validate(emkProduct, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            emkProductService.saveOrUpdate(emkProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        emkProductService.deleteEntityById(EmkProductEntity.class, id);
    }
}
