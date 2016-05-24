package com.te.itf.service;

import java.util.List;

import com.te.vo.CompotitionGradeVO;

public interface ICompetitionGradeService {
	
	public int insert(CompotitionGradeVO compotitionGradeVO);
	
	public CompotitionGradeVO query(String id);
	
	public int update(CompotitionGradeVO compotitionGradeVO);
	
	public List<CompotitionGradeVO> selectAll();
}
