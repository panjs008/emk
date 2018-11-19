<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery-webos,easyui,tools,DatePicker,autocomplete"></t:base>

<div style="display:none">
    <input id="hsCode" name="hsCode" type="text"/>
    <input id="zzVal" name="zzVal" type="text"/>
    <input id="tsVal" name="tsVal" type="text"/>

    <input id="proNum" name="proNum" type="text"/>
    <input id="proType" name="proType" type="text" />
    <input id="proTypeName" name="proTypeName" type="text" />
    <input id="proZnName" name="proZnName" type="text" />
    <input id="proEnName" name="proEnName" type="text" />
    <input id="brand" name="brand" type="text" />
    <input id="unit" name="unit" type="text" />
    <input id="id" name="id" type="text" />
    <input id="indexFlag" name="indexFlag" type="text" value="0"/>

    <t:choose  hiddenName="id"  hiddenid="id" url="emkProductController.do?proSelect" name="emkProductList" width="750px" height="500px"
           icon="icon-search" title="选择商品" textname="id,proNum,proType,proTypeName,proZnName,proEnName,brand,unit,hsCode,zzVal,tsVal" isclear="true" isInit="true"></t:choose></div>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <c:if test="${order.state eq 0 || order.state eq null}">
      <t:datagrid name="emkOrderDetailList" checkbox="true"  pagination="true" onClick="editRow" fitColumns="false" title="" actionUrl="emkOrderDetailController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
          <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <%--<t:dgCol title="操作" field="opt" width="180" hidden="${order.state eq 1 ? true:false}"  frozenColumn="true" extendParams="editor:'button'"></t:dgCol>--%>
          <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="产品编号"  field="proNum"  formatterjs="formatProNum" queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="产品类型"  field="proName"  formatterjs="formatProType" queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="产品ID"  field="proId"  hidden="true" queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="海关商品编码"  field="hairProNum" formatterjs="formatHairProNum" queryMode="single"  width="90"></t:dgCol>
          <t:dgCol title="中文品名"  field="proZnName"  formatterjs="formatProZnName" queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="英文品名"  field="proEnName" formatterjs="formatProEnName" queryMode="single"  width="90"></t:dgCol>
          <t:dgCol title="规格型号"  field="brand" formatterjs="formatBrand" queryMode="single"  width="90"></t:dgCol>
          <t:dgCol title="签约数量"  field="signTotal"  formatterjs="formatSignTotal" queryMode="single"   width="70"></t:dgCol>
          <t:dgCol title="签约单位"  field="signUnit" formatterjs="formatSignUnit" queryMode="single"  width="70"></t:dgCol>
          <t:dgCol title="签约单价"  field="signPrice"  formatterjs="formatSignPrice" queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="不含税金额"  field="notSignPrice"  formatterjs="formatNotSignPrice" queryMode="single"  width="70"></t:dgCol>
          <t:dgCol title="增值税率/%"  field="zzsl"  formatterjs="formatZzsl" queryMode="single"  width="90"></t:dgCol>
          <t:dgCol title="增值税额"  field="zzse"  formatterjs="formatZzse" queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="退税率/%"  field="tsl"  formatterjs="formatTsl" queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="退税额"  field="tse"  formatterjs="formatTse" queryMode="single"  width="70"></t:dgCol>
          <t:dgCol title="备注"  field="remark" formatterjs="formatRemark" queryMode="single"   width="80"></t:dgCol>
          <%--<t:dgCol title="订单ID"  field="orderId"  queryMode="single"  width="120"></t:dgCol>--%>
          <%--<t:dgFunOpt funname="goAdd" title="添加" exp="createName#ne#1" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
             <t:dgFunOpt funname="editRow" title="编辑"  urlclass="ace_button" urlfont="fa-edit"></t:dgFunOpt>
             <t:dgDelOpt title="删除" operationCode="delete" exp="createName#ne#1" url="emkOrderDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
          <t:dgToolBar title="新增" icon="fa fa-plus" url="" funname="addRow"></t:dgToolBar>
          <%--<t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" funname="editRow"></t:dgToolBar>--%>
          <%--<t:dgToolBar title="保存" icon="fa fa-save" url="emkProductController.do?saveRows" funname="saveData"></t:dgToolBar>--%>
          <t:dgToolBar title="取消"  icon="fa fa-remove" url="" funname="removeit"></t:dgToolBar>
          <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOrderDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
          <%--<t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderDetailController.do?goAdd" funname="goAdd"></t:dgToolBar>--%>

          <%--<t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderDetailController.do?goAdd" funname="add"></t:dgToolBar>
          <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderDetailController.do?goUpdate" funname="update"></t:dgToolBar>
          <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOrderDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
      </t:datagrid>
  </c:if>

      <c:if test="${order.state ne 0 && order.state ne null}">
          <t:datagrid name="emkOrderDetailList" checkbox="false"  pagination="true" onClick="editRow" fitColumns="false" title="" actionUrl="emkOrderDetailController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
              <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
              <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
              <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
              <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
              <%--<t:dgCol title="操作" field="opt" width="180" hidden="${order.state eq 1 ? true:false}"  frozenColumn="true" extendParams="editor:'button'"></t:dgCol>--%>
              <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
              <t:dgCol title="产品编号"  field="proNum"   queryMode="single" width="100"></t:dgCol>
              <t:dgCol title="产品类型"  field="proName"  queryMode="single" width="100"></t:dgCol>
              <t:dgCol title="产品ID"  field="proId"  hidden="true" queryMode="single" width="100"></t:dgCol>
              <t:dgCol title="海关商品编码"  field="hairProNum"  queryMode="single"  width="90"></t:dgCol>
              <t:dgCol title="中文品名"  field="proZnName"  queryMode="single"  width="120"></t:dgCol>
              <t:dgCol title="英文品名"  field="proEnName"  queryMode="single"  width="90"></t:dgCol>
              <t:dgCol title="规格型号"  field="brand"  queryMode="single"  width="90"></t:dgCol>
              <t:dgCol title="签约数量"  field="signTotal"  queryMode="single"   width="70"></t:dgCol>
              <t:dgCol title="签约单位"  field="signUnit"  queryMode="single"  width="70"></t:dgCol>
              <t:dgCol title="签约单价"  field="signPrice"  queryMode="single"  width="80"></t:dgCol>
              <t:dgCol title="不含税金额"  field="notSignPrice"   queryMode="single"  width="70"></t:dgCol>
              <t:dgCol title="增值税率/%"  field="zzsl"  queryMode="single"  width="90"></t:dgCol>
              <t:dgCol title="增值税额"  field="zzse"  queryMode="single"  width="80"></t:dgCol>
              <t:dgCol title="退税率/%"  field="tsl"   queryMode="single"  width="80"></t:dgCol>
              <t:dgCol title="退税额"  field="tse"  queryMode="single"  width="70"></t:dgCol>
              <t:dgCol title="备注"  field="remark" formatterjs="formatRemark"  queryMode="single"   width="80"></t:dgCol>
          </t:datagrid>
      </c:if>
  </div>
 </div>

 <script src = "webpage/com/emk/bill/orderdetail/emkOrderDetailList.js"></script>		
 <script type="text/javascript">
     var indexFlag = 0;
     var currentFlag = 0;
 $(document).ready(function(){
     /*$("#proNum").bind("input propertychange change",function(event){
         $("#proName"+currentFlag).val($("#proTypeName").val());
         $("#proNum"+currentFlag).val($("#proNum").val());
         $("#proZnName"+currentFlag).val($("#proZnName").val());
         $("#proEnName"+currentFlag).val($("#proEnName").val());
         $("#brand"+currentFlag).val($("#brand").val());
     });*/
 });

     function setRemark(remarkId){
         alert(remarkId);
         //$("#remark"+remarkId).val($("#remarkVal").val());
     }
