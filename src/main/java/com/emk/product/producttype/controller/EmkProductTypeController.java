package com.emk.product.producttype.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.emk.product.producttype.entity.EmkProductTypeEntity;
import com.emk.product.producttype.service.EmkProductTypeServiceI;
import com.emk.util.ParameterUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
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
@RequestMapping({"/emkProductTypeController"})
public class EmkProductTypeController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProductTypeController.class);
    @Autowired
    private EmkProductTypeServiceI emkProductTypeService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/product/producttype/emkProductTypeList");
    }

    @RequestMapping(params = {"treegrid"})
    @ResponseBody
    public Object treegrid(EmkProductTypeEntity productTypeEntity, HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductTypeEntity.class);
        if ("yes".equals(request.getParameter("isSearch"))) {
            treegrid.setId(null);
            productTypeEntity.setId(null);
        }
        productTypeEntity.setOrgId(ResourceUtil.getSessionUser().getCurrentDepart().getOrgCode());
        if (null != productTypeEntity.getContent()) {
            HqlGenerateUtil.installHql(cq, productTypeEntity);
        }
        if (treegrid.getId() != null) {
            cq.eq("productTypeEntity.id", Integer.valueOf(Integer.parseInt(treegrid.getId())));
        }
        if (treegrid.getId() == null) {
            cq.isNull("productTypeEntity");
        }
        cq.eq("isDel", Integer.valueOf(0));
        cq.add();
        List<TreeGrid> officeList = null;
        officeList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        if ((officeList.size() == 0) && (productTypeEntity.getContent() != null)) {
            cq = new CriteriaQuery(EmkProductTypeEntity.class);
            EmkProductTypeEntity model = new EmkProductTypeEntity();
            productTypeEntity.setProductTypeEntity(model);
            HqlGenerateUtil.installHql(cq, productTypeEntity);
            officeList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        }
        List<TreeGrid> treeGrids = new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setTextField("content");
        treeGridModel.setParentText("productTypeEntity_content");
        treeGridModel.setSrc("orgName");
        treeGridModel.setParentId("productTypeEntity_id");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("productTypeEntities");
        Map<String, Object> fieldMap = new HashMap();

        fieldMap.put("proCode", "proCode");
        fieldMap.put("remark", "remark");


        treeGridModel.setFieldMap(fieldMap);
        treeGrids = this.systemService.treegrid(officeList, treeGridModel);

        JSONArray jsonArray = new JSONArray();
        for (TreeGrid treeGrid : treeGrids) {
            jsonArray.add(JSON.parse(treeGrid.toJson()));
        }
        return jsonArray;
    }

    @RequestMapping(params = {"setPOfficeInfo"})
    @ResponseBody
    public List<ComboTree> setPOfficeInfo(HttpServletRequest request, ComboTree comboTree) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        CriteriaQuery cq = new CriteriaQuery(EmkProductTypeEntity.class);
        if ((null != request.getParameter("selfId")) && (!request.getParameter("selfId").isEmpty())) {
            cq.notEq("id", request.getParameter("selfId"));
        }
        if (comboTree.getId() != null) {
            cq.eq("productTypeEntity.id", Integer.valueOf(Integer.parseInt(comboTree.getId())));
        }
        if (comboTree.getId() == null) {
            cq.isNull("productTypeEntity");
        }
        cq.eq("isDel", Integer.valueOf(0));
        cq.add();
        List<EmkProductTypeEntity> officeInfoList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));

        List<ComboTree> comboTrees = new ArrayList();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "content", "productTypeEntities");

        comboTrees = this.systemService.ComboTree(officeInfoList, comboTreeModel, null, false);

        MutiLangUtil.setMutiTree(comboTrees);
        return comboTrees;
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProductTypeEntity emkProductType, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductTypeEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProductType, request.getParameterMap());


        cq.add();
        this.emkProductTypeService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProductTypeEntity emkProductType, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProductType = (EmkProductTypeEntity) this.systemService.getEntity(EmkProductTypeEntity.class, emkProductType.getId());
        message = "产品类别表删除成功";
        try {
            this.emkProductTypeService.delete(emkProductType);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品类别表删除失败";
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
        message = "产品类别表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProductTypeEntity emkProductType = (EmkProductTypeEntity) this.systemService.getEntity(EmkProductTypeEntity.class, Integer.valueOf(Integer.parseInt(id)));


                this.emkProductTypeService.delete(emkProductType);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品类别表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProductTypeEntity productTypeEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产品类别表添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if (productTypeEntity.getOfficeLevel().intValue() == 0) {
                productTypeEntity.setProductTypeEntity(null);
            }
            this.emkProductTypeService.save(productTypeEntity);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品类别表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProductTypeEntity productTypeEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "产品类别表更新成功";
        EmkProductTypeEntity t = (EmkProductTypeEntity) this.emkProductTypeService.get(EmkProductTypeEntity.class, productTypeEntity.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(productTypeEntity, t);
            this.emkProductTypeService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "产品类别表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProductTypeEntity emkProductType, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductType.getId())) {
            emkProductType = (EmkProductTypeEntity) this.emkProductTypeService.getEntity(EmkProductTypeEntity.class, emkProductType.getId());
            req.setAttribute("emkProductTypePage", emkProductType);
        }
        return new ModelAndView("com/emk/product/producttype/emkProductType-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProductTypeEntity emkProductType, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProductType.getId())) {
            emkProductType = (EmkProductTypeEntity) this.emkProductTypeService.getEntity(EmkProductTypeEntity.class, emkProductType.getId());
            req.setAttribute("emkProductTypePage", emkProductType);
        }
        return new ModelAndView("com/emk/product/producttype/emkProductType-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProductTypeController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProductTypeEntity emkProductType, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProductTypeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProductType, request.getParameterMap());
        List<EmkProductTypeEntity> emkProductTypes = this.emkProductTypeService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "产品类别表");
        modelMap.put("entity", EmkProductTypeEntity.class);
        modelMap.put("params", new ExportParams("产品类别表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProductTypes);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProductTypeEntity emkProductType, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "产品类别表");
        modelMap.put("entity", EmkProductTypeEntity.class);
        modelMap.put("params", new ExportParams("产品类别表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<EmkProductTypeEntity> list() {
        List<EmkProductTypeEntity> listEmkProductTypes = this.emkProductTypeService.getList(EmkProductTypeEntity.class);
        return listEmkProductTypes;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        EmkProductTypeEntity task = (EmkProductTypeEntity) this.emkProductTypeService.get(EmkProductTypeEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody EmkProductTypeEntity emkProductType, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProductTypeEntity>> failures = this.validator.validate(emkProductType, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.emkProductTypeService.save(emkProductType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = emkProductType.getId().toString();
        URI uri = uriBuilder.path("/rest/emkProductTypeController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody EmkProductTypeEntity emkProductType) {
        Set<ConstraintViolation<EmkProductTypeEntity>> failures = this.validator.validate(emkProductType, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.emkProductTypeService.saveOrUpdate(emkProductType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.emkProductTypeService.deleteEntityById(EmkProductTypeEntity.class, id);
    }
}
