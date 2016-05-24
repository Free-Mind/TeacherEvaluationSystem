package com.te.itf.service;

import java.util.List;

import com.te.vo.OtherGradeVO;

public interface IOtherGradeService {
	
	public int insertData(OtherGradeVO otherGrade);
	
	public int updateData(OtherGradeVO otherGrade);
	
	public OtherGradeVO query(String tid);
	
	public List<OtherGradeVO> selectAll();
}
