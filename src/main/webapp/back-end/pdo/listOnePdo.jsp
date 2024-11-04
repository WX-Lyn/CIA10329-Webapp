<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pdo.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂單資料 - listOnePdo.jsp</title>

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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>訂單資料 - listOnePdo.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img class="back"
						src="<%=request.getContextPath()%>/back-end/pdo/images/back.png"
						border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商城訂單編號</th>
			<th>會員編號</th>
			<th>訂單生成日期</th>
			<th>訂單總金額</th>
			<th>訂單狀態</th>
			<th>付款狀態</th>
			<th>派送地址</th>
			<th>配送方式</th>
			<th>訂單評分</th>
			<th>訂單評論文字</th>
			<th>評價建立時間</th>
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