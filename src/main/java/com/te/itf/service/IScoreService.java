package com.te.itf.service;

import java.util.List;

import com.te.vo.AvgScoreVO;
import com.te.vo.ScoreVO;

public interface IScoreService {

	public ScoreVO[] selectByWhere(ScoreVO svo);
	
	public int insertSelective(ScoreVO record);
	
	public List<ScoreVO> selectAll();
	
	public List<AvgScoreVO> selectAvgBytcid();
}
