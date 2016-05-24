package com.te.controller;




import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.te.itf.service.ICompetitionGradeService;
import com.te.itf.service.ICourseService;
import com.te.itf.service.ICourseWeightService;
import com.te.itf.service.IEffectScoreService;
import com.te.itf.service.IOtherGradeService;
import com.te.itf.service.IScoreService;
import com.te.itf.service.IScoreWeightService;
import com.te.itf.service.ITeacherService;
import com.te.itf.service.ITeachingService;
import com.te.util.ListUtil;
import com.te.util.SessionUtil;
import com.te.vo.AdminVO;
import com.te.vo.CompotitionGradeVO;
import com.te.vo.CourseVO;
import com.te.vo.CourseWeightVO;
import com.te.vo.EffectScoreVO;
import com.te.vo.OtherGradeVO;
import com.te.vo.ResultVO;
import com.te.vo.ScoreVO;
import com.te.vo.ScoreWeightVO;
import com.te.vo.TeacherVO;
import com.te.vo.TeachingVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/data")
public class MainDataController {

	@Resource
	private ITeacherService teacherService;
	
	@Resource
	private ITeachingService teachingService;
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private ICompetitionGradeService competitionService;
	
	@Resource
	private ICourseWeightService courseWeightService;
	
	@Resource
	private IOtherGradeService otherGradeService;
	
	@Resource
	private IScoreService scoreService;
	
	@Resource
	private IScoreWeightService scoreWeightService;
	
	@Resource
	private IEffectScoreService effectScoreService; 
	
	Logger logger = Logger.getLogger(MainDataController.class);
	
	
	/**
	 * 获取用户 名字
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	public @ResponseBody ResultVO getUser(HttpServletRequest req){
		logger.info("/data/getUser");
		ResultVO result = new ResultVO();
		TeacherVO tvo1 = (TeacherVO) req.getSession().getAttribute("curUser");
		String user = tvo1.getId();
		TeacherVO tvo = this.teacherService.selectByPK(user);
		result.setMsg(tvo.getName());
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取用户全部信息
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getUserinfo",method=RequestMethod.GET)
	public @ResponseBody ResultVO getUserinfo(HttpServletRequest req){
		logger.info("/data/getUserinfo");
		ResultVO result = new ResultVO();
		TeacherVO tvo_s = (TeacherVO) req.getSession().getAttribute("curUser");
		String user = tvo_s.getId();
		TeacherVO tvo = this.teacherService.selectByPK(user);
		tvo.setPassword("");
		result.setT(tvo);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 注销
	 * @param req
	 * @return
	 */
	@RequestMapping("/Logout")
	public @ResponseBody ResultVO Logout(HttpServletRequest req){
		logger.info("/data/Logout");
		ResultVO result = new ResultVO();
		req.getSession().removeAttribute("curUser");
		result.setResult(true);
		return result;
	}
	
