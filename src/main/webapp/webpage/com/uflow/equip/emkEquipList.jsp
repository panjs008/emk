<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkEquipList" checkbox="true" pagination="true" fitColumns="true" title="设备资产表" actionUrl="emkEquipController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="省份"  field="shengFen"  queryMode="single"  dictionary="t_s_category,code,name" width="75"></t:dgCol>
      <t:dgCol title="城市"  field="chengShi"  queryMode="single" dictionary="t_s_category,code,name" width="75"></t:dgCol>
      <t:dgCol title="片区"  field="pianQu"  queryMode="single" dictionary="t_s_category,code,name"  width="75"></t:dgCol>
      <t:dgCol title="固资编号"  field="gzbh" query="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="资产名称"  field="zcmc" query="true" queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="序列号"  field="xlh"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资产类型"  field="zclx"  queryMode="single" dictionary="t_s_category,code,name"  width="100"></t:dgCol>
   <t:dgCol title="品牌"  field="pp"  queryMode="single" dictionary="t_s_category,code,name"  width="90"></t:dgCol>
   <t:dgCol title="型号"  field="xh"  queryMode="single" dictionary="t_s_category,code,name"  width="120"></t:dgCol>

   <t:dgCol title="状态"  field="zt"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="归属"  field="gs"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="上架日期"  field="sjrq"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="托管人"  field="tgr"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="托管人邮箱"  field="tgryx"  queryMode="single"  width="90"></t:dgCol>

       <t:dgToolBar title="录入" icon="fa fa-plus" url="emkEquipController.do?goAdd" funname="addEquip" width="900" height="500"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkEquipController.do?goUpdate" funname="updateEquip" width="900" height="500"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="emkEquipController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="emkEquipController.do?goUpdate" funname="detail" width="900" height="500"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<div id="tempSearchColums" style="display: none;width:100px">
    <div name="searchColums">
        &nbsp;&nbsp;&nbsp;&nbsp;省份： <select id="shengFen" name="shengFen" style="width:100px;">
        <option value="">--选择--</option>
        <c:forEach var="item" items="${codeList}">
            <option value="${item.code}">${item.name}</option>
        </c:forEach>
    </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        城市： <select id="chengShi" name="chengShi" style="width:100px;">
    </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        片区： <select id="pianQu" name="pianQu" style="width:100px;">
    </select>
        &nbsp;&nbsp;&nbsp;&nbsp;资产类型：<select id="zclx" name="zclx" style="width:100px;">
        <option value="">--选择--</option>
        <c:forEach var="item" items="${codeList2}">
            <option value="${item.code}">${item.name}</option>
        </c:forEach>
    </select>
        &nbsp;&nbsp;&nbsp;&nbsp;品牌： <select id="pp" name="pp" style="width:100px;">
    </select>
      &nbsp;&nbsp;&nbsp;&nbsp;型号：
        <select id="xh" name="xh" style="width:100px;">
            <option value="">--选择--</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;序列号：
        <input id="xlh" name="xlh" type="text" style="width: 100px" class="inputxt" >
    </div>
