package com.te.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.te.itf.service.ICompetitionGradeService;
import com.te.itf.service.ICourseWeightService;
import com.te.itf.service.IEffectScoreService;
import com.te.itf.service.IOtherGradeService;
import com.te.itf.service.IScoreService;
import com.te.itf.service.IScoreWeightService;
import com.te.itf.service.ITeacherService;
import com.te.itf.service.ITeachingService;
import com.te.util.ExcelProcessUtil;
import com.te.util.ScoreComputeUtil;
import com.te.util.SessionUtil;
import com.te.vo.AvgScoreVO;
import com.te.vo.CompotitionGradeVO;
import com.te.vo.CourseWeightVO;
import com.te.vo.EffectScoreVO;
import com.te.vo.OtherGradeVO;
import com.te.vo.ResultVO;
import com.te.vo.ScoreExcelVO;
import com.te.vo.ScoreWeightVO;
import com.te.vo.TeacherVO;
import com.te.vo.TeachingVO;


@Controller
@RequestMapping("/scorecompute")
@SuppressWarnings("rawtypes")
public class ScoreComputeController {

	@Resource
	private IScoreService scoreService;
	
	@Resource
	private IScoreWeightService scoreWeightService;
	
	@Resource
	private ITeachingService teachingService;
	
	@Resource
	private ITeacherService teacherService;
	
	@Resource
	private ICourseWeightService courseWeightService;
	
	@Resource
	private ICompetitionGradeService competitionGradeService;
	
	@Resource
	private IOtherGradeService otherGradeService;
	
	@Resource
	private IEffectScoreService effectScoreService;

	private static final String OUT_PATH = "教师成绩.xls";
	
	Logger logger = Logger.getLogger(ScoreComputeController.class);
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAllAvgScore",method=RequestMethod.POST)
	public @ResponseBody ResultVO vgetAllAvgScore(HttpServletRequest req){
		logger.info("/scorecompute/getAllAvgScore");
		ResultVO result = new ResultVO();
		
		if(SessionUtil.checkUser(req).getType().equals("teacher")){
			result.setResult(false);
			result.setMsg("请先登录");
			return result;
		}
		//评分表中的数据:p1 和p2 有用:教学准备、课程实施
		List<AvgScoreVO> asvo_list = this.scoreService.selectAvgBytcid();
		//获取教学效果评分
		List<EffectScoreVO> effectScoreList = this.effectScoreService.selectByWhere(new EffectScoreVO());
		//将教学效果评分（effectScore）填充进入评分表
		for(int i=0;i<effectScoreList.size();i++){
			for(int j=0;j<asvo_list.size();j++){
				if(asvo_list.get(j).getTc_id() == effectScoreList.get(i).getTeacherCourseId()){
					asvo_list.get(j).setP3(effectScoreList.get(i).getScore());
				}
			}
		}
		
		// 评分项的权重
		ScoreWeightVO swvo = this.scoreWeightService.selectByPK(1);
		// 课程表数据
		List<TeachingVO> tgvo_list = this.teachingService.selectByWhere(new TeachingVO());
		TeacherVO tvo = new TeacherVO();
		tvo.setType("teacher");
		tvo.setOrganization(SessionUtil.checkUser(req).getOrganization());
		// 教师数据 管理员只能选出本校的老师
		TeacherVO[] tvos = this.teacherService.selectByWhere(tvo);
		// 课程权重
		List<CourseWeightVO> cwvo_list = this.courseWeightService.selectAll();
		//获取竞赛得分
		List<CompotitionGradeVO> cometition_list = this.competitionGradeService.selectAll();
		//获取其他加减分
		List<OtherGradeVO> other_grade_list = this.otherGradeService.selectAll();
		
		ScoreExcelVO sevo = ScoreComputeUtil.compute(asvo_list, swvo, tgvo_list, tvos, cwvo_list,other_grade_list,cometition_list);
		
		ExcelProcessUtil.outputExcel(sevo, OUT_PATH);
		
		result.setResult(true);
		result.setT(sevo);
		return result;
	}
	
	
	@RequestMapping(value="/download")
	public String vdownload(HttpServletResponse res,HttpServletRequest req){
		
		if(SessionUtil.checkUser(req).getType().equals("teacher")){
			return "login";
		}
		
		res.setCharacterEncoding("utf-8");
        res.setContentType("multipart/form-data");
        String filename = "score.xls";
		res.setHeader("Content-Disposition", "attachment;fileName="+filename);
        try {
        	
            InputStream inputStream = new FileInputStream(new File("教师成绩.xls"));
 
            OutputStream os = res.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
 
            os.close();
 
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return null;
	}
			
}
