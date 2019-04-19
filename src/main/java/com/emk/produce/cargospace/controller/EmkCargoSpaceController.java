package com.emk.produce.cargospace.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.produce.cargospace.entity.EmkCargoSpaceEntity;
import com.emk.produce.cargospace.service.EmkCargoSpaceServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
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

@Api(value = "EmkCargoSpace", description = "订舱通知单", tags = "emkCargoSpaceController")
@Controller
@RequestMapping("/emkCargoSpaceController")
public class EmkCargoSpaceController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkCargoSpaceController.class);
    @Autowired
    private EmkCargoSpaceServiceI emkCargoSpaceService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/cargospace/emkCargoSpaceList");
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
        return new ModelAndView("com/emk/produce/cargospace/orderMxList");
    }

    @RequestMapping(params = "boxMxList")
    public ModelAndView boxMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);
        if(Utils.notEmpty(map.get("cargoId"))) {
            List<EmkProOrderBoxEntity> emkProOrderBoxEntities = systemService.findHql("from EmkProOrderBoxEntity where orderId=? and boxType=6", map.get("cargoId"));
            request.setAttribute("emkProOrderBoxEntities", emkProOrderBoxEntities);
        }

        return new ModelAndView("com/emk/produce/cargospace/boxMxList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkCargoSpaceEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkCargoSpace, request.getParameterMap());


        cq.add();
        emkCargoSpaceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkCargoSpace = systemService.getEntity(EmkCargoSpaceEntity.class, emkCargoSpace.getId());
        message = "订舱通知单删除成功";
        try {
            emkCargoSpaceService.delete(emkCargoSpace);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "订舱通知单删除失败";
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
        message = "订舱通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkCargoSpaceEntity emkCargoSpace = systemService.getEntity(EmkCargoSpaceEntity.class, id);


                emkCargoSpaceService.delete(emkCargoSpace);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "订舱通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订舱通知单添加成功";
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(CARGO_NO, 3)),0)+1 AS signed) orderNum from emk_cargo_space");
            //Map orderNum = systemService.findOneForJdbc("select count(0)+1 orderNum from emk_cargo_space where sys_org_code=?", user.getCurrentDepart().getOrgCode()});
            emkCargoSpace.setCargoNo("DC" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkCargoSpaceService.save(emkCargoSpace);
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkCargoSpace.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        systemService.save(orderMxEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "订舱通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订舱通知单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkCargoSpaceEntity t = emkCargoSpaceService.get(EmkCargoSpaceEntity.class, map.get("cargoId"));
        try {
            emkCargoSpace.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkCargoSpace, t);
            emkCargoSpaceService.saveOrUpdate(t);
            String dataRows = (String)map.get("orderMxListIDBox");
            //保存单色单码装数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_pro_order_box where order_id = ? and box_type=6",t.getId());

                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkProOrderBoxEntity emkProOrderBoxEntity = new EmkProOrderBoxEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].acolorName00"))) {
                        emkProOrderBoxEntity.setGyzl(map.get("orderMxList["+i+"].gyzl00"));
                        emkProOrderBoxEntity.setProTypeName(map.get("orderMxList["+i+"].proTypeName00"));

                        emkProOrderBoxEntity.setColorName(map.get("orderMxList["+i+"].acolorName00"));
                        emkProOrderBoxEntity.setSize(map.get("orderMxList["+i+"].asizeBox00"));
                        emkProOrderBoxEntity.setPrice(map.get("orderMxList["+i+"].price00"));
                        emkProOrderBoxEntity.setMoney(map.get("orderMxList["+i+"].money00"));

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].ainboxTotal00"))){
                            emkProOrderBoxEntity.setInboxTotal(Integer.parseInt(map.get("orderMxList["+i+"].ainboxTotal00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
                            emkProOrderBoxEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumZsl00"))){
                            emkProOrderBoxEntity.setSumZsl(Integer.parseInt(map.get("orderMxList["+i+"].asumZsl00").toString()));
                        }

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].alongVal00"))){
                            emkProOrderBoxEntity.setLongVal(Double.parseDouble(map.get("orderMxList["+i+"].alongVal00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].awidthVal00"))){
                            emkProOrderBoxEntity.setWidthVal(Double.parseDouble(map.get("orderMxList["+i+"].awidthVal00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].aheightVal00"))){
                            emkProOrderBoxEntity.setHeightVal(Double.parseDouble(map.get("orderMxList["+i+"].aheightVal00").toString()));
                        }

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].aboxWeightMao00"))){
                            emkProOrderBoxEntity.setBoxWeightMao(Double.parseDouble(map.get("orderMxList["+i+"].aboxWeightMao00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].aboxWeightJz00"))){
                            emkProOrderBoxEntity.setBoxWeightJz(Double.parseDouble(map.get("orderMxList["+i+"].aboxWeightJz00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].aboxVolume00"))){
                            emkProOrderBoxEntity.setBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].aboxVolume00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
                            emkProOrderBoxEntity.setSumVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
                            emkProOrderBoxEntity.setSumWeightMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00").toString()));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
                            emkProOrderBoxEntity.setSumWeightJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00").toString()));
                        }
                        emkProOrderBoxEntity.setBoxType("6");
                        emkProOrderBoxEntity.setOrderId(t.getId());

                        systemService.save(emkProOrderBoxEntity);
                    }
                }
            }
            /*systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", t.getId());
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        systemService.save(orderMxEntity);
                    }
                }
            }*/
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "订舱通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkCargoSpace.getId())) {
            emkCargoSpace = emkCargoSpaceService.getEntity(EmkCargoSpaceEntity.class, emkCargoSpace.getId());
            req.setAttribute("emkCargoSpacePage", emkCargoSpace);
        }
        return new ModelAndView("com/emk/produce/cargospace/emkCargoSpace-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest req) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkCargoSpace.getId())) {
            emkCargoSpace = emkCargoSpaceService.getEntity(EmkCargoSpaceEntity.class, emkCargoSpace.getId());
            req.setAttribute("emkCargoSpacePage", emkCargoSpace);
        }
        return new ModelAndView("com/emk/produce/cargospace/emkCargoSpace-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkCargoSpaceController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkCargoSpaceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkCargoSpace, request.getParameterMap());
        List<EmkCargoSpaceEntity> emkCargoSpaces = emkCargoSpaceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "订舱通知单");
        modelMap.put("entity", EmkCargoSpaceEntity.class);
        modelMap.put("params", new ExportParams("订舱通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkCargoSpaces);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkCargoSpaceEntity emkCargoSpace, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "订舱通知单");
        modelMap.put("entity", EmkCargoSpaceEntity.class);
        modelMap.put("params", new ExportParams("订舱通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "订舱通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkCargoSpaceEntity>> list() {
        List<EmkCargoSpaceEntity> listEmkCargoSpaces = emkCargoSpaceService.getList(EmkCargoSpaceEntity.class);
        return Result.success(listEmkCargoSpaces);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取订舱通知单信息", notes = "根据ID获取订舱通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkCargoSpaceEntity task = emkCargoSpaceService.get(EmkCargoSpaceEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取订舱通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建订舱通知单")
    public ResponseMessage<?> create(@ApiParam(name = "订舱通知单对象") @RequestBody EmkCargoSpaceEntity emkCargoSpace, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkCargoSpaceEntity>> failures = validator.validate(emkCargoSpace, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkCargoSpaceService.save(emkCargoSpace);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("订舱通知单信息保存失败");
        }
        return Result.success(emkCargoSpace);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新订舱通知单", notes = "更新订舱通知单")
    public ResponseMessage<?> update(@ApiParam(name = "订舱通知单对象") @RequestBody EmkCargoSpaceEntity emkCargoSpace) {
        Set<ConstraintViolation<EmkCargoSpaceEntity>> failures = validator.validate(emkCargoSpace, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkCargoSpaceService.saveOrUpdate(emkCargoSpace);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新订舱通知单信息失败");
        }
        return Result.success("更新订舱通知单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除订舱通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkCargoSpaceService.deleteEntityById(EmkCargoSpaceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("订舱通知单删除失败");
        }
        return Result.success();
    }
}
