package com.te.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.te.itf.service.ICourseService;
import com.te.itf.service.IScoreService;
import com.te.itf.service.ITeacherService;
import com.te.itf.service.ITeachingService;
import com.te.util.ListUtil;
import com.te.util.SessionUtil;
import com.te.vo.ResultVO;
import com.te.vo.ScoreVO;
import com.te.vo.TeacherVO;
import com.te.vo.TeachingVO;
import com.te.vo.TotalGradeVO;


@Controller
public class MainController {
	
	@Resource
	private ITeacherService teacherService;
	
	@Resource
	private ITeachingService teachingService;
	
	@Resource
	private IScoreService scoreService;

	
	Logger logger = Logger.getLogger(MainController.class);
	
	/**
	 * 老师页面
	 * @param req
	 * @return
	 */
	@RequestMapping("/teacher_main")
	public String vTMain(HttpServletRequest req){
		logger.info("/teacher_main");
		String curUser = SessionUtil.checkUser(req).getId();
		if(curUser != null && !curUser.equals(""))
			return "teacher_main";
		else
			return "login";
	}
	
	/**
	 * 评分员页面
	 * @param req
	 * @return
	 */
	@RequestMapping("/scorer_main")
	public String vSMain(HttpServletRequest req){
		logger.info("/scorer_main");
		TeacherVO curUser = SessionUtil.checkUser(req);
		if(curUser != null && !curUser.equals(""))
			return "scorer_main";
		else
			return "login";
	}
	
	/**
	 * 打分页面
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/evaluate")
	public String vEvaluate(Model model,HttpServletRequest req){
		logger.info("/evaluate");
		String tid = req.getParameter("tid");
		model.addAttribute("tid", tid);
		return "evaluate";
	}
	//管理员页面
	@RequestMapping("/admin_main")
	public String vadmin(HttpServletRequest req){
		TeacherVO admin = SessionUtil.checkUser(req);
		if(admin != null && admin.getType().equals("admin")){
			logger.info("/admin_main");
			return "manage";
		}else{
			return "login";
		}
	}
	//教导主任页面
	@RequestMapping("/dean_main")
	public String vdean(HttpServletRequest req){
		TeacherVO admin = SessionUtil.checkUser(req);
		if(admin != null && admin.getType().equals("dean")){
			logger.info("/dean_main");
			return "scorer_main";
		}else{
			return "login";
		}
	}
	@RequestMapping("/other_grade")
	public String vother(HttpServletRequest req){
		String data[] = new String[2];
		ResultVO result = getEvalTeacher(req);
		req.setAttribute("result", result);
		logger.info("/other_grade");
		return "other_grade";
	}
	
	@RequestMapping("/competiotion_grade")
	public String vcompetition(HttpServletRequest req){
		String data[] = new String[2];
		ResultVO result = getEvalTeacher(req);
		req.setAttribute("result", result);
		logger.info("/competition_grade");
		return "competition_grade";
	}
	
//	@RequestMapping("/get_total_grade")
//	public String vtotalgrade(HttpServletRequest req){
//		List<TotalGradeVO> totals =new ArrayList<TotalGradeVO>();
//		String data[] = new String[2];
//		ResultVO evalTeachers = getEvalTeacher(req);
//		List<TeacherVO> teachers = (List)evalTeachers.getT();
//		for(int i=0;i<teachers.size();i++){
//			String tid = teachers.get(i).getId();
//			//找出老师选的课程
//			
//			//计算老师的总成绩
//			//1.计算课程质量平均成绩：教学准备，课程实施，教学效果
//			
//		}
//		logger.info("/competition_grade");
//		return "competition_grade";
//	}
//	
	@RequestMapping("/manage")
	public String vmanage(){
		logger.info("/manage");
		return "manage";
	}
	//载入超级管理员页面
	@RequestMapping("/super_manage")
	public String vsupermanage(HttpServletRequest req){
		logger.info("/super_manage");
		//获取所有的未激活管理员信息
		TeacherVO conditionVO = new TeacherVO();
		conditionVO.setType("admin_uncheck");
		TeacherVO[] vos = this.teacherService.selectByWhere(conditionVO);
		ResultVO result = new ResultVO();
		result.setT(vos);
		req.setAttribute("result", result);
		return "super_manage";
	}
	//载入为激活管理员界面
	@RequestMapping("/admin_uncheck_main")
	public String vadminuncheckmain(HttpServletRequest req){
		logger.info("/admin_uncheck_main");
		return "admin_uncheck_main";
	}
	
	private ResultVO getTeacherTotalGrade(HttpServletRequest req){
		return null;
	}
	
	private ResultVO getEvalTeacher(HttpServletRequest req){
		
		ResultVO result = new ResultVO();
		String user = SessionUtil.checkUser(req).getId();
		TeacherVO tvo = this.teacherService.selectByPK(user);
		String org = tvo.getOrganization();
		TeacherVO wherevo = new TeacherVO();
		wherevo.setOrganization(org);
		wherevo.setType("teacher");
		TeacherVO[] tvos = this.teacherService.selectByWhere(wherevo);
		
		ScoreVO svo = new ScoreVO();
		svo.settId(user);
		ScoreVO[] svos = this.scoreService.selectByWhere(svo);
		List<String> tid_list = new ArrayList<String>();
		if(svos.length>0){
			for(int i=0;i<svos.length;i++){
				Integer tcid = svos[i].getTcId();
				TeachingVO tgvo = this.teachingService.selectByPK(tcid);
				String tid = String.valueOf(tgvo.gettId());
				tid_list = ListUtil.addIfDif(tid, tid_list);
			}
		}
		List<TeacherVO> tvos_list = new ArrayList<TeacherVO>();
		for(int i=0;i<tvos.length;i++){
			tvos_list.add(tvos[i]);
		}
		tvos_list = ListUtil.removeIfHasTid(tvos_list, tid_list);
		
		if(tvos_list.size() > 0){
			result.setResult(true);
			for(TeacherVO tvo_temp : tvos_list){
				//tvos[i].setId("");
				tvo_temp.setPassword("");
				tvo_temp.setType("");
			}
			result.setT(tvos_list);
		}else{
			result.setResult(false);
			result.setMsg("没有查询到信息");
		}
		return result;
	}
}