</div>
 <script src = "webpage/com/uflow/equip/emkEquipList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#emkEquipListtb").find("div[name='searchColums']").find("form#emkEquipListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');

     $.ajax({
         url : "emkEquipController.do?getEquipType&code="+$("#zclx").val(),
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);

             if (d.success) {
                 var msg = d.msg;
                 var dataItems = new Array(); //定义一数组
                 dataItems = d.obj.split(";"); //字符分割
                 //W.document.location.reload(true);
                 $('#pp').empty();
                 var option2='';
                 option2 += '<option value="">--选择--</option>';
                 for (i=0;i<dataItems.length ;i++ ) {
                     var dataitem = new Array(); //定义一数组
                     dataitem = dataItems[i].split(","); //字符分割
                     if(dataitem[0]!=""){
                         option2 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
                     }
                 }
                 $("#pp").append(option2);
             }
         }
     });

     $("#shengFen").change(function(){
         $.ajax({
             url : "emkEquipController.do?getEquipType&code="+$("#shengFen").val(),
             type : 'post',
             cache : false,
             data: null,
             success : function(data) {
                 var d = $.parseJSON(data);
                 if (d.success) {
                     var msg = d.msg;
                     var dataItems = new Array(); //定义一数组
                     dataItems = d.obj.split(";"); //字符分割
                     //W.document.location.reload(true);
                     $('#chengShi').empty();
                     $('#pianQu').empty();


                     var option3='';
                     option3 += '<option value="">--选择--</option>';

                     for (i=0;i<dataItems.length ;i++ ) {
                         var dataitem = new Array(); //定义一数组
                         dataitem = dataItems[i].split(","); //字符分割
                         if(dataitem[0]!="") {
                             option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
                         }
                     }
                     $("#chengShi").append(option3);
                 }
             }
         });
     });
     $("#chengShi").change(function(){
         $.ajax({
             url : "emkEquipController.do?getEquipType&code="+$("#chengShi").val(),
             type : 'post',
             cache : false,
             data: null,
             success : function(data) {
                 var d = $.parseJSON(data);
                 if (d.success) {
                     var msg = d.msg;
                     var dataItems = new Array(); //定义一数组
                     dataItems = d.obj.split(";"); //字符分割
                     //W.document.location.reload(true);
                     $('#pianQu').empty();
                     var option3='';
                     option3 += '<option value="">--选择--</option>';

                     for (i=0;i<dataItems.length ;i++ ) {
                         var dataitem = new Array(); //定义一数组
                         dataitem = dataItems[i].split(","); //字符分割
                         if(dataitem[0]!="") {
                             option3 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';
                         }
                     }
                     $("#pianQu").append(option3);
                 }
             }
         });
     });
     $("#zclx").change(function(){
         $.ajax({
             url : "emkEquipController.do?getEquipType&code="+$("#zclx").val(),
             type : 'post',
             cache : false,
             data: null,
             success : function(data) {
                 var d = $.parseJSON(data);
                 if (d.success) {
                     var msg = d.msg;
                     var dataItems = new Array(); //定义一数组
                     dataItems = d.obj.split(";"); //字符分割
                     //W.document.location.reload(true);
                     $('#pp').empty();
                     $('#xh').empty();

                     var option4='';
                     option4 += '<option value="">--选择--</option>';

                     for (i=0;i<dataItems.length ;i++ ) {
                         var dataitem = new Array(); //定义一数组
                         dataitem = dataItems[i].split(","); //字符分割
                         if(dataitem[0]!=""){
                             option4 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';

                         }
                     }
                     $("#pp").append(option4);
                 }
             }
         });
     });

     $("#pp").change(function(){
         $.ajax({
             url : "emkEquipController.do?getEquipType&code="+$("#pp").val(),
             type : 'post',
             cache : false,
             data: null,
             success : function(data) {
                 var d = $.parseJSON(data);
                 if (d.success) {
                     var msg = d.msg;
                     var dataItems = new Array(); //定义一数组
                     dataItems = d.obj.split(";"); //字符分割
                     //W.document.location.reload(true);
                     $('#xh').empty();
                     var option4='';
                     option4 += '<option value="">--选择--</option>';

                     for (i=0;i<dataItems.length ;i++ ) {
                         var dataitem = new Array(); //定义一数组
                         dataitem = dataItems[i].split(","); //字符分割
                         if(dataitem[0]!=""){
                             option4 += '<option value='+dataitem[0]+'>'+dataitem[1]+'</option>';

                         }
                     }
                     $("#xh").append(option4);
                 }
             }
         });
     });
 });

 function addEquip(){
     add("添加设备资产", "emkEquipController.do?goAdd","emkEquipList","900px","500px");
 }

 function updateEquip() {
     var url = "emkEquipController.do?goUpdate";
     update('编辑设备资产', url, "emkEquipList","900px","500px");
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkEquipController.do?upload', "emkEquipList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkEquipController.do?exportXls","emkEquipList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkEquipController.do?exportXlsByT","emkEquipList");
}

 </script>