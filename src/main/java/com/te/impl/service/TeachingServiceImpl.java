package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ITeachingDAO;
import com.te.itf.service.ITeachingService;
import com.te.vo.TeachingVO;


@Service("TeachingService")
public class TeachingServiceImpl implements ITeachingService {

	@Resource
	private ITeachingDAO teachingDAO;
	
	@Override
	public List<TeachingVO> selectByTid(String tid) {
		return this.teachingDAO.selectByTid(tid);
	}

	@Override
	public int insertSelective(TeachingVO tgvo) {
		return this.teachingDAO.insertSelective(tgvo);
	}

	@Override
	public TeachingVO selectByPK(Integer id) {
		return this.teachingDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<TeachingVO> selectByWhere(TeachingVO tgvo) {
		return this.teachingDAO.selectByWhere(tgvo);
	}

}
