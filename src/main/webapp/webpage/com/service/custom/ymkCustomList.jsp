<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="ymkCustomList" checkbox="true"  pagination="true" singleSelect="true" sortName="cusNum" sortOrder="asc"  pageSize="20" fitColumns="false" title="" btnCls="bootstrap" actionUrl="ymkCustomController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <%--<t:dgCol title="操作" field="opt" width="135" frozenColumn="true"></t:dgCol>--%>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="档案号"  field="daanNum"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="客户编码"  field="cusNum" query="true" queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="客户名称"  field="cusName"  query="true" queryMode="single"  width="200"></t:dgCol>
    <%--<t:dgCol title="联系人"  field="zlxr"  queryMode="single"  width="80"></t:dgCol>
    <t:dgCol title="电话"  field="telphone"  queryMode="single"  width="90"></t:dgCol>--%>
    <t:dgCol title="区域"  field="shengFen"  dictionary="t_s_category,code,name"   queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="国家"  field="guoJia"  query="true" dictionary="trade" queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="业务跟单员"  field="tracerName"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="生产跟单员"  field="developerName"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="客户状态"  field="status" query="true" replace="激活_0,暂停_1,禁止_2" queryMode="single"  width="70"></t:dgCol>

    <%--<t:dgCol title="片区"  field="pianQu" dictionary="t_s_category,code,name"   queryMode="single"  width="70"></t:dgCol>--%>
    <%--<t:dgCol title="客户类型"  field="cusType" query="true" dictionary="custom" queryMode="single"  width="80"></t:dgCol>--%>
    <t:dgToolBar title="录入" icon="fa fa-plus" url="ymkCustomController.do?goAdd&winTitle=录入客户档案" width="1100" height="560" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="fa fa-edit" url="ymkCustomController.do?goUpdate&winTitle=编辑客户档案" width="1100" height="560" funname="update"></t:dgToolBar>
    <t:dgToolBar title="更改状态" icon="fa fa-edit" url="ymkCustomController.do?goUpdateState&winTitle=更改状态" width="400" height="100" funname="update"></t:dgToolBar>
    <t:dgToolBar title="删除"  icon="fa fa-remove"  url="ymkCustomController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    <%--<t:dgToolBar title="查看" icon="fa fa-search" url="ymkCustomController.do?goUpdate" funname="detail" width="1100" height="560"></t:dgToolBar>--%>
    <%--<t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>--%>
    <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
    <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right"  funname="doPrintPDF"></t:dgToolBar>

    <%--<t:dgToolBar title="模板下载" icon="fa fa-arrow-circle-o-down" funname="ExportXlsByT"></t:dgToolBar>--%>
</t:datagrid>

 <script src = "webpage/com/service/custom/ymkCustomList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){

 });



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
     $.dialog.confirm('您是否确定同意客户入驻?', function(r) {
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
     $.dialog.confirm('您是否确定同意客户入驻?', function(r) {
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
 function doPrintPDF() {
     var rowsData = $('#ymkCustomList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要导出PDF的客户档案');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定导出PDF的客户档案?', function(r) {
         if (r) {
             window.location.href = "ymkCustomController.do?doPrintPDF&ids="+ids;
         }
     });
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