<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pdo.model.*"%>

<% //��com.emp.controller.EmpServlet.java��238��s�Jreq��pdoVO���� (������J�榡�����~�ɪ�pdoVO����)
   PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�q��s�W - addPDO.jsp</title>

<style>
  table#table-1 {
	background-color: #EEE3CB;
	border: 1px ridge #967E76;
    color: #967E76;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	border-radius: 10px;
  }
  table, th, td {
    border: 0px solid #967E76;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~�q��s�W - addPDO.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mebId" value="<%= (pdoVO==null)? "�|���s��" : pdoVO.getMebId()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�q���`���B:</td>
		<td><input type="TEXT" name="pdTotalPrice"   value="<%= (pdoVO==null)? "�q���`���B" : pdoVO.getPdTotalPrice()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�q�檬�A:</td>
		<td><input type="TEXT" name="pdoStatus"   value="<%= (pdoVO==null)? "�q�檬�A" : pdoVO.getPdoStatus()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�I�ڪ��A:</td>
		<td><input type="TEXT" name="paymentStatus"   value="<%= (pdoVO==null)? "�I�ڪ��A" : pdoVO.getPaymentStatus()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>���e�a�}:</td>
		<td><input type="TEXT" name="shippingAddr"   value="<%= (pdoVO==null)? "���e�a�}" : pdoVO.getShippingAddr()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�t�e�覡:</td>
		<td><input type="TEXT" name="shippingMethod"   value="<%= (pdoVO==null)? "�t�e�覡" : pdoVO.getShippingMethod()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�q�����:</td>
		<td><input type="TEXT" name="pdoReviewRate"   value="<%= (pdoVO==null)? "�q�����" : pdoVO.getPdoReviewRate()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�q����פ�r:</td>
		<td><input type="TEXT" name="pdoReviewComm"   value="<%= (pdoVO==null)? "�q����פ�r" : pdoVO.getPdoReviewComm()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�����إ߮ɶ�:</td>
		<td><input name="createdTime" type="date" ></td>
<!-- 		���γo�Ӥ��ӥ�HTML���ܡA�R��id�H��type�令date -->
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>

</html>