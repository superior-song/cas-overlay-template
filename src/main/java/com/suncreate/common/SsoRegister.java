package com.suncreate.common;

import lombok.Data;

/**
 * Created by songxiaoliang on 2020/12/28 0028.
 * 注册信息基础类
 */
@Data
public class SsoRegister {
    private Long id;
    private String serviceId;//服务id
    private String despriction;//描述
    private String name;//名称
    private String url;

}
