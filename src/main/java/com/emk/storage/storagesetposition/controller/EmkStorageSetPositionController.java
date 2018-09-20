package com.emk.storage.storagesetposition.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.storagesetposition.entity.EmkStorageSetPositionEntity;
import com.emk.storage.storagesetposition.service.EmkStorageSetPositionServiceI;
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

@Api(value = "EmkStorageSetPosition", description = "库位表", tags = {"emkStorageSetPositionController"})
@Controller
@RequestMapping({"/emkStorageSetPositionController"})
public class EmkStorageSetPositionController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkStorageSetPositionController.class);
    @Autowired
    private EmkStorageSetPositionServiceI emkStorageSetPositionService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/storagesetposition/emkStorageSetPositionList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageSetPositionEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkStorageSetPosition, request.getParameterMap());


        cq.add();
        this.emkStorageSetPositionService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkStorageSetPosition = (EmkStorageSetPositionEntity) this.systemService.getEntity(EmkStorageSetPositionEntity.class, emkStorageSetPosition.getId());
        message = "库位表删除成功";
        try {
            this.emkStorageSetPositionService.delete(emkStorageSetPosition);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库位表删除失败";
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
        message = "库位表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkStorageSetPositionEntity emkStorageSetPosition = (EmkStorageSetPositionEntity) this.systemService.getEntity(EmkStorageSetPositionEntity.class, id);


                this.emkStorageSetPositionService.delete(emkStorageSetPosition);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "库位表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "库位表添加成功";
        try {
            this.emkStorageSetPositionService.save(emkStorageSetPosition);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库位表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "库位表更新成功";
        EmkStorageSetPositionEntity t = (EmkStorageSetPositionEntity) this.emkStorageSetPositionService.get(EmkStorageSetPositionEntity.class, emkStorageSetPosition.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkStorageSetPosition, t);
            this.emkStorageSetPositionService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库位表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"getPosition"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public AjaxJson getPosition(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        Map param = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            List<Map<String, Object>> codeList = this.systemService.findForJdbc("SELECT id,position_name FROM emk_storage_set_position WHERE storage_id=?", new Object[]{param.get("storage_id")});
            String dataItems = "";
            try {
                for (Map map : codeList) {
                    dataItems = dataItems + map.get("id") + "," + map.get("position_name") + ";";
                }
                if (dataItems.indexOf(";") > 0) {
                    dataItems = dataItems.substring(0, dataItems.length() - 1);
                } else {
                    j.setSuccess(false);
                }
                j.setObj(dataItems);
            } catch (Exception e) {
                logger.error(ExceptionUtil.getExceptionMessage(e));
            }
        } catch (Exception e) {
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorageSetPosition.getId())) {
            emkStorageSetPosition = (EmkStorageSetPositionEntity) this.emkStorageSetPositionService.getEntity(EmkStorageSetPositionEntity.class, emkStorageSetPosition.getId());
            req.setAttribute("emkStorageSetPositionPage", emkStorageSetPosition);
        }
        return new ModelAndView("com/emk/storage/storagesetposition/emkStorageSetPosition-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorageSetPosition.getId())) {
            emkStorageSetPosition = (EmkStorageSetPositionEntity) this.emkStorageSetPositionService.getEntity(EmkStorageSetPositionEntity.class, emkStorageSetPosition.getId());
            req.setAttribute("emkStorageSetPositionPage", emkStorageSetPosition);
        }
        return new ModelAndView("com/emk/storage/storagesetposition/emkStorageSetPosition-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkStorageSetPositionController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageSetPositionEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkStorageSetPosition, request.getParameterMap());
        List<EmkStorageSetPositionEntity> emkStorageSetPositions = this.emkStorageSetPositionService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "库位表");
        modelMap.put("entity", EmkStorageSetPositionEntity.class);
        modelMap.put("params", new ExportParams("库位表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkStorageSetPositions);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkStorageSetPositionEntity emkStorageSetPosition, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "库位表");
        modelMap.put("entity", EmkStorageSetPositionEntity.class);
        modelMap.put("params", new ExportParams("库位表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "库位表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkStorageSetPositionEntity>> list() {
        List<EmkStorageSetPositionEntity> listEmkStorageSetPositions = this.emkStorageSetPositionService.getList(EmkStorageSetPositionEntity.class);
        return Result.success(listEmkStorageSetPositions);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取库位表信息", notes = "根据ID获取库位表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkStorageSetPositionEntity task = (EmkStorageSetPositionEntity) this.emkStorageSetPositionService.get(EmkStorageSetPositionEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取库位表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建库位表")
    public ResponseMessage<?> create(@ApiParam(name = "库位表对象") @RequestBody EmkStorageSetPositionEntity emkStorageSetPosition, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkStorageSetPositionEntity>> failures = this.validator.validate(emkStorageSetPosition, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkStorageSetPositionService.save(emkStorageSetPosition);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("库位表信息保存失败");
        }
        return Result.success(emkStorageSetPosition);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新库位表", notes = "更新库位表")
    public ResponseMessage<?> update(@ApiParam(name = "库位表对象") @RequestBody EmkStorageSetPositionEntity emkStorageSetPosition) {
        Set<ConstraintViolation<EmkStorageSetPositionEntity>> failures = this.validator.validate(emkStorageSetPosition, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkStorageSetPositionService.saveOrUpdate(emkStorageSetPosition);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新库位表信息失败");
        }
        return Result.success("更新库位表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除库位表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkStorageSetPositionService.deleteEntityById(EmkStorageSetPositionEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("库位表删除失败");
        }
        return Result.success();
    }
}
