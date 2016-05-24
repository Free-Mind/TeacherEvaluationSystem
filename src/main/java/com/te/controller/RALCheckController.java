package com.te.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.te.itf.service.ITeacherService;
import com.te.vo.PswVO;
import com.te.vo.ResultVO;
import com.te.vo.TeacherVO;
import com.te.vo.UserVO;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/data")
public class RALCheckController {

	@Resource
	private ITeacherService teacherService;
	
	private Logger logger = Logger.getLogger(RALCheckController.class);
	
	private static final String PSW = "123456789";
	
	/**
	 * 注册时判断改用户名是否可以使用
	 * @param uvo
	 * @return
	 */
	@RequestMapping("/checkUsername")
	public @ResponseBody ResultVO checkUsername(@RequestBody UserVO uvo){
		logger.info("/data/checkUsername");
		String id = uvo.getUsername();
		TeacherVO tvo = this.teacherService.selectByPK(id);
		ResultVO result = new ResultVO();
		if(tvo == null){
			result.setResult(true);
			result.setMsg("该用户名可以使用");
		}else{
			result.setResult(false);
			result.setMsg("该用户名已经存在");
		}
		return result;
	}
	
	/**
	 * 注册操作
	 * @param tvo
	 * @return
	 */
	@RequestMapping("/registerForTeacher")
	public @ResponseBody ResultVO vregister(@RequestBody TeacherVO tvo){
		logger.info("/data/registerForTeacher");
		ResultVO result = new ResultVO();
		
		int r = this.teacherService.insert(tvo);
		//System.out.println(r);
		if(r == 1){
			result.setResult(true);
		}else{
			result.setResult(false);
			result.setMsg("注册失败，请稍后再试！");
		}
		
		return result;
	}
	
	/**
	 * 登录操作
	 * @param uvo
	 * @param req
	 * @return
	 */
	@RequestMapping("/checkUP")
	public @ResponseBody ResultVO vcheckUP(@RequestBody UserVO uvo,HttpServletRequest req){
		logger.info("/data/checkUP");
		ResultVO result = new ResultVO();
		TeacherVO tvo = this.teacherService.selectByPK(uvo.getUsername());
		if(tvo == null){
			result.setResult(false);
			result.setMsg("该账号不存在！");
		}else{
			String pw_in_db = tvo.getPassword();
			if(pw_in_db.equals(uvo.getPassword())){
				result.setResult(true);
				String type = tvo.getType();
				result.setMsg(type + "_main");
				req.getSession().setAttribute("curUser", tvo);
			}else{
				result.setResult(false);
				result.setMsg("密码错误！");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/checkpsw",method=RequestMethod.POST)
	public @ResponseBody ResultVO vcheck(@RequestBody PswVO pvo,HttpServletRequest req){
		logger.info("/data/checkpsw");
		ResultVO result = new ResultVO();
		String psw = pvo.getPsw();
		if(psw.equals(PSW)){
			result.setResult(true);
			result.setMsg("super_manage");
		}else{
			result.setResult(false);
			result.setMsg("授权码错误！");
		}
		req.getSession().setAttribute("admin", "admin");
		return result;
	}
	
	
}
