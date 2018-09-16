package com.emk.storage.storagelog.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.storage.storagelog.service.EmkStorageLogServiceI;
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

@Api(value="EmkStorageLog", description="库存日记表", tags={"emkStorageLogController"})
@Controller
@RequestMapping({"/emkStorageLogController"})
public class EmkStorageLogController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkStorageLogController.class);
  @Autowired
  private EmkStorageLogServiceI emkStorageLogService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/storagelog/emkStorageLogList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkStorageLogEntity emkStorageLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkStorageLogEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkStorageLog, request.getParameterMap());
    




    cq.add();
    this.emkStorageLogService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkStorageLogEntity emkStorageLog, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkStorageLog = (EmkStorageLogEntity)this.systemService.getEntity(EmkStorageLogEntity.class, emkStorageLog.getId());
    message = "库存日记表删除成功";
    try
    {
      this.emkStorageLogService.delete(emkStorageLog);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存日记表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doBatchDel"})
  @ResponseBody
  public AjaxJson doBatchDel(String ids, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "库存日记表删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkStorageLogEntity emkStorageLog = (EmkStorageLogEntity)this.systemService.getEntity(EmkStorageLogEntity.class, id);
        

        this.emkStorageLogService.delete(emkStorageLog);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存日记表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkStorageLogEntity emkStorageLog, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "库存日记表添加成功";
    try
    {
      this.emkStorageLogService.save(emkStorageLog);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存日记表添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkStorageLogEntity emkStorageLog, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "库存日记表更新成功";
    EmkStorageLogEntity t = (EmkStorageLogEntity)this.emkStorageLogService.get(EmkStorageLogEntity.class, emkStorageLog.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkStorageLog, t);
      this.emkStorageLogService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存日记表更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkStorageLogEntity emkStorageLog, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkStorageLog.getId()))
    {
      emkStorageLog = (EmkStorageLogEntity)this.emkStorageLogService.getEntity(EmkStorageLogEntity.class, emkStorageLog.getId());
      req.setAttribute("emkStorageLogPage", emkStorageLog);
    }
    return new ModelAndView("com/emk/storage/storagelog/emkStorageLog-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkStorageLogEntity emkStorageLog, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkStorageLog.getId()))
    {
      emkStorageLog = (EmkStorageLogEntity)this.emkStorageLogService.getEntity(EmkStorageLogEntity.class, emkStorageLog.getId());
      req.setAttribute("emkStorageLogPage", emkStorageLog);
    }
    return new ModelAndView("com/emk/storage/storagelog/emkStorageLog-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkStorageLogController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkStorageLogEntity emkStorageLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkStorageLogEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkStorageLog, request.getParameterMap());
    List<EmkStorageLogEntity> emkStorageLogs = this.emkStorageLogService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "库存日记表");
    modelMap.put("entity", EmkStorageLogEntity.class);
    modelMap.put("params", new ExportParams("库存日记表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkStorageLogs);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkStorageLogEntity emkStorageLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "库存日记表");
    modelMap.put("entity", EmkStorageLogEntity.class);
    modelMap.put("params", new ExportParams("库存日记表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="库存日记表列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkStorageLogEntity>> list()
  {
    List<EmkStorageLogEntity> listEmkStorageLogs = this.emkStorageLogService.getList(EmkStorageLogEntity.class);
    return Result.success(listEmkStorageLogs);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取库存日记表信息", notes="根据ID获取库存日记表信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkStorageLogEntity task = (EmkStorageLogEntity)this.emkStorageLogService.get(EmkStorageLogEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取库存日记表信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建库存日记表")
  public ResponseMessage<?> create(@ApiParam(name="库存日记表对象") @RequestBody EmkStorageLogEntity emkStorageLog, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkStorageLogEntity>> failures = this.validator.validate(emkStorageLog, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkStorageLogService.save(emkStorageLog);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("库存日记表信息保存失败");
    }
    return Result.success(emkStorageLog);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新库存日记表", notes="更新库存日记表")
  public ResponseMessage<?> update(@ApiParam(name="库存日记表对象") @RequestBody EmkStorageLogEntity emkStorageLog)
  {
    Set<ConstraintViolation<EmkStorageLogEntity>> failures = this.validator.validate(emkStorageLog, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkStorageLogService.saveOrUpdate(emkStorageLog);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新库存日记表信息失败");
    }
    return Result.success("更新库存日记表信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除库存日记表")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkStorageLogService.deleteEntityById(EmkStorageLogEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("库存日记表删除失败");
    }
    return Result.success();
  }
}
