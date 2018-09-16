package com.emk.produce.reception.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.reception.entity.EmkReceptionEntity;
import com.emk.produce.reception.service.EmkReceptionServiceI;
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

@Api(value="EmkReception", description="业务接待单", tags={"emkReceptionController"})
@Controller
@RequestMapping({"/emkReceptionController"})
public class EmkReceptionController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkReceptionController.class);
  @Autowired
  private EmkReceptionServiceI emkReceptionService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/produce/reception/emkReceptionList");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkReceptionEntity emkReception, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkReceptionEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkReception, request.getParameterMap());
    




    cq.add();
    this.emkReceptionService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkReceptionEntity emkReception, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkReception = (EmkReceptionEntity)this.systemService.getEntity(EmkReceptionEntity.class, emkReception.getId());
    message = "业务接待单删除成功";
    try
    {
      this.emkReceptionService.delete(emkReception);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "业务接待单删除失败";
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
    message = "业务接待单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkReceptionEntity emkReception = (EmkReceptionEntity)this.systemService.getEntity(EmkReceptionEntity.class, id);
        

        this.emkReceptionService.delete(emkReception);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "业务接待单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkReceptionEntity emkReception, HttpServletRequest req)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "业务接待单添加成功";
    try
    {
      TSUser user = (TSUser)req.getSession().getAttribute("LOCAL_CLINET_USER");
      Map map = ParameterUtil.getParamMaps(req.getParameterMap());
      emkReception.setOutUserIds(map.get("userName").toString());
      emkReception.setOutUserNames(map.get("realName").toString());
      Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_reception where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
      emkReception.setRecevieNum("JD" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
      this.emkReceptionService.save(emkReception);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "业务接待单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkReceptionEntity emkReception, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "业务接待单更新成功";
    EmkReceptionEntity t = (EmkReceptionEntity)this.emkReceptionService.get(EmkReceptionEntity.class, emkReception.getId());
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(emkReception, t);
      this.emkReceptionService.saveOrUpdate(t);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "业务接待单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkReceptionEntity emkReception, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkReception.getId()))
    {
      emkReception = (EmkReceptionEntity)this.emkReceptionService.getEntity(EmkReceptionEntity.class, emkReception.getId());
      req.setAttribute("emkReceptionPage", emkReception);
    }
    return new ModelAndView("com/emk/produce/reception/emkReception-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkReceptionEntity emkReception, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkReception.getId()))
    {
      emkReception = (EmkReceptionEntity)this.emkReceptionService.getEntity(EmkReceptionEntity.class, emkReception.getId());
      req.setAttribute("emkReceptionPage", emkReception);
    }
    return new ModelAndView("com/emk/produce/reception/emkReception-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkReceptionController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkReceptionEntity emkReception, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkReceptionEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkReception, request.getParameterMap());
    List<EmkReceptionEntity> emkReceptions = this.emkReceptionService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "业务接待单");
    modelMap.put("entity", EmkReceptionEntity.class);
    modelMap.put("params", new ExportParams("业务接待单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkReceptions);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkReceptionEntity emkReception, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "业务接待单");
    modelMap.put("entity", EmkReceptionEntity.class);
    modelMap.put("params", new ExportParams("业务接待单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="业务接待单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkReceptionEntity>> list()
  {
    List<EmkReceptionEntity> listEmkReceptions = this.emkReceptionService.getList(EmkReceptionEntity.class);
    return Result.success(listEmkReceptions);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取业务接待单信息", notes="根据ID获取业务接待单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkReceptionEntity task = (EmkReceptionEntity)this.emkReceptionService.get(EmkReceptionEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取业务接待单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建业务接待单")
  public ResponseMessage<?> create(@ApiParam(name="业务接待单对象") @RequestBody EmkReceptionEntity emkReception, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkReceptionEntity>> failures = this.validator.validate(emkReception, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkReceptionService.save(emkReception);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("业务接待单信息保存失败");
    }
    return Result.success(emkReception);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新业务接待单", notes="更新业务接待单")
  public ResponseMessage<?> update(@ApiParam(name="业务接待单对象") @RequestBody EmkReceptionEntity emkReception)
  {
    Set<ConstraintViolation<EmkReceptionEntity>> failures = this.validator.validate(emkReception, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkReceptionService.saveOrUpdate(emkReception);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新业务接待单信息失败");
    }
    return Result.success("更新业务接待单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除业务接待单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkReceptionService.deleteEntityById(EmkReceptionEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("业务接待单删除失败");
    }
    return Result.success();
  }
}
