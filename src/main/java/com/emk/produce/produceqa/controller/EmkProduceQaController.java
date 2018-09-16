package com.emk.produce.produceqa.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.produceqa.entity.EmkProduceQaEntity;
import com.emk.produce.produceqa.service.EmkProduceQaServiceI;
import com.emk.util.ParameterUtil;
import com.emk.util.WebFileUtils;
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

@Api(value="EmkProduceQa", description="生产问题管理单", tags={"emkProduceQaController"})
@Controller
@RequestMapping({"/emkProduceQaController"})
public class EmkProduceQaController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkProduceQaController.class);
  @Autowired
  private EmkProduceQaServiceI emkProduceQaService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/produce/produceqa/emkProduceQaList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkProduceQaEntity emkProduceQa, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkProduceQaEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkProduceQa, request.getParameterMap());
    




    cq.add();
    this.emkProduceQaService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkProduceQaEntity emkProduceQa, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkProduceQa = (EmkProduceQaEntity)this.systemService.getEntity(EmkProduceQaEntity.class, emkProduceQa.getId());
    message = "生产问题管理单删除成功";
    try
    {
      this.emkProduceQaService.delete(emkProduceQa);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "生产问题管理单删除失败";
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
    message = "生产问题管理单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkProduceQaEntity emkProduceQa = (EmkProduceQaEntity)this.systemService.getEntity(EmkProduceQaEntity.class, id);
        

        this.emkProduceQaService.delete(emkProduceQa);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "生产问题管理单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkProduceQaEntity emkProduceQa, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "生产问题管理单添加成功";
    try
    {
      this.emkProduceQaService.save(emkProduceQa);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "生产问题管理单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkProduceQaEntity emkProduceQa, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "生产问题管理单更新成功";
    EmkProduceQaEntity t = (EmkProduceQaEntity)this.emkProduceQaService.get(EmkProduceQaEntity.class, emkProduceQa.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkProduceQa, t);
      this.emkProduceQaService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "生产问题管理单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkProduceQaEntity emkProduceQa, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    TSUser user = (TSUser)req.getSession().getAttribute("LOCAL_CLINET_USER");
    Map map = ParameterUtil.getParamMaps(req.getParameterMap());
    Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_produce_qa where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
    req.setAttribute("qaNo", "QA" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
    if (StringUtil.isNotEmpty(emkProduceQa.getId()))
    {
      emkProduceQa = (EmkProduceQaEntity)this.emkProduceQaService.getEntity(EmkProduceQaEntity.class, emkProduceQa.getId());
      req.setAttribute("emkProduceQaPage", emkProduceQa);
    }
    return new ModelAndView("com/emk/produce/produceqa/emkProduceQa-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkProduceQaEntity emkProduceQa, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkProduceQa.getId()))
    {
      emkProduceQa = (EmkProduceQaEntity)this.emkProduceQaService.getEntity(EmkProduceQaEntity.class, emkProduceQa.getId());
      req.setAttribute("emkProduceQaPage", emkProduceQa);
    }
    return new ModelAndView("com/emk/produce/produceqa/emkProduceQa-update");
  }
  
  @RequestMapping(params={"dowaLoadFile"})
  public ModelAndView dowaLoadFile(EmkProduceQaEntity emkProduceQa, HttpServletRequest request, HttpServletResponse response)
  {
    String message = null;
    message = "文件下载成功";
    try
    {
      String filePath = "";String file = "";
      Map map = ParameterUtil.getParamMaps(request.getParameterMap());
      file = emkProduceQa.getScanUrl();
      if ((file != null) && (!file.isEmpty()))
      {
        filePath = request.getRealPath("/") + file;
        WebFileUtils.downLoad(filePath, response, false);
      }
      else
      {
        emkProduceQa = (EmkProduceQaEntity)this.emkProduceQaService.getEntity(EmkProduceQaEntity.class, emkProduceQa.getId());
        request.setAttribute("emkProduceQaPage", emkProduceQa);
        return new ModelAndView("com/emk/produce/produceqa/emkProduceQa-update");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "文件下载失败";
      throw new BusinessException(e.getMessage());
    }
    return null;
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkProduceQaController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkProduceQaEntity emkProduceQa, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkProduceQaEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkProduceQa, request.getParameterMap());
    List<EmkProduceQaEntity> emkProduceQas = this.emkProduceQaService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "生产问题管理单");
    modelMap.put("entity", EmkProduceQaEntity.class);
    modelMap.put("params", new ExportParams("生产问题管理单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkProduceQas);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkProduceQaEntity emkProduceQa, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "生产问题管理单");
    modelMap.put("entity", EmkProduceQaEntity.class);
    modelMap.put("params", new ExportParams("生产问题管理单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="生产问题管理单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkProduceQaEntity>> list()
  {
    List<EmkProduceQaEntity> listEmkProduceQas = this.emkProduceQaService.getList(EmkProduceQaEntity.class);
    return Result.success(listEmkProduceQas);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取生产问题管理单信息", notes="根据ID获取生产问题管理单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkProduceQaEntity task = (EmkProduceQaEntity)this.emkProduceQaService.get(EmkProduceQaEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取生产问题管理单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建生产问题管理单")
  public ResponseMessage<?> create(@ApiParam(name="生产问题管理单对象") @RequestBody EmkProduceQaEntity emkProduceQa, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkProduceQaEntity>> failures = this.validator.validate(emkProduceQa, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkProduceQaService.save(emkProduceQa);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("生产问题管理单信息保存失败");
    }
    return Result.success(emkProduceQa);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新生产问题管理单", notes="更新生产问题管理单")
  public ResponseMessage<?> update(@ApiParam(name="生产问题管理单对象") @RequestBody EmkProduceQaEntity emkProduceQa)
  {
    Set<ConstraintViolation<EmkProduceQaEntity>> failures = this.validator.validate(emkProduceQa, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkProduceQaService.saveOrUpdate(emkProduceQa);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新生产问题管理单信息失败");
    }
    return Result.success("更新生产问题管理单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除生产问题管理单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkProduceQaService.deleteEntityById(EmkProduceQaEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("生产问题管理单删除失败");
    }
    return Result.success();
  }
}
