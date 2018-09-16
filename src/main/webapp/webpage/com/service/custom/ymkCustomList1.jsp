<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ymkCustomList" checkbox="true"  pagination="true"  fitColumns="false" title="" btnCls="bootstrap" actionUrl="ymkCustomController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编码"  field="cusNum"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="服务商"  field="cusName"   queryMode="single"  width="190"></t:dgCol>
      <t:dgCol title="城市"  field="chengShi" dictionary="t_s_category,code,name"    queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="片区"  field="pianQu" dictionary="t_s_category,code,name"   queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="服务商简称"  field="cusCode"  queryMode="single"  width="95"></t:dgCol>
      <%--<t:dgCol title="客户类型"  field="cusType"  dictionary="custom" queryMode="single"  width="80"></t:dgCol>--%>
   <t:dgCol title="类型"  field="cusType"  replace="设备中标商_0,本地服务商_1" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="status"  replace="待审核_0,已通过_1,未通过_2" queryMode="single"  width="55"></t:dgCol>

   <%--<t:dgCol title="客户来源"  field="cusFrom"  query="true" queryMode="single"  width="70"></t:dgCol>--%>
   <t:dgCol title="办公地址"  field="address"  queryMode="single"  width="130"></t:dgCol>
   <t:dgCol title="联系人电话"  field="telphone"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="办公传真"  field="fax"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="公司网址"  field="companyNet" formatterjs="detailCompany" queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="备注"  field="remark"  queryMode="single"  width="90"></t:dgCol>--%>
  <%-- <t:dgFunOpt funname="queryDetail1(id,cusName)" title="联系人" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
   <t:dgFunOpt funname="queryDetail2(id,cusName)" title="提醒" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
   <t:dgFunOpt funname="queryDetail3(id,cusName)" title="跟进" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
   <t:dgFunOpt funname="queryDetail4(id,cusName)" title="银行" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>--%>
   <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomController.do?goUpdate" funname="updateService"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="fa fa-search" url="ymkCustomController.do?goUpdate" funname="detail" width="1000" height="550"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<%--<div data-options="region:'east',
	title:'联系人',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 500px; overflow: hidden;" id="eastPanel">
 <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="proDetialListpanel"></div>--%>
 <script src = "webpage/com/service/custom/ymkCustomList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){

 });

 function updateService() {
     var url = "ymkCustomController.do?goUpdate";
     update('编辑服务商', url, "ymkCustomList","1100px","550px");
 }

 function detailCustom(val,row,index){
     $('#ymkCustomList').datagrid('unselectAll');
     var s = '<a href="javascript:addOneTab(\'客户：'+row.cusName+'\',\'ymkCustomController.do?jump&r=common&id='+row.id+'\')">'+row.cusNum+'</a>';
     return s;
     //addOneTab('客户：'+rowsData[0].cusName, 'ymkCustomController.do?jump&r=common&id='+rowsData[0].id);
 }

 function detailCompany(val,row,index){
     var url = '';
     if(val.indexOf("http")>=0){
         url = val;
     }else{
         url = "http://"+val;
     }
     var s = "<a href='javascript: window.open(\""+url+"\");'>"+val+"</a>";
     return s;
 }

 function agree(id) {
     $.dialog.confirm('您是否确定同意服务商入驻?', function(r) {
         if (r) {
             $.ajax({
                 url : "ymkCustomController.do?check&id="+id+"&status=1",
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#ymkCustomList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }
 function notAgree(id) {
     $.dialog.confirm('您是否确定同意服务商入驻?', function(r) {
         if (r) {
             $.ajax({
                 url : "ymkCustomController.do?check&id="+id+"&status=2",
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#ymkCustomList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function queryDetail1(id,cusName){
     $('#ymkCustomList').datagrid('unselectAll');
     var title = "客户："+ cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "ymkCustomContactController.do?list&customId=" + id);

 }

 function queryDetail2(id,cusName){
     $('#ymkCustomList').datagrid('unselectAll');
     var title = "客户："+ cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "ymkCustomAlertController.do?list&customId=" + id);

 }

 function queryDetail3(id,cusName){
     $('#ymkCustomList').datagrid('unselectAll');
     var title = "客户："+ cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "ymkCustomTraceController.do?list&customId=" + id);

 }

 function queryDetail4(id,cusName){
     $('#ymkCustomList').datagrid('unselectAll');
     var title = "客户："+ cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "ymkCustomBankController.do?list&customId=" + id);

 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'ymkCustomController.do?upload', "ymkCustomList");
}

//导出
function ExportXls() {
	JeecgExcelExport("ymkCustomController.do?exportXls","ymkCustomList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("ymkCustomController.do?exportXlsByT","ymkCustomList");
}

 </script>