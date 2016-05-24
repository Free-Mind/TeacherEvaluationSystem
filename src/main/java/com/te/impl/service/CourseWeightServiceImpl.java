package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ICourseWeightDAO;
import com.te.itf.service.ICourseWeightService;
import com.te.vo.CourseWeightVO;


@Service("CourseWeightService")
public class CourseWeightServiceImpl implements ICourseWeightService {

	@Resource
	private ICourseWeightDAO courseWeightDAO;
	
	@Override
	public int insertSelective(CourseWeightVO cwvo) {
		return this.courseWeightDAO.insertSelective(cwvo);
	}

	@Override
	public List<CourseWeightVO> selectAll() {
		return this.courseWeightDAO.selectAll();
	}

	@Override
	public int updateBycid(CourseWeightVO cwvo) {
		return this.courseWeightDAO.updateBycid(cwvo);
	}

}
