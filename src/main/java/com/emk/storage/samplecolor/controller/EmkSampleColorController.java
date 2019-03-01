package com.emk.storage.samplecolor.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.color.entity.EmkColorDetailEntity;
import com.emk.storage.samplecolor.entity.EmkSampleColorEntity;
import com.emk.storage.samplecolor.service.EmkSampleColorServiceI;
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

@Api(value = "EmkSampleColor", description = "色样通知单", tags = "emkSampleColorController")
@Controller
@RequestMapping("/emkSampleColorController")
public class EmkSampleColorController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleColorController.class);
    @Autowired
    private EmkSampleColorServiceI emkSampleColorService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/samplecolor/emkSampleColorList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleColorEntity emkSampleColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleColorEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkSampleColor, request.getParameterMap());


        cq.add();
        this.emkSampleColorService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleColorEntity emkSampleColor, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleColor = (EmkSampleColorEntity) this.systemService.getEntity(EmkSampleColorEntity.class, emkSampleColor.getId());
        message = "色样通知单删除成功";
        try {
            this.emkSampleColorService.delete(emkSampleColor);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "色样通知单删除失败";
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
        message = "色样通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleColorEntity emkSampleColor = (EmkSampleColorEntity) this.systemService.getEntity(EmkSampleColorEntity.class, id);


                this.emkSampleColorService.delete(emkSampleColor);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "色样通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleColorEntity emkSampleColor, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "色样通知单添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(sytzdbh, 2)),0)+1 AS signed) orderNum from emk_sample_color");
            emkSampleColor.setSytzdbh("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", new Object[]{Integer.parseInt(orderNum.get("orderNum").toString())}));
            this.emkSampleColorService.save(emkSampleColor);
            //保存明细数据
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                EmkColorDetailEntity detailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].colorZnName"))){
                        detailEntity = new EmkColorDetailEntity();
                        detailEntity.setColorId(emkSampleColor.getId());
                        detailEntity.setColorZnName(map.get("orderMxList["+i+"].colorZnName").toString());
                        detailEntity.setSortDesc(String.valueOf(i+1));
                        detailEntity.setSeNum(map.get("orderMxList["+i+"].seNum").toString());
                        detailEntity.setVersion(map.get("orderMxList["+i+"].version").toString());
                        detailEntity.setColorBrand(map.get("orderMxList["+i+"].colorBrand").toString());
                        detailEntity.setColorTotal(map.get("orderMxList["+i+"].colorTotal").toString());
                        systemService.save(detailEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "色样通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleColorEntity emkSampleColor, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "色样通知单更新成功";
        EmkSampleColorEntity t = (EmkSampleColorEntity) this.emkSampleColorService.get(EmkSampleColorEntity.class, emkSampleColor.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleColor, t);
            this.emkSampleColorService.saveOrUpdate(t);
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());

            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_color_detail where color_id=?",t.getId());
                int rows = Integer.parseInt(dataRows);
                EmkColorDetailEntity detailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].colorZnName"))){
                        detailEntity = new EmkColorDetailEntity();
                        detailEntity.setColorId(t.getId());
                        detailEntity.setColorZnName(map.get("orderMxList["+i+"].colorZnName").toString());
                        detailEntity.setSortDesc(String.valueOf(i+1));
                        detailEntity.setSeNum(map.get("orderMxList["+i+"].seNum").toString());
                        detailEntity.setVersion(map.get("orderMxList["+i+"].version").toString());
                        detailEntity.setColorBrand(map.get("orderMxList["+i+"].colorBrand").toString());
                        detailEntity.setColorTotal(map.get("orderMxList["+i+"].colorTotal").toString());
                        systemService.save(detailEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "色样通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleColorEntity emkSampleColor, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSampleColor.getId())) {
            emkSampleColor = (EmkSampleColorEntity) this.emkSampleColorService.getEntity(EmkSampleColorEntity.class, emkSampleColor.getId());
            req.setAttribute("emkSampleColorPage", emkSampleColor);
        }
        return new ModelAndView("com/emk/storage/samplecolor/emkSampleColor-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleColorEntity emkSampleColor, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleColor.getId())) {
            emkSampleColor = (EmkSampleColorEntity) this.emkSampleColorService.getEntity(EmkSampleColorEntity.class, emkSampleColor.getId());
            req.setAttribute("emkSampleColorPage", emkSampleColor);
        }
        return new ModelAndView("com/emk/storage/samplecolor/emkSampleColor-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleColorController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleColorEntity emkSampleColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleColorEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleColor, request.getParameterMap());
        List<EmkSampleColorEntity> emkSampleColors = this.emkSampleColorService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "色样通知单");
        modelMap.put("entity", EmkSampleColorEntity.class);
        modelMap.put("params", new ExportParams("色样通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleColors);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleColorEntity emkSampleColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "色样通知单");
        modelMap.put("entity", EmkSampleColorEntity.class);
        modelMap.put("params", new ExportParams("色样通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "色样通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleColorEntity>> list() {
        List<EmkSampleColorEntity> listEmkSampleColors = this.emkSampleColorService.getList(EmkSampleColorEntity.class);
        return Result.success(listEmkSampleColors);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取色样通知单信息", notes = "根据ID获取色样通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleColorEntity task = (EmkSampleColorEntity) this.emkSampleColorService.get(EmkSampleColorEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取色样通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建色样通知单")
    public ResponseMessage<?> create(@ApiParam(name = "色样通知单对象") @RequestBody EmkSampleColorEntity emkSampleColor, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleColorEntity>> failures = this.validator.validate(emkSampleColor, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleColorService.save(emkSampleColor);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("色样通知单信息保存失败");
        }
        return Result.success(emkSampleColor);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新色样通知单", notes = "更新色样通知单")
    public ResponseMessage<?> update(@ApiParam(name = "色样通知单对象") @RequestBody EmkSampleColorEntity emkSampleColor) {
        Set<ConstraintViolation<EmkSampleColorEntity>> failures = this.validator.validate(emkSampleColor, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkSampleColorService.saveOrUpdate(emkSampleColor);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新色样通知单信息失败");
        }
        return Result.success("更新色样通知单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除色样通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkSampleColorService.deleteEntityById(EmkSampleColorEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("色样通知单删除失败");
        }
        return Result.success();
    }
}
