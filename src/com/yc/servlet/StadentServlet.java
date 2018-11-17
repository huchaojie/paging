package com.yc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.PageBean;
import com.yc.bean.Stadent;
import com.yc.dao.impl.StadentDaoImpl;

@WebServlet("/stadent.action")
public class StadentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	StadentDaoImpl sdi = new StadentDaoImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if ("staAll".equals(op)) {
			staAll(req, resp);
		} else if ("staPage".equals(op)) {
			staPage(req, resp);
		}
	}

	private void staPage(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int pages = Integer.parseInt(req.getParameter("pages"));
		int pagesize = Integer.parseInt(req.getParameter("pageSize"));
		System.out.println(pages+"-------"+pagesize);
		PageBean pb = sdi.finadpages(pages, pagesize);
		
		session.setAttribute("vals", pagesize);
		
		jm.setCode(1);
		if(pb.getList()!=null){
			jm.setObj(pb);
			super.outJsonString(jm, resp);
		}
		
		
	}

	private void staAll(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int pages = Integer.parseInt(req.getParameter("pages"));
		int pagesize = Integer.parseInt(req.getParameter("pageSize"));

		session.setAttribute("vals", pagesize);
		
		PageBean pb = sdi.finadpages(pages, pagesize);
		jm.setCode(1);
		jm.setObj(pb);
		super.outJsonString(jm, resp);
	}

}
