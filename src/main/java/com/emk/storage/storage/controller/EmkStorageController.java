package com.emk.storage.storage.controller;

import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storage.service.EmkStorageServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 库存表
 * @date 2018-03-07 15:28:45
 */
@Api(value = "EmkStorage", description = "库存表", tags = "emkStorageController")
@Controller
@RequestMapping("/emkStorageController")
public class EmkStorageController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(EmkStorageController.class);

    @Autowired
    private EmkStorageServiceI emkStorageService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;


    /**
     * 库存表列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/storage/emkStorageList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkStorage, request.getParameterMap());
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);

        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.emkStorageService.getDataGridReturn(cq, true);
        String sql = "SELECT ifnull(ROUND(SUM(total),2),0) zongji FROM emk_storage WHERE 1=1";

        if (emkStorage.getStorageName() != null && !emkStorage.getStorageName().isEmpty()) {
            sql += " and storage_name like '%" + emkStorage.getStorageName() + "%'";
        }
        if (emkStorage.getProNum() != null && !emkStorage.getProNum().isEmpty()) {
            sql += " and pro_num like '%" + emkStorage.getProNum() + "%'";
        }
        if (emkStorage.getProZnName() != null && !emkStorage.getProZnName().isEmpty()) {
            sql += " and pro_zn_name like '%" + emkStorage.getProZnName() + "%'";
        }
        if (emkStorage.getBrand() != null && !emkStorage.getBrand().isEmpty()) {
            sql += " and brand like '%" + emkStorage.getBrand() + "%'";
        }
        String zongji = String.valueOf(systemService.findOneForJdbc(sql, null).get("zongji"));
        dataGrid.setFooter("brand:合计,total:" + zongji);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除库存表
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkStorageEntity emkStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkStorage = systemService.getEntity(EmkStorageEntity.class, emkStorage.getId());
        message = "库存表删除成功";
        try {
            emkStorageService.delete(emkStorage);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库存表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除库存表
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "库存表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkStorageEntity emkStorage = systemService.getEntity(EmkStorageEntity.class,
                        id
                );
                emkStorageService.delete(emkStorage);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "库存表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加库存表
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkStorageEntity emkStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "库存表添加成功";
        try {
            emkStorageService.save(emkStorage);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库存表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新库存表
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkStorageEntity emkStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "库存表更新成功";
        EmkStorageEntity t = emkStorageService.get(EmkStorageEntity.class, emkStorage.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkStorage, t);
            emkStorageService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "库存表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 库存表新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkStorageEntity emkStorage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorage.getId())) {
            emkStorage = emkStorageService.getEntity(EmkStorageEntity.class, emkStorage.getId());
            req.setAttribute("emkStoragePage", emkStorage);
        }
        return new ModelAndView("com/emk/storage/storage/emkStorage-add");
    }

    /**
     * 库存表编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkStorageEntity emkStorage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorage.getId())) {
            emkStorage = emkStorageService.getEntity(EmkStorageEntity.class, emkStorage.getId());
            req.setAttribute("emkStoragePage", emkStorage);
        }
        return new ModelAndView("com/emk/storage/storage/emkStorage-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkStorageController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageEntity.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkStorage, request.getParameterMap());
        List<EmkStorageEntity> emkStorages = this.emkStorageService.getListByCriteriaQuery(cq, false);
        modelMap.put(NormalExcelConstants.FILE_NAME, "库存表");
        modelMap.put(NormalExcelConstants.CLASS, EmkStorageEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("库存表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, emkStorages);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkStorageEntity emkStorage, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put(NormalExcelConstants.FILE_NAME, "库存表");
        modelMap.put(NormalExcelConstants.CLASS, EmkStorageEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("库存表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<EmkStorageEntity> listEmkStorageEntitys = ExcelImportUtil.importExcel(file.getInputStream(), EmkStorageEntity.class, params);
                for (EmkStorageEntity emkStorage : listEmkStorageEntitys) {
                    emkStorageService.save(emkStorage);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "库存表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkStorageEntity>> list() {
        List<EmkStorageEntity> listEmkStorages = emkStorageService.getList(EmkStorageEntity.class);
        return Result.success(listEmkStorages);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID获取库存表信息", notes = "根据ID获取库存表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkStorageEntity task = emkStorageService.get(EmkStorageEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取库存表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "创建库存表")
    public ResponseMessage<?> create(@ApiParam(name = "库存表对象") @RequestBody EmkStorageEntity emkStorage, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<EmkStorageEntity>> failures = validator.validate(emkStorage);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }

        //保存
        try {
            emkStorageService.save(emkStorage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("库存表信息保存失败");
        }
        return Result.success(emkStorage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "更新库存表", notes = "更新库存表")
    public ResponseMessage<?> update(@ApiParam(name = "库存表对象") @RequestBody EmkStorageEntity emkStorage) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<EmkStorageEntity>> failures = validator.validate(emkStorage);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }

        //保存
        try {
            emkStorageService.saveOrUpdate(emkStorage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新库存表信息失败");
        }

        //按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return Result.success("更新库存表信息成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除库存表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        // 验证
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkStorageService.deleteEntityById(EmkStorageEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("库存表删除失败");
        }

        return Result.success();
    }
}
