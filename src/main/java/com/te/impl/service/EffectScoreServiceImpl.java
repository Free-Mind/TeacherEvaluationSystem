package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ICourseWeightDAO;
import com.te.itf.dao.IEffectScoreDAO;
import com.te.itf.service.IEffectScoreService;
import com.te.vo.EffectScoreVO;
@Service("EffectScoreService")
public class EffectScoreServiceImpl implements IEffectScoreService {
	
	@Resource
	private IEffectScoreDAO effectScoreDAO;
	
	@Override
	public int insert(EffectScoreVO effectScoreVO) {
		// TODO Auto-generated method stub
		return this.effectScoreDAO.insert(effectScoreVO);
	}

	@Override
	public List<EffectScoreVO> selectByWhere(EffectScoreVO effectScoreVO) {
		// TODO Auto-generated method stub
		return this.effectScoreDAO.selectByWhere(effectScoreVO);
	}

}
