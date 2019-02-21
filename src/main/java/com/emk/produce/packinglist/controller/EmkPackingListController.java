package com.emk.produce.packinglist.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.packinglist.entity.EmkPackingListEntity;
import com.emk.produce.packinglist.service.EmkPackingListServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
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

@Api(value = "EmkPackingList", description = "装箱单", tags = {"emkPackingListController"})
@Controller
@RequestMapping({"/emkPackingListController"})
public class EmkPackingListController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkPackingListController.class);
    @Autowired
    private EmkPackingListServiceI emkPackingListService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/packinglist/emkPackingListList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/packinglist/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkPackingListEntity emkPackingList, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkPackingListEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkPackingList, request.getParameterMap());


        cq.add();
        this.emkPackingListService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkPackingListEntity emkPackingList, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPackingList = (EmkPackingListEntity) this.systemService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
        message = "装箱单删除成功";
        try {
            this.emkPackingListService.delete(emkPackingList);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单删除失败";
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
        message = "装箱单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkPackingListEntity emkPackingList = (EmkPackingListEntity) this.systemService.getEntity(EmkPackingListEntity.class, id);


                this.emkPackingListService.delete(emkPackingList);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkPackingListEntity emkPackingList, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "装箱单添加成功";
        try {
            this.emkPackingListService.save(emkPackingList);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkPackingList.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkPackingListEntity emkPackingList, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "装箱单更新成功";
        EmkPackingListEntity t = (EmkPackingListEntity) this.emkPackingListService.get(EmkPackingListEntity.class, emkPackingList.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkPackingList, t);
            this.emkPackingListService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkPackingListEntity emkPackingList, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkPackingList.getId())) {
            emkPackingList = (EmkPackingListEntity) this.emkPackingListService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
            req.setAttribute("emkPackingListPage", emkPackingList);
        }
        return new ModelAndView("com/emk/produce/packinglist/emkPackingList-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkPackingListEntity emkPackingList, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkPackingList.getId())) {
            emkPackingList = (EmkPackingListEntity) this.emkPackingListService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
            req.setAttribute("emkPackingListPage", emkPackingList);
        }
        return new ModelAndView("com/emk/produce/packinglist/emkPackingList-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkPackingListController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkPackingListEntity emkPackingList, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkPackingListEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkPackingList, request.getParameterMap());
        List<EmkPackingListEntity> emkPackingLists = this.emkPackingListService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "装箱单");
        modelMap.put("entity", EmkPackingListEntity.class);
        modelMap.put("params", new ExportParams("装箱单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkPackingLists);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkPackingListEntity emkPackingList, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "装箱单");
        modelMap.put("entity", EmkPackingListEntity.class);
        modelMap.put("params", new ExportParams("装箱单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "装箱单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkPackingListEntity>> list() {
        List<EmkPackingListEntity> listEmkPackingLists = this.emkPackingListService.getList(EmkPackingListEntity.class);
        return Result.success(listEmkPackingLists);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取装箱单信息", notes = "根据ID获取装箱单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPackingListEntity task = (EmkPackingListEntity) this.emkPackingListService.get(EmkPackingListEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取装箱单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建装箱单")
    public ResponseMessage<?> create(@ApiParam(name = "装箱单对象") @RequestBody EmkPackingListEntity emkPackingList, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPackingListEntity>> failures = this.validator.validate(emkPackingList, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPackingListService.save(emkPackingList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("装箱单信息保存失败");
        }
        return Result.success(emkPackingList);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新装箱单", notes = "更新装箱单")
    public ResponseMessage<?> update(@ApiParam(name = "装箱单对象") @RequestBody EmkPackingListEntity emkPackingList) {
        Set<ConstraintViolation<EmkPackingListEntity>> failures = this.validator.validate(emkPackingList, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPackingListService.saveOrUpdate(emkPackingList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新装箱单信息失败");
        }
        return Result.success("更新装箱单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除装箱单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkPackingListService.deleteEntityById(EmkPackingListEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("装箱单删除失败");
        }
        return Result.success();
    }
}
