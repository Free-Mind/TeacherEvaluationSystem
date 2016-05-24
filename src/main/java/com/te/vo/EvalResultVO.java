package com.te.vo;

import java.util.List;

/**
 * 给前台传入需要评价的课程信息，还有评价人的类型（管理员 or 评分员）
 * */
public class EvalResultVO {
	//当前用户的类型
	private Object type;
	//课程列表
	private List<CourseVO> c_list;
	
	public Object getType(){
		return this.type;
	}
	public void setType(Object type){
		this.type = type;
	}
	public List<CourseVO> getList(){
		return this.c_list;
	}
	public void setList(List<CourseVO> c_list){
		this.c_list = c_list;
	}
	
}
