package com.te.itf.service;

import java.util.List;

import com.te.vo.EffectScoreVO;

public interface IEffectScoreService {
	
	public int insert(EffectScoreVO effectScoreVO);
	
	public List<EffectScoreVO> selectByWhere(EffectScoreVO effectScoreVO);
}
