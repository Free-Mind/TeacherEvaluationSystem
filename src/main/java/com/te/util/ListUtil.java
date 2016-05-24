package com.te.util;

import java.util.List;

import com.te.vo.TeacherVO;

/**
 * 
 * @author LichKing
 *	封装了一些List操作的方法
 */
public class ListUtil {

	
	/**
	 * 如果list中没有obj 则list.add(obj)
	 * @param ob
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List addIfDif(Object ob,List list){
		if(list.size() == 0){
			list.add(ob);
		}else{
			boolean flag = true;
			for(Object o : list){
				if(o.equals(ob))
					flag = false;
			}
			if(flag){
				list.add(ob);
			}
		}
			
		return list;
	}
	
	/**
	 * 如果svo_list中的TeacherVO的tid存在于tid_list中 则从svo_list中移除这个TeacherVO
	 * @param svo_list
	 * @param tid_list
	 * @return
	 */
	public static List<TeacherVO> removeIfHasTid(List<TeacherVO> svo_list,List<String> tid_list){
		for(String tid : tid_list){
			for(TeacherVO tvo : svo_list){
				if(tid.equals(tvo.getId())){
					svo_list.remove(tvo);
					break;
				}
			}
		}
		
		return svo_list;
	}
	
}
