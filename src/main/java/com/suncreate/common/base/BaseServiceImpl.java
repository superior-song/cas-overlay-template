package com.suncreate.common.base;

import com.github.pagehelper.PageInfo;
import com.suncreate.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: BaseServiceImpl.java</p>
 * <p>Description:通用的BaseServiceImpl</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-9 15:05:22
 **/
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	public abstract BaseMapper<T> getMapper();

	/** 根据主键删除实体 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteByKey(Long uuid)throws BusinessException {
		return getMapper().deleteByKey(uuid);
	}
	/** 插入实体 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(T t)throws BusinessException {
		return getMapper().insert(t);
	}
	/** 精确更新实体 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateSelective(T t) throws BusinessException{
		return getMapper().updateSelective(t);
	}
	/** 根据条件删除返回实体 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteByWhere(T t) throws BusinessException{
		return getMapper().deleteByWhere(t);
	}
	/** 根据主键查询返回实体 */
	@Override
	public T getModelByKey(Long uuid) {
		return getMapper().getModelByKey(uuid);
	}
	/** 统计记录 */
	@Override
	public Long countAll(T t) {
		return getMapper().countAll(t);
	}
	/** 查询所有的ID */
	@Override
	public List<String> getAllIds(T t) {
		return getMapper().getAllIds(t);
	}
	/** 分页 */
	@Override
	public PageInfo<T> getListByPage(T t) {
		List<T> tList = getMapper().getListByWhere(t);
		PageInfo<T> tPageInfo = new PageInfo<T>(tList);
		return  tPageInfo;
	}
	/** 根据条件查询实体集合  */
	@Override
	public List<T> getListByWhere(T t) {
		return getMapper().getListByWhere(t);
	}
	/** 根据条件查询返回实体 */
	@Override
	public T getModelByWhere(T t) {
		return getMapper().getModelByWhere(t);
	}
}
