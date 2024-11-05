<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pdo.model.*"%>
<!-- 此頁練習採用 EL 的寫法取值 -->

<%
PdoService pdoSvc = new PdoService();
List<PdoVO> list = pdoSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
	<head>
		<title>所有商品訂單 - listAllPDO.jsp</title>
		
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
			text-align: center;
		}
		
		.hover:hover{
			background-color: #FAF7F0;
			color: #4A4947;
			transition: 0.3s;
		}
		
		input {
			background-color: #967E76;
			color: #EEE3CB;
			border: 0px;
			border-radius: 5px;
		}
		
		input:hover {
			background-color: #D7C0AE;
			color: #EEE3CB;
			transition: 0.2s;
		}
		
		.back {
			width: 2%;
			vertical-align: middle;
			margin-right: 5px;
		}
		
		a{
			text-decoration:none;
		}
		
		a:hover {
			text-decoration:underline;
		}
		
		</style>
	
	</head>
	<body bgcolor='white'>
	
		<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
		<table id="table-1">
			<tr>
				<td>
					<h3>所有商品訂單 - listAllPDO.jsp</h3>
					<h4>
						<a href="select_page.jsp"><img class="back" src="<%=request.getContextPath()%>/back-end/pdo/images/back.png" border="0">回首頁</a>
					</h4>
				</td>
			</tr>
		</table>
	
		<table >
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
				<th>修改</th>
				<th>刪除</th>	
				
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="pdoVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
	
				<tr class="hover">
					<td>${pdoVO.pdoId}</td>
					<td>${pdoVO.mebId}</td>
					<td>${pdoVO.pdoDate}</td>
					<td>${pdoVO.pdTotalPrice}</td>
					<td>${pdoVO.pdoStatus}</td>
					<td>${pdoVO.paymentStatus}</td>
					<td>${pdoVO.shippingAddr}</td>
					<td>${pdoVO.shippingMethod}</td>
					<td>${pdoVO.pdoReviewRate}</td>
					<td>${pdoVO.pdoReviewComm}</td>
					<td>${pdoVO.createdTime}</td>
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" style="margin-bottom: 0px;">
					     <input type="submit" value="修改">
					     <input type="hidden" name="pdoId"  value="${pdoVO.pdoId}, ${pdoVO.mebId}, ${pdoVO.pdoDate}">
					     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					</td>
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" style="margin-bottom: 0px;">
					     <input type="submit" value="刪除">
					     <input type="hidden" name="pdoId"  value="${pdoVO.pdoId}">
					     <input type="hidden" name="action" value="delete"></FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	
	</body>
</html>