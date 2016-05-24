package com.te.impl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ITeacherDAO;
import com.te.itf.service.ITeacherService;
import com.te.vo.TeacherVO;


@Service("TeacherService")
public class TeacherServiceImpl implements ITeacherService {

	@Resource
	private ITeacherDAO teacherDAO;
	
	@Override
	public TeacherVO selectByPK(String id) {
		return this.teacherDAO.selectByPrimaryKey(id);
	}

	@Override
	public int insert(TeacherVO tvo) {
		return this.teacherDAO.insert(tvo);
	}

	@Override
	public TeacherVO[] selectByWhere(TeacherVO tvo) {
		return this.teacherDAO.selectByWhere(tvo);
	}

	@Override
	public int update(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return this.teacherDAO.updateByPrimaryKey(tvo);
	}
	
	
}
