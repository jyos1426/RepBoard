package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;

import com.my.vo.Customer;

@Repository
public class CustomerDAOOracle implements CustomerDAO {	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(Customer c) throws Exception {	
		try{						
			session.insert("CustomerDAOMapper.insert",c);
		}catch(DuplicateKeyException e){
	        throw new Exception("이미 존재하는 아이디입니다.");
	    }catch(BadSqlGrammarException e){
		   throw new Exception("SQL구문 오류!");
	    }catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Customer> selectAll() throws Exception {
		List<Customer> list = null;
		try{
			list = session.selectList("CustomerDAOMapper.selectAll");				
		}catch(Exception e){
			throw e;
		}
		return list;
	}

	@Override
	public Customer selectById(String id) throws Exception {		
		Customer c = null;
		try{
			c = session.selectOne("CustomerDAOMapper.selectById",id);				
		}catch(Exception e){
			throw e;
		}
		return c;
	}

	@Override
	public List<Customer> selectByName(String name) throws Exception {
		List<Customer> list = null;
		try{
			list = session.selectList("CustomerDAOMapper.selectByName",name);				
		}catch(Exception e){
			throw e;
		}
		return list;
	}

}
