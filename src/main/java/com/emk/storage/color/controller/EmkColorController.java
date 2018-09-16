package com.emk.storage.color.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.color.entity.EmkColorEntity;
import com.emk.storage.color.service.EmkColorServiceI;
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

@Api(value="EmkColor", description="色样需求单", tags={"emkColorController"})
@Controller
@RequestMapping({"/emkColorController"})
public class EmkColorController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkColorController.class);
  @Autowired
  private EmkColorServiceI emkColorService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/color/emkColorList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkColorEntity emkColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkColorEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkColor, request.getParameterMap());
    




    cq.add();
    this.emkColorService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkColorEntity emkColor, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkColor = (EmkColorEntity)this.systemService.getEntity(EmkColorEntity.class, emkColor.getId());
    message = "色样需求单删除成功";
    try
    {
      this.emkColorService.delete(emkColor);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "色样需求单删除失败";
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
    message = "色样需求单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkColorEntity emkColor = (EmkColorEntity)this.systemService.getEntity(EmkColorEntity.class, id);
        

        this.emkColorService.delete(emkColor);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "色样需求单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkColorEntity emkColor, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "色样需求单添加成功";
    try
    {
      TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
      Map map = ParameterUtil.getParamMaps(request.getParameterMap());
      Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_color where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
      emkColor.setColorNo("SYXP" + emkColor.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
      this.emkColorService.save(emkColor);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "色样需求单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkColorEntity emkColor, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "色样需求单更新成功";
    EmkColorEntity t = (EmkColorEntity)this.emkColorService.get(EmkColorEntity.class, emkColor.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkColor, t);
      this.emkColorService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "色样需求单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkColorEntity emkColor, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkColor.getId()))
    {
      emkColor = (EmkColorEntity)this.emkColorService.getEntity(EmkColorEntity.class, emkColor.getId());
      req.setAttribute("emkColorPage", emkColor);
    }
    return new ModelAndView("com/emk/storage/color/emkColor-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkColorEntity emkColor, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkColor.getId()))
    {
      emkColor = (EmkColorEntity)this.emkColorService.getEntity(EmkColorEntity.class, emkColor.getId());
      req.setAttribute("emkColorPage", emkColor);
    }
    return new ModelAndView("com/emk/storage/color/emkColor-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkColorController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkColorEntity emkColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkColorEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkColor, request.getParameterMap());
    List<EmkColorEntity> emkColors = this.emkColorService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "色样需求单");
    modelMap.put("entity", EmkColorEntity.class);
    modelMap.put("params", new ExportParams("色样需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkColors);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkColorEntity emkColor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "色样需求单");
    modelMap.put("entity", EmkColorEntity.class);
    modelMap.put("params", new ExportParams("色样需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }

  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="色样需求单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkColorEntity>> list()
  {
    List<EmkColorEntity> listEmkColors = this.emkColorService.getList(EmkColorEntity.class);
    return Result.success(listEmkColors);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取色样需求单信息", notes="根据ID获取色样需求单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkColorEntity task = (EmkColorEntity)this.emkColorService.get(EmkColorEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取色样需求单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建色样需求单")
  public ResponseMessage<?> create(@ApiParam(name="色样需求单对象") @RequestBody EmkColorEntity emkColor, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkColorEntity>> failures = this.validator.validate(emkColor, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkColorService.save(emkColor);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("色样需求单信息保存失败");
    }
    return Result.success(emkColor);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新色样需求单", notes="更新色样需求单")
  public ResponseMessage<?> update(@ApiParam(name="色样需求单对象") @RequestBody EmkColorEntity emkColor)
  {
    Set<ConstraintViolation<EmkColorEntity>> failures = this.validator.validate(emkColor, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkColorService.saveOrUpdate(emkColor);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新色样需求单信息失败");
    }
    return Result.success("更新色样需求单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除色样需求单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkColorService.deleteEntityById(EmkColorEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("色样需求单删除失败");
    }
    return Result.success();
  }
}
