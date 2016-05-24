package com.te.itf.dao;

import com.te.vo.CourseVO;

public interface ICourseDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseVO record);

    int insertSelective(CourseVO record);

    CourseVO selectByPrimaryKey(Integer id);
    
    CourseVO[] selectByWhere(CourseVO record);

    int updateByPrimaryKeySelective(CourseVO record);

    int updateByPrimaryKey(CourseVO record);
    
}