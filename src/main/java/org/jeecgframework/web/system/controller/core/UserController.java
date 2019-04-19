package org.jeecgframework.web.system.controller.core;

import com.emk.util.ParameterUtil;
import com.service.custom.entity.YmkCustomEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.InterroleEntity;
import org.jeecgframework.web.system.pojo.base.InterroleUserEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSServiceUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private UserService userService;
    private SystemService systemService;

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(params = "menu")
    public void menu(HttpServletRequest request, HttpServletResponse response) {
        SetListSort sort = new SetListSort();
        TSUser u = ResourceUtil.getSessionUser();

        Set<TSFunction> loginActionlist = new HashSet();
        List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
        for (TSRoleUser ru : rUsers) {
            TSRole role = ru.getTSRole();
            List<TSRoleFunction> roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
            if (roleFunctionList.size() > 0) {
                for (TSRoleFunction roleFunction : roleFunctionList) {
                    TSFunction function = roleFunction.getTSFunction();
                    loginActionlist.add(function);
                }
            }
        }
        List<TSFunction> bigActionlist = new ArrayList();
        List<TSFunction> smailActionlist = new ArrayList();
        if (loginActionlist.size() > 0) {
            for (TSFunction function : loginActionlist) {
                if (function.getFunctionLevel().shortValue() == 0) {
                    bigActionlist.add(function);
                } else if (function.getFunctionLevel().shortValue() == 1) {
                    smailActionlist.add(function);
                }
            }
        }
        Collections.sort(bigActionlist, sort);
        Collections.sort(smailActionlist, sort);
        String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
        try {
            response.getWriter().write(logString);
            response.getWriter().flush();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(params = "user")
    public String user(HttpServletRequest request) {
        List<TSDepart> departList = this.systemService.getList(TSDepart.class);
        request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
        departList.clear();
        return "system/user/userList";
    }

    @RequestMapping(params = "ownerList")
    public String ownerList(HttpServletRequest request) {
        return "system/user/ownerList";
    }

    @RequestMapping(params = "userinfo")
    public String userinfo(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUser();
        request.setAttribute("user", user);
        return "system/user/userinfo";
    }

    @RequestMapping(params = "changepassword")
    public String changepassword(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUser();
        request.setAttribute("user", user);
        return "system/user/changepassword";
    }

    @RequestMapping(params = "changeportrait")
    public String changeportrait(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUser();
        request.setAttribute("user", user);
        return "system/user/changeportrait";
    }

    @RequestMapping(params = "saveportrait")
    @ResponseBody
    public AjaxJson saveportrait(HttpServletRequest request, String fileName) {
        AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUser();
        user.setPortrait(fileName);
        j.setMsg("修改成功");
        try {
            this.systemService.updateEntitie(user);
        } catch (Exception e) {
            j.setMsg("修改失败");
            e.printStackTrace();
        }
        return j;
    }

    @RequestMapping(params = "savenewpwd")
    @ResponseBody
    public AjaxJson savenewpwd(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUser();
        logger.info("[" + IpUtil.getIpAddr(request) + "][修改密码] start");
        String password = oConvertUtils.getString(request.getParameter("password"));
        String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
        String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
        if (!pString.equals(user.getPassword())) {
            j.setMsg("原密码不正确");
            j.setSuccess(false);
        } else {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.systemService.updateEntitie(user);
            j.setMsg("修改成功");
            logger.info("[" + IpUtil.getIpAddr(request) + "][修改密码]修改成功 userId:" + user.getUserName());
        }
        return j;
    }

    @RequestMapping(params = "changepasswordforuser")
    public ModelAndView changepasswordforuser(TSUser user, HttpServletRequest req) {
        logger.info("[" + IpUtil.getIpAddr(req) + "][跳转重置用户密码页面][" + user.getUserName() + "]");
        if (StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            idandname(req, user);
        }
        return new ModelAndView("system/user/adminchangepwd");
    }

    @RequestMapping(params = "savenewpwdforuser")
    @ResponseBody
    public AjaxJson savenewpwdforuser(HttpServletRequest req) {
        logger.info("[" + IpUtil.getIpAddr(req) + "][重置密码] start");
        String message = null;
        AjaxJson j = new AjaxJson();
        String id = oConvertUtils.getString(req.getParameter("id"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        if (StringUtil.isNotEmpty(id)) {
            TSUser users = (TSUser) this.systemService.getEntity(TSUser.class, id);
            if (("admin".equals(users.getUserName())) && (!"admin".equals(ResourceUtil.getSessionUser().getUserName()))) {
                message = "超级管理员[admin]，只有admin本人可操作，其他人无权限!";
                logger.info("[" + IpUtil.getIpAddr(req) + "]" + message);
                j.setMsg(message);
                return j;
            }
            users.setPassword(PasswordUtil.encrypt(users.getUserName(), password, PasswordUtil.getStaticSalt()));
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(users.getActivitiSync());
            this.systemService.updateEntitie(users);
            message = "用户: " + users.getUserName() + "密码重置成功";
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            logger.info("[" + IpUtil.getIpAddr(req) + "][重置密码]" + message);
        }
        j.setMsg(message);

        return j;
    }

    @RequestMapping(params = "lock")
    @ResponseBody
    public AjaxJson lock(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String message = null;
        TSUser user = (TSUser) this.systemService.getEntity(TSUser.class, id);
        if ("admin".equals(user.getUserName())) {
            message = "超级管理员[admin]不可操作";
            j.setMsg(message);
            return j;
        }
        String lockValue = req.getParameter("lockvalue");

        user.setStatus(new Short(lockValue));
        try {
            this.userService.updateEntitie(user);
            if ("0".equals(lockValue)) {
                message = "用户：" + user.getUserName() + "锁定成功!";
            } else if ("1".equals(lockValue)) {
                message = "用户：" + user.getUserName() + "激活成功!";
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            logger.info("[" + IpUtil.getIpAddr(req) + "][锁定账户]" + message);
        } catch (Exception e) {
            message = "操作失败!";
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "role")
    @ResponseBody
    public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
        String id = request.getParameter("id");
        List<ComboBox> comboBoxs = new ArrayList();
        List<TSRole> roles = new ArrayList();
        if (StringUtil.isNotEmpty(id)) {
            List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", id);
            if (roleUser.size() > 0) {
                for (TSRoleUser ru : roleUser) {
                    roles.add(ru.getTSRole());
                }
            }
        }
        List<TSRole> roleList = this.systemService.getList(TSRole.class);
        comboBoxs = TagUtil.getComboBox(roleList, roles, comboBox);

        roleList.clear();
        roles.clear();

        return comboBoxs;
    }

    @RequestMapping(params = "depart")
    @ResponseBody
    public List<ComboBox> depart(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
        String id = request.getParameter("id");
        List<ComboBox> comboBoxs = new ArrayList();
        List<TSDepart> departs = new ArrayList();
        if (StringUtil.isNotEmpty(id)) {
            TSUser user = (TSUser) this.systemService.get(TSUser.class, id);


            List<TSDepart[]> resultList = this.systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.orgId and uo.id=?", new Object[]{id});
            for (TSDepart[] departArr : resultList) {
                departs.add(departArr[0]);
            }
        }
        List<TSDepart> departList = this.systemService.getList(TSDepart.class);
        comboBoxs = TagUtil.getComboBox(departList, departs, comboBox);
        return comboBoxs;
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);

        HqlGenerateUtil.installHql(cq, user);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if (roleMap.get("rolecode").toString().contains("owneradmin")) {
            TSUser suser = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            cq.eq("createBy", suser.getUserName());
        }
        Short[] userstate = {Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);

        String orgIds = request.getParameter("orgIds");
        List<String> orgIdList = extractIdListByComma(orgIds);
        if (!CollectionUtils.isEmpty(orgIdList)) {
            CriteriaQuery subCq = new CriteriaQuery(TSUserOrg.class);
            subCq.setProjection(Property.forName("tsUser.id"));
            subCq.in("tsDepart.id", orgIdList.toArray());
            subCq.add();

            cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
        }
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (map.get("userFlag") != null) {
            cq.notEq("userKey", "客户");
        }
        cq.add();
        this.systemService.getDataGridReturn(cq, true);

        List<TSUser> cfeList = new ArrayList();
        for (Object o : dataGrid.getResults()) {
            if ((o instanceof TSUser)) {
                TSUser cfe = (TSUser) o;
                if ((cfe.getId() != null) && (!"".equals(cfe.getId()))) {
                    List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                    if (roleUser.size() > 0) {
                        String roleName = "";
                        for (TSRoleUser ru : roleUser) {
                            roleName = roleName + ru.getTSRole().getRoleName() + ",";
                        }
                        roleName = roleName.substring(0, roleName.length() - 1);
                        cfe.setUserKey(roleName);
                    }
                }
                cfeList.add(cfe);
            }
        }
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "servicedatagrid")
    public void servicedatagrid(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            String sql = "";
            String countsql = "";
            sql = "SELECT t2.id,t2.`username` userName,t1.`mobilePhone`,t2.`realname` realName,t2.`userkey`,t4.`cus_name` customName,t4.`id` customId,t4.cus_num customNum FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID`\nLEFT JOIN ymk_custom_contact_user t3 ON t3.`user_id`=t1.`id`\nLEFT JOIN ymk_custom t4 ON t4.`id`=t3.`contact_id` where t2.`userkey`='" + map.get("userkey") + "' ";


            countsql = " SELECT COUNT(0) FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID` WHERE t2.`userkey`='" + map.get("userkey") + "' ";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "signservicedatagrid")
    public void signservicedatagrid(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            TSUser cuser = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");

            String sql = "";
            String countsql = "";
            sql = "SELECT t2.id,t2.`username` userName,t1.`mobilePhone`,t2.`realname` realName,t2.`userkey`,t4.`cus_name`,t4.`id` cus_id FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID`\nLEFT JOIN ymk_custom_contact_user t3 ON t3.`user_id`=t1.`id`\nLEFT JOIN ymk_custom t4 ON t4.`id`=t3.`contact_id`\nleft join u_sign_service t5 on t5.`part_b_id`=t4.id\nwhere t5.`arealtioner_id`='" + cuser.getId() + "'";


            countsql = " SELECT count(0) FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID`\nLEFT JOIN ymk_custom_contact_user t3 ON t3.`user_id`=t1.`id`\nLEFT JOIN ymk_custom t4 ON t4.`id`=t3.`contact_id`\nleft join u_sign_service t5 on t5.`part_b_id`=t4.id\nwhere t5.`arealtioner_id`='" + cuser.getId() + "' ";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "ygservicedatagrid")
    public void ygservicedatagrid(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");
            String sql = "";
            String countsql = "";
            sql = "SELECT t2.id,t2.`username` userName,t1.`mobilePhone`,t2.`realname` realName,t2.`userkey`,t4.`cus_name`,t4.`id` cus_id FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID`\nLEFT JOIN ymk_custom_contact_user t3 ON t3.`user_id`=t1.`id`\nLEFT JOIN ymk_custom_contact t31 ON t31.`id`=t3.`contact_id`\nLEFT JOIN ymk_custom t4 ON t4.`id`=t31.`custom_id`\nWHERE t4.`id`='" + custom.getId() + "' ";


            countsql = " SELECT count(0) FROM t_s_user t1\nLEFT JOIN t_s_base_user t2 ON t1.`id`=t2.`ID`\nLEFT JOIN ymk_custom_contact_user t3 ON t3.`user_id`=t1.`id`\nLEFT JOIN ymk_custom_contact t31 ON t31.`id`=t3.`contact_id`\nLEFT JOIN ymk_custom t4 ON t4.`id`=t31.`custom_id`\nwhere t4.`id`='" + custom.getId() + "'";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "userdeptdatagrid0")
    public void userdeptdatagrid0(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");
            String sql = "";
            String countsql = "";
            sql = "SELECT t1.`userkey` userkey0,t1.`ID`,t1.`realname` sender,t1.`username` senderUserNames,t3.`org_code` sendDeptCode,t3.`departname` sendDeptName FROM t_s_base_user t1 \nLEFT JOIN t_s_user_org t2 ON t1.`ID`=t2.`user_id`\nLEFT JOIN t_s_depart t3 ON t3.`ID`=t2.`org_id` where t1.userkey !='客户'";


            countsql = " SELECT count(1) FROM t_s_base_user t1 where t1.userkey !='客户'";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "userdeptdatagrid1")
    public void userdeptdatagrid1(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");
            String sql = "";
            String countsql = "";
            sql = "SELECT t1.`userkey` userkey1,t1.`ID`,t1.`realname` recevier,t1.`username` recevierUserNames,t3.`org_code` recevieDeptCode,t3.`departname` recevieDeptName FROM t_s_base_user t1 \nLEFT JOIN t_s_user_org t2 ON t1.`ID`=t2.`user_id`\nLEFT JOIN t_s_depart t3 ON t3.`ID`=t2.`org_id` where t1.userkey !='客户'";


            countsql = " SELECT count(1) FROM t_s_base_user t1 where t1.userkey !='客户'";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "userdeptdatagrid2")
    public void userdeptdatagrid2(TSServiceUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
            YmkCustomEntity custom = (YmkCustomEntity) request.getSession().getAttribute("custom");
            String sql = "";
            String countsql = "";
            sql = "SELECT t1.`userkey` userkey2,t1.`ID`,t1.`realname` copyer,t1.`username` copyerUserNames,t3.`org_code` copyDeptCode,t3.`departname` copyDeptName FROM t_s_base_user t1 \nLEFT JOIN t_s_user_org t2 ON t1.`ID`=t2.`user_id`\nLEFT JOIN t_s_depart t3 ON t3.`ID`=t2.`org_id` where t1.userkey !='客户'";


            countsql = " SELECT count(1) FROM t_s_base_user t1 where t1.userkey !='客户'";
            if (dataGrid.getPage() == 1) {
                sql = sql + " limit 0, " + dataGrid.getRows();
            } else {
                sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params = "deleteDialog")
    public String deleteDialog(TSUser user, HttpServletRequest request) {
        request.setAttribute("user", user);
        return "system/user/user-delete";
    }

    @RequestMapping(params = "delete")
    @ResponseBody
    public AjaxJson delete(TSUser user, @RequestParam String deleteType, HttpServletRequest req) {
        if (deleteType.equals("delete")) {
            return del(user, req);
        }
        if (deleteType.equals("deleteTrue")) {
            return trueDel(user, req);
        }
        AjaxJson j = new AjaxJson();

        j.setMsg("删除逻辑参数异常,请重试.");
        return j;
    }

    @RequestMapping(params = "del")
    @ResponseBody
    public AjaxJson del(TSUser user, HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if ("admin".equals(user.getUserName())) {
            message = "超级管理员[admin]不可删除";
            j.setMsg(message);
            return j;
        }
        user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
        if (!user.getStatus().equals(Globals.User_ADMIN)) {
            user.setDeleteFlag(Globals.Delete_Forbidden);
            this.userService.updateEntitie(user);
            message = "用户：" + user.getUserName() + "删除成功";
            logger.info("[" + IpUtil.getIpAddr(req) + "][逻辑删除用户]" + message);
        } else {
            message = "超级管理员不可删除";
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "trueDel")
    @ResponseBody
    public AjaxJson trueDel(TSUser user, HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if ("admin".equals(user.getUserName())) {
            message = "超级管理员[admin]不可删除";
            j.setMsg(message);
            return j;
        }
        user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
        try {
            message = this.userService.trueDel(user);
            logger.info("[" + IpUtil.getIpAddr(req) + "][真实删除用户]" + message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "删除失败";
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "checkUser")
    @ResponseBody
    public ValidForm checkUser(HttpServletRequest request) {
        ValidForm v = new ValidForm();
        String userName = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSUser> roles = this.systemService.findByProperty(TSUser.class, "userName", userName);
        if ((roles.size() > 0) && (!code.equals(userName))) {
            v.setInfo("用户名已存在");
            v.setStatus("n");
        }
        return v;
    }

    @RequestMapping(params = "checkUserEmail")
    @ResponseBody
    public ValidForm checkUserEmail(HttpServletRequest request) {
        ValidForm validForm = new ValidForm();
        String email = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSUser> userList = this.systemService.findByProperty(TSUser.class, "email", email);
        if ((userList.size() > 0) && (!code.equals(email))) {
            validForm.setInfo("邮箱已绑定相关用户信息");
            validForm.setStatus("n");
        }
        return validForm;
    }

    @RequestMapping(params = "saveUser")
    @ResponseBody
    public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
        String message = null;
        AjaxJson j = new AjaxJson();

        Short logType = Globals.Log_Type_UPDATE;

        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String orgid = oConvertUtils.getString(req.getParameter("orgIds"));
        if (StringUtil.isNotEmpty(user.getId())) {
            TSUser users = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setDevFlag(user.getDevFlag());
            users.setRealName(user.getRealName());
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(user.getActivitiSync());
            this.userService.saveOrUpdate(users, orgid.split(","), roleid.split(","));
            message = "用户: " + users.getUserName() + "更新成功";
        } else {
            TSUser users = (TSUser) this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            if (users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getUserName(), oConvertUtils.getString(req.getParameter("password")), PasswordUtil.getStaticSalt()));
                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                this.userService.saveOrUpdate(user, orgid.split(","), roleid.split(","));
                message = "用户: " + user.getUserName() + "添加成功";
                logType = Globals.Log_Type_INSERT;
            }
        }
        this.systemService.addLog(message, logType, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        logger.info("[" + IpUtil.getIpAddr(req) + "][添加编辑用户]" + message);
        return j;
    }

    private void saveUserOrgList(HttpServletRequest request, TSUser user) {
        String orgIds = oConvertUtils.getString(request.getParameter("orgIds"));

        List<TSUserOrg> userOrgList = new ArrayList();
        List<String> orgIdList = extractIdListByComma(orgIds);
        for (String orgId : orgIdList) {
            TSDepart depart = new TSDepart();
            depart.setId(orgId);

            TSUserOrg userOrg = new TSUserOrg();
            userOrg.setTsUser(user);
            userOrg.setTsDepart(depart);

            userOrgList.add(userOrg);
        }
        if (!userOrgList.isEmpty()) {
            this.systemService.batchSave(userOrgList);
        }
    }

    protected void saveRoleUser(TSUser user, String roleidstr) {
        String[] roleids = roleidstr.split(",");
        for (int i = 0; i < roleids.length; i++) {
            TSRoleUser rUser = new TSRoleUser();
            TSRole role = (TSRole) this.systemService.getEntity(TSRole.class, roleids[i]);
            rUser.setTSRole(role);
            rUser.setTSUser(user);
            this.systemService.save(rUser);
        }
    }

    @RequestMapping(params = "roles")
    public ModelAndView roles(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("system/user/users");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(params = "datagridRole")
    public void datagridRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);

        HqlGenerateUtil.installHql(cq, tsRole);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {
        List<String> orgIdList = new ArrayList();
        TSDepart tsDepart = new TSDepart();
        if (StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());

            req.setAttribute("user", user);
            idandname(req, user);
            getOrgInfos(req, user);
        }
        req.setAttribute("tsDepart", tsDepart);


        return new ModelAndView("system/user/user");
    }

    @RequestMapping(params = "addorupdateInterfaceUser")
    public ModelAndView addorupdateInterfaceUser(TSUser user, HttpServletRequest req) {
        TSDepart tsDepart = new TSDepart();
        if (StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            interfaceroleidandname(req, user);
        } else {
            String roleId = req.getParameter("roleId");
            InterroleEntity role = (InterroleEntity) this.systemService.getEntity(InterroleEntity.class, roleId);
            req.setAttribute("roleId", roleId);
            req.setAttribute("roleName", role.getRoleName());
        }
        req.setAttribute("tsDepart", tsDepart);
        return new ModelAndView("system/user/interfaceUser");
    }

    public void interfaceroleidandname(HttpServletRequest req, TSUser user) {
        List<InterroleUserEntity> roleUsers = this.systemService.findByProperty(InterroleUserEntity.class, "TSUser.id", user.getId());
        String roleId = "";
        String roleName = "";
        if (roleUsers.size() > 0) {
            for (InterroleUserEntity interroleUserEntity : roleUsers) {
                roleId = roleId + interroleUserEntity.getInterroleEntity().getId() + ",";
                roleName = roleName + interroleUserEntity.getInterroleEntity().getRoleName() + ",";
            }
        }
        req.setAttribute("roleId", roleId);
        req.setAttribute("roleName", roleName);
    }

    @RequestMapping(params = "saveInterfaceUser")
    @ResponseBody
    public AjaxJson saveInterfaceUser(HttpServletRequest req, TSUser user) {
        String message = null;
        AjaxJson j = new AjaxJson();

        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        if (StringUtil.isNotEmpty(user.getId())) {
            TSUser users = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setDevFlag(user.getDevFlag());


            users.setRealName(user.getRealName());
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(user.getActivitiSync());

            users.setUserNameEn(user.getUserNameEn());
            users.setUserType(user.getUserType());

            users.setSex(user.getSex());
            users.setEmpNo(user.getEmpNo());
            users.setCitizenNo(user.getCitizenNo());
            users.setFax(user.getFax());
            users.setAddress(user.getAddress());
            users.setPost(user.getPost());
            users.setMemo(user.getMemo());

            this.systemService.updateEntitie(users);
            List<TSRoleUser> ru = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            this.systemService.deleteAllEntitie(ru);
            message = "用户: " + users.getUserName() + "更新成功";


            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            TSUser users = (TSUser) this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            if (users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));


                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                this.systemService.save(user);


                message = "用户: " + user.getUserName() + "添加成功";
                if (StringUtil.isNotEmpty(roleid)) {
                    saveInterfaceRoleUser(user, roleid);
                }
                this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            }
        }
        j.setMsg(message);
        logger.info("[" + IpUtil.getIpAddr(req) + "][添加编辑用户]" + message);
        return j;
    }

    protected void saveInterfaceRoleUser(TSUser user, String roleidstr) {
        String[] roleids = roleidstr.split(",");
        for (int i = 0; i < roleids.length; i++) {
            InterroleUserEntity rUser = new InterroleUserEntity();
            InterroleEntity role = (InterroleEntity) this.systemService.getEntity(InterroleEntity.class, roleids[i]);
            rUser.setInterroleEntity(role);
            rUser.setTSUser(user);
            this.systemService.save(rUser);
        }
    }

    @RequestMapping(params = "userOrgSelect")
    public ModelAndView userOrgSelect(HttpServletRequest request) {
        List<TSDepart> orgList = new ArrayList();
        String userId = oConvertUtils.getString(request.getParameter("userId"));

        List<Object[]> orgArrList = this.systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.tsDepart.id and uo.tsUser.id=?", new String[]{userId});
        for (Object[] departs : orgArrList) {
            orgList.add((TSDepart) departs[0]);
        }
        request.setAttribute("orgList", orgList);

        TSUser user = (TSUser) this.systemService.getEntity(TSUser.class, userId);
        request.setAttribute("user", user);

        return new ModelAndView("system/user/userOrgSelect");
    }

    public void idandname(HttpServletRequest req, TSUser user) {
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        String roleId = "";
        String roleName = "";
        if (roleUsers.size() > 0) {
            for (TSRoleUser tRoleUser : roleUsers) {
                roleId = roleId + tRoleUser.getTSRole().getId() + ",";
                roleName = roleName + tRoleUser.getTSRole().getRoleName() + ",";
            }
        }
        req.setAttribute("id", roleId);
        req.setAttribute("roleName", roleName);
    }

    public void getOrgInfos(HttpServletRequest req, TSUser user) {
        List<TSUserOrg> tSUserOrgs = this.systemService.findByProperty(TSUserOrg.class, "tsUser.id", user.getId());
        String orgIds = "";
        String departname = "";
        if (tSUserOrgs.size() > 0) {
            for (TSUserOrg tSUserOrg : tSUserOrgs) {
                orgIds = orgIds + tSUserOrg.getTsDepart().getId() + ",";

                departname = tSUserOrg.getTsDepart().getDepartname();
            }
        }
        req.setAttribute("orgIds", orgIds);
        req.setAttribute("departname", departname);
    }

    @RequestMapping(params = "choose")
    public String choose(HttpServletRequest request) {
        List<TSRole> roles = this.systemService.loadAll(TSRole.class);
        request.setAttribute("roleList", roles);
        return "system/membership/checkuser";
    }

    @RequestMapping(params = "chooseUser")
    public String chooseUser(HttpServletRequest request) {
        String departid = request.getParameter("departid");
        String roleid = request.getParameter("roleid");
        request.setAttribute("roleid", roleid);
        request.setAttribute("departid", departid);
        return "system/membership/userlist";
    }

    @RequestMapping(params = "datagridUser")
    public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String departid = request.getParameter("departid");
        String roleid = request.getParameter("roleid");
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        if (departid.length() > 0) {
            cq.eq("TDepart.departid", Integer.valueOf(oConvertUtils.getInt(departid, 0)));
            cq.add();
        }
        String userid = "";
        if (roleid.length() > 0) {
            List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TRole.roleid", Integer.valueOf(oConvertUtils.getInt(roleid, 0)));
            if (roleUsers.size() > 0) {
                for (TSRoleUser tRoleUser : roleUsers) {
                    userid = userid + tRoleUser.getTSUser().getId() + ",";
                }
            }
            cq.in("userid", oConvertUtils.getInts(userid.split(",")));
            cq.add();
        }
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "roleDepart")
    public String roleDepart(HttpServletRequest request) {
        List<TSRole> roles = this.systemService.loadAll(TSRole.class);
        request.setAttribute("roleList", roles);
        return "system/membership/roledepart";
    }

    @RequestMapping(params = "chooseDepart")
    public ModelAndView chooseDepart(HttpServletRequest request) {
        String nodeid = request.getParameter("nodeid");
        ModelAndView modelAndView = null;
        if (nodeid.equals("role")) {
            modelAndView = new ModelAndView("system/membership/users");
        } else {
            modelAndView = new ModelAndView("system/membership/departList");
        }
        return modelAndView;
    }

    @RequestMapping(params = "datagridDepart")
    public void datagridDepart(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "test")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        String jString = request.getParameter("_dt_json");
        DataTables dataTables = new DataTables(request);
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataTables);
        String username = request.getParameter("userName");
        if (username != null) {
            cq.like("userName", username);
            cq.add();
        }
        DataTableReturn dataTableReturn = this.systemService.getDataTableReturn(cq, true);
        TagUtil.datatable(response, dataTableReturn, "id,userName,mobilePhone,TSDepart_departname");
    }

    @RequestMapping(params = "index")
    public String index() {
        return "bootstrap/main";
    }

    @RequestMapping(params = "main")
    public String main() {
        return "bootstrap/test";
    }

    @RequestMapping(params = "testpage")
    public String testpage(HttpServletRequest request) {
        return "test/test";
    }

    @RequestMapping(params = "addsign")
    public ModelAndView addsign(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return new ModelAndView("system/user/usersign");
    }

    @RequestMapping(params = "savesign", method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public AjaxJson savesign(HttpServletRequest req) {
        String message = null;
        UploadFile uploadFile = new UploadFile(req);
        String id = uploadFile.get("id");
        TSUser user = (TSUser) this.systemService.getEntity(TSUser.class, id);
        uploadFile.setRealPath("signatureFile");
        uploadFile.setCusPath("signature");
        uploadFile.setByteField("signature");
        uploadFile.setBasePath("resources");
        uploadFile.setRename(false);
        uploadFile.setObject(user);
        AjaxJson j = new AjaxJson();
        message = user.getUserName() + "设置签名成功";
        this.systemService.uploadFile(uploadFile);
        this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        j.setMsg(message);

        return j;
    }

    @RequestMapping(params = "testSearch")
    public void testSearch(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        if (user.getUserName() != null) {
            cq.like("userName", user.getUserName());
        }
        if (user.getRealName() != null) {
            cq.like("realName", user.getRealName());
        }
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "changestyle")
    public String changeStyle(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUser();
        if (user == null) {
            return "login/login";
        }
        SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
        request.setAttribute("indexStyle", sysThemesEnum.getStyle());

        return "system/user/changestyle";
    }

    @RequestMapping(params = "savestyle")
    @ResponseBody
    public AjaxJson saveStyle(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        j.setSuccess(Boolean.FALSE.booleanValue());
        TSUser user = ResourceUtil.getSessionUser();
        if (user != null) {
            String indexStyle = request.getParameter("indexStyle");
            if (StringUtils.isNotEmpty(indexStyle)) {
                Cookie cookie = new Cookie("JEECGINDEXSTYLE", indexStyle);

                cookie.setMaxAge(2592000);
                response.addCookie(cookie);
                logger.debug(" ----- 首页样式: indexStyle ----- " + indexStyle);
                j.setSuccess(Boolean.TRUE.booleanValue());
                j.setMsg("样式修改成功，请刷新页面");
            }
            try {
                ClientManager.getInstance().getClient().getFunctions().clear();
            } catch (Exception localException) {
            }
        } else {
            j.setMsg("请登录后再操作");
        }
        return j;
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "userController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(TSUser tsUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tsUser, request.getParameterMap());
        List<TSUser> tsUsers = this.userService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        for (int i = 0; i < tsUsers.size(); i++) {
            TSUser user = (TSUser) tsUsers.get(i);

            this.systemService.getSession().evict(user);
            String id = user.getId();

            String queryRole = "select * from t_s_role where id in (select roleid from t_s_role_user where userid=:userid)";
            List<TSRole> roles = this.systemService.getSession().createSQLQuery(queryRole).addEntity(TSRole.class).setString("userid", id).list();
            String roleCodes = "";
            for (TSRole role : roles) {
                roleCodes = roleCodes + "," + role.getRoleCode();
            }
            user.setUserKey(roleCodes.replaceFirst(",", ""));
            String queryDept = "select * from t_s_depart where id in (select org_id from t_s_user_org where user_id=:userid)";
            List<TSDepart> departs = this.systemService.getSession().createSQLQuery(queryDept).addEntity(TSDepart.class).setString("userid", id).list();
            String departCodes = "";
            for (TSDepart depart : departs) {
                departCodes = departCodes + "," + depart.getOrgCode();
            }
            user.setDepartid(departCodes.replaceFirst(",", ""));
        }
        modelMap.put("fileName", "用户表");
        modelMap.put("entity", TSUser.class);
        modelMap.put("params", new ExportParams("用户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", tsUsers);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(TSUser tsUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "用户表");
        modelMap.put("entity", TSUser.class);
        modelMap.put("params", new ExportParams("用户表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(params = "userSelect")
    public String userSelect() {
        return "system/user/userSelect";
    }

    @RequestMapping(params = "addorupdateMyOrgUser")
    public ModelAndView addorupdateMyOrgUser(TSUser user, HttpServletRequest req) {
        List<String> orgIdList = new ArrayList();
        TSDepart tsDepart = new TSDepart();
        if (StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser) this.systemService.getEntity(TSUser.class, user.getId());

            req.setAttribute("user", user);
            idandname(req, user);
            getOrgInfos(req, user);
        } else {
            String departid = oConvertUtils.getString(req.getParameter("departid"));
            TSDepart org = (TSDepart) this.systemService.getEntity(TSDepart.class, departid);
            req.setAttribute("orgIds", departid);
            req.setAttribute("departname", org.getDepartname());
        }
        req.setAttribute("tsDepart", tsDepart);
        return new ModelAndView("system/user/myOrgUser");
    }

    @RequestMapping(params = "userOwner")
    public String userOwner() {
        return "system/user/userOwner";
    }

    @RequestMapping(params = "userProjectService")
    public String userProjectService() {
        return "system/user/userProjectService";
    }

    @RequestMapping(params = "userService")
    public String userService() {
        return "system/user/userService";
    }

    @RequestMapping(params = "userSignService")
    public String userSignService() {
        return "system/user/userSignService";
    }

    @RequestMapping(params = "userServiceYg")
    public String userServiceYg() {
        return "system/user/userServiceYg";
    }

    @RequestMapping(params = "userSelect0")
    public String userSelect1() {
        return "system/user/userSelect0";
    }

    @RequestMapping(params = "userSelect2")
    public String userSelect2() {
        return "system/user/userSelect2";
    }

    @RequestMapping(params = "userSelectCw")
    public String userSelectCw() {
        return "system/user/userSelectCw";
    }
    @RequestMapping(params = "userSelectUserKey")
    public String userSelectUserKey() {
        return "system/user/userSelectUserKey";
    }
    @RequestMapping(params = "userdept0")
    public String userdept0() {
        return "system/user/userdept0";
    }

    @RequestMapping(params = "userdept1")
    public String userdept1() {
        return "system/user/userdept1";
    }

    @RequestMapping(params = "userdept2")
    public String userdept2() {
        return "system/user/userdept2";
    }
}
