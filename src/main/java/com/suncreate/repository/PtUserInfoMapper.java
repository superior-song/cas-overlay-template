package com.suncreate.repository;


import com.suncreate.common.base.BaseMapper;
import com.suncreate.entity.PtUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: PtUserInfoMapper.java</p>
 * <p>Description:
 平台_用户信息DAO</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-12-30 21:30:08
 */
@Mapper
@Repository
public interface PtUserInfoMapper  extends BaseMapper<PtUserInfo> {

}
