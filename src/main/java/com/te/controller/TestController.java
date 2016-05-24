package com.te.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.te.itf.dao.ITeacherDAO;
import com.te.itf.service.IScoreService;
import com.te.itf.service.ITeacherService;
import com.te.itf.service.ITeachingService;
import com.te.vo.AvgScoreVO;
import com.te.vo.TeacherVO;
import com.te.vo.TeachingVO;


@Controller
@RequestMapping("/test")
public class TestController {

	@Resource
	private IScoreService scoreService;
	
	@Resource
	private ITeachingService teachingService;
	
	@Resource
	private ITeacherService teacherService;
	
	@RequestMapping("/getAvg")
	public String vgetAvg(){
		List<AvgScoreVO> avg_list = this.scoreService.selectAvgBytcid();
		@SuppressWarnings("unused")
		List<TeachingVO> tgvo_list = this.teachingService.selectByWhere(new TeachingVO());
		//System.out.println(avg_list.size());
		return "test";
	}
	
	@RequestMapping("/tsbw")
	public String vtsbw(){
		TeacherVO tvo = new TeacherVO();
		tvo.setType("teacher");
		tvo.setOrganization("清华大学");
		TeacherVO[] tvos = this.teacherService.selectByWhere(tvo);
		
		return "test";
	}
	
}
