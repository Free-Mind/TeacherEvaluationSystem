package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.ICompotitionGradeDAO;
import com.te.itf.service.ICompetitionGradeService;
import com.te.vo.CompotitionGradeVO;
@Service("CompetitionGradeService")
public class CompetitionGradeServiceImpl implements  ICompetitionGradeService{

	@Resource
	private ICompotitionGradeDAO competition;
	
	@Override
	public int insert(CompotitionGradeVO compotitionGradeVO) {
		// TODO Auto-generated method stub
		return this.competition.insert(compotitionGradeVO);
	}

	@Override
	public CompotitionGradeVO query(String id) {
		// TODO Auto-generated method stub
		return this.competition.selectByPrimaryKey(id);
	}

	@Override
	public int update(CompotitionGradeVO compotitionGradeVO) {
		// TODO Auto-generated method stub
		return this.competition.updateByPrimaryKey(compotitionGradeVO);
	}

	@Override
	public List<CompotitionGradeVO> selectAll() {
		// TODO Auto-generated method stub
		return this.competition.selectAll();
	}
	
}
