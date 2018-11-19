<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkOrderList" checkbox="true"  pagination="true" fitColumns="false" title="" actionUrl="emkOrderController.do?datagrid" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购合同号"  field="orderNum" formatterjs="detailOrder"   queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="状态"  extendParams="styler:statetype" replace="草拟_0,审核_1,待入库_2"  field="state"  queryMode="single"  width="70"></t:dgCol>

          <t:dgCol title="合同币种"  field="htbz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签约日期"  field="signDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="签约地"  field="signPlace"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="运输方式"  field="ysfs"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="目的地"  field="mdd"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="贸易国别"  field="tradeName"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="备注"  field="remark"  queryMode="single"  width="150"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <t:dgDelOpt title="删除" operationCode="delete" url="emkOrderController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderController.do?goAdd" funname="orderTab"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderController.do?goUpdate" funname="editOrderTab"></t:dgToolBar>
          <t:dgToolBar title="生成待入库单" icon="fa fa-arrow-circle-right" url="emkOrderController.do?goAdd" funname="doWaitStorage"></t:dgToolBar>
          <%--<t:dgToolBar title="查看" icon="fa fa-search" url="emkOrderController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
<%--   <t:dgToolBar title="查看" icon="fa fa-search" url="emkOrderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/order/emkOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function detailOrder(val,row,index){
     $('#emkOrderList').datagrid('unselectAll');
     var s = '<a href="javascript:addOneTab(\'编辑订单采购合同\',\'emkOrderController.do?editOrderTab&id='+row.id+'&orderNum='+row.orderNum+'\')">'+row.orderNum+'</a>';
     return s;
 }
 function orderTab(){
     addOneTab('订单采购合同', 'emkOrderController.do?orderTab');
 }

 function doWaitStorage() {
     var rowsData = $('#emkOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要生成待入库单数据');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定生成待入库单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkOrderController.do?doWaitStorage&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkOrderList').datagrid('reload');

                     }else{
                         var msg = d.msg;
                     }
                 }
             });
         }
     });



 }

 function statetype(val,row,index){
     var s1 = 'background-color: #EE9A49;border-radius: 5px;color:#fff;';
     var s2 = 'background-color: #9B30FF;border-radius: 5px;color:#fff';
     if (val =='0') {
         return s1;
     }else if (val =='1') {
         return s2;
     }
 }

 function editOrderTab(){
     var rowsData = $('#emkOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip("请勾选订单在编辑！");
         return;
     }
     addOneTab('编辑订单采购合同', 'emkOrderController.do?editOrderTab&orderNum='+rowsData[0].orderNum+'&id='+rowsData[0].id);
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderController.do?upload', "emkOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderController.do?exportXls","emkOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderController.do?exportXlsByT","emkOrderList");
}

 </script>