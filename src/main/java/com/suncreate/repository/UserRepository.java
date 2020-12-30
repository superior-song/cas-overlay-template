package com.suncreate.repository;

import com.suncreate.entity.User;

/**
 * Created by songxiaoliang on 2020/12/31 0031.
 */
public interface UserRepository {
    User getPrincipal(String phoneNumber);
}