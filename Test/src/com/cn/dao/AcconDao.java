package com.cn.dao;

import java.util.List;

import com.cn.entities.Accon;

public interface AcconDao {
     List<Accon> queryAll();
     int addAccon(Accon accon);
     int login(Accon accon);
}
