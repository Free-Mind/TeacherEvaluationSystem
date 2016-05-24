package com.te.vo;

import java.util.Date;
import java.util.List;

import com.te.vo.co.ScoreRowVO;

/**
 * 
 * @author LichKing
 * 总成绩 数据类型
 */
public class ScoreExcelVO {

	private List<ScoreRowVO> s_list;
	
	private Date date;

	public List<ScoreRowVO> getS_list() {
		return s_list;
	}

	public void setS_list(List<ScoreRowVO> s_list) {
		this.s_list = s_list;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
