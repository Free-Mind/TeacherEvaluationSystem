package com.te.itf.dao;

import java.util.List;

import com.te.vo.CompotitionGradeVO;

public interface ICompotitionGradeDAO {
    int deleteByPrimaryKey(String id);

    int insert(CompotitionGradeVO record);

    int insertSelective(CompotitionGradeVO record);

    CompotitionGradeVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompotitionGradeVO record);

    int updateByPrimaryKey(CompotitionGradeVO record);
    
    List<CompotitionGradeVO> selectAll();
}