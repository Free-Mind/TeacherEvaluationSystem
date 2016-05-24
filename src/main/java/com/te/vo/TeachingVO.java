package com.te.vo;

/**
 * 
 * @author LichKing
 * 对应数据库中 teacher_course表的数据类型
 */
public class TeachingVO {
    private Integer id;

    private String tId;

    private String uId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }
}