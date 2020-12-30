package com.suncreate.common.base;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>Title: UcmDepartmentMapper.java</p>
 * <p>Description:通用的mapper</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-9 15:05:22
 **/
public interface BaseMapper<T> extends Mapper<T>{

	/** 根据主键删除实体 */
	int deleteByKey(Long uuid);
	/** 插入实体 */
	int insert(T t);
	/** 精确更新实体 */
	int updateSelective(T t);
	/** 根据条件删除返回实体 */
	int deleteByWhere(T t);
	/** 根据主键查询返回实体 */
	T getModelByKey(Long uuid);
	/** 统计记录 */
	Long countAll(T t);
	/** 查询所有的ID */
	List<String> getAllIds(T t);
	/** 根据条件查询实体集合  */
	List<T> getListByWhere(T t);
	/** 根据条件查询返回实体 */
	T getModelByWhere(T t);

}
