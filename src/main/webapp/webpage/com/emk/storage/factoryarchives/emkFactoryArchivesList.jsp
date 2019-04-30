<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="emkFactoryArchivesList" checkbox="false" pagination="true" fitColumns="false" title=""
                    actionUrl="emkFactoryArchivesController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"
                    queryMode="group">
            <t:dgCol title="主键" field="id" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建人名称" field="createName" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建人登录名称" field="createBy" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
            <t:dgCol title="创建日期" field="createDate" formatter="yyyy-MM-dd" hidden="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="所属部门" field="sysOrgCode" hidden="true" queryMode="single" width="150"></t:dgCol>
            <t:dgCol title="当前流程节点" field="processName" hidden="true" queryMode="single" width="120"></t:dgCol>

            <t:dgCol title="供应商档案编号" query="true" field="archivesNo" frozenColumn="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="工厂名称" query="true" field="companyNameZn" queryMode="single" width="200"></t:dgCol>
            <t:dgCol title="工厂代码" query="true" field="companyCode" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="地址" field="addressZn" queryMode="single" width="150"></t:dgCol>
            <t:dgCol title="电话" field="primaryContactTel" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="省份" field="shengFen" dictionary="t_s_category,code,name" queryMode="single"
                     width="80"></t:dgCol>
            <t:dgCol title="城市" field="chengShi" dictionary="t_s_category,code,name" queryMode="single"
                     width="80"></t:dgCol>
            <t:dgCol title="国家" field="guoJia" dictionary="trade" queryMode="single" width="90"></t:dgCol>
            <t:dgCol title="联系人" field="primaryContact" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="产品类型" dictionary="emk_product_type,id,content" field="proType" queryMode="single"
                     width="90"></t:dgCol>
            <t:dgCol title="产业类型" formatterjs="bhFormat" field="productClassification" queryMode="single"
                     width="200"></t:dgCol>
            <t:dgCol title="营业执照号" field="licenseNumber" queryMode="single" width="150"></t:dgCol>
            <t:dgCol title="有效期" field="permitExpirationDate" queryMode="single" width="80"></t:dgCol>
            <t:dgCol title="风险等级" field="level" replace="放心做单_2,能做单有风险_1,不能做单_0" queryMode="single"
                     width="100"></t:dgCol>
            <t:dgCol title="状态" field="state" formatterjs="formatColor" queryMode="single" width="100"></t:dgCol>
            <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process"
                        urlclass="ace_button" urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
            <t:dgToolBar title="录入" icon="fa fa-plus" url="emkFactoryArchivesController.do?goAdd&winTitle=录入供应商档案"
                         funname="add" width="1100" height="580"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkFactoryArchivesController.do?goUpdate&winTitle=编辑供应商档案"
                         funname="update" width="1100" height="580"></t:dgToolBar>
            <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doSubmitV"></t:dgToolBar>
            <t:dgToolBar title="删除" icon="fa fa-remove" url="emkFactoryArchivesController.do?doBatchDel"
                         funname="deleteALLSelect"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="fa fa-search" url="emkFactoryArchivesController.do?goUpdate" funname="detail"
                         width="1100" height="580"></t:dgToolBar>
            <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
            <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
            <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right" funname="doPrintPDF"></t:dgToolBar>

        </t:datagrid>
    </div>
