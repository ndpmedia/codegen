package com.yeahmobi.yunit.sample.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.yeahmobi.yunit.DbUnitTestExecutionListener;
import com.yeahmobi.yunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class PersonMapperTest {

	@Autowired
	private PersonDao personDao;

	@Test
	@DatabaseSetup
	public void testGet() throws Exception {
		System.out.println(this.personDao.selectAll().size());
		System.out.println(this.personDao.selectByPrimaryKey(1).getName());
	}

}
