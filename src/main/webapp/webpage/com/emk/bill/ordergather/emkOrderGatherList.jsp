<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<input id="indexFlag0" name="indexFlag0" type="hidden" value="0"/>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
<c:if test="${param.state eq 0 || param.state eq null}">
    <t:datagrid name="emkOrderGatherList" checkbox="true"  pagination="false" fitColumns="false" title="" actionUrl="emkOrderGatherController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
        <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="操作" field="opt" formatterjs="formatOpt" hidden="${param.state ne 0 ? true:false}" width="120" ></t:dgCol>

        <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="订单ID"  field="orderId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="货前后"  field="hpreNext" formatterjs="formathpreNext" replace="货前_0,货后_1"  queryMode="single"  width="90"></t:dgCol>
        <t:dgCol title="收款方式"  field="gatherType" formatterjs="formatgatherType"  queryMode="single"  width="150"></t:dgCol>
        <t:dgCol title="日期方式"  field="dateType" formatterjs="formatdateType" dictionary="rqfs" queryMode="single"  width="180"></t:dgCol>
        <t:dgCol title="比例"  field="dopute" formatterjs="formatdopute"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="金额"  field="money" formatterjs="formatmoney"  queryMode="single"  width="150"></t:dgCol>
        <t:dgCol title="备注"  field="remark" formatterjs="formatgremark"  queryMode="single"  width="150"></t:dgCol>

        <t:dgFunOpt funname="addRow" title="添加"  exp="createName#ne#1"  urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
        <t:dgFunOpt funname="removeit" title="删除" operationCode="delete"  exp="createName#ne#1"  urlclass="ace_button" urlfont="fa-trash-o"></t:dgFunOpt>

    </t:datagrid>
</c:if>
<c:if test="${param.state ne 0 && param.state ne null}">
    <t:datagrid name="emkOrderGatherList" checkbox="false"  pagination="false" fitColumns="false" title="" actionUrl="emkOrderGatherController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
        <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="订单ID"  field="orderId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="货前后"  field="hpreNext"  replace="货前_0,货后_1"  queryMode="single"  width="90"></t:dgCol>
        <t:dgCol title="收款方式"  field="gatherType"  queryMode="single"  width="150"></t:dgCol>
        <t:dgCol title="日期方式"  field="dateType" dictionary="rqfs" queryMode="single"  width="180"></t:dgCol>
        <t:dgCol title="比例"  field="dopute"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="金额"  field="money"   queryMode="single"  width="150"></t:dgCol>
        <t:dgCol title="备注"  field="remark"  queryMode="single"  width="150"></t:dgCol>

    </t:datagrid>
</c:if>
  </div>
 </div>
 <script src = "webpage/com/emk/bill/ordergather/emkOrderGatherList.js"></script>		
 <script type="text/javascript">
     var indexFlag0 = 0;
 $(document).ready(function(){
 });

 function goAdd(){
     createwindow("添加收款方式", "emkOrderGatherController.do?goAdd&orderId=${param.orderId}",700,400);
 }
 function addRow(title,addurl,gname){
     indexFlag0 = indexFlag0 +1;

     $('#emkOrderGatherList').datagrid('appendRow',{});
     var editIndex = $('#emkOrderGatherList').datagrid('getRows').length-1;
     $('#emkOrderGatherList').datagrid('selectRow', editIndex)
         .datagrid('beginEdit', editIndex);
 }
 function removeit(rowIndex){
     $('#emkOrderGatherList').datagrid('deleteRow', rowIndex);
     /* var editIndex = $('#emkOrderDetailList').datagrid('getRows').length-1;
      $('#emkOrderDetailList').datagrid('deleteRow', editIndex);
      indexFlag = indexFlag -1;*/

 }
 function formatOpt(val,row,index){
     /*var s = '<button class="btn btn-default btn-xs" onclick="addRow(null,null,null)"><i class="fa fa-plus"></i></button>';
     s += '<button class="btn btn-default btn-xs" onclick=""><i class="fa fa-remove"></i></button>';*/
     var s = '<a href="#" class="ace_button" onclick="addRow(null,null,null)">  <i class=" fa fa-cog"></i>添加</a>&nbsp;';
     s += '<a href="#" class="ace_button" onclick="removeit('+index+');">  <i class="fa fa-trash-o"></i>删除</a>';
     return s;

 }

 function formathpreNext(val,row,index){
     indexFlag0 = index;
     var s = '<select id="hpreNext'+index+'" name="hpreNext'+index+'" style="width:100%;">';
     s += '<option value="0" ';
     if(row.hpreNext=="0"){
         s += ' selected >货前</option>';
     }else{
         s += '>货前</option>';
     }
     s += '<option value="1" ';
     if(row.hpreNext=="1"){
         s += ' selected >货后</option>';
     }else{
         s += '>货后</option>';
     }
     s += '</select>';
     $("#indexFlag0").val(indexFlag0);
     return s;

 }

 function formatgatherType(val,row,index){
     var s = '';
     if(row.gatherType == null|| row.gatherType == ''){
         s = '<input id="gatherType'+index+'" name="gatherType'+index+'" value=""  type="text" style="width: 100%" />';

     }else{
         s = '<input id="gatherType'+index+'" name="gatherType'+index+'" value="'+row.gatherType +'"  type="text" style="width: 100%" />';
     }
     return s;

 }
 function formatdateType(val,row,index){

     var s = '<select id="dateType'+index+'" name="dateType'+index+'" style="width:100%;">';
     <c:forEach var="list" items="${typeList}" varStatus="status">
     if(row.dateType=="${list.typecode}"){
         s += '<option value="${list.typecode}" selected>${list.typename}</option>';
     }else{
         s += '<option value="${list.typecode}">${list.typename}</option>';
     }
     </c:forEach>
     s += '</select>';
     return s;

 }
 function formatdopute(val,row,index){
     var s = '';
     if(row.dopute == null|| row.dopute == ''){
         s = '<input id="dopute'+index+'" name="dopute'+index+'" value=""  type="text" style="width: 100%" />';

     }else{
         s = '<input id="dopute'+index+'" name="dopute'+index+'" value="'+row.dopute +'"  type="text" style="width: 100%" />';
     }
     return s;

 }
 function formatmoney(val,row,index){
     var s = '';
     if(row.money == null|| row.money == ''){
         s = '<input id="money'+index+'" name="money'+index+'" value=""  type="text" style="width: 100%" />';

     }else{
         s = '<input id="money'+index+'" name="money'+index+'" value="'+row.money +'"  type="text" style="width: 100%" />';
     }
     return s;

 }
 function formatgremark(val,row,index){
     var s = '';
     if(row.gatherType == null|| row.gatherType == ''){
         s = '<input id="remark'+index+'" name="remark'+index+'" value=""  type="text" style="width: 100%" />';

     }else{
         s = '<input id="remark'+index+'" name="remark'+index+'" value="'+row.remark +'"  type="text" style="width: 100%" />';
     }
     return s;

 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderGatherController.do?upload', "emkOrderGatherList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderGatherController.do?exportXls","emkOrderGatherList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderGatherController.do?exportXlsByT","emkOrderGatherList");
}

 </script>