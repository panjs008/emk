<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSSmsList" checkbox="true" fitColumns="false" title="" actionUrl="tSSmsController.do?datagrid" idField="id" fit="true" queryMode="group" sortName="createDate" sortOrder="desc">
   <t:dgCol title="common.esId"  field="id"  hidden="true"  queryMode="single" ></t:dgCol>
   <t:dgCol title="common.messageType"  hidden="true" field="esType"  queryMode="single" dictionary="msgType"></t:dgCol>
   <t:dgCol title="common.messageHeader" width="115" field="esTitle" queryMode="single" ></t:dgCol>
   <t:dgCol title="common.sender" width="60" field="createName"  queryMode="single"></t:dgCol>
   <%--<t:dgCol title="common.receiver"  field="esReceiver"  queryMode="single"></t:dgCol>--%>
   <t:dgCol title="common.content_2" width="520" field="esContent"  queryMode="single" ></t:dgCol>
   <t:dgCol title="发送时间"  width="130" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" queryMode="group" ></t:dgCol>
   <t:dgCol title="common.sendtime" hidden="true" field="esSendtime"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single" ></t:dgCol>
   <%--<t:dgCol title="common.sendState"  field="esStatus"  queryMode="single" dictionary="msgStatus"  ></t:dgCol>--%>
   <%--<t:dgCol title="common.remark"  field="remark"  queryMode="single" ></t:dgCol>--%>
   <%--<t:dgToolBar title="common.iconEdit" icon="icon-edit" url="tSSmsController.do?goUpdate" funname="update"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
	 $("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	 $("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 </script>