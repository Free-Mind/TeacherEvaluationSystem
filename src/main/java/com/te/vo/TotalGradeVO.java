package com.te.vo;

public class TotalGradeVO {
	
	private String id;
	private String name;
	private String org;
	private double total;
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setOrg(String org){
		this.org = org;
	}
	public String getOrg(){
		return this.org;
	}
	public void setTotal(double total){
		this.total = total;
	}
	public double getTotal(){
		return this.total;
	}
}

