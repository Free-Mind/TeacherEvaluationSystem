package com.te.itf.dao;

import com.te.vo.ScoreWeightVO;

public interface IScoreWeightDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreWeightVO record);

    int insertSelective(ScoreWeightVO record);

    ScoreWeightVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreWeightVO record);

    int updateByPrimaryKey(ScoreWeightVO record);
}