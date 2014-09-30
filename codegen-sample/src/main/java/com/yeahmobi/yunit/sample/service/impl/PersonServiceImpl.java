package com.yeahmobi.yunit.sample.service.impl;

import javax.annotation.Resource;

import com.yeahmobi.yunit.sample.dao.PersonDao;
import com.yeahmobi.yunit.sample.entity.Person;
import com.yeahmobi.yunit.sample.service.PersonService;

public class PersonServiceImpl implements PersonService {

	@Resource
	private PersonDao personDao;

	public Person get(Integer id) {
		return this.personDao.selectByPrimaryKey(id);
	}

}
