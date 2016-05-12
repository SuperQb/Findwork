package com.cn.dao;

import java.util.List;


import com.cn.entities.Person;

public interface PersonDao {
     List<Person> queryAll();
     int addPerson(Person person);
	int updataPerson(Person person);
}
