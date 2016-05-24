package com.te.itf.dao;

import java.util.List;

import com.te.vo.CourseWeightVO;

public interface ICourseWeightDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseWeightVO record);

    int insertSelective(CourseWeightVO record);

    CourseWeightVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseWeightVO record);

    int updateByPrimaryKey(CourseWeightVO record);
    
    List<CourseWeightVO> selectAll();
    
    int updateBycid(CourseWeightVO record);
}