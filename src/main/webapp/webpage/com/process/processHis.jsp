<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="instorageList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="flowController.do?hisProcessDatagrid&sqlType=${param.sqlType}&id=${param.id}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="流程节点"  field="NAME_"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="操作人"  field="workname"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="开始时间"  field="startTime"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="结束时间"  field="endTime"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="DELETE_REASON_" replace="完成_completed"  queryMode="single"  width="120"></t:dgCol>
</t:datagrid>