function openRemarkWin(remarkId){
         $.dialog({
             content: 'url:emkOrderDetailController.do?goRemark&remark='+$("#remark"+remarkId).val(),
             lock : true,
             zIndex: getzIndex(),
             width:400,
             height:200,
             title:'备注',
             opacity : 0.3,
             cache:false,
             ok: function(){
                 var iframe = this.iframe.contentWindow;
                 $("#remark"+remarkId).val($('#remark', iframe.document).val());
                 //return false;
             },
             cancelVal: '关闭',
             cancel: true /*为true等价于function(){}*/
         });
     }
 function productLook(indexFlag){
     currentFlag = indexFlag;
     $("#chkInfo").click();
 }
function returnToVal(){
    $("#hairProNum"+currentFlag).val($("#hsCode").val());
    $("#zzsl"+currentFlag).val($("#zzVal").val());
    $("#tsl"+currentFlag).val($("#tsVal").val());

    $("#proName"+currentFlag).val($("#proTypeName").val());
    $("#proNum"+currentFlag).val($("#proNum").val());
    $("#proZnName"+currentFlag).val($("#proZnName").val());
    $("#proEnName"+currentFlag).val($("#proEnName").val());
    $("#brand"+currentFlag).val($("#brand").val());
    $("#unit"+currentFlag).val($("#unit").val());
    $("#proId"+currentFlag).val($("#id").val());
}
 function goAdd(){
     createwindow("添加商品", "emkOrderDetailController.do?goAdd&orderId=${param.orderId}",900,500);
 }
     function setTotal(proId){
         $("#signTotal"+proId).val($("#signTotal"+proId).val().replace(/[^\d.]/g,""));
     }
     function setsignPrice(proId){
         $("#signPrice"+proId).val($("#signPrice"+proId).val().replace(/[^\d.]/g,""));
     }
     function setnotSignPrice(proId){
         $("#notSignPrice"+proId).val($("#notSignPrice"+proId).val().replace(/[^\d.]/g,""));
     }
     function formatProType(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="proId'+indexFlag+'"  name="proId'+indexFlag+'" value="" type="hidden"/><input readonly id="proName'+indexFlag+'" name="proName'+indexFlag+'" value=""  type="text" style="width: 100%" />';
         }else{
             s = '<input id="proId'+indexFlag+'"  name="proId'+indexFlag+'" value="'+row.proId+'" type="hidden"/><input readonly id="proName'+indexFlag+'" name="proName'+indexFlag+'" value="'+row.proName+'"   type="text" style="width: 100%" />';

         }
         return s;
     }
     function formatProNum(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="proNum'+indexFlag+'" readonly name="proNum'+indexFlag+'" value=""  type="text" onclick="productLook('+indexFlag+');" style="width: 100%" />';

         }else{
             s = '<input id="proNum'+indexFlag+'" readonly name="proNum'+indexFlag+'" value="'+row.proNum +'"  onclick="productLook('+indexFlag+');" type="text" style="width: 100%" />';
         }
         return s;
     }
     function formatHairProNum(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="hairProNum'+indexFlag+'" readonly name="hairProNum'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="hairProNum'+indexFlag+'" readonly name="hairProNum'+indexFlag+'" value="'+row.hairProNum +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatProZnName(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="proZnName'+indexFlag+'" readonly name="proZnName'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="proZnName'+indexFlag+'" readonly name="proZnName'+indexFlag+'" value="'+row.proZnName +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatProEnName(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="proEnName'+indexFlag+'" readonly name="proEnName'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="proEnName'+indexFlag+'" readonly name="proEnName'+indexFlag+'" value="'+row.proEnName +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatBrand(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="brand'+indexFlag+'" readonly name="brand'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="brand'+indexFlag+'" readonly name="brand'+indexFlag+'" value="'+row.brand +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatSignUnit(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="unit'+indexFlag+'" readonly name="unit'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="unit'+indexFlag+'" readonly name="unit'+indexFlag+'" value="'+row.signUnit +'"  type="text" style="width: 100%" />';
         }
         return s;

     }

     function formatSignTotal(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="signTotal'+indexFlag+'" onkeyup="javascript:setTotal('+index+');"  name="signTotal'+indexFlag+'" value="0"  type="text" style="width: 100%" />';

         }else{
             s = '<input id="signTotal'+indexFlag+'" onkeyup="javascript:setTotal('+index+');"  name="signTotal'+indexFlag+'" value="'+row.signTotal +'"  type="text" style="width: 100%" />';
         }
         return s;

     }

     function formatSignPrice(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="signPrice'+indexFlag+'" onkeyup="javascript:setsignPrice('+index+');" name="signPrice'+indexFlag+'" value="0"  type="text" style="width: 100%" />';

         }else{
             s = '<input id="signPrice'+indexFlag+'" onkeyup="javascript:setsignPrice('+index+');"  name="signPrice'+indexFlag+'" value="'+row.signPrice +'"  type="text" style="width: 100%" />';
         }
         return s;

     }

     function formatNotSignPrice(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="notSignPrice'+indexFlag+'" onkeyup="javascript:setnotSignPrice('+index+');" name="notSignPrice'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="notSignPrice'+indexFlag+'" onkeyup="javascript:setnotSignPrice('+index+');" name="notSignPrice'+indexFlag+'" value="'+row.notSignPrice +'"  type="text" style="width: 100%" />';
         }
         return s;

     }

     function formatZzsl(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="zzsl'+indexFlag+'" readonly name="zzsl'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="zzsl'+indexFlag+'" readonly name="zzsl'+indexFlag+'" value="'+row.zzsl +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatZzse(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="zzse'+indexFlag+'" name="zzse'+indexFlag+'" value="0"  type="text" style="width: 100%" />';

         }else{
             s = '<input id="zzse'+indexFlag+'" name="zzse'+indexFlag+'" value="'+row.zzse +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatTsl(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="tsl'+indexFlag+'" readonly name="tsl'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="tsl'+indexFlag+'" readonly name="tsl'+indexFlag+'" value="'+row.tsl +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatTse(val,row,index){
         var s = '';
         if(row.proNum == null|| row.proNum == ''){
             s = '<input id="tse'+indexFlag+'" name="tse'+indexFlag+'" value="0"  type="text" style="width: 100%" />';

         }else{
             s = '<input id="tse'+indexFlag+'" name="tse'+indexFlag+'" value="'+row.tse +'"  type="text" style="width: 100%" />';
         }
         return s;

     }
     function formatRemark(val,row,index){
         var s = '';
         if(row.proNum == null || row.proNum == '' ){
             s = '<input id="remark'+indexFlag+'" onclick="openRemarkWin('+indexFlag+');" name="remark'+indexFlag+'" value=""  type="text" style="width: 100%" />';

         }else{
             s = '<input id="remark'+indexFlag+'" onclick="openRemarkWin('+indexFlag+');" name="remark'+indexFlag+'" value="'+row.remark +'"  type="text" style="width: 100%" />';
             if(indexFlag < $('#emkOrderDetailList').datagrid('getRows').length-1){
                 indexFlag = indexFlag +1 ;
                 $("#indexFlag").val(indexFlag);
             }
         }
         return s;

     }
 //添加行
 function addRow(title,addurl,gname){
     indexFlag = indexFlag +1;
     $('#'+gname).datagrid('appendRow',{});
     var editIndex = $('#'+gname).datagrid('getRows').length-1;
     $('#'+gname).datagrid('selectRow', editIndex)
         .datagrid('beginEdit', editIndex);
     $("#indexFlag").val(indexFlag);
 }
 //保存数据
 function saveData(title,addurl,gname){
     var result={};
     for(var i=0;i<=indexFlag;i++){
         result["proNum"+i] = $("#proNum"+i).val();
         result["hairProNum"+i] = $("#hairProNum"+i).val();
         result["proName"+i] = $("#proName"+i).val();
         result["proZnName"+i] = $("#proZnName"+i).val();
         result["proEnName"+i] = $("#proEnName"+i).val();
         result["brand"+i] = $("#brand"+i).val();
         result["unit"+i] = $("#unit"+i).val();
         result["signTotal"+i] = $("#signTotal"+i).val();
         result["signPrice"+i] = $("#signPrice"+i).val();
         result["notSignPrice"+i] = $("#notSignPrice"+i).val();
         result["zzsl"+i] = $("#zzsl"+i).val();
         result["zzse"+i] = $("#zzse"+i).val();
         result["tsl"+i] = $("#tsl"+i).val();
         result["tse"+i] = $("#tse"+i).val();
         result["remark"+i] = $("#remark"+i).val();
         result["proId"+i] = $("#proId"+i).val();
     }
     $.ajax({
         url:"emkOrderDetailController.do?saveRows&orderId=${order.id}&dataSize="+indexFlag+"&data="+JSON.stringify(result),
         data:null,
         type:'post',
         success:function(data){
             var d = $.parseJSON(data);
             if (d.success) {
                 var msg = d.msg;
                 tip(msg);
             }
         }
     });
     /*if(!endEdit(gname))
         return false;
     var rows=$('#'+gname).datagrid("getChanges","inserted");
     var uprows=$('#'+gname).datagrid("getChanges","updated");
     rows=rows.concat(uprows);
     if(rows.length<=0){
         tip("没有需要保存的数据！")
         return false;
     }
     var result={};
     for(var i=0;i<rows.length;i++){
         result["demos["+i+"].proName"]=$("#proName1").val();
         for(var d in rows[i]){
             result["demos["+i+"]."+d]=rows[i][d];
         }
     }
     $.ajax({
         url:"emkOrderDetailController.do?saveRows&data="+JSON.stringify(result),
         data:null,
         type:'post',
         success:function(data){
             tip(data.msg);
             if(data.success){
                 reloadTable();
             }
         }
     });*/
 }
 //结束编辑
 function endEdit(gname){
     var  editIndex = $('#'+gname).datagrid('getRows').length-1;
     for(var i=0;i<=editIndex;i++){
         if($('#'+gname).datagrid('validateRow', i)){
             $('#'+gname).datagrid('endEdit', i);
         }else{

             tip("请选择必填项(带有红色三角形状的字段)!");

             return false;
         }
     }
     return true;
 }
 //编辑行
 function editRow(title,addurl,gname){
     var rows=$('#'+gname).datagrid("getChecked");
     /*if(rows.length==0){
         tip("请选择条目");
         return false;
     }*/
     for(var i=0;i<rows.length;i++){
         var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
         $('#'+gname).datagrid('beginEdit', index);
     }
 }

     function removeit(){
         var rowIndex=$('#emkOrderDetailList').datagrid('getRowIndex',$('#emkOrderDetailList').datagrid('getSelected'));
         $('#emkOrderDetailList').datagrid('deleteRow', rowIndex);
         /* var editIndex = $('#emkOrderDetailList').datagrid('getRows').length-1;
          $('#emkOrderDetailList').datagrid('deleteRow', editIndex);
          indexFlag = indexFlag -1;*/

     }

 //取消编辑
 function reject(title,addurl,gname){
     $('#'+gname).datagrid('clearChecked');
     $('#'+gname).datagrid('rejectChanges');
     indexFlag = 0;
     $("#indexFlag").val("0");
 }


 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderDetailController.do?upload', "emkOrderDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderDetailController.do?exportXls","emkOrderDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderDetailController.do?exportXlsByT","emkOrderDetailList");
}

 </script>