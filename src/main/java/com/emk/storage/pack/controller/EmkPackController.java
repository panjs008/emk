package com.emk.storage.pack.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.pack.entity.EmkPackEntity;
import com.emk.storage.pack.service.EmkPackServiceI;
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

@Api(value="EmkPack", description="包装辅料需求开发单", tags={"emkPackController"})
@Controller
@RequestMapping({"/emkPackController"})
public class EmkPackController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkPackController.class);
  @Autowired
  private EmkPackServiceI emkPackService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/pack/emkPackList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkPackEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkPack, request.getParameterMap());
    




    cq.add();
    this.emkPackService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkPackEntity emkPack, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkPack = (EmkPackEntity)this.systemService.getEntity(EmkPackEntity.class, emkPack.getId());
    message = "包装辅料需求开发单删除成功";
    try
    {
      this.emkPackService.delete(emkPack);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "包装辅料需求开发单删除失败";
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
    message = "包装辅料需求开发单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkPackEntity emkPack = (EmkPackEntity)this.systemService.getEntity(EmkPackEntity.class, id);
        

        this.emkPackService.delete(emkPack);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "包装辅料需求开发单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkPackEntity emkPack, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "包装辅料需求开发单添加成功";
    try
    {
      emkPack.setParkNo(emkPack.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
      this.emkPackService.save(emkPack);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "包装辅料需求开发单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkPackEntity emkPack, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "包装辅料需求开发单更新成功";
    EmkPackEntity t = (EmkPackEntity)this.emkPackService.get(EmkPackEntity.class, emkPack.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkPack, t);
      this.emkPackService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "包装辅料需求开发单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkPackEntity emkPack, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkPack.getId()))
    {
      emkPack = (EmkPackEntity)this.emkPackService.getEntity(EmkPackEntity.class, emkPack.getId());
      req.setAttribute("emkPackPage", emkPack);
    }
    return new ModelAndView("com/emk/storage/pack/emkPack-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkPackEntity emkPack, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkPack.getId()))
    {
      emkPack = (EmkPackEntity)this.emkPackService.getEntity(EmkPackEntity.class, emkPack.getId());
      req.setAttribute("emkPackPage", emkPack);
    }
    return new ModelAndView("com/emk/storage/pack/emkPack-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkPackController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkPackEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkPack, request.getParameterMap());
    List<EmkPackEntity> emkPacks = this.emkPackService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "包装辅料需求开发单");
    modelMap.put("entity", EmkPackEntity.class);
    modelMap.put("params", new ExportParams("包装辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkPacks);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "包装辅料需求开发单");
    modelMap.put("entity", EmkPackEntity.class);
    modelMap.put("params", new ExportParams("包装辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="包装辅料需求开发单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkPackEntity>> list()
  {
    List<EmkPackEntity> listEmkPacks = this.emkPackService.getList(EmkPackEntity.class);
    return Result.success(listEmkPacks);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取包装辅料需求开发单信息", notes="根据ID获取包装辅料需求开发单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkPackEntity task = (EmkPackEntity)this.emkPackService.get(EmkPackEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取包装辅料需求开发单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建包装辅料需求开发单")
  public ResponseMessage<?> create(@ApiParam(name="包装辅料需求开发单对象") @RequestBody EmkPackEntity emkPack, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkPackEntity>> failures = this.validator.validate(emkPack, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkPackService.save(emkPack);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("包装辅料需求开发单信息保存失败");
    }
    return Result.success(emkPack);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新包装辅料需求开发单", notes="更新包装辅料需求开发单")
  public ResponseMessage<?> update(@ApiParam(name="包装辅料需求开发单对象") @RequestBody EmkPackEntity emkPack)
  {
    Set<ConstraintViolation<EmkPackEntity>> failures = this.validator.validate(emkPack, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkPackService.saveOrUpdate(emkPack);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新包装辅料需求开发单信息失败");
    }
    return Result.success("更新包装辅料需求开发单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除包装辅料需求开发单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkPackService.deleteEntityById(EmkPackEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("包装辅料需求开发单删除失败");
    }
    return Result.success();
  }
}
