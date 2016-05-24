package com.te.itf.dao;

import com.te.vo.TeacherVO;

public interface ITeacherDAO {
    int deleteByPrimaryKey(String id);

    int insert(TeacherVO record);

    int insertSelective(TeacherVO record);

    TeacherVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeacherVO record);

    int updateByPrimaryKey(TeacherVO record);
    
    TeacherVO[] selectByWhere(TeacherVO record);
}