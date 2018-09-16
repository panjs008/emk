package com.emk.storage.enquiry.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.service.EmkEnquiryServiceI;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
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

@Api(value="EmkEnquiry", description="询盘单", tags={"emkEnquiryController"})
@Controller
@RequestMapping({"/emkEnquiryController"})
public class EmkEnquiryController
  extends BaseController
{
  private static final Logger logger = Logger.getLogger(EmkEnquiryController.class);
  @Autowired
  private EmkEnquiryServiceI emkEnquiryService;
  @Autowired
  private SystemService systemService;
  @Autowired
  private Validator validator;
  
  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/enquiry/emkEnquiryList");
  }
  
  @RequestMapping(params={"photo"})
  public ModelAndView photo(HttpServletRequest request)
  {
    return new ModelAndView("com/emk/storage/enquiry/photo");
  }
  
  @RequestMapping(params={"datagrid"})
  public void datagrid(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkEnquiryEntity.class, dataGrid);
    
    HqlGenerateUtil.installHql(cq, emkEnquiry, request.getParameterMap());
    




    cq.add();
    this.emkEnquiryService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(EmkEnquiryEntity emkEnquiry, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    emkEnquiry = (EmkEnquiryEntity)this.systemService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
    message = "询盘单删除成功";
    try
    {
      this.emkEnquiryService.delete(emkEnquiry);
      this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "询盘单删除失败";
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
    message = "询盘单删除成功";
    try
    {
      for (String id : ids.split(","))
      {
        EmkEnquiryEntity emkEnquiry = (EmkEnquiryEntity)this.systemService.getEntity(EmkEnquiryEntity.class, id);
        

        this.emkEnquiryService.delete(emkEnquiry);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "询盘单删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(EmkEnquiryEntity emkEnquiry, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "询盘单添加成功";
    try
    {
      TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
      Map map = ParameterUtil.getParamMaps(request.getParameterMap());
      Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_enquiry where sys_org_code=?", new Object[] { user.getCurrentDepart().getOrgCode() });
      emkEnquiry.setEnquiryNo("YXDD" + emkEnquiry.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString())) }));
      this.emkEnquiryService.save(emkEnquiry);
      
      EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
      samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
      samplePriceEntity.setBz(map.get("pbz").toString());
      samplePriceEntity.setEnquiryId(emkEnquiry.getId());
      samplePriceEntity.setState(map.get("pstate").toString());
      this.systemService.save(samplePriceEntity);
      
      this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "询盘单添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(EmkEnquiryEntity emkEnquiry, HttpServletRequest request)
  {
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "询盘单更新成功";
    EmkEnquiryEntity t = (EmkEnquiryEntity)this.emkEnquiryService.get(EmkEnquiryEntity.class, emkEnquiry.getId());
    try
    {
      Map map = ParameterUtil.getParamMaps(request.getParameterMap());
      
      MyBeanUtils.copyBeanNotNull2Bean(emkEnquiry, t);
      this.emkEnquiryService.saveOrUpdate(t);
      
      this.systemService.executeSql("delete from emk_sample_price where ENQUIRY_ID=?", new Object[] { emkEnquiry.getId() });
      EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
      samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
      samplePriceEntity.setBz(map.get("pbz").toString());
      samplePriceEntity.setEnquiryId(emkEnquiry.getId());
      samplePriceEntity.setState(map.get("pstate").toString());
      this.systemService.save(samplePriceEntity);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "询盘单更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(EmkEnquiryEntity emkEnquiry, HttpServletRequest req)
  {
    req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
    if (StringUtil.isNotEmpty(emkEnquiry.getId()))
    {
      emkEnquiry = (EmkEnquiryEntity)this.emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
      req.setAttribute("emkEnquiryPage", emkEnquiry);
    }
    return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-add");
  }
  
  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(EmkEnquiryEntity emkEnquiry, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(emkEnquiry.getId()))
    {
      emkEnquiry = (EmkEnquiryEntity)this.emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
      req.setAttribute("emkEnquiryPage", emkEnquiry);
      
      EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity)this.systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkEnquiry.getId());
      req.setAttribute("samplePriceEntity", samplePriceEntity);
    }
    return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-update");
  }
  
  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "emkEnquiryController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }
  
  @RequestMapping(params={"exportXls"})
  public String exportXls(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(EmkEnquiryEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, emkEnquiry, request.getParameterMap());
    List<EmkEnquiryEntity> emkEnquirys = this.emkEnquiryService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "询盘单");
    modelMap.put("entity", EmkEnquiryEntity.class);
    modelMap.put("params", new ExportParams("询盘单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", emkEnquirys);
    return "jeecgExcelView";
  }
  
  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "询盘单");
    modelMap.put("entity", EmkEnquiryEntity.class);
    modelMap.put("params", new ExportParams("询盘单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
    
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="询盘单列表信息", produces="application/json", httpMethod="GET")
  public ResponseMessage<List<EmkEnquiryEntity>> list()
  {
    List<EmkEnquiryEntity> listEmkEnquirys = this.emkEnquiryService.getList(EmkEnquiryEntity.class);
    return Result.success(listEmkEnquirys);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  @ApiOperation(value="根据ID获取询盘单信息", notes="根据ID获取询盘单信息", httpMethod="GET", produces="application/json")
  public ResponseMessage<?> get(@ApiParam(required=true, name="id", value="ID") @PathVariable("id") String id)
  {
    EmkEnquiryEntity task = (EmkEnquiryEntity)this.emkEnquiryService.get(EmkEnquiryEntity.class, id);
    if (task == null) {
      return Result.error("根据ID获取询盘单信息为空");
    }
    return Result.success(task);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation("创建询盘单")
  public ResponseMessage<?> create(@ApiParam(name="询盘单对象") @RequestBody EmkEnquiryEntity emkEnquiry, UriComponentsBuilder uriBuilder)
  {
    Set<ConstraintViolation<EmkEnquiryEntity>> failures = this.validator.validate(emkEnquiry, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkEnquiryService.save(emkEnquiry);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("询盘单信息保存失败");
    }
    return Result.success(emkEnquiry);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  @ResponseBody
  @ApiOperation(value="更新询盘单", notes="更新询盘单")
  public ResponseMessage<?> update(@ApiParam(name="询盘单对象") @RequestBody EmkEnquiryEntity emkEnquiry)
  {
    Set<ConstraintViolation<EmkEnquiryEntity>> failures = this.validator.validate(emkEnquiry, new Class[0]);
    if (!failures.isEmpty()) {
      return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
    }
    try
    {
      this.emkEnquiryService.saveOrUpdate(emkEnquiry);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("更新询盘单信息失败");
    }
    return Result.success("更新询盘单信息成功");
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("删除询盘单")
  public ResponseMessage<?> delete(@ApiParam(name="id", value="ID", required=true) @PathVariable("id") String id)
  {
    logger.info("delete[{}]" + id);
    if (StringUtils.isEmpty(id)) {
      return Result.error("ID不能为空");
    }
    try
    {
      this.emkEnquiryService.deleteEntityById(EmkEnquiryEntity.class, id);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Result.error("询盘单删除失败");
    }
    return Result.success();
  }
}
