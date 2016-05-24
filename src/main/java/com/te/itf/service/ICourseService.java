package com.te.itf.service;


import com.te.vo.CourseVO;

public interface ICourseService {

	public int insertSelective(CourseVO cvo);
	
	public CourseVO[] selectByWhere(CourseVO cvo);
	
	public CourseVO selectByPK(Integer id);
	
}
