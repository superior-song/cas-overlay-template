package com.suncreate.entity;

import lombok.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: PtUserInfoEntity.java</p>
 * <p>Description:
 平台_用户信息实体</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * @author songxiaoliang
 * @date 2020-12-30 21:27:20
 */
@Data
public class PtUserInfo{

    /**ID*/
    private String id;
    /**用户名*/
    private String userName;
    /**密码*/
    private String password;
    /**是否必须修改密码*/
    private Long isMustModifyPsd;
    /**用户姓名*/
    private String realName;
    /**姓名拼音搜索*/
    private String pinyin;
    /**性别*/
    private Double sex;
    /**出生日期*/
    private Date birthday;
    /**所属部门ID*/
    private Double orgId;
    /**职位*/
    private String jobId;
    /**手机号码*/
    private String mobileNo;
    /**邮箱*/
    private String email;
    /**用户类型*/
    private Double userType;
    /**上次登录时间*/
    private Date lastLoginTime;
    /**上次登录IP*/
    private String lastLoginIp;
    /**登录次数*/
    private Double loginCount;
    /**QQ号*/
    private String qqNo;
    /**MSN账号*/
    private String msnNo;
    /**家庭住址*/
    private String homeAddress;
    /**用户头像地址*/
    private String userAvatar;
    /**VCN注册的ID*/
    private String vcnUserId;
    /**注册VCN的字符串*/
    private String vcnStr;
    /**云台控制级别,默认为0*/
    private Long ptzLevel;
    /**是否启用有效期控制*/
    private Long isUseValidity;
    /**有效开始时间*/
    private Date validityBeginDate;
    /**有效结束时间*/
    private Date validityEndDate;
    /**子系统访问权限，逗号分隔，子系统代码参照子系统表*/
    private String subSysPrivilege;
    /**是否删除*/
    private Long isDel;
    /**排序值*/
    private Long orderValue;
    /**添加人*/
    private String addId;
    /**添加时间*/
    private Date addTime;
    /**修改人*/
    private String modifyId;
    /**修改时间*/
    private Date modifyTime;
    /**变更标识，1:增2:删3:改*/
    private Long changeFlag;
    /**办公电话*/
    private String officePhone;
    /**办公地址*/
    private String officeAddress;
    /**项目标识，1：天网，2：雪亮*/
    private Long projectFlag;
    /**是否是社会公众账号*/
    private Long isSocialAccount;
    /**权限性质类型*/
    private Long validityType;
    /**身份证*/
    private String idCard;
    /**警号*/
    private String policeNo;
    /**暂无信息*/
    private String orgname;
    /**暂无信息*/
    private String roleIds;
    /**是否校验用户登录IP，0不校验，1校验*/
    private Long isValidateUserIp;
    /**用户限定的登录IP，配合是否校验用户登录IP字段使用*/
    private String userIp;
    /**暂无信息*/
    private Date passwordUpdateDate;
    /** 扩展属性集合 **/
    private final Map<String,Object> map=new HashMap<String, Object>();

    /**
     *无参构造函数
     */
    public PtUserInfo(){
        super();
    }

}