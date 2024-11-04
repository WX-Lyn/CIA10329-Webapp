<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pdo.model.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的pdoVO物件 (此為輸入格式有錯誤時的pdoVO物件)
   PdoVO pdoVO = (PdoVO) request.getAttribute("pdoVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品訂單新增 - addPDO.jsp</title>

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
		 <h3>商品訂單新增 - addPDO.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pdo/pdo.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mebId" value="<%= (pdoVO==null)? "會員編號" : pdoVO.getMebId()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>訂單總金額:</td>
		<td><input type="TEXT" name="pdTotalPrice"   value="<%= (pdoVO==null)? "訂單總金額" : pdoVO.getPdTotalPrice()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="pdoStatus"   value="<%= (pdoVO==null)? "訂單狀態" : pdoVO.getPdoStatus()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>付款狀態:</td>
		<td><input type="TEXT" name="paymentStatus"   value="<%= (pdoVO==null)? "付款狀態" : pdoVO.getPaymentStatus()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>派送地址:</td>
		<td><input type="TEXT" name="shippingAddr"   value="<%= (pdoVO==null)? "派送地址" : pdoVO.getShippingAddr()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>配送方式:</td>
		<td><input type="TEXT" name="shippingMethod"   value="<%= (pdoVO==null)? "配送方式" : pdoVO.getShippingMethod()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>訂單評分:</td>
		<td><input type="TEXT" name="pdoReviewRate"   value="<%= (pdoVO==null)? "訂單評分" : pdoVO.getPdoReviewRate()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>訂單評論文字:</td>
		<td><input type="TEXT" name="pdoReviewComm"   value="<%= (pdoVO==null)? "訂單評論文字" : pdoVO.getPdoReviewComm()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>評價建立時間:</td>
		<td><input name="createdTime" type="date" ></td>
<!-- 		不用這個月曆而用HTML的話，刪掉id以及type改成date -->
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>

</html>