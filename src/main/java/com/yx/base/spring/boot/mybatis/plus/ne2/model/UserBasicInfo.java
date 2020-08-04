package com.yx.base.spring.boot.mybatis.plus.ne2.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户基本信息(UserBasicInfo)实体类
 * @author yx
 * @since 2020-08-04 16:50:41
 */
@Data
@TableName(value="user_basic_info",schema = "sagittarius")
@ApiModel(value = "用户对象", description = "用户对象")
public class UserBasicInfo implements Serializable {
    private static final long serialVersionUID = -85407084891451352L;
    /**
    * id
    */
    private Long id;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 用户账号
    */
    private String userAccount;
    /**
    * 用户姓名
    */
    private String userName;
    /**
    * 用户信息快照
    */
    private String userDetailSnapshot;
    /**
    * 来电/去电号码
    */
    private String inOrOutNumber;
    /**
    * 工单编号
    */
    private Long orderNumber;
    /**
    * 手机号码1
    */
    private String phoneNumber1;
    /**
    * 手机号码2
    */
    private String phoneNumber2;
    /**
    * 用户身份 1:未知; 2:个人或家庭; 3:企业采购; 4:电商平台; 5:微商;  6:贸易商;  7:加工厂; 8:餐饮/食堂/酒楼;  9:门店/超市;  10:批发商; 11:代办; 12:种养户
    */
    private Integer userIdentity;
    /**
    * 微信/QQ
    */
    private String wechatOrQq;
    /**
    * 紧急程度 1:特急; 2:紧急; 3:一般
    */
    private Integer urgency;
    /**
    * 用户信息备注
    */
    private String userInfoRemark;
    /**
    * 创建日期
    */
    private LocalDateTime createTime;
    /**
    * 创建人
    */
    private String createUser;
    /**
    * 更新日期
    */
    private LocalDateTime modifyTime;
    /**
    * 更新人
    */
    private String updateUser;

}