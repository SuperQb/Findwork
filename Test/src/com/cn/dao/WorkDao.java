package com.cn.dao;

import java.util.List;

import com.cn.entities.Person;
import com.cn.entities.Work;

public interface WorkDao {
     List<Work> queryAll();
     int addWork(Work work);
}
