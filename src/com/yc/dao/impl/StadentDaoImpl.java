package com.yc.dao.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.PageBean;
import com.yc.bean.Stadent;
import com.yc.dao.StadentDao;
import com.yc.util.DBUtil;

public class StadentDaoImpl implements StadentDao{

	@Override
	public List<Stadent> funStadentAll(int pages,int pagesize) {
		int statr=(pages-1)*pagesize;
		String sql=" select * from  student limit ?,? ";
		System.out.println(statr+"-------"+pagesize);
		return DBUtil.list(Stadent.class, sql, statr,pagesize);
	}

	@Override
	public int funStadentCount() {
		String sql=" select count(*)  as total from student ";
		Map<String,Object> map=DBUtil.get(sql, null);
		return Integer.parseInt(map.get("TOTAL").toString());
	}
	
	public PageBean finadpages(int pages,int pagesize){
		PageBean pb=new PageBean();
		int total=funStadentCount();
		pb.setTotal((long)total);
		List<Stadent> list=funStadentAll(pages, pagesize);
		pb.setList(list);
		pb.setPages(pages);
		pb.setPageSize(pagesize);
		
		int totalPage= total%pagesize==0?total/pagesize:(total/pagesize+1);
		
	     pb.setTotalPage((long)totalPage);
		return pb;
		
	}

}
