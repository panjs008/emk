<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ymkCustomList" checkbox="false"  pagination="true"  pageSize="15" fitColumns="false" title="" btnCls="bootstrap" actionUrl="ymkCustomController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="135" frozenColumn="true"></t:dgCol>--%>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户编码"  field="cusNum"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="客户名称"  field="cusName"  query="true" queryMode="single"  width="190"></t:dgCol>
      <t:dgCol title="贸易国别"  field="guoJia" dictionary="trade"    queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"   queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务部门ID"  field="businesseDeptId" hidden="true"  queryMode="single"  width="190"></t:dgCol>
      <t:dgCol title="业务员" hidden="true"  field="businesser"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="业务跟单员" hidden="true"  field="tracer"   queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务跟单员" field="tracerName"   queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="生产跟单员" hidden="true"  field="developer"   queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务跟单员" field="developerName"   queryMode="single"  width="80"></t:dgCol>
     <%-- <t:dgCol title="币种"  field="bz"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="城市"  field="chengShi" dictionary="t_s_category,code,name"    queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="片区"  field="pianQu" dictionary="t_s_category,code,name"   queryMode="single"  width="60"></t:dgCol>--%>
      <%--<t:dgCol title="客户简称"  field="cusCode"  queryMode="single"  width="95"></t:dgCol>--%>
   <%--<t:dgCol title="联系人电话"  field="telphone"  queryMode="single"  width="90"></t:dgCol>--%>
  </t:datagrid>
  </div>
 </div>
