<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pdo.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�q���� - listOnePdo.jsp</title>

<style>
table#table-1 {
	background-color: #EEE3CB;
	border: 1px solid 967E76;
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

.back {
	width: 2%;
	vertical-align: middle;
	margin-right: 5px;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�q���� - listOnePdo.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img class="back"
						src="<%=request.getContextPath()%>/back-end/pdo/images/back.png"
						border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ӫ��q��s��</th>
			<th>�|���s��</th>
			<th>�q��ͦ����</th>
			<th>�q���`���B</th>
			<th>�q�檬�A</th>
			<th>�I�ڪ��A</th>
			<th>���e�a�}</th>
			<th>�t�e�覡</th>
			<th>�q�����</th>
			<th>�q����פ�r</th>
			<th>�����إ߮ɶ�</th>
		</tr>
		<tr>

			<td><%=pdoVO.getPdoId()%></td>
			<td><%=pdoVO.getMebId()%></td>
			<td><%=pdoVO.getPdoDate()%></td>
			<td><%=pdoVO.getPdTotalPrice()%></td>
			<td><%=pdoVO.getPdoStatus()%></td>
			<td><%=pdoVO.getPaymentStatus()%></td>
			<td><%=pdoVO.getShippingAddr()%></td>
			<td><%=pdoVO.getShippingMethod()%></td>
			<td><%=pdoVO.getPdoReviewRate()%></td>
			<td><%=pdoVO.getPdoReviewComm()%></td>
			<td><%=pdoVO.getCreatedTime()%></td>

		</tr>
	</table>

</body>
</html>