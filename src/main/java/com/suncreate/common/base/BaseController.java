package com.suncreate.common.base;

import java.util.List;

/**
 * @author  wq
 * @date  2019/5/24 14:39
 *
 * @desc controller的基类
 **/
public class BaseController {

	/**
	 * 分页助手，因为这个是WFM，无法SQL分页，只能物理分页
	 * @param tList	要分页的list列表
	 * @param currPage	当前页
	 * @param pageSize	每页多少条数据
	 * @param <T>	list 类型的数据
	 * @return	截取后的list列表
	 */
	protected  <T> List<T> pageHelper(List<T> tList,Integer currPage, Integer pageSize){
		int size = tList.size();
		int startNum = (currPage - 1) * pageSize;
		int endNum = currPage * pageSize;
		if(startNum > size){
			return tList;
		}else{
			if(endNum > size){
				endNum = size;
			}
			return tList.subList(startNum,endNum);
		}
	}

}
