package com.emk.storage.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.material.service.EmkMaterialServiceI;
import com.emk.util.ParameterUtil;
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

@Api(value="EmkMaterial", description="原料面料需求开发单", tags={"emkMaterialController"})
@Controller
@RequestMapping({"/emkMaterialController"})
public class EmkMaterialController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkMaterialController.class);
  @Autowired
  private EmkMaterialServiceI emkMaterialService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/material/emkMaterialList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkMaterialEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkMaterial, request.getParameterMap());
    




    cq.add();
    this.emkMaterialService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkMaterialEntity emkMaterial, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkMaterial = (EmkMaterialEntity)this.systemService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
    message = "原料面料需求开发单删除成功";
    try
    {
      this.emkMaterialService.delete(emkMaterial);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "原料面料需求开发单删除失败";
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
    message = "原料面料需求开发单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkMaterialEntity emkMaterial = (EmkMaterialEntity)this.systemService.getEntity(EmkMaterialEntity.class, id);
        

        this.emkMaterialService.delete(emkMaterial);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "原料面料需求开发单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkMaterialEntity emkMaterial, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "原料面料需求开发单添加成功";
    try
    {
      TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
      Map map = ParameterUtil.getParamMaps(request.getParameterMap());
      Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_material where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
      emkMaterial.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
      this.emkMaterialService.save(emkMaterial);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "原料面料需求开发单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkMaterialEntity emkMaterial, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "原料面料需求开发单更新成功";
    EmkMaterialEntity t = (EmkMaterialEntity)this.emkMaterialService.get(EmkMaterialEntity.class, emkMaterial.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkMaterial, t);
      this.emkMaterialService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "原料面料需求开发单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkMaterialEntity emkMaterial, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkMaterial.getId()))
    {
      emkMaterial = (EmkMaterialEntity)this.emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
      req.setAttribute("emkMaterialPage", emkMaterial);
    }
    return new ModelAndView("com/emk/storage/material/emkMaterial-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkMaterialEntity emkMaterial, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkMaterial.getId()))
    {
      emkMaterial = (EmkMaterialEntity)this.emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
      req.setAttribute("emkMaterialPage", emkMaterial);
    }
    return new ModelAndView("com/emk/storage/material/emkMaterial-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkMaterialController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkMaterialEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkMaterial, request.getParameterMap());
    List<EmkMaterialEntity> emkMaterials = this.emkMaterialService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "原料面料需求开发单");
    modelMap.put("entity", EmkMaterialEntity.class);
    modelMap.put("params", new ExportParams("原料面料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkMaterials);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "原料面料需求开发单");
    modelMap.put("entity", EmkMaterialEntity.class);
    modelMap.put("params", new ExportParams("原料面料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="原料面料需求开发单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkMaterialEntity>> list()
  {
    List<EmkMaterialEntity> listEmkMaterials = this.emkMaterialService.getList(EmkMaterialEntity.class);
    return Result.success(listEmkMaterials);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取原料面料需求开发单信息", notes="根据ID获取原料面料需求开发单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkMaterialEntity task = (EmkMaterialEntity)this.emkMaterialService.get(EmkMaterialEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取原料面料需求开发单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建原料面料需求开发单")
  public ResponseMessage<?> create(@ApiParam(name="原料面料需求开发单对象") @RequestBody EmkMaterialEntity emkMaterial, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkMaterialEntity>> failures = this.validator.validate(emkMaterial, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkMaterialService.save(emkMaterial);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("原料面料需求开发单信息保存失败");
    }
    return Result.success(emkMaterial);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新原料面料需求开发单", notes="更新原料面料需求开发单")
  public ResponseMessage<?> update(@ApiParam(name="原料面料需求开发单对象") @RequestBody EmkMaterialEntity emkMaterial)
  {
    Set<ConstraintViolation<EmkMaterialEntity>> failures = this.validator.validate(emkMaterial, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkMaterialService.saveOrUpdate(emkMaterial);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新原料面料需求开发单信息失败");
    }
    return Result.success("更新原料面料需求开发单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除原料面料需求开发单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkMaterialService.deleteEntityById(EmkMaterialEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("原料面料需求开发单删除失败");
    }
    return Result.success();
  }
}
