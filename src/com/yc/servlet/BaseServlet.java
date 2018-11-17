package com.yc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.util.JsonModel;

//使用适配器
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 585887208310113815L;
    protected String op;
    protected int pages=1;
    protected int pagesize=5;
    protected HttpSession session;
    protected JsonModel jm;
    
    protected int page;
    protected int rows;
    protected String sort;
    protected String order;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse arg1) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		op=req.getParameter("op");
		session=req.getSession();
		jm=new JsonModel();
		if(req.getParameter("pages")!=null){
			pages=Integer.parseInt(req.getParameter("pages").trim());
		}
		if(req.getParameter("pagesize")!=null){
			pagesize=Integer.parseInt(req.getParameter("pagesize").trim());
		}
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		if(req.getParameter("rows")!=null){
			rows=Integer.parseInt(req.getParameter("rows"));
		}
		if(req.getParameter("sort")!=null){
			sort=req.getParameter("sort");
		}
		if(req.getParameter("order")!=null){
			order=req.getParameter("order");
		}
		
		super.service(req, arg1);
	}
	protected void  outJsonString(Object obj,HttpServletResponse response) throws IOException {
		Gson gson=new Gson();
		String jsonString=gson.toJson(obj);
		outJsonString(jsonString, response);
	}
	protected void  outJsonString(String jsonString,HttpServletResponse response) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(jsonString);
		out.flush();
	}
	public Object parseParameterToT(HttpServletRequest req, Class class1) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//将req中的 Map<String,String[]>转成 Map<String,String>
		Map<String,String[]> map=req.getParameterMap();
		Map<String,String> parameters=new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry:map.entrySet()){
			parameters.put(entry.getKey(), entry.getValue()[0]);
		}
		//parameters  uname pwd
		List<Method> listmethods=FindAllSetMethods(class1);
		Object obj=class1.newInstance(); //new Person();
		for (Method m : listmethods) {//setuname setpwd
			for (Map.Entry<String, String> entry:parameters.entrySet()) {//uname pwd
				String methodName=m.getName();//setUname
				String pName="set"+entry.getKey();//setname
				//TODO: 求出 m 这个方法中的参数类型，强制类型转换 entry.getValue()的类型
				//m就是要找的方法
				if(methodName.equalsIgnoreCase(pName)){
				  Class parameterType=m.getParameterTypes()[0];
				  String parameteTypeName=parameterType.getName();
				  if("int".equals(parameteTypeName) || "java.lang.Integer".equals(parameteTypeName)){
					  int  v=Integer.parseInt(  entry.getValue());
					  m.invoke(obj, v);
				  }else if("short".equals(parameteTypeName) || "java.lang.Short".equals(parameteTypeName)){
					  short v=Short.parseShort(  entry.getValue());
					  m.invoke(obj, v);
				  }else if("float".equals(parameteTypeName) || "java.lang.Float".equals(parameteTypeName)){
					  float v=Float.parseFloat(  entry.getValue());
					  m.invoke(obj, v);
				  }else if("double".equals(parameteTypeName) || "java.lang.Double".equals(parameteTypeName)){
					  double v=Double.parseDouble(  entry.getValue());
					  m.invoke(obj, v);
				  }else{
					  m.invoke(obj, entry.getValue()); //person.setUname("zy");
				   }
				}
			}
		}
		return obj;
	}
	/**
	 * 去除所有的set方法
	 * @param clz
	 * @return
	 */
	private List<Method> FindAllSetMethods(Class clz) {
		List<Method> list=new ArrayList<Method>();
		Method[] ms=clz.getMethods();
		for (Method m : ms) {
			if(m.getName().startsWith("set")){
				list.add(m);
			}
		}
		return list;
	}
	
	
	public void writeJson(HttpServletResponse response, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		writeJson( response, jsonString);
	}
	public void writeJson(HttpServletResponse response, String jsonString) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
		out.close();
	}
}
