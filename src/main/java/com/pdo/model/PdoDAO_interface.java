package com.pdo.model;

import java.util.*;

public interface PdoDAO_interface {
          public void insert(PdoVO epdoVO);
          public void update(PdoVO pdoVO);
//          public void delete(Integer empno);
          public PdoVO findByPrimaryKey(Integer pdono);
          public List<PdoVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
