package com.yx.base.spring.boot.mybatis.plus.ne2.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 17:25
 */
@ApiModel("返回数据")
@Data
public class UserBasicResp {

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
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新日期
     */
    private Date modifyTime;
    /**
     * 更新人
     */
    private String updateUser;
}
