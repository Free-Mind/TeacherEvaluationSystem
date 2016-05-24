package com.te.itf.service;

import com.te.vo.ScoreWeightVO;

public interface IScoreWeightService {

	public ScoreWeightVO selectByPK(Integer id);

	public int updateByPrimaryKeySelective(ScoreWeightVO swvo);
	
}
