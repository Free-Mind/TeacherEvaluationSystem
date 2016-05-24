package com.te.impl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ICourseDAO;
import com.te.itf.service.ICourseService;
import com.te.vo.CourseVO;


@Service("CourseService")
public class CourseServiceImpl implements ICourseService {

	@Resource
	private ICourseDAO courseDAO;
	
	@Override
	public int insertSelective(CourseVO cvo) {
		return this.courseDAO.insertSelective(cvo);
	}

	@Override
	public CourseVO[] selectByWhere(CourseVO cvo) {
		return this.courseDAO.selectByWhere(cvo);
	}

	@Override
	public CourseVO selectByPK(Integer id) {
		return this.courseDAO.selectByPrimaryKey(id);
	}

}
