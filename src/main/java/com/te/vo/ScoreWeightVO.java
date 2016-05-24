package com.te.vo;

/**
 * 
 * @author LichKing
 * 对应数据库中score_weight表的数据类型
 */
public class ScoreWeightVO {
    private Integer id;

    private Double p1I;

    private Double p2I;

    private Double p3I;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getP1I() {
        return p1I;
    }

    public void setP1I(Double p1I) {
        this.p1I = p1I;
    }

    public Double getP2I() {
        return p2I;
    }

    public void setP2I(Double p2I) {
        this.p2I = p2I;
    }

    public Double getP3I() {
        return p3I;
    }

    public void setP3I(Double p3I) {
        this.p3I = p3I;
    }
}