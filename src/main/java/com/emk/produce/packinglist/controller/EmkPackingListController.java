package com.emk.produce.packinglist.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.produce.packinglist.entity.EmkPackingListDetailEntity;
import com.emk.produce.packinglist.entity.EmkPackingListEntity;
import com.emk.produce.packinglist.service.EmkPackingListServiceI;
import com.emk.produce.testcost.entity.EmkTestCostDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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

@Api(value = "EmkPackingList", description = "装箱单", tags = "emkPackingListController")
@Controller
@RequestMapping("/emkPackingListController")
public class EmkPackingListController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkPackingListController.class);
    @Autowired
    private EmkPackingListServiceI emkPackingListService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/packinglist/emkPackingListList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/packinglist/orderMxList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);

        if (Utils.notEmpty(map.get("pactId"))) {
            List<EmkPackingListDetailEntity> emkPackingListDetailEntities = systemService.findHql("from EmkPackingListDetailEntity where pactId=?", map.get("pactId"));
            request.setAttribute("emkPackingListDetailEntities", emkPackingListDetailEntities);

            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("pactId"));
            request.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/produce/packinglist/detailMxList");
    }

    @RequestMapping(params = "datagrid")
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
        emkPackingListService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkPackingListEntity emkPackingList, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPackingList = systemService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
        message = "装箱单删除成功";
        try {
            emkPackingListService.delete(emkPackingList);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单删除失败";
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
        message = "装箱单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkPackingListEntity emkPackingList = systemService.getEntity(EmkPackingListEntity.class, id);


                emkPackingListService.delete(emkPackingList);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkPackingListEntity emkPackingList, EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "装箱单添加成功";
        try {
            emkPackingListService.save(emkPackingList);
            emkSize.setFormId(emkPackingList.getId());
            systemService.save(emkSize);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                EmkPackingListDetailEntity emkPackingListDetailEntity = null;
                EmkSizeTotalEntity emkSizeTotalEntity = null;
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    emkPackingListDetailEntity = new EmkPackingListDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
                        emkPackingListDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
                        emkPackingListDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
                        emkPackingListDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));

                        emkPackingListDetailEntity.setColor(map.get("orderMxList["+i+"].acolorName00"));
                        emkPackingListDetailEntity.setSize(map.get("orderMxList["+i+"].asizeBox00"));
                        emkPackingListDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

                        emkPackingListDetailEntity.setChangdu(map.get("orderMxList["+i+"].alongVal00"));
                        emkPackingListDetailEntity.setKuandu(map.get("orderMxList["+i+"].awidthVal00"));
                        emkPackingListDetailEntity.setGaodu(map.get("orderMxList["+i+"].aheightVal00"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
                            emkPackingListDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
                        }
                        emkPackingListDetailEntity.setOneBoxMz(map.get("orderMxList["+i+"].aboxWeightMao00"));
                        emkPackingListDetailEntity.setOneBoxJz(map.get("orderMxList["+i+"].aboxWeightJz00"));
                        emkPackingListDetailEntity.setOneBoxVolume(map.get("orderMxList["+i+"].aboxVolume00"));

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
                            emkPackingListDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
                            emkPackingListDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
                            emkPackingListDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
                        }
                        emkPackingListDetailEntity.setPactId(emkPackingList.getId());
                        systemService.save(emkPackingListDetailEntity);

                        emkSizeTotalEntity = new EmkSizeTotalEntity();
                        emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
                        emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
                        emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
                        emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
                        emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
                        emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
                        emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
                        emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
                        emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
                        emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
                        emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
                        emkSizeTotalEntity.setpId(emkPackingListDetailEntity.getId());
                        systemService.save(emkSizeTotalEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkPackingListEntity emkPackingList, EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "装箱单更新成功";
        Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkPackingListEntity t = emkPackingListService.get(EmkPackingListEntity.class, map.get("packId"));
        EmkSizeEntity t2 = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());

        try {
            emkPackingList.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkPackingList, t);
            emkPackingListService.saveOrUpdate(t);

            emkSize.setId(null);
            if(Utils.notEmpty(t2)){
                MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
                systemService.saveOrUpdate(t2);
            }else{
                t2 = new EmkSizeEntity();
                MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
                t2.setFormId(t.getId());
                systemService.save(t2);
            }

            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_packing_list_detail where pact_id = ?",t.getId());
                EmkPackingListDetailEntity emkPackingListDetailEntity = null;
                EmkSizeTotalEntity emkSizeTotalEntity = null;
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    emkPackingListDetailEntity = new EmkPackingListDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
                        emkPackingListDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
                        emkPackingListDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
                        emkPackingListDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));

                        emkPackingListDetailEntity.setColor(map.get("orderMxList["+i+"].acolorName00"));
                        emkPackingListDetailEntity.setSize(map.get("orderMxList["+i+"].asizeBox00"));
                        emkPackingListDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

                        emkPackingListDetailEntity.setChangdu(map.get("orderMxList["+i+"].alongVal00"));
                        emkPackingListDetailEntity.setKuandu(map.get("orderMxList["+i+"].awidthVal00"));
                        emkPackingListDetailEntity.setGaodu(map.get("orderMxList["+i+"].aheightVal00"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
                            emkPackingListDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
                        }
                        emkPackingListDetailEntity.setOneBoxMz(map.get("orderMxList["+i+"].aboxWeightMao00"));
                        emkPackingListDetailEntity.setOneBoxJz(map.get("orderMxList["+i+"].aboxWeightJz00"));
                        emkPackingListDetailEntity.setOneBoxVolume(map.get("orderMxList["+i+"].aboxVolume00"));

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
                            emkPackingListDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
                            emkPackingListDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
                            emkPackingListDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
                        }
                        emkPackingListDetailEntity.setPactId(t.getId());
                        systemService.save(emkPackingListDetailEntity);

                        emkSizeTotalEntity = new EmkSizeTotalEntity();
                        emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
                        emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
                        emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
                        emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
                        emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
                        emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
                        emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
                        emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
                        emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
                        emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
                        emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
                        emkSizeTotalEntity.setpId(emkPackingListDetailEntity.getId());
                        systemService.save(emkSizeTotalEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "装箱单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkPackingListEntity emkPackingList, HttpServletRequest req) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkPackingList.getId())) {
            emkPackingList = emkPackingListService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
            req.setAttribute("emkPackingListPage", emkPackingList);
        }
        return new ModelAndView("com/emk/produce/packinglist/emkPackingList-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkPackingListEntity emkPackingList, HttpServletRequest req) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkPackingList.getId())) {
            emkPackingList = emkPackingListService.getEntity(EmkPackingListEntity.class, emkPackingList.getId());
            req.setAttribute("emkPackingListPage", emkPackingList);
        }
        return new ModelAndView("com/emk/produce/packinglist/emkPackingList-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkPackingListController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkPackingListEntity emkPackingList, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkPackingListEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkPackingList, request.getParameterMap());
        List<EmkPackingListEntity> emkPackingLists = emkPackingListService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "装箱单");
        modelMap.put("entity", EmkPackingListEntity.class);
        modelMap.put("params", new ExportParams("装箱单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkPackingLists);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
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
        List<EmkPackingListEntity> listEmkPackingLists = emkPackingListService.getList(EmkPackingListEntity.class);
        return Result.success(listEmkPackingLists);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取装箱单信息", notes = "根据ID获取装箱单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPackingListEntity task = emkPackingListService.get(EmkPackingListEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取装箱单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建装箱单")
    public ResponseMessage<?> create(@ApiParam(name = "装箱单对象") @RequestBody EmkPackingListEntity emkPackingList, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPackingListEntity>> failures = validator.validate(emkPackingList, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPackingListService.save(emkPackingList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("装箱单信息保存失败");
        }
        return Result.success(emkPackingList);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新装箱单", notes = "更新装箱单")
    public ResponseMessage<?> update(@ApiParam(name = "装箱单对象") @RequestBody EmkPackingListEntity emkPackingList) {
        Set<ConstraintViolation<EmkPackingListEntity>> failures = validator.validate(emkPackingList, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPackingListService.saveOrUpdate(emkPackingList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新装箱单信息失败");
        }
        return Result.success("更新装箱单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除装箱单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkPackingListService.deleteEntityById(EmkPackingListEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("装箱单删除失败");
        }
        return Result.success();
    }
}
