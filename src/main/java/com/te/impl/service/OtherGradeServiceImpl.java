package com.te.impl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.te.itf.dao.IOtherGradeDAO;
import com.te.itf.service.IOtherGradeService;
import com.te.vo.OtherGradeVO;
@Service("OtherGradeService")
public class OtherGradeServiceImpl implements IOtherGradeService {
	
	@Resource
	private IOtherGradeDAO otherGradeDAO;
	@Override
	public int insertData(OtherGradeVO otherGrade) {
		// TODO Auto-generated method stub
		int result = this.otherGradeDAO.insert(otherGrade);
		return result;
	}

	@Override
	public int updateData(OtherGradeVO otherGrade) {
		// TODO Auto-generated method stub
		int result = this.otherGradeDAO.updateByPrimaryKey(otherGrade);
		return result;
	}

	@Override
	public OtherGradeVO query(String tid) {
		// TODO Auto-generated method stub
		OtherGradeVO otherGradeVO = this.otherGradeDAO.selectByPrimaryKey(tid);
		return otherGradeVO;
	}

	@Override
	public List<OtherGradeVO> selectAll() {
		// TODO Auto-generated method stub
		return this.otherGradeDAO.selectAll();
	}

}
