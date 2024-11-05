package com.pdo.model;

import java.util.List;

public class PdoService {

	private PdoDAO_interface dao;

	public PdoService() {
		dao = new PdoJDBCDAO();
	}

	public PdoVO addPdo(Integer mebId, Integer pdTotalPrice, String pdoStatus, String paymentStatus, String shippingAddr, String shippingMethod, Integer pdoReviewRate, String pdoReviewComm, java.sql.Date createdTime) {

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
		dao.insert(pdoVO);

		return pdoVO;
	}

//	public PdoVO updateEmp(Integer pdo_id, Integer meb_id, java.sql.Date pdo_date, Integer pd_total_price, String pdo_status, String payment_status, String shipping_addr, String shipping_method, Integer pdo_review_rate, String pdo_review_comm, java.sql.Date created_time) {
	public PdoVO updatePdo(Integer pdTotalPrice, String pdoStatus, String paymentStatus, String shippingAddr, String shippingMethod, Integer pdoReviewRate, String pdoReviewComm, java.sql.Date createdTime) {
//		Integer pdoId, Integer mebId, java.sql.Date pdoDate,
		PdoVO pdoVO = new PdoVO();
//		pdoVO.setPdoId(pdoId);
//		pdoVO.setMebId(mebId);
//		pdoVO.setPdoDate(pdoDate);
		pdoVO.setPdTotalPrice(pdTotalPrice);
		pdoVO.setPdoStatus(pdoStatus);
		pdoVO.setPaymentStatus(paymentStatus);
		pdoVO.setShippingAddr(shippingAddr);
		pdoVO.setShippingMethod(shippingMethod);
		pdoVO.setPdoReviewRate(pdoReviewRate);
		pdoVO.setPdoReviewComm(pdoReviewComm);
		pdoVO.setCreatedTime(createdTime);
		return pdoVO;
	}
//
//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}
//
	public PdoVO getOnePdo(Integer pdoId) {
		return dao.findByPrimaryKey(pdoId);
	}

	public List<PdoVO> getAll() {
		return dao.getAll();
	}
}
