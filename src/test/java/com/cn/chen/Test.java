package com.cn.chen;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.chen.dao.UseraccountMapper;
import com.cn.chen.domain.Useraccount;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	@Autowired
	private UseraccountMapper useraccountMapper;
	
	@org.junit.Test
	public void test(){
		Useraccount useraccount=useraccountMapper.selectByPrimaryKey(1);
		System.out.println(useraccount);
	}
}
