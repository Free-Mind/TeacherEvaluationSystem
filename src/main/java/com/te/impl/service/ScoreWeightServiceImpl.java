package com.te.impl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.IScoreWeightDAO;
import com.te.itf.service.IScoreWeightService;
import com.te.vo.ScoreWeightVO;

@Service("ScoreWeightService")
public class ScoreWeightServiceImpl implements IScoreWeightService {

	@Resource
	private IScoreWeightDAO scoreWeightDAO;
	
	@Override
	public ScoreWeightVO selectByPK(Integer id) {
		return this.scoreWeightDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ScoreWeightVO swvo) {
		return this.scoreWeightDAO.updateByPrimaryKeySelective(swvo);
	}

}
