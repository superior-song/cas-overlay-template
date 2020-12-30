package com.suncreate.common.base;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/8/13 0013.
 */
@Data
public class AppMessage {
/**消息内容**/
 private String content;
 /**生效时间**/
 private String effectiveDate;
 /**失效时间**/
 private String failureDate;
 /**用户组信息**/
 private List groupList;
 /**用户id**/
 private String id;
 /**用户名**/
 private String name;
 /**用户组信息序列化字符串**/
 private String groupListString;
 /**座席APP消息id**/
 private  String noticeId;
 /**消息类型**/
 private String noticeType;
 /**消息级别**/
 private String noticeLevel;
	/**消息标题**/
 private String title;
	/**消息发布者**/
 private String auditUserName;
 /**区别座席APP和平台**/
 private String idNotice;//
	/**消息发送类型**/
 private String sendSysType;//
}
