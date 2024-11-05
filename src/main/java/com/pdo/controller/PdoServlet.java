package com.pdo.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.pdo.model.*;

public class PdoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String str = req.getParameter("pdoId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號(10000-19999)");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer pdoId = Integer.valueOf(req.getParameter("pdoId"));
			if (pdoId > 19999 || pdoId < 10000) {
				errorMsgs.add("訂單編號: 格式不正確(10000-19999)");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PdoService pdoSvc = new PdoService();
			PdoVO pdoVO = pdoSvc.getOnePdo(pdoId);
			if (pdoVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("pdoVO", pdoVO); // 資料庫取出的pdoVO物件,存入req
			String url = "/back-end/pdo/listOnePdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		///

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer pdoId = Integer.parseInt(req.getParameter("pdoId"));
				
//				String mebId = req.getParameter("mebId");
//				
//				java.sql.Date pdoDate = java.sql.Date.valueOf(req.getParameter("pdoDate").trim());
				
				/***************************2.開始查詢資料****************************************/
				PdoService pdoSvc = new PdoService();
				PdoVO pdoVO = pdoSvc.getOnePdo(pdoId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pdoVO", pdoVO);         // 資料庫取出的pdoVO物件,存入req
				String url = "/back-end/pdo/update_pdo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}

		///
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer pdoId = Integer.valueOf(req.getParameter("pdoId"));
//			
//			Integer mebId = Integer.valueOf(req.getParameter("mebId"));
//			
//			java.sql.Date pdoDate = java.sql.Date.valueOf(req.getParameter("pdoDate").trim());

			Integer pdTotalPrice = null;
			try {
				pdTotalPrice = Integer.valueOf(req.getParameter("pdTotalPrice").trim());
			} catch (NumberFormatException e) {
				pdTotalPrice = 0;
				errorMsgs.add("訂單總金額請填整數");
			}

			String pdoStatus = req.getParameter("pdoStatus").trim();
			if (Integer.valueOf(pdoStatus) > 2 || Integer.valueOf(pdoStatus) < 0) {
				errorMsgs.add("訂單狀態請輸入0-2");
			}

			String paymentStatus = req.getParameter("paymentStatus").trim();
			if (Integer.valueOf(paymentStatus) > 1 || Integer.valueOf(paymentStatus) < 0) {
				errorMsgs.add("付款狀態請輸入0-1");
			}

			String shippingAddr = req.getParameter("shippingAddr").trim();
			if (shippingAddr == null || shippingAddr.trim().length() == 0) {
				errorMsgs.add("派送地址請勿空白");
			}

			String shippingMethod = req.getParameter("shippingMethod").trim();
			if (Integer.valueOf(shippingMethod) > 1 || Integer.valueOf(shippingMethod) < 0) {
				errorMsgs.add("配送方式請輸入0-1");
			}

			Integer pdoReviewRate = Integer.valueOf(req.getParameter("pdoReviewRate").trim());
			if (pdoReviewRate > 11 || pdoReviewRate < 0) {
				errorMsgs.add("訂單評分應介於0-10");
			}

			String pdoReviewComm = req.getParameter("pdoReviewComm");
			if (pdoReviewComm != null && pdoReviewComm.length() > 255) {
				errorMsgs.add("訂單評論文字不可超過255個字");
			}

			java.sql.Date createdTime = null;
			try {
				createdTime = java.sql.Date.valueOf(req.getParameter("createdTime").trim());
			} catch (IllegalArgumentException e) {
				createdTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			PdoVO pdoVO = new PdoVO();
//			pdoVO.setPdoId(pdoId);
//			pdoVO.setMebId(mebId);
//			pdoVO.setPdoDate(pdoDate);
			pdoVO.setPdTotalPrice(pdTotalPrice);
			pdoVO.setPdoStatus(pdoStatus);
			pdoVO.setPaymentStatus(paymentStatus);
			pdoVO.setShippingAddr(shippingAddr);
			pdoVO.setShippingMethod(shippingMethod);
			pdoVO.setPdoReviewRate(pdoReviewRate);
			pdoVO.setPdoReviewComm(pdoReviewComm);
			pdoVO.setCreatedTime(createdTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("pdoVO", pdoVO); // 含有輸入格式錯誤的pdoVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/update_pdo_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PdoService pdoSvc = new PdoService();
			pdoVO = pdoSvc.updatePdo(pdTotalPrice, pdoStatus, paymentStatus, shippingAddr, shippingMethod,
					pdoReviewRate, pdoReviewComm, createdTime);
//			pdoId, mebId, pdoDate,

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("pdoVO", pdoVO); // 資料庫update成功後,正確的的pdoVO物件,存入req
			String url = "/back-end/pdo/listOnePdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		///

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer mebId = Integer.valueOf(req.getParameter("mebId"));
			if (mebId == null) {
				errorMsgs.add("會員編號: 請勿空白");
			} else if (mebId < 2000 || mebId > 2999) {
				errorMsgs.add("會員編號: 範圍必需在2000到2999之間");
			}

			Integer pdTotalPrice = null;
			try {
				pdTotalPrice = Integer.valueOf(req.getParameter("pdTotalPrice").trim());
			} catch (NumberFormatException e) {
				pdTotalPrice = 0;
				errorMsgs.add("訂單總金額請填整數");
			}

			String pdoStatus = req.getParameter("pdoStatus").trim();
			if (Integer.valueOf(pdoStatus) > 2 || Integer.valueOf(pdoStatus) < 0) {
				errorMsgs.add("訂單狀態請輸入0-2");
			}

			String paymentStatus = req.getParameter("paymentStatus").trim();
			if (Integer.valueOf(paymentStatus) > 1 || Integer.valueOf(paymentStatus) < 0) {
				errorMsgs.add("付款狀態請輸入0-1");
			}

			String shippingAddr = req.getParameter("shippingAddr").trim();
			if (shippingAddr == null || shippingAddr.trim().length() == 0) {
				errorMsgs.add("派送地址請勿空白");
			}

			String shippingMethod = req.getParameter("shippingMethod").trim();
			if (Integer.valueOf(shippingMethod) > 1 || Integer.valueOf(shippingMethod) < 0) {
				errorMsgs.add("配送方式請輸入0-1");
			}

			Integer pdoReviewRate = Integer.valueOf(req.getParameter("pdoReviewRate").trim());
			if (pdoReviewRate > 11 || pdoReviewRate < 0) {
				errorMsgs.add("訂單評分應介於0-10");
			}

			String pdoReviewComm = req.getParameter("pdoReviewComm").trim();
			if (pdoReviewComm.length() > 255) {
				errorMsgs.add("訂單評論文字不可超過255個字");
			}

			java.sql.Date createdTime = null;
			try {
				createdTime = java.sql.Date.valueOf(req.getParameter("createdTime").trim());
			} catch (IllegalArgumentException e) {
				createdTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			PdoVO pdoVO = new PdoVO();
			pdoVO.setMebId(mebId);
			pdoVO.setPdTotalPrice(pdTotalPrice);
			pdoVO.setPdoStatus(pdoStatus);
			pdoVO.setPaymentStatus(paymentStatus);
			pdoVO.setShippingAddr(shippingAddr);
			pdoVO.setShippingMethod(shippingMethod);
			pdoVO.setPdoReviewRate(pdoReviewRate);
			pdoVO.setPdoReviewComm(pdoReviewComm);
			pdoVO.setCreatedTime(createdTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("pdoVO", pdoVO); // 含有輸入格式錯誤的pdoVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/addPdo.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PdoService pdoSvc = new PdoService();
			pdoVO = pdoSvc.addPdo(mebId, pdTotalPrice, pdoStatus, paymentStatus, shippingAddr, shippingMethod,
					pdoReviewRate, pdoReviewComm, createdTime);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/pdo/listAllPdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer empno = Integer.valueOf(req.getParameter("empno"));
//
//			/*************************** 2.開始刪除資料 ***************************************/
//			PdoService empSvc = new PdoService();
//			empSvc.deleteEmp(empno);
//
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//		}
	}
}
