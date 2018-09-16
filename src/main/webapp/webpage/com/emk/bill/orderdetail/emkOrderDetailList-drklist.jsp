<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery-webos,easyui,tools,DatePicker,autocomplete"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
      <t:datagrid name="emkOrderDetailList" checkbox="true"  pagination="true" onClick="editRow" fitColumns="false" title="" actionUrl="emkOrderDetailController.do?drkdatagrid&rk_state=0&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
          <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="业务员"  field="ywy"   queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="客户名称"  field="cus_name"   queryMode="single" width="120"></t:dgCol>
          <t:dgCol title="采购单号"  field="order_num"   queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="合同币种"  field="htbz"   queryMode="single" width="70"></t:dgCol>
          <t:dgCol title="合同金额"  field="heji"   queryMode="single" width="80"></t:dgCol>
          <t:dgCol title="贸易国别"  field="trade_name"   queryMode="single" width="80"></t:dgCol>
          <t:dgCol title="合同条款"  field="httk"   queryMode="single" width="90"></t:dgCol>
          <t:dgCol title="签约日期"  field="sign_date"   queryMode="single" width="80"></t:dgCol>
          <t:dgCol title="目的地"  field="mdd"   queryMode="single" width="60"></t:dgCol>

          <t:dgCol title="产品编号"  field="pro_num"   queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="产品类型"  field="pro_name"  queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="产品ID"  field="pro_id"  hidden="true" queryMode="single" width="100"></t:dgCol>
          <t:dgCol title="海关商品编码"  field="hair_pro_num"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="中文品名"  field="pro_zn_name"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="英文品名"  field="pro_en_name"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="规格型号"  field="brand"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="签约数量"  field="sign_total"  queryMode="single"   width="65"></t:dgCol>
          <t:dgCol title="签约单位"  field="sign_unit"  queryMode="single"  width="65"></t:dgCol>
          <t:dgCol title="签约单价"  field="sign_price"  queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="不含税金额"  field="not_sign_price"   queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="增值税率(%)"  field="zzsl"  queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="增值税额"  field="zzse"  queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="退税率(%)"  field="tsl"   queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="退税额"  field="tse"  queryMode="single"  width="80"></t:dgCol>
          <t:dgCol title="备注"  field="remark"  queryMode="single"   width="80"></t:dgCol>

          <t:dgToolBar title="生成入库单" icon="fa fa-arrow-circle-right" url="emkOrderController.do?goAdd" funname="doInStorage"></t:dgToolBar>

      </t:datagrid>
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

     function doInStorage() {
         var rowsData = $('#emkOrderDetailList').datagrid('getSelections');
         var ids = [];
         if (!rowsData || rowsData.length == 0) {
             tip('请选择需要生成入库单数据');
             return;
         }
         for ( var i = 0; i < rowsData.length; i++) {
             ids.push(rowsData[i].id);
         }
         $.dialog.confirm('您是否确定生成入库单?', function(r) {
             if (r) {
                 $.ajax({
                     url : "emkOrderController.do?doInStorage&orderId=${param.orderId}&drkNo=${param.drkNo}&drkId=${param.drkId}&ids="+ids,
                     type : 'post',
                     cache : false,
                     data: null,
                     success : function(data) {
                         var d = $.parseJSON(data);
                         tip(d.msg);
                         if (d.success) {
                             //window.open("${webRoot}/gjsTestpaperController.do?reload&index=0&countflag=0");
                             //window.location.href = "emkOrderController.do?list";
                             $('#emkOrderDetailList').datagrid('reload');
                         }
                     }
                 });
             }
         });



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