	/**
	 * 添加新课程
	 * @param cvo
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addCourseNew",method=RequestMethod.POST)
	public @ResponseBody ResultVO vaddCourseNew(@RequestBody CourseVO cvo,HttpServletRequest req){
		logger.info("/data/addCourseNew");
		ResultVO result = new ResultVO();
		
		CourseVO[] cvos_check = this.courseService.selectByWhere(cvo);
		if(cvos_check.length>0){
			result.setResult(false);
			result.setMsg("该课程已存在");
		}else{
			int r = this.courseService.insertSelective(cvo);
			if(r == 1){
				String user = SessionUtil.checkUser(req).getId();
				CourseVO[] cvos = this.courseService.selectByWhere(cvo);
				if(cvos.length == 0 || cvos == null){
					result.setResult(false);
					logger.error("未查询到");
				}else{
					String cid = String.valueOf(cvos[0].getId());
					TeachingVO tgvo = new TeachingVO();
					tgvo.setuId(cid);
					tgvo.settId(user);
					int r2 = this.teachingService.insertSelective(tgvo);
					CourseWeightVO cwvo = new CourseWeightVO();
					cwvo.setcId(Integer.parseInt(cid));
					cwvo.setCourseWeight(1);
					int r3 = this.courseWeightService.insertSelective(cwvo);
					if(r2 == 1 && r3 == 1){
						result.setResult(true);
					}else{
						result.setResult(false);
						result.setMsg("插入授课表时出错");
					}
				}
				
			}else{
				result.setResult(false);
				result.setMsg("插入课程表时出错");
			}
		}
		
		
		
		return result;
	}
	
	/**
	 * 搜索已有课程
	 * @param cvo
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/searchCourse")
	public @ResponseBody ResultVO vsearchCourse(@RequestBody CourseVO cvo,HttpServletRequest req){
		logger.info("/data/searchCourse");
		ResultVO result = new ResultVO();
		
		CourseVO[] cvos = this.courseService.selectByWhere(cvo);
		result.setResult(true);
		result.setT(cvos);
		
		return result;
	}
	
	/**
	 * 获取用户已有课程
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getCourseSelected")
	public @ResponseBody ResultVO vgetCourseSelected(HttpServletRequest req){
		String userid = SessionUtil.checkUser(req).getId();
		logger.info("/data/getCourseSelected");
		ResultVO result = new ResultVO();
		List<TeachingVO> tglist = this.teachingService.selectByTid(userid);
		List<CourseVO> clist = new ArrayList<CourseVO>();
		for(TeachingVO tgvo : tglist){
			String cid = tgvo.getuId();
			Integer id = Integer.parseInt(cid);
			CourseVO cvo = this.courseService.selectByPK(id);
			clist.add(cvo);
		}
		result.setResult(true);
		result.setT(clist);
		return result;
	}
	
	/**
	 * 添加数据库中已有的课程
	 * @param courseid
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addCourseHad",method=RequestMethod.POST)
	public @ResponseBody ResultVO vaddCourseHad(@RequestBody Integer courseid,HttpServletRequest req){
		logger.info("/data/addCourseHad");
		ResultVO result = new ResultVO();
		String tid = SessionUtil.checkUser(req).getId();
		CourseVO cvo = this.courseService.selectByPK(courseid);
		if(cvo != null){
			List<TeachingVO> tglist = this.teachingService.selectByTid(tid);
			for(TeachingVO tgvo : tglist){
				String uid = tgvo.getuId();
				if(uid.equals(String.valueOf(courseid))){
					result.setResult(false);
					result.setMsg("已教授该课程");
					return result;
				}
			}
			TeachingVO tgvo = new TeachingVO();
			tgvo.setuId(String.valueOf(courseid));
			tgvo.settId(tid);
			int r = this.teachingService.insertSelective(tgvo);
			if(r == 1){
				result.setResult(true);
				result.setMsg("添加课程成功");
			}else{
				result.setResult(false);
				result.setMsg("添加课程失败");
			}
		}else{
			result.setResult(false);
			result.setMsg("该课程不存在");
		}
		
		return result;
	}
	
	/**
	 * 获取某位评分员需要评分的老师列表
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getEvaluateTeacher",method=RequestMethod.POST)
	public @ResponseBody ResultVO vgetEvaluateTeacher(HttpServletRequest req){
		logger.info("/getEvaluateTeacher");
		ResultVO result = new ResultVO();
		
		String user = SessionUtil.checkUser(req).getId();
		TeacherVO tvo = this.teacherService.selectByPK(user);
		String org = tvo.getOrganization();
		String type = tvo.getType();
		TeacherVO wherevo = new TeacherVO();
		wherevo.setOrganization(org);
		wherevo.setType("teacher");
		TeacherVO[] tvos = this.teacherService.selectByWhere(wherevo);
		//如果评分员对某一个老师的每一门课程都评价过了，就不能再给该老师评价了.
		//现在的问题是：对于管理员来说，他打的教学效果分在effect_score表里面，对于评分员来说，
		//他们打的分数在score表里面,所以这里需要区分;
		List<String> tid_list = new ArrayList<String>();
		if(type.equals("admin")){
			EffectScoreVO effectScoreVO = new EffectScoreVO();
			effectScoreVO.setAdminId(user);
			List<EffectScoreVO> evos = this.effectScoreService.selectByWhere(effectScoreVO);
			if(evos.size()>0){
				for(int i=0;i<evos.size();i++){
					Integer tcid = evos.get(i).getTeacherCourseId();
					TeachingVO teachingVO = this.teachingService.selectByPK(tcid);
					String teacher_id = String.valueOf(teachingVO.gettId());
					tid_list = ListUtil.addIfDif(teacher_id, tid_list);
				}
			}
		}else if(type.equals("scorer")){
			ScoreVO svo = new ScoreVO();
			svo.settId(user);
			ScoreVO[] svos = this.scoreService.selectByWhere(svo);
			if(svos.length>0){
				for(int i=0;i<svos.length;i++){
					Integer tcid = svos[i].getTcId();
					TeachingVO tgvo = this.teachingService.selectByPK(tcid);
					String tid = String.valueOf(tgvo.gettId());
					tid_list = ListUtil.addIfDif(tid, tid_list);
				}
			}
		}
//		ScoreVO svo = new ScoreVO();
//		svo.settId(user);
//		ScoreVO[] svos = this.scoreService.selectByWhere(svo);
//		List<String> tid_list = new ArrayList<String>();
//		if(svos.length>0){
//			for(int i=0;i<svos.length;i++){
//				Integer tcid = svos[i].getTcId();
//				TeachingVO tgvo = this.teachingService.selectByPK(tcid);
//				String tid = String.valueOf(tgvo.gettId());
//				tid_list = ListUtil.addIfDif(tid, tid_list);
//			}
//		}
		
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
	
	/**
	 * 判断该老师还能不能添加新课程
	 * 判断标准  有没有评分已经对老师的课程开始评价
	 * @param req
	 * @return
	 */
	@RequestMapping(value="isCanAdd")
	public @ResponseBody ResultVO visCanAdd(HttpServletRequest req){
		logger.info("/data/isCanAdd");
		ResultVO result = new ResultVO();
		
		String tid = SessionUtil.checkUser(req).getId();
		List<TeachingVO> tgvo_list = this.teachingService.selectByTid(tid);
		if(tgvo_list.size() == 0){
			result.setResult(true);
			return result;
		}else{
			Integer tcid = tgvo_list.get(0).getId();
			ScoreVO svo = new ScoreVO();
			svo.setTcId(tcid);
			ScoreVO[] svos = this.scoreService.selectByWhere(svo);
			if(svos.length == 0){
				result.setResult(true);
				return result;
			}else{
				result.setResult(false);
				return result;
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getScoreWeight",method=RequestMethod.POST)
	public @ResponseBody ResultVO vgetScoreWeight(){
		logger.info("data/getScoreWeight");
		ResultVO result = new ResultVO();
		ScoreWeightVO swvo = this.scoreWeightService.selectByPK(1);
		result.setResult(true);
		result.setT(swvo);
		return result;
	}
	
	@RequestMapping(value="changeSW",method=RequestMethod.POST)
	public @ResponseBody ResultVO vchangeSW(@RequestBody ScoreWeightVO swvo){
		logger.info("data/changeSW");
		ResultVO result = new ResultVO();
		
		swvo.setId(1);
		int r = this.scoreWeightService.updateByPrimaryKeySelective(swvo);
		if(r == 1){
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		
		return result;
	}
	
	@RequestMapping(value="changeCW",method=RequestMethod.POST)
	public @ResponseBody ResultVO vchangeCW(@RequestBody CourseWeightVO cwvo){
		logger.info("data/changeSW");
		ResultVO result = new ResultVO();
		
		int r = this.courseWeightService.updateBycid(cwvo);
		
		if(r == 1){
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		
		return result;
	}
	//管理员提交老师其他项加减分
	@RequestMapping(value="submitOtherScore",method=RequestMethod.POST)
	public @ResponseBody ResultVO vsubmitOtherScore(@RequestBody List<OtherGradeVO> otherGradeVOs){
		logger.info("data/submitOtherScore");
		ResultVO result = new ResultVO();
		for(int i=0;i<otherGradeVOs.size();i++){
			OtherGradeVO old = this.otherGradeService.query(otherGradeVOs.get(i).getId());
			
			if(old == null){
				int r = this.otherGradeService.insertData(otherGradeVOs.get(i));
				if(r==1)
					result.setResult(true);
				else
					result.setResult(false);
			}else{
				int r = this.otherGradeService.updateData(otherGradeVOs.get(i));
				if(r==1)
					result.setResult(true);
				else
					result.setResult(false);
			}
		}
		return result;
	}
	
	//管理员提交老师竞赛分数
	@RequestMapping(value="submitCompetitionScore",method=RequestMethod.POST)
	public @ResponseBody ResultVO vsubmitCompetitionScore(@RequestBody List<CompotitionGradeVO> compotitionGradeVOs){
		logger.info("data/submitCompetitionScore");
		ResultVO result = new ResultVO();
		for(int i=0;i<compotitionGradeVOs.size();i++){
			//如果之前提交过，则改为更新
			CompotitionGradeVO old = this.competitionService.query(compotitionGradeVOs.get(i).getId());
			if(old == null){
				int r = this.competitionService.insert(compotitionGradeVOs.get(i));
				if(r==1)
					result.setResult(true);
				else
					result.setResult(false);
			}else{
				int r = this.competitionService.update(compotitionGradeVOs.get(i));
				if(r==1)
					result.setResult(true);
				else
					result.setResult(false);
			}
		}
		return result;
	}
	//超级管理员设置管理员
	@RequestMapping(value="submitAdmin",method=RequestMethod.POST)
	public @ResponseBody ResultVO vsubmitAdmin(@RequestBody AdminVO adminVO){
		logger.info("data/submitCompetitionScore");
		ResultVO result = new ResultVO();
		//每个学校只能够有一个管理员，只能设置那些角色为admin_uncheck的用户（表示未激活的管理员）
		String adminId = adminVO.getAdminId();
		
		TeacherVO target = this.teacherService.selectByPK(adminId);
		
		String org = target.getOrganization();
		TeacherVO conditionVO = new TeacherVO();
		conditionVO.setOrganization(org);
		conditionVO.setType("admin");
		
		TeacherVO oldAdmins[] = this.teacherService.selectByWhere(conditionVO);
		if(oldAdmins != null){
			for(int i=0;i<oldAdmins.length;i++){
				oldAdmins[i].setType("admin_uncheck");
				this.teacherService.update(oldAdmins[i]);
			}
		}
		target.setType("admin");
		this.teacherService.update(target);
		
		result.setMsg("success");
		result.setResult(true);
		return result;
	}
}
