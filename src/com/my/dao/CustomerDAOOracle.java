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

import com.my.vo.Customer;

public class CustomerDAOOracle implements CustomerDAO {	
	private SqlSessionFactory sqlSessionFactory;

	
	public CustomerDAOOracle() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Override
	public void insert(Customer c) throws Exception {	
		SqlSession session = null;
		try{						
			session = sqlSessionFactory.openSession();	
			session.insert("CustomerDAOMapper.insert",c);
			session.commit();			
		}catch(PersistenceException e){
	         Throwable t = e.getCause();
	         if(t instanceof SQLException){
	            SQLException sqle = (SQLException)t;
	            int errorCode = sqle.getErrorCode();
	            if(errorCode == 1){      //오라클에서 PK중복인 경우 오류 1번을 의미
	               throw new Exception("이미 존재하는 아이디입니다.");
	            }
	         }
		}finally{
			session.close();			
		}
	}
	
	@Override
	public List<Customer> selectAll() throws Exception {
		List<Customer> list = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			list = session.selectList("CustomerDAOMapper.selectAll");				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return list;
	}

	@Override
	public Customer selectById(String id) throws Exception {		
		Customer c = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			c = session.selectOne("CustomerDAOMapper.selectById",id);				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return c;
	}

	@Override
	public List<Customer> selectByName(String name) throws Exception {
		List<Customer> list = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			list = session.selectList("CustomerDAOMapper.selectByName",name);				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return list;
	}

}
