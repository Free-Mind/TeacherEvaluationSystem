package com.te.util;

import javax.servlet.http.HttpServletRequest;

import com.te.vo.TeacherVO;

/**
 * 封装session操作
 * @author LichKing
 *
 */
public class SessionUtil {

	/**
	 * 获取当前用户
	 * @param req
	 * @return
	 */
	public static TeacherVO checkUser(HttpServletRequest req){
		TeacherVO curUser =  (TeacherVO) req.getSession().getAttribute("curUser");
		return curUser;
	}
	
}
