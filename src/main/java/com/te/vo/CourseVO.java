package com.te.vo;

import java.util.Date;

/**
 * 对应数据库中的course表的数据类型
 * @author LichKing
 *
 */
public class CourseVO {
    private Integer id;

    private String courseName;

    private String level;

    private Boolean courseType;

    private Integer courseHour;

    private Date startTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Boolean getCourseType() {
        return courseType;
    }

    public void setCourseType(Boolean courseType) {
        this.courseType = courseType;
    }

    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}