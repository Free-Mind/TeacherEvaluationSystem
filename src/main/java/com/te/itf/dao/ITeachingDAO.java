package com.te.itf.dao;

import java.util.List;

import com.te.vo.TeachingVO;

public interface ITeachingDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(TeachingVO record);

    int insertSelective(TeachingVO record);

    TeachingVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeachingVO record);

    int updateByPrimaryKey(TeachingVO record);
    
    List<TeachingVO> selectByTid(String tid);
    
    List<TeachingVO> selectByWhere(TeachingVO record);
}