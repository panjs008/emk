package com.service.custom.controller;

import com.emk.storage.supplier.entity.EmkSupplierEntity;
import com.emk.util.ChineseToEnglish;
import com.emk.util.ParameterUtil;
import com.service.custom.entity.YmkCustomEntity;
import com.service.custom.service.YmkCustomServiceI;
import com.service.customcontact.entity.YmkCustomContactEntity;
import com.service.customcontact.entity.YmkCustomContactUserEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
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
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping({"/ymkCustomController"})
public class YmkCustomController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomController.class);
    @Autowired
    private YmkCustomServiceI ymkCustomService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private UserService userService;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/custom/ymkCustomList");
    }

    @RequestMapping(params = {"select"})
    public ModelAndView select(HttpServletRequest request) {
        return new ModelAndView("com/service/custom/ymkCustomList-select");
    }

    @RequestMapping(params = {"list1"})
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/service/custom/ymkCustomList1");
    }

    @RequestMapping({"/customDetail"})
    public Object customDetail(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());


        return "forward:/metro/custom.html";
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomEntity ymkCustom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustom, request.getParameterMap());
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            if (!user.getUserName().equals("admin")) {
                YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");
                cq.eq("id", custom.getId());
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.ymkCustomService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustom = (YmkCustomEntity) this.systemService.getEntity(YmkCustomEntity.class, ymkCustom.getId());
        message = "客户表删除成功";
        try {
            this.ymkCustomService.delete(ymkCustom);
            this.systemService.executeSql("delete from ymk_custom_contact where CUSTOM_ID=?", new Object[]{ymkCustom.getId()});
            this.systemService.executeSql("delete from ymk_custom_alert where CUSTOM_ID=?", new Object[]{ymkCustom.getId()});
            this.systemService.executeSql("delete from ymk_custom_bank where CUSTOM_ID=?", new Object[]{ymkCustom.getId()});
            this.systemService.executeSql("delete from ymk_custom_trace where CUS_ID=?", new Object[]{ymkCustom.getId()});
            this.systemService.executeSql("delete from ymk_custom_from where CUSTOM_ID=?", new Object[]{ymkCustom.getId()});

            YmkCustomContactUserEntity customContactUserEntity = (YmkCustomContactUserEntity) this.systemService.findUniqueByProperty(YmkCustomContactUserEntity.class, "contactId", ymkCustom.getId());
            this.systemService.executeSql("delete from ymk_custom_contact_user where contact_id=?", new Object[]{ymkCustom.getId()});
            this.systemService.executeSql("delete from t_s_user_org where user_id=?", new Object[]{customContactUserEntity.getUserId()});
            this.systemService.executeSql("delete from t_s_role_user where userid=?", new Object[]{customContactUserEntity.getUserId()});
            this.systemService.executeSql("delete from t_s_base_user where id=?", new Object[]{customContactUserEntity.getUserId()});
            this.systemService.executeSql("delete from t_s_user where id=?", new Object[]{customContactUserEntity.getUserId()});

            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"check"})
    @ResponseBody
    public AjaxJson check(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        YmkCustomEntity t = (YmkCustomEntity) this.systemService.getEntity(YmkCustomEntity.class, ymkCustom.getId());
        message = "服务商审核成功";
        try {
            t.setStatus(ymkCustom.getStatus());
            this.ymkCustomService.saveOrUpdate(t);

            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "服务商审核失败";
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
        message = "客户表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomEntity ymkCustom = (YmkCustomEntity) this.systemService.getEntity(YmkCustomEntity.class, id);


                this.ymkCustomService.delete(ymkCustom);
                Map customUser = this.systemService.findOneForJdbc("select * from ymk_custom_contact_user where contact_id=?", new Object[]{ymkCustom.getId()});
                if (customUser != null) {
                    this.systemService.executeSql("delete from ymk_custom_contact_user where contact_id =?", new Object[]{ymkCustom.getId()});
                    this.systemService.executeSql("delete from t_s_role_user where userid =?", new Object[]{customUser.get("user_id")});
                    this.systemService.executeSql("delete from t_s_user_org where user_id =?", new Object[]{customUser.get("user_id")});
                    this.systemService.executeSql("delete from t_s_user where id =?", new Object[]{customUser.get("user_id")});
                    this.systemService.executeSql("delete from t_s_base_user where ID =?", new Object[]{customUser.get("user_id")});
                }
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    private synchronized String getMaxLocalCode(String parentCode) {
        if (oConvertUtils.isEmpty(parentCode)) {
            parentCode = "";
        }
        int localCodeLength = parentCode.length() + 3;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT org_code FROM t_s_depart");
        if (ResourceUtil.getJdbcUrl().indexOf("sqlserver") != -1) {
            sb.append(" where LEN(org_code) = ").append(localCodeLength);
        } else {
            sb.append(" where LENGTH(org_code) = ").append(localCodeLength);
        }
        if (oConvertUtils.isNotEmpty(parentCode)) {
            sb.append(" and  org_code like '").append(parentCode).append("%'");
        } else {
            sb.append(" and LEFT(org_code,1)='A'");
        }
        sb.append(" ORDER BY org_code DESC");
        List<Map<String, Object>> objMapList = this.systemService.findForJdbc(sb.toString(), 1, 1);
        String returnCode = null;
        if ((objMapList != null) && (objMapList.size() > 0)) {
            returnCode = (String) ((Map) objMapList.get(0)).get("org_code");
        }
        return returnCode;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户表添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            ymkCustom.setStatus("0");


            ymkCustom.setCusCode(ChineseToEnglish.getPinYinHeadChar(ymkCustom.getCusName()));
            TSUser tsUser = (TSUser) this.systemService.findUniqueByProperty(TSUser.class, "userName", ymkCustom.getBusinesserName());
            TSUserOrg userOrg = (TSUserOrg) tsUser.getUserOrgList().get(0);
            ymkCustom.setBusinesseDeptName(userOrg.getTsDepart().getDepartname());
            ymkCustom.setBusinesseDeptId(userOrg.getTsDepart().getId());

            this.ymkCustomService.save(ymkCustom);

            TSUser user = new TSUser();

            user.setUserName(ymkCustom.getDaanNum());
            user.setMobilePhone(ymkCustom.getTelphone());
            user.setPassword(PasswordUtil.encrypt(ymkCustom.getDaanNum(), oConvertUtils.getString("123456"), PasswordUtil.getStaticSalt()));
            user.setStatus(Globals.User_Normal);
            user.setDeleteFlag(Globals.Delete_Normal);
            user.setDevFlag("0");
            this.userService.saveOrUpdate(user, "402880e447e99cf10147e9a03b320003".split(","), "ff8080816521731c016521a3ed3d001d".split(","));

            message = "用户账号: " + user.getUserName() + "添加成功";

            YmkCustomContactUserEntity customContactUserEntity = new YmkCustomContactUserEntity();
            customContactUserEntity.setContactId(ymkCustom.getId());
            customContactUserEntity.setUserId(user.getId());
            this.systemService.saveOrUpdate(customContactUserEntity);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAddUser"})
    @ResponseBody
    public AjaxJson doAddUser(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户表添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            TSUser user = new TSUser();
            user.setRealName(map.get("contactName").toString());
            user.setUserName(map.get("userName").toString());
            user.setMobilePhone(ymkCustom.getTelphone());
            user.setPassword(PasswordUtil.encrypt(map.get("userName").toString(), oConvertUtils.getString(map.get("password").toString()), PasswordUtil.getStaticSalt()));
            user.setStatus(Globals.User_Normal);
            user.setDeleteFlag(Globals.Delete_Normal);
            user.setDevFlag("0");
            user.setUserType(ymkCustom.getCusType());
            if (ymkCustom.getCusType().equals("0")) {
                this.userService.saveOrUpdate(user, "2c948c3362fb382e0162fb54053d001c".split(","), "4028819062fd3e6e0162fd40bf0f0001".split(","));
            } else {
                this.userService.saveOrUpdate(user, "2c948c3362fb382e0162fb54053d001c".split(","), "2c948c3362faa6a40162facd67ee000c".split(","));
            }
            message = "用户账号: " + user.getUserName() + "添加成功";

            YmkCustomContactUserEntity customContactUserEntity = new YmkCustomContactUserEntity();
            customContactUserEntity.setContactId(ymkCustom.getId());
            customContactUserEntity.setUserId(user.getId());
            this.systemService.saveOrUpdate(customContactUserEntity);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "客户表更新成功";
        YmkCustomEntity t = (YmkCustomEntity) this.ymkCustomService.get(YmkCustomEntity.class, ymkCustom.getId());
        try {
            ymkCustom.setCusCode(ChineseToEnglish.getPinYinHeadChar(ymkCustom.getCusName()));
            TSUser tsUser = (TSUser) this.systemService.findUniqueByProperty(TSUser.class, "userName", ymkCustom.getBusinesserName());
            TSUserOrg userOrg = (TSUserOrg) tsUser.getUserOrgList().get(0);
            ymkCustom.setBusinesseDeptName(userOrg.getTsDepart().getDepartname());
            ymkCustom.setBusinesseDeptId(userOrg.getTsDepart().getId());
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustom, t);

            this.ymkCustomService.saveOrUpdate(t);

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "客户表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"findCustomList"})
    @ResponseBody
    public Object findCustomList(YmkCustomEntity customEntity, HttpServletRequest request) {
        TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        String sql = "select a.id ,a.cus_name name from ymk_custom a where 1=1 and sys_org_code='" + user.getCurrentDepart().getOrgCode() + "' ";
        List<Map<String, Object>> temp = null;
        if ((map.get("q") != null) && (!map.get("q").toString().isEmpty())) {
            sql = sql + "  and a.cus_code like '%" + map.get("q") + "%' order by id asc ";

            temp = this.systemService.findForJdbc(sql, new Object[0]);
        }
        return temp;
    }

    @RequestMapping(params = {"findUserList"})
    @ResponseBody
    public AjaxJson findUserList(YmkCustomEntity customEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<TSUser> userList = this.systemService.findHql("from TSUser where userKey=?", new Object[]{map.get("userKey")});
        j.setObj(userList);


        return j;
    }

    @RequestMapping(params = {"findSupplierList"})
    @ResponseBody
    public AjaxJson findSupplierList(YmkCustomEntity customEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<EmkSupplierEntity> supplierEntities = this.systemService.findHql("from EmkSupplierEntity", new Object[0]);
        j.setObj(supplierEntities);
        return j;
    }

    @RequestMapping(params = {"getCity"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public AjaxJson getCity(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        Map param = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{param.get("code")});
            String dataItems = "";
            try {
                for (Map map : codeList) {
                    dataItems = dataItems + map.get("code") + "," + map.get("name") + ";";
                }
                if (dataItems.indexOf(";") > 0) {
                    dataItems = dataItems.substring(0, dataItems.length() - 1);
                }
                j.setObj(dataItems);
            } catch (Exception e) {
                logger.error(ExceptionUtil.getExceptionMessage(e));
            }
        } catch (Exception e) {
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return j;
    }

    @RequestMapping(params = {"doCustomData"})
    @ResponseBody
    public Object doCustomData(YmkCustomEntity ymkCustom, HttpServletRequest request) {
        String message = null;
        YmkCustomEntity t = (YmkCustomEntity) this.ymkCustomService.get(YmkCustomEntity.class, ymkCustom.getId());
        return JSONHelper.bean2json(t);
    }

    @RequestMapping(params = {"regCustom"})
    public ModelAndView regCustom(YmkCustomEntity ymkCustom, HttpServletRequest req) {
        Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 cusNum from ymk_custom", new Object[0]);
        req.setAttribute("cusNum", DateUtils.format(new Date(), "yyyy") + String.format("%06d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("cusNum").toString()))}));
        if (StringUtil.isNotEmpty(ymkCustom.getId())) {
            ymkCustom = (YmkCustomEntity) this.ymkCustomService.getEntity(YmkCustomEntity.class, ymkCustom.getId());
            req.setAttribute("ymkCustomPage", ymkCustom);
        }
        return new ModelAndView("com/service/custom/ymkCustom-reg");
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomEntity ymkCustom, HttpServletRequest req) {
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
        Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from ymk_custom where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
        req.setAttribute("daanNum", "KHDA1" + String.format("%04d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
        req.setAttribute("cusNum", String.format("%04d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
        req.setAttribute("createDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(ymkCustom.getId())) {
            ymkCustom = (YmkCustomEntity) this.ymkCustomService.getEntity(YmkCustomEntity.class, ymkCustom.getId());
            req.setAttribute("ymkCustomPage", ymkCustom);
        }
        return new ModelAndView("com/service/custom/ymkCustom-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomEntity ymkCustom, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustom.getId())) {
            ymkCustom = (YmkCustomEntity) this.ymkCustomService.getEntity(YmkCustomEntity.class, ymkCustom.getId());
            req.setAttribute("ymkCustomPage", ymkCustom);

            Map contact = this.systemService.findOneForJdbc("select * from ymk_custom_contact_user where contact_id=?", new Object[]{ymkCustom.getId()});
            if (contact != null) {
                TSUser user = (TSUser) this.systemService.get(TSUser.class, contact.get("user_id").toString());
                req.setAttribute("user", user);
            }
        }
        return new ModelAndView("com/service/custom/ymkCustom-update");
    }

    @RequestMapping(params = {"jump"})
    public Object jump(HttpServletRequest req) {
        Map<String, String> map = ParameterUtil.getParamMaps(req.getParameterMap());
        String showflag = "";
        if (map.get("showflag") != null) {
            showflag = (String) map.get("showflag");
        }
        if (((String) map.get("r")).equals("custom")) {
            TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
            YmkCustomEntity ymkCustom = (YmkCustomEntity) this.ymkCustomService.getEntity(YmkCustomEntity.class, (Serializable) map.get("id"));


            req.setAttribute("ymkCustomPage", ymkCustom);
            List<YmkCustomContactEntity> contactEntities = this.systemService.findHql("from YmkCustomContactEntity where customId=?", new Object[]{ymkCustom.getId()});
            req.setAttribute("contactEntities", contactEntities);
            Map<String, Object> alert = this.systemService.findOneForJdbc("SELECT id,DATE_FORMAT(alert_time, '%Y-%m-%d') create_date1,DATE_FORMAT(alert_time, '%H:%i') create_date2,alert_content,alert_time,create_name,state FROM ymk_custom_alert where custom_id='" + (String) map.get("id") + "'  ORDER BY CREATE_DATE DESC LIMIT 0,1", new Object[0]);
            req.setAttribute("alert", alert);

            List<Map<String, Object>> alertList = this.systemService.findForJdbc("SELECT id,DATE_FORMAT(alert_time, '%Y-%m-%d') create_date1,DATE_FORMAT(alert_time, '%H:%i') create_date2,alert_content,alert_time,create_name,state FROM ymk_custom_alert where custom_id='" + (String) map.get("id") + "' ORDER BY CREATE_DATE DESC ", new Object[0]);
            req.setAttribute("alertList", alertList);

            List<Map<String, Object>> traceList = this.systemService.findForJdbc("SELECT id,DATE_FORMAT(trace_time, '%Y-%m-%d') create_date1,DATE_FORMAT(trace_time, '%H:%i') create_date2,trace_content,trace_time,create_name FROM ymk_custom_trace where cus_id='" + (String) map.get("id") + "' ORDER BY CREATE_DATE DESC ", new Object[0]);
            req.setAttribute("traceList", traceList);
            return "forward:/metro/custom.jsp";
        }
        if (((String) map.get("r")).equals("common")) {
            req.setAttribute("url", "ymkCustomController.do?jump&r=custom&id=" + (String) map.get("id") + "&showflag=" + showflag);
        }
        if ((!((String) map.get("r")).equals("order")) ||


                (map.get("type") != null)) {
        }
        return "forward:/context/" + (String) map.get("r") + ".jsp";
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomEntity ymkCustom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustom, request.getParameterMap());
        List<YmkCustomEntity> ymkCustoms = this.ymkCustomService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "客户表");
        modelMap.put("entity", YmkCustomEntity.class);
        modelMap.put("params", new ExportParams("客户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustoms);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomEntity ymkCustom, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "客户表");
        modelMap.put("entity", YmkCustomEntity.class);
        modelMap.put("params", new ExportParams("客户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomEntity> list() {
        List<YmkCustomEntity> listYmkCustoms = this.ymkCustomService.getList(YmkCustomEntity.class);
        return listYmkCustoms;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomEntity task = (YmkCustomEntity) this.ymkCustomService.get(YmkCustomEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomEntity ymkCustom, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomEntity>> failures = this.validator.validate(ymkCustom, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomService.save(ymkCustom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustom.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomEntity ymkCustom) {
        Set<ConstraintViolation<YmkCustomEntity>> failures = this.validator.validate(ymkCustom, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomService.saveOrUpdate(ymkCustom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomService.deleteEntityById(YmkCustomEntity.class, id);
    }
}