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

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllPdo.jsp'>List</a> all Product Orders. <br>
			<br></li>

<!-- �d�� -->

		  <li>
		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		        <b>��J�q��s�� (�p10001):</b>
		        <input type="text" name="pdoId">
		        <input type="hidden" name="action" value="getOne_For_Display">
		        <input type="submit" value="�e�X">
		    </FORM>
		  </li>

		  <jsp:useBean id="pdoSvc" scope="page" class="com.pdo.model.PdoService" />

		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		       <b>��ܭq��s��:</b>
		       <select size="1" name="pdoId">
		         <c:forEach var="pdoVO" items="${pdoSvc.all}" > 
		          <option value="${pdoVO.pdoId}">${pdoVO.pdoId}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="�e�X">
		    </FORM>
		  </li>

		  <li>
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" >
		       <b>��ܷ|���s��:</b>
		       <select size="1" name="pdoId">
		         <c:forEach var="pdoVO" items="${pdoSvc.all}" > 
		          <option value="${pdoVO.pdoId}">${pdoVO.mebId}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="�e�X">
		     </FORM>
		  </li>
		</ul>
<!-- �d�ߵ��� -->

		<h3>�q��޲z�G</h3>

		<ul>
			<li><a href='addPdo.jsp'>Add</a> a new product order.</li>
		</ul>
</body>
</html>