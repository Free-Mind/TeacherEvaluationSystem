package com.te.vo;

/**
 * 
 * @author LichKing
 *	对应数据库中course_weight表的数据类型
 */
public class CourseWeightVO {
    private Integer id;

    private Integer cId;

    private Integer courseWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getCourseWeight() {
        return courseWeight;
    }

    public void setCourseWeight(Integer courseWeight) {
        this.courseWeight = courseWeight;
    }
}