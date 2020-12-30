package com.suncreate.common.base;

import com.github.pagehelper.PageInfo;
import com.suncreate.exception.BusinessException;

import java.util.List;

/**
 * <p>Title: BaseService.java</p>
 * <p>Description:通用的BaseService</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-9 15:05:22
 **/
public interface BaseService<T>  {
	/** 根据主键删除实体 */
	int deleteByKey(Long uuid)throws BusinessException;
	/** 插入实体 */
	int insert(T t)throws BusinessException;
	/** 精确更新实体 */
	int updateSelective(T t)throws BusinessException;
	/** 根据条件删除返回实体 */
	int deleteByWhere(T t)throws BusinessException;
	/** 根据主键查询返回实体 */
	T getModelByKey(Long uuid);
	/** 统计记录 */
	Long countAll(T t);
	/** 查询所有的ID */
	List<String> getAllIds(T t);
	/** 分页 */
	PageInfo<T> getListByPage(T t);
	/** 根据条件查询实体集合  */
	List<T> getListByWhere(T t);
	/** 根据条件查询返回实体 */
	T getModelByWhere(T t);
}
