package com.te.itf.service;

import java.util.List;

import com.te.vo.TeachingVO;

public interface ITeachingService {

	public List<TeachingVO> selectByTid(String tid);
	
	public int insertSelective(TeachingVO tgvo);
	
	public TeachingVO selectByPK(Integer id);
	
	public List<TeachingVO> selectByWhere(TeachingVO tgvo);
}
