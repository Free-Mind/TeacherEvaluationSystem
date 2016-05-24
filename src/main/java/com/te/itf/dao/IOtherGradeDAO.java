package com.te.itf.dao;

import java.util.List;

import com.te.vo.OtherGradeVO;

public interface IOtherGradeDAO {
    int deleteByPrimaryKey(String id);

    int insert(OtherGradeVO record);

    int insertSelective(OtherGradeVO record);

    OtherGradeVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OtherGradeVO record);

    int updateByPrimaryKey(OtherGradeVO record);
    
    List<OtherGradeVO> selectAll();
}