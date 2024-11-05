<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pdo.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_pdo_input.jsp</title>

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
				<h3>訂單資料修改 - update_pdo_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img class="back"
						src="<%=request.getContextPath()%>/back-end/pdo/images/back.png"
						border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
				<td>訂單編號:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getPdoId()%></td>
			</tr>

			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getMebId()%></td>
			</tr>

			<tr>
				<td>訂單生成日期:<font color=red><b>*</b></font></td>
				<td><%=pdoVO.getPdoDate()%></td>
			</tr>

			<tr>
				<td>訂單總金額:</td>
				<td><input type="TEXT" name="pdTotalPrice"
					value="<%=pdoVO.getPdTotalPrice()%>" size="45" /></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><input type="TEXT" name="pdoStatus"
					value="<%=pdoVO.getPdoStatus()%>" size="45" /></td>
			</tr>

			<tr>
				<td>付款狀態:</td>
				<td><input type="TEXT" name="paymentStatus"
					value="<%=pdoVO.getPaymentStatus()%>" size="45" /></td>
			</tr>

			<tr>
				<td>派送地址:</td>
				<td><input type="TEXT" name="shippingAddr"
					value="<%=pdoVO.getShippingAddr()%>" size="45" /></td>
			</tr>
			<tr>
				<td>配送方式:</td>
				<td><input type="TEXT" name="shippingMethod"
					value="<%=pdoVO.getShippingMethod()%>" size="45" /></td>
			</tr>

			<tr>
				<td>訂單評分:</td>
				<td><input type="TEXT" name="pdoReviewRate"
					value="<%=pdoVO.getPdoReviewRate()%>" size="45" /></td>
			</tr>

			<tr>
				<td>訂單評論文字:</td>
				<td><input type="TEXT" name="pdoReviewComm"
					value="<%=pdoVO.getPdoReviewComm()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>評價建立時間:</td>
				<td><input name="createdTime" type="date"></td>
				<!-- 		不用這個月曆而用HTML的話，刪掉id以及type改成date -->
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="pdoId" value="<%=pdoVO.getPdoId()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>
</html>