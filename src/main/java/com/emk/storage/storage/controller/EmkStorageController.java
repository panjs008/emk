package com.emk.storage.storage.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storage.service.EmkStorageServiceI;
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
import javax.servlet.http.HttpSession;
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

@Api(value="EmkStorage", description="库存表", tags={"emkStorageController"})
@Controller
@RequestMapping({"/emkStorageController"})
public class EmkStorageController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkStorageController.class);
  @Autowired
  private EmkStorageServiceI emkStorageService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/storage/emkStorageList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkStorageEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkStorage, request.getParameterMap());
    TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
    





    cq.add();
    this.emkStorageService.getDataGridReturn(cq, true);
    String sql = "SELECT ROUND(SUM(total),2) zongji FROM emk_storage WHERE sys_org_code='" + user.getCurrentDepart().getOrgCode().substring(0, 3) + "'";
    if ((emkStorage.getStorageName() != null) && (!emkStorage.getStorageName().isEmpty())) {
      sql = sql + " and storage_name like '%" + emkStorage.getStorageName() + "%'";
    }
    if ((emkStorage.getProNum() != null) && (!emkStorage.getProNum().isEmpty())) {
      sql = sql + " and pro_num like '%" + emkStorage.getProNum() + "%'";
    }
    if ((emkStorage.getProZnName() != null) && (!emkStorage.getProZnName().isEmpty())) {
      sql = sql + " and pro_zn_name like '%" + emkStorage.getProZnName() + "%'";
    }
    if ((emkStorage.getBrand() != null) && (!emkStorage.getBrand().isEmpty())) {
      sql = sql + " and brand like '%" + emkStorage.getBrand() + "%'";
    }
    String zongji = String.valueOf(this.systemService.findOneForJdbc(sql, null).get("zongji"));
    dataGrid.setFooter("brand:合计,total:" + zongji);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkStorageEntity emkStorage, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkStorage = (EmkStorageEntity)this.systemService.getEntity(EmkStorageEntity.class, emkStorage.getId());
    message = "库存表删除成功";
    try
    {
      this.emkStorageService.delete(emkStorage);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存表删除失败";
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
    message = "库存表删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkStorageEntity emkStorage = (EmkStorageEntity)this.systemService.getEntity(EmkStorageEntity.class, id);
        

        this.emkStorageService.delete(emkStorage);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkStorageEntity emkStorage, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "库存表添加成功";
    try
    {
      this.emkStorageService.save(emkStorage);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存表添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkStorageEntity emkStorage, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "库存表更新成功";
    EmkStorageEntity t = (EmkStorageEntity)this.emkStorageService.get(EmkStorageEntity.class, emkStorage.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkStorage, t);
      this.emkStorageService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "库存表更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkStorageEntity emkStorage, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkStorage.getId()))
    {
      emkStorage = (EmkStorageEntity)this.emkStorageService.getEntity(EmkStorageEntity.class, emkStorage.getId());
      req.setAttribute("emkStoragePage", emkStorage);
    }
    return new ModelAndView("com/emk/storage/storage/emkStorage-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkStorageEntity emkStorage, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkStorage.getId()))
    {
      emkStorage = (EmkStorageEntity)this.emkStorageService.getEntity(EmkStorageEntity.class, emkStorage.getId());
      req.setAttribute("emkStoragePage", emkStorage);
    }
    return new ModelAndView("com/emk/storage/storage/emkStorage-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkStorageController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkStorageEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkStorage, request.getParameterMap());
    List<EmkStorageEntity> emkStorages = this.emkStorageService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "库存表");
    modelMap.put("entity", EmkStorageEntity.class);
    modelMap.put("params", new ExportParams("库存表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkStorages);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "库存表");
    modelMap.put("entity", EmkStorageEntity.class);
    modelMap.put("params", new ExportParams("库存表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="库存表列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkStorageEntity>> list()
  {
    List<EmkStorageEntity> listEmkStorages = this.emkStorageService.getList(EmkStorageEntity.class);
    return Result.success(listEmkStorages);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取库存表信息", notes="根据ID获取库存表信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkStorageEntity task = (EmkStorageEntity)this.emkStorageService.get(EmkStorageEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取库存表信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建库存表")
  public ResponseMessage<?> create(@ApiParam(name="库存表对象") @RequestBody EmkStorageEntity emkStorage, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkStorageEntity>> failures = this.validator.validate(emkStorage, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkStorageService.save(emkStorage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("库存表信息保存失败");
    }
    return Result.success(emkStorage);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新库存表", notes="更新库存表")
  public ResponseMessage<?> update(@ApiParam(name="库存表对象") @RequestBody EmkStorageEntity emkStorage)
  {
    Set<ConstraintViolation<EmkStorageEntity>> failures = this.validator.validate(emkStorage, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkStorageService.saveOrUpdate(emkStorage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新库存表信息失败");
    }
    return Result.success("更新库存表信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除库存表")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkStorageService.deleteEntityById(EmkStorageEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("库存表删除失败");
    }
    return Result.success();
  }
}
