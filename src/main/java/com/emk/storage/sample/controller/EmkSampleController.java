package com.emk.storage.sample.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sample.service.EmkSampleServiceI;
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

@Api(value="EmkSample", description="样品单", tags={"emkSampleController"})
@Controller
@RequestMapping({"/emkSampleController"})
public class EmkSampleController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkSampleController.class);
  @Autowired
  private EmkSampleServiceI emkSampleService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/sample/emkSampleList");
  }
  
  @RequestMapping(params={"list0"})
  public ModelAndView list0(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/sample/emkSampleList0");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());
    




    cq.add();
    this.emkSampleService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkSampleEntity emkSample, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkSample = (EmkSampleEntity)this.systemService.getEntity(EmkSampleEntity.class, emkSample.getId());
    message = "样品单删除成功";
    try
    {
      if (!emkSample.getState().equals("0"))
      {
        message = "样品单单已经提交处理，无法删除";
        j.setMsg(message);
        j.setSuccess(false);
        return j;
      }
      this.emkSampleService.delete(emkSample);
      this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[] { emkSample.getId() });
      this.systemService.executeSql("delete from emk_sample_total where sample_id=?", new Object[] { emkSample.getId() });
      
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "样品单删除失败";
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
    message = "样品单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkSampleEntity emkSample = (EmkSampleEntity)this.systemService.getEntity(EmkSampleEntity.class, id);
        if (!emkSample.getState().equals("0"))
        {
          message = "样品单单已经提交处理，无法删除";
          j.setMsg(message);
          j.setSuccess(false);
          return j;
        }
        this.emkSampleService.delete(emkSample);
        this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[] { emkSample.getId() });
        this.systemService.executeSql("delete from emk_sample_gx where sample_id=?", new Object[] { emkSample.getId() });
        this.systemService.executeSql("delete from emk_sample_ran where sample_id=?", new Object[] { emkSample.getId() });
        this.systemService.executeSql("delete from emk_sample_yin where sample_id=?", new Object[] { emkSample.getId() });
        this.systemService.executeSql("delete from emk_sample_total where sample_id=?", new Object[] { emkSample.getId() });
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "样品单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkSampleEntity emkSample, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "样品单添加成功";
    try
    {
      emkSample.setState("0");
      emkSample.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
      TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
      Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_sample where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
      emkSample.setSampleNum("YPTZD" + DateUtils.format(new Date(), "yyMMdd") + "A" + String.format("%02d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
      this.emkSampleService.save(emkSample);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "样品单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkSampleEntity emkSample, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "样品单更新成功";
    EmkSampleEntity t = (EmkSampleEntity)this.emkSampleService.get(EmkSampleEntity.class, emkSample.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkSample, t);
      this.emkSampleService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "样品单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkSampleEntity emkSample, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkSample.getId()))
    {
      emkSample = (EmkSampleEntity)this.emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
      req.setAttribute("emkSamplePage", emkSample);
    }
    return new ModelAndView("com/emk/storage/sample/emkSample-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkSampleEntity emkSample, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkSample.getId()))
    {
      emkSample = (EmkSampleEntity)this.emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
      req.setAttribute("emkSamplePage", emkSample);
    }
    return new ModelAndView("com/emk/storage/sample/emkSample-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkSampleController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());
    List<EmkSampleEntity> emkSamples = this.emkSampleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "样品单");
    modelMap.put("entity", EmkSampleEntity.class);
    modelMap.put("params", new ExportParams("样品单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkSamples);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "样品单");
    modelMap.put("entity", EmkSampleEntity.class);
    modelMap.put("params", new ExportParams("样品单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }

  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="样品单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkSampleEntity>> list()
  {
    List<EmkSampleEntity> listEmkSamples = this.emkSampleService.getList(EmkSampleEntity.class);
    return Result.success(listEmkSamples);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取样品单信息", notes="根据ID获取样品单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkSampleEntity task = (EmkSampleEntity)this.emkSampleService.get(EmkSampleEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取样品单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建样品单")
  public ResponseMessage<?> create(@ApiParam(name="样品单对象") @RequestBody EmkSampleEntity emkSample, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkSampleEntity>> failures = this.validator.validate(emkSample, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkSampleService.save(emkSample);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("样品单信息保存失败");
    }
    return Result.success(emkSample);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新样品单", notes="更新样品单")
  public ResponseMessage<?> update(@ApiParam(name="样品单对象") @RequestBody EmkSampleEntity emkSample)
  {
    Set<ConstraintViolation<EmkSampleEntity>> failures = this.validator.validate(emkSample, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkSampleService.saveOrUpdate(emkSample);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新样品单信息失败");
    }
    return Result.success("更新样品单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除样品单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkSampleService.deleteEntityById(EmkSampleEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("样品单删除失败");
    }
    return Result.success();
  }
}
