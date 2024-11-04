package com.pdo.model;

import java.util.*;
import java.sql.*;

public class PdoJDBCDAO implements PdoDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/petsfriendly?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "lin20084";

	private static final String INSERT_STMT = 
		"INSERT INTO product_order (MEB_ID, PD_TOTAL_PRICE, PDO_STATUS, PAYMENT_STATUS, SHIPPING_ADDR, SHIPPING_METHOD, PDO_REVIEW_RATE, PDO_REVIEW_COMM, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT PDO_ID, MEB_ID, PDO_DATE, PD_TOTAL_PRICE, PDO_STATUS, PAYMENT_STATUS, SHIPPING_ADDR, SHIPPING_METHOD, PDO_REVIEW_RATE, PDO_REVIEW_COMM, CREATED_TIME FROM  product_order order by PDO_ID";
//	
	private static final String GET_ONE_STMT = 
		"SELECT PDO_ID, MEB_ID, PDO_DATE, PD_TOTAL_PRICE, PDO_STATUS, PAYMENT_STATUS, SHIPPING_ADDR, SHIPPING_METHOD, PDO_REVIEW_RATE, PDO_REVIEW_COMM, CREATED_TIME FROM product_order where PDO_ID = ?";

//	private static final String DELETE = 
//		"DELETE FROM emp2 where empno = ?";

	private static final String UPDATE = 
		"UPDATE product_order set meb_id=?, pdo_date=?, pd_total_price=?, pdo_status=?, payment_status=?, shipping_addr=?, shipping_method=?, pdo_review_rate=?, pdo_review_comm=?, created_time=? where pdo_id = ?";

	
	
	@Override
	public void insert(PdoVO pdoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pdoVO.getMebId());
			pstmt.setInt(2, pdoVO.getPdTotalPrice());
			pstmt.setString(3, pdoVO.getPdoStatus());
			pstmt.setString(4, pdoVO.getPaymentStatus());
			pstmt.setString(5, pdoVO.getShippingAddr());
			pstmt.setString(6, pdoVO.getShippingMethod());
			pstmt.setInt(7, pdoVO.getPdoReviewRate());
			pstmt.setString(8, pdoVO.getPdoReviewComm());
			pstmt.setDate(9, pdoVO.getCreatedTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(PdoVO pdoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, pdoVO.getMebId());
			pstmt.setDate(2, pdoVO.getPdoDate());
			pstmt.setInt(3, pdoVO.getPdTotalPrice());
			pstmt.setString(4, pdoVO.getPdoStatus());
			pstmt.setString(5, pdoVO.getPaymentStatus());
			pstmt.setString(6, pdoVO.getShippingAddr());
			pstmt.setString(7, pdoVO.getShippingMethod());
			pstmt.setInt(8, pdoVO.getPdoReviewRate());
			pstmt.setString(9, pdoVO.getPdoReviewComm());
			pstmt.setDate(10, pdoVO.getCreatedTime());
			pstmt.setInt(11, pdoVO.getPdoId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//	@Override
//	public void delete(Integer empno) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, empno);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public PdoVO findByPrimaryKey(Integer empno) {

		PdoVO pdoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				pdoVO = new PdoVO();
				
				pdoVO.setPdoId(rs.getInt("pdo_id"));
				pdoVO.setMebId(rs.getInt("meb_id"));
				pdoVO.setPdoDate(rs.getDate("pdo_date"));
				pdoVO.setPdTotalPrice(rs.getInt("pd_total_price"));
				pdoVO.setPdoStatus(rs.getString("pdo_status"));
				pdoVO.setPaymentStatus(rs.getString("payment_status"));
				pdoVO.setShippingAddr(rs.getString("shipping_addr"));
				pdoVO.setShippingMethod(rs.getString("shipping_method"));
				pdoVO.setPdoReviewRate(rs.getInt("pdo_review_rate"));
				pdoVO.setPdoReviewComm(rs.getString("pdo_review_comm"));
				pdoVO.setCreatedTime(rs.getDate("created_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pdoVO;
	}

	@Override
	public List<PdoVO> getAll() {
		List<PdoVO> list = new ArrayList<PdoVO>();
		PdoVO pdoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				pdoVO = new PdoVO();
				pdoVO.setPdoId(rs.getInt("pdo_id"));
				pdoVO.setMebId(rs.getInt("meb_id"));
				pdoVO.setPdoDate(rs.getDate("pdo_date"));
				pdoVO.setPdTotalPrice(rs.getInt("pd_total_price"));
				pdoVO.setPdoStatus(rs.getString("pdo_status"));
				pdoVO.setPaymentStatus(rs.getString("payment_status"));
				pdoVO.setShippingAddr(rs.getString("shipping_addr"));
				pdoVO.setShippingMethod(rs.getString("shipping_method"));
				pdoVO.setPdoReviewRate(rs.getInt("pdo_review_rate"));
				pdoVO.setPdoReviewComm(rs.getString("pdo_review_comm"));
				pdoVO.setCreatedTime(rs.getDate("created_time"));

				list.add(pdoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		PdoJDBCDAO dao = new PdoJDBCDAO();

		// 新增 --ok (使用編譯器可新增SQL資料)
//		PdoVO pdoVO1 = new PdoVO();
//		pdoVO1.setMebId(2999);
//		pdoVO1.setPdTotalPrice(999);
//		pdoVO1.setPdoStatus("1");
//		pdoVO1.setPaymentStatus("1");
//		pdoVO1.setShippingAddr("花蓮");
//		pdoVO1.setShippingMethod("1");
//		pdoVO1.setPdoReviewRate(8);
//		pdoVO1.setPdoReviewComm("尚可");
//		pdoVO1.setCreatedTime(java.sql.Date.valueOf("2014-10-11"));
//		dao.insert(pdoVO1);
//
//		// 修改 --ok (使用編譯器可更新SQL資料)
//		PdoVO pdoVO2 = new PdoVO();
//		pdoVO2.setPdoId(10005);
//		pdoVO2.setMebId(9999);
//		pdoVO2.setPdoDate(java.sql.Date.valueOf("2024-10-15"));
//		pdoVO2.setPdTotalPrice(9999);
//		pdoVO2.setPdoStatus("1");
//		pdoVO2.setPaymentStatus("1");
//		pdoVO2.setShippingAddr("美國紐約");
//		pdoVO2.setShippingMethod("1");
//		pdoVO2.setPdoReviewRate(99);
//		pdoVO2.setPdoReviewComm("AAAAAAAAAAAAAAAAaaa");
//		pdoVO2.setCreatedTime(java.sql.Date.valueOf("2024-10-20"));
//		dao.update(pdoVO2);
//
//		// 刪除 --非作業要求
//		dao.delete(7014);
		
		// 查詢 --ok (使用編譯器可查詢單筆SQL資料)
//		PdoVO pdoVO3 = dao.findByPrimaryKey(10002);
//		System.out.print(pdoVO3.getPdoId() + ",");
//		System.out.print(pdoVO3.getMebId() + ",");
//		System.out.print(pdoVO3.getPdoDate() + ",");
//		System.out.print(pdoVO3.getPdTotalPrice() + ",");
//		System.out.print(pdoVO3.getPdoStatus() + ",");
//		System.out.print(pdoVO3.getPaymentStatus() + ",");
//		System.out.print(pdoVO3.getShippingAddr() + ",");
//		System.out.print(pdoVO3.getShippingMethod() + ",");
//		System.out.print(pdoVO3.getPdoReviewRate() + ",");
//		System.out.print(pdoVO3.getPdoReviewComm() + ",");
//		System.out.println(pdoVO3.getCreatedTime() + ",");
//		System.out.println("---------------------");
//
//		// 查詢 ALL
//		List<PdoVO> list = dao.getAll();
//		for (PdoVO aDpo : list) {
//			System.out.print(aDpo.getPdoId() + ",");
//			System.out.print(aDpo.getMebId() + ",");
//			System.out.print(aDpo.getPdoDate() + ",");
//			System.out.print(aDpo.getPdTotalPrice() + ",");
//			System.out.print(aDpo.getPdoStatus() + ",");
//			System.out.print(aDpo.getPaymentStatus() + ",");
//			System.out.print(aDpo.getShippingAddr() + ",");
//			System.out.print(aDpo.getShippingMethod() + ",");
//			System.out.print(aDpo.getPdoReviewRate() + ",");
//			System.out.print(aDpo.getPdoReviewComm() + ",");
//			System.out.print(aDpo.getCreatedTime() + ",");
//			System.out.println();
//		}
	}
}