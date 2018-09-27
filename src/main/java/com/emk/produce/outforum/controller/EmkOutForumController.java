package com.emk.produce.outforum.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.outforum.entity.EmkOutForumEntity;
import com.emk.produce.outforum.service.EmkOutForumServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
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

@Api(value = "EmkOutForum", description = "出货通知单", tags = {"emkOutForumController"})
@Controller
@RequestMapping({"/emkOutForumController"})
public class EmkOutForumController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkOutForumController.class);
    @Autowired
    private EmkOutForumServiceI emkOutForumService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/outforum/emkOutForumList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/outforum/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkOutForumEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkOutForum, request.getParameterMap());


        cq.add();
        this.emkOutForumService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkOutForum = (EmkOutForumEntity) this.systemService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
        message = "出货通知单删除成功";
        try {
            this.emkOutForumService.delete(emkOutForum);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单删除失败";
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
        message = "出货通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkOutForumEntity emkOutForum = (EmkOutForumEntity) this.systemService.getEntity(EmkOutForumEntity.class, id);


                this.emkOutForumService.delete(emkOutForum);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "出货通知单添加成功";
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_out_forum where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
            emkOutForum.setOutForumNo("CH" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
            this.emkOutForumService.save(emkOutForum);
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkOutForum.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "出货通知单更新成功";
        EmkOutForumEntity t = (EmkOutForumEntity) this.emkOutForumService.get(EmkOutForumEntity.class, emkOutForum.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkOutForum, t);
            this.emkOutForumService.saveOrUpdate(t);

            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{t.getId()});
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkOutForumEntity emkOutForum, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkOutForum.getId())) {
            emkOutForum = (EmkOutForumEntity) this.emkOutForumService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
            req.setAttribute("emkOutForumPage", emkOutForum);
        }
        return new ModelAndView("com/emk/produce/outforum/emkOutForum-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkOutForumEntity emkOutForum, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkOutForum.getId())) {
            emkOutForum = (EmkOutForumEntity) this.emkOutForumService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
            req.setAttribute("emkOutForumPage", emkOutForum);
        }
        return new ModelAndView("com/emk/produce/outforum/emkOutForum-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkOutForumController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkOutForumEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkOutForum, request.getParameterMap());
        List<EmkOutForumEntity> emkOutForums = this.emkOutForumService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "出货通知单");
        modelMap.put("entity", EmkOutForumEntity.class);
        modelMap.put("params", new ExportParams("出货通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkOutForums);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "出货通知单");
        modelMap.put("entity", EmkOutForumEntity.class);
        modelMap.put("params", new ExportParams("出货通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "出货通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkOutForumEntity>> list() {
        List<EmkOutForumEntity> listEmkOutForums = this.emkOutForumService.getList(EmkOutForumEntity.class);
        return Result.success(listEmkOutForums);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取出货通知单信息", notes = "根据ID获取出货通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkOutForumEntity task = (EmkOutForumEntity) this.emkOutForumService.get(EmkOutForumEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取出货通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建出货通知单")
    public ResponseMessage<?> create(@ApiParam(name = "出货通知单对象") @RequestBody EmkOutForumEntity emkOutForum, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkOutForumEntity>> failures = this.validator.validate(emkOutForum, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkOutForumService.save(emkOutForum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("出货通知单信息保存失败");
        }
        return Result.success(emkOutForum);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新出货通知单", notes = "更新出货通知单")
    public ResponseMessage<?> update(@ApiParam(name = "出货通知单对象") @RequestBody EmkOutForumEntity emkOutForum) {
        Set<ConstraintViolation<EmkOutForumEntity>> failures = this.validator.validate(emkOutForum, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkOutForumService.saveOrUpdate(emkOutForum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新出货通知单信息失败");
        }
        return Result.success("更新出货通知单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除出货通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkOutForumService.deleteEntityById(EmkOutForumEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("出货通知单删除失败");
        }
        return Result.success();
    }
}
