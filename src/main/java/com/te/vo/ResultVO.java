package com.te.vo;

/**
 * 
 * @author LichKing
 *	用于给前台返回信息的数据类型
 * @param <T>
 */
public class ResultVO<T> {

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getT() {
		return t;
	}
	
	public void setT(T t) {
		this.t = t;
	}

	private boolean result;
	
	private String msg;
	
	private T t;

	
}