</div>
<script src="webpage/com/emk/storage/factoryarchives/emkFactoryArchivesList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });

    function bhFormat(value, row, index) {
        var text = "";
        if (row.productClassification.indexOf('[0]') >= 0) {
            text += "Home communications, entertainment & hobby equipment,";
        }
        if (row.productClassification.indexOf('[1]') >= 0) {
            text += "Sports and Outdoor Recreation Equipment,";
        }
        if (row.productClassification.indexOf('[2]') >= 0) {
            text += "Furniture and Furnishings,";
        }
        if (row.productClassification.indexOf('[3]') >= 0) {
            text += "Packaging and Containers,";
        }
        if (row.productClassification.indexOf('[4]') >= 0) {
            text += "House appliance,";
        }
        if (row.productClassification.indexOf('[5]') >= 0) {
            text += "Toys,";
        }
        if (row.productClassification.indexOf('[6]') >= 0) {
            text += "Housewares (non-powered),";
        }
        if (row.productClassification.indexOf('[7]') >= 0) {
            text += "Personal care items,";
        }
        if (row.productClassification.indexOf('[8]') >= 0) {
            text += "Chemicals & chemical products,";
        }
        if (row.productClassification.indexOf('[9]') >= 0) {
            text += "Auto Part & Accessory,";
        }
        if (row.productClassification.indexOf('[10]') >= 0) {
            text += "Home workshop apparatus tools,";
        }
        if (row.productClassification.indexOf('[11]') >= 0) {
            text += "Beverage & Food,";
        }
        if (row.productClassification.indexOf('[12]') >= 0) {
            text += "Yard and garden,";
        }
        if (row.productClassification.indexOf('[13]') >= 0) {
            text += "Pet related products,";
        }
        if (row.productClassification.indexOf('[14]') >= 0) {
            text += "Child nursery equipment & supplies,";
        }
        if (row.productClassification.indexOf('[15]') >= 0) {
            text += "Medicine,";
        }
        if (row.productClassification.indexOf('[16]') >= 0) {
            text += "Personal use items,";
        }
        if (row.productClassification.indexOf('[17]') >= 0) {
            text += "Raw Material,";
        }
        if (row.productClassification.indexOf('[18]') >= 0) {
            text += "Garments, Footwear & Accessories,";
        }
        if (row.productClassification.indexOf('[19]') >= 0) {
            text += "Miscellaneous Products,";
        }

        if (row.productClassification.indexOf('[21]') >= 0) {
            text += "Servicing Facilities,";
        }
        if (row.productClassification.indexOf('[23]') >= 0) {
            text += "Other,";
        }
        if (row.productClassification.indexOf('[25]') >= 0) {
            text += "Pet related products,";
        }
        return text;
    }
    function doPrintPDF() {
        var rowsData = $('#emkFactoryArchivesList').datagrid('getSelections');
        var ids = [];
        if (!rowsData || rowsData.length == 0) {
            tip('请选择需要导出PDF的供应商档案');
            return;
        }
        for (var i = 0; i < rowsData.length; i++) {
            ids.push(rowsData[i].id);
        }
        $.dialog.confirm('您是否确定导出PDF的供应商档案?', function (r) {
            if (r) {
                window.location.href = "emkFactoryArchivesController.do?doPrintPDF&ids=" + ids;
            }
        });
    }

    function formatColor(val, row) {
        if (row.state == "1") {
            return '<span style="color:	#0000FF;">已提交</span>';
        }else if (row.state == "2") {
            return '<span style="color:	#00FF00;">完成</span>';
        }else if (row.state == "3") {
            return '<span style="color:	#0000FF;">业务经理通过</span>';
        }else if(row.state=="4"){
            return '<span style="color:	#0000FF;">回退业务经理</span>';
        }else if(row.state=="21"){
            return '<span style="color:	#0000FF;">回退业务员</span>';
        }else if(row.state=="48"){
            return '<span style="color:	#0000FF;">总经理通过</span>';
        }else if (row.state == "51") {
            return '<span style="color:	#0000FF;">验厂经理通过</span>';
        }else if (row.state == "52") {
            return '<span style="color:	#0000FF;">执行验厂</span>';
        }else if(row.state=="53"){
            return '<span style="color:	#0000FF;">验厂报告</span>';
        }else {
            return '创建';
        }
    }


    function goToProcess(id, createBy, processName, state) {
        var height = window.top.document.body.offsetHeight * 0.85;
        var processNameVal = '', node = '';
        if (processName != null) {
            if (processName.indexOf('-') > 0) {
                processNameVal = processName.substring(0, processName.indexOf('-'));
                node = processName.substring(processName.indexOf('-') + 1);
            }
        }
        if (createBy == "${CUR_USER.userName}" || ${CUR_USER.userName eq 'admin'}) {
            if (state == '0') {
                createwindow("流程进度--当前环节：工厂信息表", "flowController.do?goProcess&node=" + node + "&processUrl=com/emk/storage/factoryarchives/emkFactoryArchives-process&id=" + id, 1300, height);
            } else {
                createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&node=" + node + "&state="+state+"&processUrl=com/emk/storage/factoryarchives/emkFactoryArchives-process&id=" + id, 1300, height);
            }
        }
    }

    function doSubmitV() {
        var rowsData = $('#emkFactoryArchivesList').datagrid('getSelections');
        var ids = [];
        if (!rowsData || rowsData.length == 0) {
            tip('请选择需要提交的供应商档案');
            return;
        }
        for (var i = 0; i < rowsData.length; i++) {
            ids.push(rowsData[i].id);
        }
        $.dialog.confirm('您是否确定提交供应商档案?', function (r) {
            if (r) {
                $.ajax({
                    url: "emkFactoryArchivesController.do?doSubmit&ids=" + ids,
                    type: 'post',
                    cache: false,
                    data: null,
                    success: function (data) {
                        var d = $.parseJSON(data);
                        tip(d.msg);
                        if (d.success) {
                            $('#emkFactoryArchivesList').datagrid('reload');
                        }
                    }
                });
            }
        });
    }
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'emkFactoryArchivesController.do?upload', "emkFactoryArchivesList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("emkFactoryArchivesController.do?exportXls", "emkFactoryArchivesList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("emkFactoryArchivesController.do?exportXlsByT", "emkFactoryArchivesList");
    }

</script>