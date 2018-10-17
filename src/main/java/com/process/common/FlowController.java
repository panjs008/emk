package com.process.common;

import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.util.ParameterUtil;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import com.process.repair.entity.URepairEntity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * Created by lenovo on 2017/8/23.
 */
@Controller
@RequestMapping("/flowController")
public class FlowController {
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    ManagementService managementService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;

    @Autowired
    private SystemService systemService;


    /**
     * 流程历史新页面跳转
     *
     * @return
     */
    @RequestMapping(params = "processHis")
    public ModelAndView processHis( HttpServletRequest req) {
        return new ModelAndView("com/process/processHis");
    }

    @RequestMapping(params = "hisProcessDatagrid")
    public void hisProcessDatagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        try{
            String sql = "",countsql = "";
            sql = "SELECT DATE_FORMAT(t1.START_TIME_,'%Y-%m-%d %H:%i:%s') startTime,DATE_FORMAT(t1.END_TIME_,'%Y-%m-%d %H:%i:%s') endTime,t1.*,CASE\n" +
                    " WHEN t1.TASK_DEF_KEY_='orderTask' THEN t2.create_name \n" +
                    " WHEN t1.TASK_DEF_KEY_='htTask' THEN t2.create_name \n" +
                    " WHEN t1.TASK_DEF_KEY_='outstorageTask' THEN t2.create_name \n" +
                    " WHEN t1.TASK_DEF_KEY_='instorageTask' THEN t2.create_name \n" +
                    " WHEN t1.TASK_DEF_KEY_='sampleTask' THEN t2.create_name \n" +
                    " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n";
            if(!map.get("sqlType").equals("bill") && !map.get("sqlType").equals("ht") && !map.get("sqlType").equals("sample")&& !map.get("sqlType").equals("produce")){
                sql +=  " WHEN t1.TASK_DEF_KEY_='cwTask' THEN t2.financer ";
            }
            sql +=  " ELSE ''\n" ;
            sql += " END workname FROM act_hi_taskinst t1 \n";
            if(map.get("sqlType").equals("outStorage")){
                sql +=" LEFT JOIN emk_m_out_storage t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("inStorage")){
                sql +=" LEFT JOIN emk_m_in_storage t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("order")){
                sql +=" LEFT JOIN emk_material_contract t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("bill")){
                sql +=" LEFT JOIN emk_pro_order t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("ht")){
                sql +=" LEFT JOIN emk_contract t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("enquiry")){
                sql +=" LEFT JOIN emk_enquiry t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("sample")){
                sql +=" LEFT JOIN emk_sample t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }else  if(map.get("sqlType").equals("produce")){
                sql +=" LEFT JOIN emk_produce_schedule t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";
            }

            countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='"+map.get("id")+"' ";
            sql += " order by t1.START_TIME_ asc";

            if(dataGrid.getPage()==1){
                sql += " limit 0, "+dataGrid.getRows();
            }else{
                sql += "limit "+(dataGrid.getPage()-1)*dataGrid.getRows()+","+dataGrid.getRows();
            }
            this.systemService.listAllByJdbc(dataGrid, sql, countsql);
            TagUtil.datagrid(response, dataGrid);
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @RequestMapping(params="process")
    public ModelAndView process(String processUrl, HttpServletRequest req) {
        return new ModelAndView(processUrl);
    }

    @RequestMapping(params="goProcess")
    public ModelAndView goProcess(String processUrl, HttpServletRequest req) {
        return new ModelAndView(processUrl);
    }

    @RequestMapping(params="getCurrentProcess")
    @ResponseBody
    public AjaxJson getCurrentProcess(String id,String tableName,String title, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String state = "";
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from \n");
        sql.append( tableName + " where id=? \n");
        Map beanMap = systemService.findOneForJdbc(sql.toString(),id);
        if(beanMap != null){
            state = beanMap.get("state").toString();
        }
        List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
        if (task.size() > 0) {
            Task task1 = (Task)task.get(task.size() - 1);
            j.setMsg(task1.getName());
            request.getSession().setAttribute("orderPorcess", task1);
            request.getSession().setAttribute("orderFinish", "0");
        }else if (state.equals("2")) {
            j.setMsg("完成");
            request.getSession().setAttribute("orderFinish", "1");
        }else {
            j.setMsg(title);
            request.getSession().setAttribute("orderFinish", "0");
            request.getSession().setAttribute("orderPorcess", null);
        }
        return j;
    }

    @RequestMapping(params="showProcess")
    public void showProcess(String id,String tableName,HttpServletRequest req, HttpServletResponse response) throws Exception {
//        Map map = ParameterUtil.getParamMaps(req.getParameterMap());
        List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
        String processInstanceId = "";
        String state = "";
//        EmkContractEntity t = systemService.get(EmkContractEntity.class, id);
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from \n");
        sql.append( tableName + " where id=? \n");
        Map beanMap = systemService.findOneForJdbc(sql.toString(),id);
        if(beanMap != null){
            state = beanMap.get("state").toString();
        }
        if (task.size() > 0) {
            Task task1 = (Task)task.get(task.size() - 1);
            processInstanceId = task1.getProcessInstanceId();
        }else if (state.equals("2")) {
            Map hisPorcess = systemService.findOneForJdbc("SELECT PROC_INST_ID_ processid FROM act_hi_taskinst WHERE ASSIGNEE_=? LIMIT 0,1 ", id);
            processInstanceId = String.valueOf(hisPorcess.get("processid"));
        }
        if (processInstanceId != null && !processInstanceId.isEmpty()) {
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl)processEngineConfiguration);

            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

            List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

            List<String> highLightedActivitis = new ArrayList();

            List<String> highLightedFlows = ParameterUtil.getHighLightedFlows(definitionEntity, highLightedActivitList);
            for (HistoricActivityInstance tempActivity : highLightedActivitList) {
                String activityId = tempActivity.getActivityId();
                highLightedActivitis.add(activityId);
            }
            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", null, 1.0D);

            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        }
    }

    /**
     * 发布流程
     * 发布流程后，流程文件会保存到数据库中
     * 发布流程后，流程文件会保存到数据库中
     */
    @RequestMapping(params = "deployFlow")
    public void deployFlow(HttpServletResponse response) throws Exception {
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //获取在classpath下的流程文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("produce.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        //使用deploy方法发布流程
        repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("produce")
                .deploy();
      /*  Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("inputUser", "panjs");//表示惟一用户
        *//**
         * 5：  (1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
         (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
         *//*
        //格式：LeaveBill.id的形式（使用流程变量）
        //variables.put("repairId", "a000001");
        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey("repair", "uRepair",variables);
        System.out.println("pid:" + pi.getId());*/
        /*TaskService taskService = processEngine.getTaskService();
        String assigneeUser = "潘金顺";
        String processInstanceid= "15024";
        Task task = taskService.createTaskQuery().taskAssignee(assigneeUser).processInstanceId(processInstanceid).singleResult();

        taskService.setVariable(task.getId(),"报修人","潘金顺");
        taskService.setVariableLocal(task.getId(),"userId","1234567890");
        taskService.setVariable(task.getId(),"报修时间", DateUtil.date2str(new Date(),"yyyy-MM-dd HH:mm:ss"));*/

    }
    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(params = "test")
    public void test(HttpServletResponse response) throws Exception {
        // 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
        //repositoryService.createDeployment().addClasspathResource("repairProcess.bpmn").deploy();
        // 开启流程，myprocess是流程的ID
        System.out.println("流程【启动】，环节推动到【一次审批】环节");
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("optUser", "panjs");//表示惟一用户
        runtimeService.startProcessInstanceByKey("repair", "uRepair",variables);
        // 查询历史表中的Task
        List<Task> task = taskService.createTaskQuery().list();
        Task task1 = task.get(task.size()-1);
       /* //解开注释就推动到下一环节，对应的在流程图上看到
        taskService.complete(task1.getId());
        System.out.println("执行【一次审批】环节，流程推动到【二次审批】环节");
        task1 = taskService.createTaskQuery().executionId(task1.getExecutionId()).singleResult();

        //解开注释就推动到下一环节，对应的在流程图上看到
        taskService.complete(task1.getId());
        System.out.println("执行【二次审批】环节，流程推动到【结束】环节");*/

        //processInstanceId
        String processInstanceId = task1.getProcessInstanceId();
        //获取历史流程实例
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);

        for(HistoricActivityInstance tempActivity : highLightedActivitList){
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }

        //中文显示的是口口口，设置字体就好了
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "JPEG", highLightedActivitis,highLightedFlows,"宋体","宋体",null,1.0);
        //单独返回流程图，不高亮显示
//        InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
