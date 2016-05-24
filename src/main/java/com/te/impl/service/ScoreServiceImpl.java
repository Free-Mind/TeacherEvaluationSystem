package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.IScoreDAO;
import com.te.itf.service.IScoreService;
import com.te.vo.AvgScoreVO;
import com.te.vo.ScoreVO;

@Service("ScoreService")
public class ScoreServiceImpl implements IScoreService {

	@Resource
	private IScoreDAO scoreDAO;
	
	@Override
	public ScoreVO[] selectByWhere(ScoreVO svo) {
		return this.scoreDAO.selectByWhere(svo);
	}

	@Override
	public int insertSelective(ScoreVO record) {
		return this.scoreDAO.insertSelective(record);
	}

	@Override
	public List<ScoreVO> selectAll() {
		return this.scoreDAO.selectAll();
	}

	@Override
	public List<AvgScoreVO> selectAvgBytcid() {
		return this.scoreDAO.selectAvgBytcid();
	}

}
