package com.te.itf.service;

import java.util.List;

import com.te.vo.CourseWeightVO;

public interface ICourseWeightService {

	public int insertSelective(CourseWeightVO cwvo);
	
	public List<CourseWeightVO> selectAll();

	public int updateBycid(CourseWeightVO cwvo);
}
