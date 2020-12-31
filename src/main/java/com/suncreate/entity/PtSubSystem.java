package com.suncreate.entity;

import lombok.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: PtSubSystemEntity.java</p>
 * <p>Description:
 平台_系统表实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author zhangjinguo
 * @date 2020-12-31 22:10:13
 */
@Data
public class PtSubSystem{

    /**ID*/
    private Long id;
    /**子系统名称*/
    private String subSysName;
    /**系统描述*/
    private String sysDesc;
    /**系统类型，1：BS，2：CS*/
    private Long sysType;
    /**链接URL*/
    private String sysUrl;
    /**SSO_KEY必须唯一,必须为URL地址,如：http://xxx*/
    private String ssoKey;
    /**系统图标*/
    private String sysIcon;
    /**排序值*/
    private Long orderValue;
    /**是否删除*/
    private Long isDel;
    /**添加人*/
    private String addId;
    /**添加时间*/
    private Date addTime;
    /**修改人*/
    private String modifyId;
    /**修改时间*/
    private Date modifyTime;
    /**鼠标移动显示图片*/
    private String sysBigImg;
    /**鼠标移走显示图片*/
    private String sysSmallImg;
    /**无法使用显示图片*/
    private String sysGrayImg;
    /**是否显示日志*/
    private Long showLog;
    /** 扩展属性集合 **/
    private final Map<String,Object> map=new HashMap<String, Object>();

    /**
     *无参构造函数
     */
    public PtSubSystem(){
        super();
    }

}
