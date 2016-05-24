package com.te.itf.dao;

import java.util.List;

import com.te.vo.EffectScoreVO;

public interface IEffectScoreDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(EffectScoreVO record);

    int insertSelective(EffectScoreVO record);

    EffectScoreVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EffectScoreVO record);

    int updateByPrimaryKey(EffectScoreVO record);
    
    List<EffectScoreVO> selectByWhere(EffectScoreVO effectScoreVO);
}