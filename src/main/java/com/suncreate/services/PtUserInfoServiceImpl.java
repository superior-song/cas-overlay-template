package com.suncreate.services;


import com.suncreate.common.base.BaseMapper;
import com.suncreate.common.base.BaseServiceImpl;
import com.suncreate.entity.PtUserInfo;
import com.suncreate.repository.PtUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>Title: PtUserInfo.java</p>
 * <p>Description:
 平台_用户信息服务接口实现</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-12-30 21:38:24
 */
@Slf4j
@Service("ptUserInfoService")
public class PtUserInfoServiceImpl extends BaseServiceImpl<PtUserInfo> implements PtUserInfoService {
    @Autowired
    private PtUserInfoMapper ptUserInfoMapper;

    @Override
    public BaseMapper<PtUserInfo> getMapper() {
        return this.ptUserInfoMapper;
    }
}