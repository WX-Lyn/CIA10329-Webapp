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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

			String str = req.getParameter("pdoId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�q��s��(10000-19999)");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			Integer pdoId = Integer.valueOf(req.getParameter("pdoId"));
			if (pdoId > 19999 || pdoId < 10000) {
				errorMsgs.add("�q��s��: �榡�����T(10000-19999)");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			PdoService pdoSvc = new PdoService();
			PdoVO pdoVO = pdoSvc.getOnePdo(pdoId);
			if (pdoVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("pdoVO", pdoVO); // ��Ʈw���X��pdoVO����,�s�Jreq
			String url = "/back-end/pdo/listOnePdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}
		
		///

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer pdoId = Integer.parseInt(req.getParameter("pdoId"));
				
//				String mebId = req.getParameter("mebId");
//				
//				java.sql.Date pdoDate = java.sql.Date.valueOf(req.getParameter("pdoDate").trim());
				
				/***************************2.�}�l�d�߸��****************************************/
				PdoService pdoSvc = new PdoService();
				PdoVO pdoVO = pdoSvc.getOnePdo(pdoId);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("pdoVO", pdoVO);         // ��Ʈw���X��pdoVO����,�s�Jreq
				String url = "/back-end/pdo/update_pdo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
		}

		///
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
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
				errorMsgs.add("�q���`���B�ж���");
			}

			String pdoStatus = req.getParameter("pdoStatus").trim();
			if (Integer.valueOf(pdoStatus) > 2 || Integer.valueOf(pdoStatus) < 0) {
				errorMsgs.add("�q�檬�A�п�J0-2");
			}

			String paymentStatus = req.getParameter("paymentStatus").trim();
			if (Integer.valueOf(paymentStatus) > 1 || Integer.valueOf(paymentStatus) < 0) {
				errorMsgs.add("�I�ڪ��A�п�J0-1");
			}

			String shippingAddr = req.getParameter("shippingAddr").trim();
			if (shippingAddr == null || shippingAddr.trim().length() == 0) {
				errorMsgs.add("���e�a�}�ФŪť�");
			}

			String shippingMethod = req.getParameter("shippingMethod").trim();
			if (Integer.valueOf(shippingMethod) > 1 || Integer.valueOf(shippingMethod) < 0) {
				errorMsgs.add("�t�e�覡�п�J0-1");
			}

			Integer pdoReviewRate = Integer.valueOf(req.getParameter("pdoReviewRate").trim());
			if (pdoReviewRate > 11 || pdoReviewRate < 0) {
				errorMsgs.add("�q�����������0-10");
			}

			String pdoReviewComm = req.getParameter("pdoReviewComm");
			if (pdoReviewComm != null && pdoReviewComm.length() > 255) {
				errorMsgs.add("�q����פ�r���i�W�L255�Ӧr");
			}

			java.sql.Date createdTime = null;
			try {
				createdTime = java.sql.Date.valueOf(req.getParameter("createdTime").trim());
			} catch (IllegalArgumentException e) {
				createdTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�J���!");
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
				req.setAttribute("pdoVO", pdoVO); // �t����J�榡���~��pdoVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/update_pdo_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			PdoService pdoSvc = new PdoService();
			pdoVO = pdoSvc.updatePdo(pdTotalPrice, pdoStatus, paymentStatus, shippingAddr, shippingMethod,
					pdoReviewRate, pdoReviewComm, createdTime);
//			pdoId, mebId, pdoDate,

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("pdoVO", pdoVO); // ��Ʈwupdate���\��,���T����pdoVO����,�s�Jreq
			String url = "/back-end/pdo/listOnePdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}
		
		///

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			Integer mebId = Integer.valueOf(req.getParameter("mebId"));
			if (mebId == null) {
				errorMsgs.add("�|���s��: �ФŪť�");
			} else if (mebId < 2000 || mebId > 2999) {
				errorMsgs.add("�|���s��: �d�򥲻ݦb2000��2999����");
			}

			Integer pdTotalPrice = null;
			try {
				pdTotalPrice = Integer.valueOf(req.getParameter("pdTotalPrice").trim());
			} catch (NumberFormatException e) {
				pdTotalPrice = 0;
				errorMsgs.add("�q���`���B�ж���");
			}

			String pdoStatus = req.getParameter("pdoStatus").trim();
			if (Integer.valueOf(pdoStatus) > 2 || Integer.valueOf(pdoStatus) < 0) {
				errorMsgs.add("�q�檬�A�п�J0-2");
			}

			String paymentStatus = req.getParameter("paymentStatus").trim();
			if (Integer.valueOf(paymentStatus) > 1 || Integer.valueOf(paymentStatus) < 0) {
				errorMsgs.add("�I�ڪ��A�п�J0-1");
			}

			String shippingAddr = req.getParameter("shippingAddr").trim();
			if (shippingAddr == null || shippingAddr.trim().length() == 0) {
				errorMsgs.add("���e�a�}�ФŪť�");
			}

			String shippingMethod = req.getParameter("shippingMethod").trim();
			if (Integer.valueOf(shippingMethod) > 1 || Integer.valueOf(shippingMethod) < 0) {
				errorMsgs.add("�t�e�覡�п�J0-1");
			}

			Integer pdoReviewRate = Integer.valueOf(req.getParameter("pdoReviewRate").trim());
			if (pdoReviewRate > 11 || pdoReviewRate < 0) {
				errorMsgs.add("�q�����������0-10");
			}

			String pdoReviewComm = req.getParameter("pdoReviewComm").trim();
			if (pdoReviewComm.length() > 255) {
				errorMsgs.add("�q����פ�r���i�W�L255�Ӧr");
			}

			java.sql.Date createdTime = null;
			try {
				createdTime = java.sql.Date.valueOf(req.getParameter("createdTime").trim());
			} catch (IllegalArgumentException e) {
				createdTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�J���!");
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
				req.setAttribute("pdoVO", pdoVO); // �t����J�榡���~��pdoVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pdo/addPdo.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			PdoService pdoSvc = new PdoService();
			pdoVO = pdoSvc.addPdo(mebId, pdTotalPrice, pdoStatus, paymentStatus, shippingAddr, shippingMethod,
					pdoReviewRate, pdoReviewComm, createdTime);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			String url = "/back-end/pdo/listAllPdo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.�����ШD�Ѽ� ***************************************/
//			Integer empno = Integer.valueOf(req.getParameter("empno"));
//
//			/*************************** 2.�}�l�R����� ***************************************/
//			PdoService empSvc = new PdoService();
//			empSvc.deleteEmp(empno);
//
//			/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//			successView.forward(req, res);
//		}
	}
}
