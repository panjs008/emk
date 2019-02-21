package com.emk.util;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;

import java.util.*;

public class ParameterUtil {

    public static Map<String, String> getParamMaps(Map map) {
        Map m = new HashMap();
        StringBuffer value = new StringBuffer();
        for (Object emap : map.entrySet()) {
            value.delete(0, value.length());
            Map.Entry fp = (Map.Entry) emap;
            Object ov = fp.getValue();
            if (null != fp.getValue()) {
                if (ov instanceof String[]) {
                    String[] ar = (String[]) ov;
                    for (String f : ar) {
                        value.append(f).append(",");
                    }
                } else {
                    value.append(ov);
                }
            } else {
                m.put(fp.getKey(), "");
            }
            if (value.length() > 0) {
                m.put(fp.getKey(), value.substring(0, value.length() - 1));
            }
        }
        return m;
    }

    public static String sortAbc(String str) {
        StringBuffer buff = new StringBuffer(str);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (String.valueOf(arr[i]).matches("[a-zA-Z]")) {
                while (true) {
                    if (String.valueOf(buff.charAt(j)).matches("[a-zA-Z]")) {
                        buff.setCharAt(j, arr[i]);
                        j++;
                        break;
                    }
                    j++;
                }
            }
        }
        return buff.toString();
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric2(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            System.out.println(chr);
            if (chr < 48 || chr > 57) {
                if (chr != 46)
                    return false;
            }
        }
        return true;
    }

    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    public static List<String> getHighLightedFlows(
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
                if (sameStartTimeNodes.contains(pvmActivityImpl) || activityImpl.getId().equals("parallelgateway2")|| ((activityImpl.getId().equals("ywbCheckTask") || activityImpl.getId().equals("scbCheckTask") || activityImpl.getId().equals("jsbCheckTask")) && pvmActivityImpl.getId().equals("parallelgateway3"))) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    public static List<String> getHighLightedFlows2(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances, String assignId, HistoryService historyService) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        int flag = 0;
        String[] processDefKeyArr = new String[3];
        for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
            if(historicActivityInstance.getActivityId().equals("ywbCheckTask") || historicActivityInstance.getActivityId().equals("jsbCheckTask") || historicActivityInstance.getActivityId().equals("scbCheckTask")){
                if(Utils.notEmpty(historicActivityInstance.getEndTime())){
                    processDefKeyArr[flag] = historicActivityInstance.getActivityId();
                    flag++;
                }
            }
        }
        for (int i = 0; i < historicActivityInstances.size(); i++) {// 对历史流程节点进行遍历
            HistoricActivityInstance historicActivityInstance = historicActivityInstances.get(i);
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = null;
            if(i< historicActivityInstances.size() -1){
                sameActivityImpl1 = processDefinitionEntity
                        .findActivity(historicActivityInstances.get(i + 1)
                                .getActivityId());
            }
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
            //List<Task> task = taskService.createTaskQuery().taskAssignee(assignId).list();
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线

            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if(Utils.notEmpty(historicActivityInstances)){
                    if(flag == 3){
                        if (pvmActivityImpl.getId().equals("parallelgateway3")) {
                            highFlows.add(pvmTransition.getId());
                        }
                    }else if((flag == 1 || flag == 2) && (activityImpl.getId().equals(processDefKeyArr[0]) || activityImpl.getId().equals(processDefKeyArr[1]))){
                        highFlows.add(pvmTransition.getId());
                    }
                   /* if(task.size() == 0){
                        if (pvmActivityImpl.getId().equals("parallelgateway3")) {
                            highFlows.add(pvmTransition.getId());
                        }
                    }else{
                        int flag = 0;
                        Task task1 = (Task)task.get(task.size() - 1);
                        for(Task mtask: task){
                            if(mtask.getTaskDefinitionKey().equals(activityImpl.getId())){
                                flag = 1;
                            }
                        }
                        if(flag ==0 && (activityImpl.getId().equals("ywbCheckTask") || activityImpl.getId().equals("jsbCheckTask") || activityImpl.getId().equals("scbCheckTask"))){
                            highFlows.add(pvmTransition.getId());
                        }

                    }*/
                }
                if (sameStartTimeNodes.contains(pvmActivityImpl) || activityImpl.getId().equals("parallelgateway2")) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
