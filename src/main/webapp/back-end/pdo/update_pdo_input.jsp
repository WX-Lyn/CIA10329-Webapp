<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pdo.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_pdo_input.jsp</title>

<style>
table#table-1 {
	background-color: #EEE3CB;
	border: 1px solid #967E76;
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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border-radius: 10px;
}

th {
	background-color: #EEE3CB;
	color: #967E76;
	text-align: center;
}

table, th, td {
	border: 1px solid #967E76;
}

th, td {
	padding: 10px;
}

.back {
	width: 2%;
	vertical-align: middle;
	margin-right: 5px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�q���ƭק� - update_pdo_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img class="back"
						src="<%=request.getContextPath()%>/back-end/pdo/images/back.png"
						border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do"
		name="form1">
		<table>
			<tr>
				<td>�q��s��:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getPdoId()%></td>
			</tr>

			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getMebId()%></td>
			</tr>

			<tr>
				<td>�q��ͦ����:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getPdoDate()%></td>
			</tr>

			<tr>
				<td>�q���`���B:</td>
				<td><input type="TEXT" name="pdTotalPrice"
					value="<%=pdoVO.getPdTotalPrice()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�q�檬�A:</td>
				<td><input type="TEXT" name="pdoStatus"
					value="<%=pdoVO.getPdoStatus()%>" size="45" /></td>
			</tr>

			<tr>
				<td>�I�ڪ��A:</td>
				<td><input type="TEXT" name="paymentStatus"
					value="<%=pdoVO.getPaymentStatus()%>" size="45" /></td>
			</tr>

			<tr>
				<td>���e�a�}:</td>
				<td><input type="TEXT" name="shippingAddr"
					value="<%=pdoVO.getShippingAddr()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�t�e�覡:</td>
				<td><input type="TEXT" name="shippingMethod"
					value="<%=pdoVO.getShippingMethod()%>" size="45" /></td>
			</tr>

			<tr>
				<td>�q�����:</td>
				<td><input type="TEXT" name="pdoReviewRate"
					value="<%=pdoVO.getPdoReviewRate()%>" size="45" /></td>
			</tr>

			<tr>
				<td>�q����פ�r:</td>
				<td><input type="TEXT" name="pdoReviewComm"
					value="<%=pdoVO.getPdoReviewComm()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>�����إ߮ɶ�:</td>
				<td><input name="createdTime" type="date"></td>
				<!-- 		���γo�Ӥ��ӥ�HTML���ܡA�R��id�H��type�令date -->
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="pdoId" value="<%=pdoVO.getPdoId()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>