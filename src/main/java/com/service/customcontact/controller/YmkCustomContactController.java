package com.service.customcontact.controller;

import com.emk.util.ParameterUtil;
import com.service.custom.entity.YmkCustomEntity;
import com.service.customcontact.entity.YmkCustomContactEntity;
import com.service.customcontact.entity.YmkCustomContactUserEntity;
import com.service.customcontact.service.YmkCustomContactServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
@RequestMapping({"/ymkCustomContactController"})
public class YmkCustomContactController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(YmkCustomContactController.class);
    @Autowired
    private YmkCustomContactServiceI ymkCustomContactService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private UserService userService;

    @RequestMapping(params = {"list0"})
    public ModelAndView list0(HttpServletRequest request) {
        return new ModelAndView("com/service/customcontact/ymkCustomContactList0");
    }

    @RequestMapping(params = {"shehuiList"})
    public ModelAndView shehuiList(HttpServletRequest request) {
        return new ModelAndView("com/service/customcontact/shehuiList");
    }

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/service/customcontact/ymkCustomContactList");
    }

    @RequestMapping(params = {"list1"})
    public ModelAndView list1(HttpServletRequest request) {
        return new ModelAndView("com/service/customcontact/ymkCustomContactList1");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomContactEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, ymkCustomContact, request.getParameterMap());
        try {
            YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");

            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            if (!user.getUserName().equals("admin")) {
                cq.eq("sysOrgCode", user.getCurrentDepart().getOrgCode());
                cq.eq("customId", custom.getId());
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.ymkCustomContactService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ymkCustomContact = (YmkCustomContactEntity) this.systemService.getEntity(YmkCustomContactEntity.class, ymkCustomContact.getId());
        message = "联系人表删除成功";
        try {
            this.ymkCustomContactService.delete(ymkCustomContact);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "联系人表删除失败";
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
        message = "联系人表删除成功";
        try {
            for (String id : ids.split(",")) {
                YmkCustomContactEntity ymkCustomContact = (YmkCustomContactEntity) this.systemService.getEntity(YmkCustomContactEntity.class, id);


                Map customUser = this.systemService.findOneForJdbc("select * from ymk_custom_contact_user where contact_id=?", new Object[]{ymkCustomContact.getId()});
                if (customUser != null) {
                    this.systemService.executeSql("delete from ymk_custom_contact_user where contact_id =?", new Object[]{ymkCustomContact.getId()});
                    this.systemService.executeSql("delete from t_s_role_user where userid =?", new Object[]{customUser.get("user_id")});
                    this.systemService.executeSql("delete from t_s_user_org where user_id =?", new Object[]{customUser.get("user_id")});

                    this.systemService.executeSql("delete from t_s_user where id =?", new Object[]{customUser.get("user_id")});
                    this.systemService.executeSql("delete from t_s_base_user where ID =?", new Object[]{customUser.get("user_id")});
                }
                this.ymkCustomContactService.delete(ymkCustomContact);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "联系人表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "联系人表添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            TSUser users = (TSUser) this.systemService.findUniqueByProperty(TSUser.class, "userName", map.get("userName"));
            TSUser cuuuser = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            if (users != null) {
                message = "用户账号: " + users.getUserName() + "已经存在";
                j.setSuccess(false);
                j.setMsg(message);
                return j;
            }
            YmkCustomEntity customEntity = (YmkCustomEntity) this.systemService.get(YmkCustomEntity.class, ymkCustomContact.getCustomId());
            if (customEntity == null) {
                j.setSuccess(false);
                j.setMsg("您的账号无法创建，服务商的员工账号，请联系管理员创建");
                return j;
            }
            ymkCustomContact.setCustomName(customEntity.getCusName());
            this.ymkCustomContactService.save(ymkCustomContact);

            TSUser user = new TSUser();
            user.setRealName(ymkCustomContact.getUserName());
            user.setMobilePhone(ymkCustomContact.getTelphone());
            user.setUserName(map.get("userAccount").toString());
            user.setPassword(PasswordUtil.encrypt(map.get("userAccount").toString(), oConvertUtils.getString(map.get("password").toString()), PasswordUtil.getStaticSalt()));
            user.setStatus(Globals.User_Normal);
            user.setDeleteFlag(Globals.Delete_Normal);
            user.setDevFlag("0");
            if (cuuuser.getUserKey().equals("设备中标商")) {
                this.userService.saveOrUpdate(user, "2c948c3362fb382e0162fb54053d001c".split(","), "4028819062fd3e6e0162fd4264c30005".split(","));
            } else {
                this.userService.saveOrUpdate(user, "2c948c3362fb382e0162fb54053d001c".split(","), "4028819062fd3e6e0162fd4229d80003".split(","));
            }
            message = "用户账号: " + user.getUserName() + "添加成功";

            YmkCustomContactUserEntity customContactUserEntity = new YmkCustomContactUserEntity();
            customContactUserEntity.setContactId(ymkCustomContact.getId());
            customContactUserEntity.setUserId(user.getId());
            this.systemService.saveOrUpdate(customContactUserEntity);

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "联系人表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "联系人表更新成功";
        YmkCustomContactEntity t = (YmkCustomContactEntity) this.ymkCustomContactService.get(YmkCustomContactEntity.class, ymkCustomContact.getId());
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        TSUser cuuuser = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
        try {
            if (StringUtil.isNotEmpty(map.get("userId"))) {
                TSUser users = (TSUser) this.systemService.getEntity(TSUser.class, map.get("userId").toString());
                if (!map.get("oldPassword").toString().equals(map.get("password").toString())) {
                    users.setPassword(PasswordUtil.encrypt(map.get("userAccount").toString(), oConvertUtils.getString(map.get("password").toString()), PasswordUtil.getStaticSalt()));
                }
                if (cuuuser.getUserKey().equals("设备中标商")) {
                    this.userService.saveOrUpdate(users, "2c948c3362fb382e0162fb54053d001c".split(","), "4028819062fd3e6e0162fd4264c30005".split(","));
                } else {
                    this.userService.saveOrUpdate(users, "2c948c3362fb382e0162fb54053d001c".split(","), "4028819062fd3e6e0162fd4229d80003".split(","));
                }
            }
            MyBeanUtils.copyBeanNotNull2Bean(ymkCustomContact, t);
            this.ymkCustomContactService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "联系人表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(YmkCustomContactEntity ymkCustomContact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomContact.getId())) {
            ymkCustomContact = (YmkCustomContactEntity) this.ymkCustomContactService.getEntity(YmkCustomContactEntity.class, ymkCustomContact.getId());
            req.setAttribute("ymkCustomContactPage", ymkCustomContact);
        }
        return new ModelAndView("com/service/customcontact/ymkCustomContact-add");
    }

    @RequestMapping(params = {"getContect"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public AjaxJson getContect(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        Map param = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            List<Map<String, Object>> codeList = this.systemService.findForJdbc("SELECT id,user_name FROM ymk_custom_contact WHERE custom_id=?", new Object[]{param.get("customId")});
            String dataItems = "";
            try {
                for (Map map : codeList) {
                    dataItems = dataItems + map.get("id") + "," + map.get("user_name") + ";";
                }
                if (dataItems.indexOf(";") > 0) {
                    dataItems = dataItems.substring(0, dataItems.length() - 1);
                } else {
                    j.setSuccess(false);
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

    @RequestMapping(params = {"goAdd1"})
    public ModelAndView goAdd1(YmkCustomContactEntity ymkCustomContact, HttpServletRequest req) {
        return new ModelAndView("com/service/customcontact/ymkCustomContact-add1");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(YmkCustomContactEntity ymkCustomContact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomContact.getId())) {
            ymkCustomContact = (YmkCustomContactEntity) this.ymkCustomContactService.getEntity(YmkCustomContactEntity.class, ymkCustomContact.getId());
            req.setAttribute("ymkCustomContactPage", ymkCustomContact);
        }
        return new ModelAndView("com/service/customcontact/ymkCustomContact-update");
    }

    @RequestMapping(params = {"goUpdate1"})
    public ModelAndView goUpdate1(YmkCustomContactEntity ymkCustomContact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ymkCustomContact.getId())) {
            ymkCustomContact = (YmkCustomContactEntity) this.ymkCustomContactService.getEntity(YmkCustomContactEntity.class, ymkCustomContact.getId());
            req.setAttribute("ymkCustomContactPage", ymkCustomContact);

            Map contact = this.systemService.findOneForJdbc("select * from ymk_custom_contact_user where contact_id=?", new Object[]{ymkCustomContact.getId()});
            if (contact != null) {
                TSUser user = (TSUser) this.systemService.get(TSUser.class, contact.get("user_id").toString());
                req.setAttribute("user", user);
            }
        }
        return new ModelAndView("com/service/customcontact/ymkCustomContact-update1");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ymkCustomContactController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(YmkCustomContactEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, ymkCustomContact, request.getParameterMap());
        List<YmkCustomContactEntity> ymkCustomContacts = this.ymkCustomContactService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "联系人表");
        modelMap.put("entity", YmkCustomContactEntity.class);
        modelMap.put("params", new ExportParams("联系人表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", ymkCustomContacts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(YmkCustomContactEntity ymkCustomContact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "联系人表");
        modelMap.put("entity", YmkCustomContactEntity.class);
        modelMap.put("params", new ExportParams("联系人表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<YmkCustomContactEntity> list() {
        List<YmkCustomContactEntity> listYmkCustomContacts = this.ymkCustomContactService.getList(YmkCustomContactEntity.class);
        return listYmkCustomContacts;
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        YmkCustomContactEntity task = (YmkCustomContactEntity) this.ymkCustomContactService.get(YmkCustomContactEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody YmkCustomContactEntity ymkCustomContact, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<YmkCustomContactEntity>> failures = this.validator.validate(ymkCustomContact, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomContactService.save(ymkCustomContact);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String id = ymkCustomContact.getId();
        URI uri = uriBuilder.path("/rest/ymkCustomContactController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    public ResponseEntity<?> update(@RequestBody YmkCustomContactEntity ymkCustomContact) {
        Set<ConstraintViolation<YmkCustomContactEntity>> failures = this.validator.validate(ymkCustomContact, new Class[0]);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }
        try {
            this.ymkCustomContactService.saveOrUpdate(ymkCustomContact);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.ymkCustomContactService.deleteEntityById(YmkCustomContactEntity.class, id);
    }
}
