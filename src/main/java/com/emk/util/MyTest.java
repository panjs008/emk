package com.emk.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

public class MyTest {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public void deployFlow() {
        RepositoryService repositoryService = this.processEngine.getRepositoryService();


        InputStream in = getClass().getClassLoader().getResourceAsStream("myleave.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);

        repositoryService.createDeployment().addZipInputStream(zipInputStream).name("Myleave").deploy();
    }

    public void queryProcdef() {
        RepositoryService repositoryService = this.processEngine.getRepositoryService();

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        query.processDefinitionKey("myProcess");


        List<ProcessDefinition> pds = query.list();
        for (ProcessDefinition pd : pds) {
            System.out.println("ID:" + pd.getId() + ",NAME:" + pd.getName() + ",KEY:" + pd.getKey() + ",VERSION:" + pd.getVersion() + ",RESOURCE_NAME:" + pd.getResourceName() + ",DGRM_RESOURCE_NAME:" + pd.getDiagramResourceName());
        }
    }

    public void startFlow() {
        RuntimeService runtimeService = this.processEngine.getRuntimeService();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
        System.out.println("id:" + processInstance.getId() + ",activitiId:" + processInstance.getActivityId());
    }

    public void queryTask() {
        TaskService taskService = this.processEngine.getTaskService();

        List<Task> tasks = ((TaskQuery) taskService.createTaskQuery().taskAssignee("张三")).list();
        for (Task task : tasks) {
            System.out.println("ID:" + task.getId() + ",姓名:" + task.getName() + ",接收人:" + task.getAssignee() + ",开始时间:" + task.getCreateTime());
        }
    }

    public void startTask() {
        TaskService taskService = this.processEngine.getTaskService();

        String taskId = "104";

        taskService.complete(taskId);
    }

    public void queryTask2() {
        TaskService taskService = this.processEngine.getTaskService();

        List<Task> tasks = ((TaskQuery) taskService.createTaskQuery().taskAssignee("老板")).list();
        for (Task task : tasks) {
            System.out.println("ID:" + task.getId() + ",姓名:" + task.getName() + ",接收人:" + task.getAssignee() + ",开始时间:" + task.getCreateTime());
        }
    }

    @Test
    public void startTask2() {
        RuntimeService runtimeService = this.processEngine.getRuntimeService();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
        System.out.println("id:" + processInstance.getId() + ",activitiId:" + processInstance.getActivityId());
    }
}
