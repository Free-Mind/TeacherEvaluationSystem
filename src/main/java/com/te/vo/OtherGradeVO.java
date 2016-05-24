package com.te.vo;

public class OtherGradeVO {
    private String id;

    private Integer otherGrade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getOtherGrade() {
        return otherGrade;
    }

    public void setOtherGrade(Integer otherGrade) {
        this.otherGrade = otherGrade;
    }
}