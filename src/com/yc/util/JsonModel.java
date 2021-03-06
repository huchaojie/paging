package com.yc.util;

import java.io.Serializable;
import java.util.List;

public class JsonModel<T> implements Serializable {

	private static final long serialVersionUID = 9106902882602433353L;

	 private Integer code;
	    private T obj;
	    private String errmsg;
	    
	    
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public T getObj() {
			return obj;
		}
		public void setObj(T obj) {
			this.obj = obj;
		}
		public String getErrmsg() {
			return errmsg;
		}
		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}
		@Override
		public String toString() {
			return "JsonModel [code=" + code + ", obj=" + obj + ", errmsg="
					+ errmsg + "]";
		}

}
