package com.te.itf.dao;

import java.util.List;

import com.te.vo.AvgScoreVO;
import com.te.vo.ScoreVO;
import com.te.vo.TotalGradeVO;

public interface IScoreDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreVO record);

    int insertSelective(ScoreVO record);

    ScoreVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreVO record);

    int updateByPrimaryKey(ScoreVO record);
    
    ScoreVO[] selectByWhere(ScoreVO record);
    
    List<ScoreVO> selectAll();
    
    List<AvgScoreVO> selectAvgBytcid();
    
}