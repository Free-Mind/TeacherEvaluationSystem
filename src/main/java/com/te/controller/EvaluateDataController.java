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

import com.te.itf.service.ICourseService;
import com.te.itf.service.IEffectScoreService;
import com.te.itf.service.IScoreService;
import com.te.itf.service.ITeachingService;
import com.te.util.SessionUtil;
import com.te.vo.CourseVO;
import com.te.vo.EffectScoreVO;
import com.te.vo.EvalResultVO;
import com.te.vo.FrontScoreVO;
import com.te.vo.ResultVO;
import com.te.vo.ScoreVO;
import com.te.vo.TeachingVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/data")
public class EvaluateDataController {

	@Resource
	private ITeachingService teachingService;
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private IScoreService scoreService;
	
	@Resource
	private IEffectScoreService effectScoreService;
	
	Logger logger = Logger.getLogger(EvaluateDataController.class);
	
	/**
	 * 获取某个评分员需要评分的课程
	 * @param req
	 * @return
	 */
	@RequestMapping("/getCourseToEvaluate")
	public @ResponseBody ResultVO vgetCourseToEvaluate(HttpServletRequest req){
		
		logger.info("/data/getCourseToEvaluate");
		
		ResultVO result = new ResultVO();
		
		if(SessionUtil.checkUser(req).getType().equals("teacher")){
			result.setResult(false);
			result.setMsg("请先登录");
			return result;
		}
		
		String tid = req.getParameter("tid");
		List<CourseVO> c_list = new ArrayList<CourseVO>();
		List<TeachingVO> tg_list =  this.teachingService.selectByTid(tid);
		for(TeachingVO tgvo : tg_list){
			String cid = tgvo.getuId();
			CourseVO cvo = this.courseService.selectByPK(Integer.parseInt(cid));
			c_list.add(cvo);
		}
		//通过session判断用户类型
		Object user = req.getSession().getAttribute("curUser");
		EvalResultVO evalResultVO = new EvalResultVO();
		evalResultVO.setType(user);
		evalResultVO.setList(c_list);
		
		result.setResult(true);
		result.setT(evalResultVO);
		
		return result;
	}
	
	/**
	 * 将输入的成绩录入数据库
	 * @param fsvo_list
	 * @param req
	 * @return
	 */
	@RequestMapping(value="submitScore",method=RequestMethod.POST)
	public @ResponseBody ResultVO vsubmitScore(@RequestBody List<FrontScoreVO> fsvo_list,HttpServletRequest req){
		logger.info("data/submitScore");
		ResultVO result = new ResultVO();
		
		if(SessionUtil.checkUser(req).getType().equals("teacher")){
			result.setResult(false);
			result.setMsg("请先登录");
			return result;
		}
		
		List<ScoreVO> svo_list = new ArrayList<ScoreVO>();
		String e_tid = SessionUtil.checkUser(req).getId();
		for(FrontScoreVO fsvo : fsvo_list){
			TeachingVO tgvo = new TeachingVO();
			tgvo.settId(fsvo.getTid());
			tgvo.setuId(fsvo.getCid());
			List<TeachingVO> tg_list = this.teachingService.selectByWhere(tgvo);
			if(tg_list.size() == 1){
				Integer tcid = tg_list.get(0).getId();
				//判断当前是评那种类型的分数：教学准备、课程实施和教学效果
				if(fsvo.getP1()==null || fsvo.getP2() == null){
					//管理员评教学效果分（p3）
					EffectScoreVO effectScoreVO = new EffectScoreVO();
					effectScoreVO.setTeacherCourseId(tcid);
					effectScoreVO.setScore(fsvo.getP3());
					effectScoreVO.setAdminId(e_tid);
					int r = this.effectScoreService.insert(effectScoreVO);
					if(r!=1){
						result.setResult(false);
						result.setMsg("插入教学效果分时出错！");
						return result;
					}
				}else{
					//评分员评教学准备和课程实施两项分数(p1和p2)
					ScoreVO svo = new ScoreVO();
					svo.setTcId(tcid);
					svo.settId(e_tid);
					svo.setP1(fsvo.getP1());
					svo.setP2(fsvo.getP2());
					svo.setP3(fsvo.getP3());
					svo.setPl(fsvo.getPl());
					int r = this.scoreService.insertSelective(svo);
					if(r!=1){
						result.setResult(false);
						result.setMsg("插入成绩表时出错！");
						return result;
					}
				}
//				ScoreVO svo = new ScoreVO();
//				svo.setTcId(tcid);
//				svo.settId(e_tid);
//				svo.setP1(fsvo.getP1());
//				svo.setP2(fsvo.getP2());
//				svo.setP3(fsvo.getP3());
//				svo.setPl(fsvo.getPl());
//				svo_list.add(svo);
			}else{
				result.setResult(false);
				result.setMsg("数据库出错，请检查");
				return result;
			}
		}
//		for(ScoreVO svo : svo_list){
//			int r = this.scoreService.insertSelective(svo);
//			if(r == 1){
//				continue;
//			}else{
//				result.setResult(false);
//				result.setMsg("插入成绩表时出错！");
//				return result;
//			}
//		}
		result.setResult(true);
		return result;
	}
}
