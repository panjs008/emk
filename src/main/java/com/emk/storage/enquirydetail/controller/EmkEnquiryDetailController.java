package com.emk.storage.enquirydetail.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.enquirydetail.service.EmkEnquiryDetailServiceI;
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

@Api(value = "EmkEnquiryDetail", description = "询盘单明细", tags = "emkEnquiryDetailController")
@Controller
@RequestMapping("/emkEnquiryDetailController")
public class EmkEnquiryDetailController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkEnquiryDetailController.class);
    @Autowired
    private EmkEnquiryDetailServiceI emkEnquiryDetailService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/enquirydetail/emkEnquiryDetailList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkEnquiryDetailEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkEnquiryDetail, request.getParameterMap());


        cq.add();
        emkEnquiryDetailService.getDataGridReturn(cq, true);
        Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ROUND(SUM(t.total*t.price),2) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?", emkEnquiryDetail.getEnquiryId());
        if(infoMap != null){
            dataGrid.setFooter("color:合计,total:" + infoMap.get("total").toString() + ",price:" + infoMap.get("sumprice").toString());
        }
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkEnquiryDetail = (EmkEnquiryDetailEntity) systemService.getEntity(EmkEnquiryDetailEntity.class, emkEnquiryDetail.getId());
        message = "询盘单明细删除成功";
        try {
            emkEnquiryDetailService.delete(emkEnquiryDetail);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单明细删除失败";
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
        message = "询盘单明细删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkEnquiryDetailEntity emkEnquiryDetail = (EmkEnquiryDetailEntity) systemService.getEntity(EmkEnquiryDetailEntity.class, id);

                emkEnquiryDetailService.delete(emkEnquiryDetail);
                Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.color=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
                emkEnquiryDetail.setColorTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
                infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.size=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());
                emkEnquiryDetail.setSizeTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
                systemService.executeSql("update emk_enquiry_detail set color_total=? where enquiry_id=? and  color=?", emkEnquiryDetail.getColorTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
                systemService.executeSql("update emk_enquiry_detail set size_total=? where enquiry_id=? and size=?", emkEnquiryDetail.getSizeTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());


                infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ROUND(SUM(t.total*t.price),2) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?", emkEnquiryDetail.getEnquiryId());
                systemService.executeSql("update emk_enquiry set sum_total=?,sum_money=? where id=?", infoMap.get("total"), infoMap.get("sumprice"), emkEnquiryDetail.getEnquiryId());

                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单明细删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "询盘单明细添加成功";
        try {
            emkEnquiryDetailService.save(emkEnquiryDetail);
            Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.color=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
            emkEnquiryDetail.setColorTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
            infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.size=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());
            emkEnquiryDetail.setSizeTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
            systemService.executeSql("update emk_enquiry_detail set color_total=? where enquiry_id=? and  color=?", emkEnquiryDetail.getColorTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
            systemService.executeSql("update emk_enquiry_detail set size_total=? where enquiry_id=? and size=?", emkEnquiryDetail.getSizeTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());

            infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ROUND(SUM(t.total*t.price),2) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?", emkEnquiryDetail.getEnquiryId());
            systemService.executeSql("update emk_enquiry set sum_total=?,sum_money=? where id=?", infoMap.get("total"), infoMap.get("sumprice"), emkEnquiryDetail.getEnquiryId());

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单明细添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "询盘单明细更新成功";
        EmkEnquiryDetailEntity t = (EmkEnquiryDetailEntity) emkEnquiryDetailService.get(EmkEnquiryDetailEntity.class, emkEnquiryDetail.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetail, t);

            Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.color=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
            emkEnquiryDetail.setColorTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
            infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total FROM emk_enquiry_detail t WHERE enquiry_id=? and t.size=?", emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());
            emkEnquiryDetail.setSizeTotal(Integer.valueOf(Integer.parseInt(infoMap.get("total").toString())));
            systemService.executeSql("update emk_enquiry_detail set color_total=? where enquiry_id=? and  color=?", emkEnquiryDetail.getColorTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getColor());
            systemService.executeSql("update emk_enquiry_detail set size_total=? where enquiry_id=? and size=?", emkEnquiryDetail.getSizeTotal(), emkEnquiryDetail.getEnquiryId(), emkEnquiryDetail.getSize());
            emkEnquiryDetailService.saveOrUpdate(t);

            infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ROUND(SUM(t.total*t.price),2) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?", emkEnquiryDetail.getEnquiryId());
            systemService.executeSql("update emk_enquiry set sum_total=?,sum_money=? where id=?", infoMap.get("total"), infoMap.get("sumprice"), emkEnquiryDetail.getEnquiryId());

            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单明细更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiryDetail.getId())) {
            emkEnquiryDetail = (EmkEnquiryDetailEntity) emkEnquiryDetailService.getEntity(EmkEnquiryDetailEntity.class, emkEnquiryDetail.getId());
            req.setAttribute("emkEnquiryDetailPage", emkEnquiryDetail);
        }
        return new ModelAndView("com/emk/storage/enquirydetail/emkEnquiryDetail-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiryDetail.getId())) {
            emkEnquiryDetail = (EmkEnquiryDetailEntity) emkEnquiryDetailService.getEntity(EmkEnquiryDetailEntity.class, emkEnquiryDetail.getId());
            req.setAttribute("emkEnquiryDetailPage", emkEnquiryDetail);
        }
        return new ModelAndView("com/emk/storage/enquirydetail/emkEnquiryDetail-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkEnquiryDetailController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkEnquiryDetailEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkEnquiryDetail, request.getParameterMap());
        List<EmkEnquiryDetailEntity> emkEnquiryDetails = emkEnquiryDetailService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "询盘单明细");
        modelMap.put("entity", EmkEnquiryDetailEntity.class);
        modelMap.put("params", new ExportParams("询盘单明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkEnquiryDetails);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkEnquiryDetailEntity emkEnquiryDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "询盘单明细");
        modelMap.put("entity", EmkEnquiryDetailEntity.class);
        modelMap.put("params", new ExportParams("询盘单明细列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "询盘单明细列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkEnquiryDetailEntity>> list() {
        List<EmkEnquiryDetailEntity> listEmkEnquiryDetails = emkEnquiryDetailService.getList(EmkEnquiryDetailEntity.class);
        return Result.success(listEmkEnquiryDetails);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取询盘单明细信息", notes = "根据ID获取询盘单明细信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkEnquiryDetailEntity task = (EmkEnquiryDetailEntity) emkEnquiryDetailService.get(EmkEnquiryDetailEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取询盘单明细信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建询盘单明细")
    public ResponseMessage<?> create(@ApiParam(name = "询盘单明细对象") @RequestBody EmkEnquiryDetailEntity emkEnquiryDetail, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkEnquiryDetailEntity>> failures = validator.validate(emkEnquiryDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkEnquiryDetailService.save(emkEnquiryDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("询盘单明细信息保存失败");
        }
        return Result.success(emkEnquiryDetail);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新询盘单明细", notes = "更新询盘单明细")
    public ResponseMessage<?> update(@ApiParam(name = "询盘单明细对象") @RequestBody EmkEnquiryDetailEntity emkEnquiryDetail) {
        Set<ConstraintViolation<EmkEnquiryDetailEntity>> failures = validator.validate(emkEnquiryDetail, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkEnquiryDetailService.saveOrUpdate(emkEnquiryDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新询盘单明细信息失败");
        }
        return Result.success("更新询盘单明细信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除询盘单明细")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkEnquiryDetailService.deleteEntityById(EmkEnquiryDetailEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("询盘单明细删除失败");
        }
        return Result.success();
    }
}
