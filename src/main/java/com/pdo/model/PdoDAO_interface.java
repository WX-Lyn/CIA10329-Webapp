package com.pdo.model;

import java.util.*;

public interface PdoDAO_interface {
          public void insert(PdoVO epdoVO);
          public void update(PdoVO pdoVO);
//          public void delete(Integer empno);
          public PdoVO findByPrimaryKey(Integer pdono);
          public List<PdoVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
