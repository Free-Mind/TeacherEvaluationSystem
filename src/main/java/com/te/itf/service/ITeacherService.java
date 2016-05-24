package com.te.itf.service;

import com.te.vo.TeacherVO;

public interface ITeacherService {

	public TeacherVO selectByPK(String id);
	
	public int insert(TeacherVO tvo);
	
	public TeacherVO[] selectByWhere(TeacherVO tvo);
	
	public int update(TeacherVO tvo); 
}
