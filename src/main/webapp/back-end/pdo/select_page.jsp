<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Pets Friendly: Product Orders</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #EEE3CB;
	color: #967E76;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 1px ridge #967E76;
	height: 80px;
	text-align: center;
	border-radius: 10px;
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
</style>

</head>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>Pets Friendly: Product Orders</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>


	<p>This is the Home page for Pets Friendly: Product Orders</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllPdo.jsp'>List</a> all Product Orders. <br>
			<br></li>

<!-- 查詢 -->

		  <li>
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		        <b>輸入訂單編號 (如10001):</b>
		        <input type="text" name="pdoId">
		        <input type="hidden" name="action" value="getOne_For_Display">
		        <input type="submit" value="送出">
		    </FORM>
		  </li>

		  <jsp:useBean id="pdoSvc" scope="page" class="com.pdo.model.PdoService" />

		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		       <b>選擇訂單編號:</b>
		       <select size="1" name="pdoId">
		         <c:forEach var="pdoVO" items="${pdoSvc.all}" > 
		          <option value="${pdoVO.pdoId}">${pdoVO.pdoId}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		    </FORM>
		  </li>

		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		       <b>選擇會員編號:</b>
		       <select size="1" name="pdoId">
		         <c:forEach var="pdoVO" items="${pdoSvc.all}" > 
		          <option value="${pdoVO.pdoId}">${pdoVO.mebId}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		     </FORM>
		  </li>
		</ul>
<!-- 查詢結束 -->

		<h3>訂單管理：</h3>

		<ul>
			<li><a href='addPdo.jsp'>Add</a> a new product order.</li>
		</ul>
</body>
</html>