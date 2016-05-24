package com.te.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.te.itf.service.ICompetitionGradeService;
import com.te.vo.AvgScoreVO;
import com.te.vo.CompotitionGradeVO;
import com.te.vo.CourseWeightVO;
import com.te.vo.OtherGradeVO;
import com.te.vo.ScoreAndWeightVO;
import com.te.vo.ScoreExcelVO;
import com.te.vo.ScoreWeightVO;
import com.te.vo.TeacherVO;
import com.te.vo.TeachingVO;
import com.te.vo.co.ScoreRowVO;


public class ScoreComputeUtil {

	//public static String EXCEL_PATH = "成绩表.xls";
	//private ICompetitionGradeService competitionGradeService;
	
	public static ScoreExcelVO compute(List<AvgScoreVO> asvo_list,ScoreWeightVO swvo,
			List<TeachingVO> tgvo_list,TeacherVO[] tvos,List<CourseWeightVO> cwvo_list,
			List<OtherGradeVO> other_grade_list,List<CompotitionGradeVO> competition_grade_list
			){
		
		ScoreExcelVO sevo = new ScoreExcelVO();
		
		//求出每个老师每门课的平均成绩
		Map<Integer,Double> AvgScoreMap = new HashMap<Integer,Double>();
		double p1i = swvo.getP1I();
		double p2i = swvo.getP2I();
		double p3i = swvo.getP3I();
		for(AvgScoreVO asvo : asvo_list){
			double p1 = asvo.getP1();
			double p2 = asvo.getP2();
			double p3 = asvo.getP3();
		//	double pl = asvo.getPl();
		//	double as = (p1*p1i + p2*p2i + p3*p3i)/(p1i + p2i + p3i) + pl;
			double as = (p1*p1i + p2*p2i + p3*p3i)/(p1i + p2i + p3i);
			AvgScoreMap.put(asvo.getTc_id(), as);
		}
		
		//得到每个老师教授课程的tcid list  如果该老师没选过课 则不会出现
		Map<String,List<Integer>> TeachingMap = TeachingListToMap(tgvo_list);
		
		List<ScoreRowVO> srvo_list = new ArrayList<ScoreRowVO>();
		for(int i = 0; i < tvos.length ; i++){
			List<Integer> tcid_list = TeachingMap.get(tvos[i].getId());
			//过滤有些老师没选课
			if(tcid_list == null){
				continue;
			}
			
			Map<Integer,ScoreAndWeightVO> ScoreAndWeightMap = getScoreAndWeightMap(AvgScoreMap, tcid_list, cwvo_list, tgvo_list);
			//得到了老师的教学质量评分
			double score = getFinalScore(ScoreAndWeightMap);
			if(Double.isNaN(score)){
				continue;
			}
			//总成绩=教学质量评分+竞赛得分+其他加减项得分
			//1.根据教师ID获取竞赛得分
			int competition_grade = 0;
			for(int j=0;j<competition_grade_list.size();j++){
				//找到对应的老师，取出其竞赛成绩
				if(competition_grade_list.get(j).getId().equals(tvos[i].getId())){
					competition_grade = competition_grade_list.get(j).getGrade();
					break;
				}
			}
			//根据教师ID取出其他加减项分数
			int other_grade = 0;
			for(int j=0;j<other_grade_list.size();i++){
				//找到对应的老师，取出其其他加减项分数
				if(other_grade_list.get(j).getId().equals(tvos[i].getId())){
					other_grade = other_grade_list.get(j).getOtherGrade();
					break;
				}
			}
			//老师总成绩
			double total = 0.3*competition_grade+score*0.7+other_grade;
			
			ScoreRowVO srvo = new ScoreRowVO();
			srvo.setTid(tvos[i].getId());
			srvo.setName(tvos[i].getName());
			srvo.setOrg(tvos[i].getOrganization());
			srvo.setScore(total);
			
			srvo_list.add(srvo);
		}
		
		sevo.setS_list(srvo_list);
		
		Date date = new Date();
		sevo.setDate(date);
		
		return sevo;
	}
	
	//将相同老师的课程合并到list中
	private static Map<String,List<Integer>> TeachingListToMap(List<TeachingVO> tgvo_list){
		Map<String,List<Integer>> TeachingMap = new HashMap<String,List<Integer>>();
		
		for(TeachingVO tgvo : tgvo_list){
			String tid = tgvo.gettId();
			Set<String> keys = TeachingMap.keySet();
			boolean isfind = false;
			for(String key : keys){
				if(key.equals(tid)){
					List<Integer> tid_list = TeachingMap.get(key);
					tid_list.add(tgvo.getId());
					TeachingMap.put(key, tid_list);
					isfind = true;
					break;
				}
			}
			if(!isfind){
				List<Integer> tid_list = new ArrayList<Integer>();
				tid_list.add(tgvo.getId());
				TeachingMap.put(tid, tid_list);
			}
		}
		
		return TeachingMap;
	}
	
	//根据list<tcid>得到map<tcid,<score,weight>>
	private static Map<Integer,ScoreAndWeightVO> getScoreAndWeightMap(Map<Integer,Double> AvgScoreMap,
			List<Integer> tcid_list,List<CourseWeightVO> cwvo_list,List<TeachingVO> tgvo_list){
		Map<Integer,ScoreAndWeightVO> ScoreAndWeightMap = new HashMap<Integer,ScoreAndWeightVO>();
		
		Set<Integer> keys = AvgScoreMap.keySet();
		for(Integer tcid : tcid_list){
			for(Integer key : keys){
				if(tcid == key){
					ScoreAndWeightVO sawvo = new ScoreAndWeightVO();
					sawvo.setScore(AvgScoreMap.get(key));
					Integer cid = getCid(tcid, tgvo_list);
					Integer weight = getWeight(cid, cwvo_list);
					sawvo.setWeight(weight);
					ScoreAndWeightMap.put(tcid, sawvo);
				}
			}
		}
		
		
		return ScoreAndWeightMap;
	}
	
	//获取cid
	private static Integer getCid(Integer tcid,List<TeachingVO> tgvo_list){
		for(TeachingVO tgvo : tgvo_list){
			if(tgvo.getId().equals(tcid)){
				return Integer.parseInt(tgvo.getuId());
			}
		}
		return -1;
	}
	
	//获取权重
	private static Integer getWeight(Integer cid,List<CourseWeightVO> cwvo_list){
		for(CourseWeightVO cwvo : cwvo_list){
			if(cwvo.getcId().equals(cid)){
				return cwvo.getCourseWeight();
			}
		}
		return -1;
	}
	
	//求出成绩的加权平均值
	private static double getFinalScore(Map<Integer,ScoreAndWeightVO> ScoreAndWeightMap){
		Integer allweights = 0;
		double score = 0.0;
		Set<Integer> keys = ScoreAndWeightMap.keySet();
		for(Integer key : keys){
			ScoreAndWeightVO sawvo = ScoreAndWeightMap.get(key);
			allweights += sawvo.getWeight();
			score += sawvo.getScore()*sawvo.getWeight();
		}
		score = score / allweights ;
		return score;
	}
}
