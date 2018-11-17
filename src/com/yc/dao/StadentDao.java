package com.yc.dao;

import java.util.List;

import com.yc.bean.Stadent;

public interface StadentDao {

	List<Stadent> funStadentAll(int pages, int pagesize);

	int funStadentCount();

}
