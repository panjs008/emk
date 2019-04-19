package com.emk.util;

import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by panjs008 on 2019-02-23.
 */
public class ApprovalUtil {

    /**
     * 保存审批意见
     *
     * @param id 审批表ID， user 用户对象，b 审批表
     * @return
     * @throws Exception
     */
    public static  EmkApprovalDetailEntity saveApprovalDetail(String id, TSUser user, EmkApprovalEntity b,String advice) throws Exception {
        EmkApprovalDetailEntity approvalDetail = new EmkApprovalDetailEntity();
        approvalDetail.setApprovalId(id);
        approvalDetail.setApproveUserId(user.getId());
        approvalDetail.setApproveDate(DateUtil.getCurrentTimeString(null));
        approvalDetail.setApproveStatus(0);
        approvalDetail.setApproveAdvice(advice);
        b.setBpmSher(user.getRealName());
        b.setBpmSherId(user.getId());
        b.setBpmDate(DateUtil.getCurrentTimeString(null));
        b.setBpmStatus("0");
        return approvalDetail;
    }

    /**
     * 保存审批表
     *
     * @param  approvalEntity 审批表对象，type 工单类型，workNum 单号，formId 对应表单ID，user 用户对象
     * @return
     * @throws Exception
     */
    public static void saveApproval(EmkApprovalEntity approvalEntity,int type,String workNum,String formId,TSUser user) throws Exception {
        approvalEntity.setType(type);
        approvalEntity.setCommitTime(DateUtil.getCurrentTimeString(null));
        approvalEntity.setCommitId(user.getId());
        approvalEntity.setStatus(0);
        approvalEntity.setWorkNum(workNum);
        approvalEntity.setFormId(formId);
    }
    /**
     * 保存发送信息记录表
     *
     * @param  approvalEntity 发送信息记录表对象，title 标题，content 内容，receviceUser 接收用户，sendUser 发送用户
     * @return
     * @throws Exception
     */
    public static void saveSms(TSSmsEntity smsEntity,String title,String content,String receviceUser,String sendUser) throws Exception {
        smsEntity.setEsReceiver(receviceUser);
        smsEntity.setEsTitle(title);
        smsEntity.setEsSender(sendUser);
        smsEntity.setEsStatus("0");
        smsEntity.setEsContent(content);
    }

    /**
     * 保存审批意见
     *
     * @param  approvalDetail 审批意见表对象，approvalid 审批表id，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
     * @return
     * @throws Exception
     */
    public static void saveApprovalDetail(EmkApprovalDetailEntity approvalDetail,String approvalid,String bpmName,String bpmNode,String advice,TSUser user) throws Exception {
        approvalDetail.setApprovalId(approvalid);
        approvalDetail.setApproveAdvice(advice);
        approvalDetail.setApproveUserId(user.getId());
        approvalDetail.setBpmName(bpmName);
        approvalDetail.setBpmNode(bpmNode);
        approvalDetail.setApproveDate(DateUtil.getCurrentTimeString(null));
    }
}
