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

import com.my.vo.Product;

public class ProductDAOOracle implements ProductDAO {
	private SqlSessionFactory sqlSessionFactory;

	
	public ProductDAOOracle() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Override
	public void insert(Product p) throws Exception {
		SqlSession session = null;
		try{						
			session = sqlSessionFactory.openSession();	
			session.insert("ProductDAOMapper.insert",p);
			session.commit();			
		}catch(PersistenceException e){
	         Throwable t = e.getCause();
	         if(t instanceof SQLException){
	            SQLException sqle = (SQLException)t;
	            int errorCode = sqle.getErrorCode();
	            if(errorCode == 1){      //오라클에서 PK중복인 경우 오류 1번을 의미
	               throw new Exception("이미 존재하는 넘버입니다.");
	            }
	         }
		}finally{
			session.close();			
		}
	}

	@Override
	public List<Product> selectAll() throws Exception {
		List<Product> list = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			list = session.selectList("ProductDAOMapper.selectAll");				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return list;
	}

	@Override
	public Product selectByNo(String no) throws Exception {
		Product p = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			p = session.selectOne("ProductDAOMapper.selectByNo",no);				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return p;
	}

	@Override
	public List<Product> selectByName(String word) throws Exception {
		List<Product> list = null;
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();	
			list = session.selectList("ProductDAOMapper.selectByName",word);				
		}catch(Exception e){
			throw e;
		}finally{
			session.close();	
		}
		return list;
	}
}
