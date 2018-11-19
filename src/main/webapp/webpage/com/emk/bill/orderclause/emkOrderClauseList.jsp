<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link type="text/css" rel="stylesheet" href="plug-in/select2/css/select2.min.css"/>
<script type="text/javascript" src="plug-in/select2/js/select2.js"></script>
<script type="text/javascript" src="plug-in/select2/js/pinyin.js"></script>
&nbsp;&nbsp;&nbsp;&nbsp;
条款编号：
<select id="clause_num" name="clause_num" style="width:150px;"></select><a href="#" class="ace_button" onclick="">  <i class=" fa fa-cog"></i>合同内容</a>
<%--
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:2px;border:0px">
      <t:datagrid name="emkOrderClauseList" checkbox="false"  pagination="false" fitColumns="false" title="" actionUrl="emkOrderClauseController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap" queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
          <t:dgCol title="操作" field="opt" hidden="${param.state ne 0 ? true:false}" width="140" ></t:dgCol>
          <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
       <t:dgCol title="条款编号"  field="clauseNum" queryMode="single"  width="200"></t:dgCol>
       <t:dgCol title="内容描述"  field="clauseContent"  queryMode="single"  width="350"></t:dgCol>
          <t:dgFunOpt funname="goAdd" title="添加" exp="createName#ne#1" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
   <t:dgDelOpt title="删除" operationCode="delete" exp="createName#ne#1" url="emkOrderClauseController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   &lt;%&ndash;<t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkOrderClauseController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkOrderClauseController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkOrderClauseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>&ndash;%&gt;
  </t:datagrid>
  </div>
 </div>--%>
<%--
<div id="tempSearchColums" style="width:100px">
    <div name="searchColums">
        &nbsp;&nbsp;&nbsp;&nbsp;
        合同条款：
    </select>

    </div>
</div>--%>
 <script src = "webpage/com/emk/bill/orderclause/emkOrderClauseList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
   /*  $("#emkOrderClauseListtb").find("div[name='searchColums']").find("form#emkOrderClauseListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');*/
     BindSelect("clause_num","",0,"");

 });

 function BindSelect(ctrlName, url,type,categoryId) {
     var control = $('#' + ctrlName);
     //设置Select2的处理
     control.select2({
         formatResult: formatState,
         formatSelection: formatState,
         escapeMarkup: function (m) {
             return m;
         }
     });
     //绑定Ajax的内容
     $.getJSON('emkClauseController.do?findClauseList', function (data) {
         control.empty();//清空下拉框

         $.each(data.obj, function (i, item) {
             control.append("<option value='" + item.clause_num + "'>&nbsp;" + item.clause_num + "</option>");
         });

         if(type ==1){
             $("#"+ctrlName).select2('val',categoryId);
         }
     });
 }

 function formatState (state) {
     if (!state.id) { return state.text; }
     var $state = $(
         '<span>' + state.text + '</span>'
     );
     return $state;
 };

  function goAdd(){
      createwindow("添加合同条款", "emkOrderClauseController.do?goAdd&orderId=${param.orderId}",600,300);
  }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkOrderClauseController.do?upload', "emkOrderClauseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkOrderClauseController.do?exportXls","emkOrderClauseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkOrderClauseController.do?exportXlsByT","emkOrderClauseList");
}

 </script>