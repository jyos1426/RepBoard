package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

public class OrderDAOOracle {
private SqlSessionFactory sqlSessionFactory;
	
	public OrderDAOOracle() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}	
	public void insert(OrderInfo info) throws Exception {
		SqlSession session = null;
		try{						
			session = sqlSessionFactory.openSession();	
			insertInfo(info,session);
			insertLine(info,session);
			session.commit();	
			System.out.println("커밋했졍");
		}catch(PersistenceException e){
	         Throwable t = e.getCause();
	         System.out.println("오류났졍");
	         e.printStackTrace();
	         session.rollback();
	         if(t instanceof SQLException){
	            SQLException sqle = (SQLException)t;
	            int errorCode = sqle.getErrorCode();
	            if(errorCode == 1){      //오라클에서 PK중복인 경우 오류 1번을 의미
	               throw new Exception("이미 존재하는 넘버입니다.");
	            }
	         }
		}finally{
			session.close();	
			System.out.println("세션닫았졍");		
		}			
		
	}
	
	public void insertInfo(OrderInfo info, SqlSession session) throws PersistenceException {
		session.insert("OrderDAOMapper.insertInfo",info);
		System.out.println("인포 들왔졍");
	}

	public void insertLine(OrderInfo info, SqlSession session) throws PersistenceException {
		List<OrderLine> lines = info.getLines();
		for (OrderLine line : lines) {
			session.insert("OrderDAOMapper.insertLine",line);
			System.out.println("라인 들왔졍");
		}
	}

	/**
	 * 전체 주문목록을 반환
	 * 
	 * @return
	 */
	public List<OrderInfo> selectAll() {
		SqlSession session= null;
		try{
			session = sqlSessionFactory.openSession();
			return session.selectList("OrderDAOMapper.selectAll");	
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	/**
	 * 주문자에 해당 주문목록 반환
	 * 
	 * @param id(주문자)
	 * @return
	 */
	public List<OrderInfo> selectById(String id) {		
		SqlSession session= null;
		try{
			session = sqlSessionFactory.openSession();
			return session.selectList("OrderDAOMapper.selectById", id);	
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	/**
	 * 주문번호로 해당 주문정보를 반환
	 * 
	 * @param info_no
	 * @return
	 */
	public OrderInfo selectByNo(int info_no) {
		return null;
	}

	/**
	 * 상품번호에 해당하는 주문목록을 반환
	 * 
	 * @param prod_no
	 * @return
	 */
	public List<OrderInfo> selectByProdNo(String prod_no) {
		return null;
	}

	public List<OrderInfo> selectByDate(String frDate, String toDate) {
		return null;
	}

}// 일처리할 때 주문 기본과 상세 정보는 한 트랜젝션에서 관리
