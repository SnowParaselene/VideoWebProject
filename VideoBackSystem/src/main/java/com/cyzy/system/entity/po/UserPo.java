package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cyzy.system.utils.LabelAndType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
@TableName(value = "t_user", autoResultMap = true)
public class UserPo implements Serializable {

    @TableId
    private Integer tUserId;
    @LabelAndType(label = "用户ID", type = "unchanged")
    private String userId;
    @LabelAndType(label = "创建时间", type = "unchanged")
    private Date createTime;
    private Date updateTime;
    @LabelAndType(label = "用户名称", type = "String")
    private String userName;
    @LabelAndType(label = "用户账号", type = "unchanged")
    private String userAccount;
    private String userPassword;
    @TableField(value = "user_status")
    @LabelAndType(label = "账号状态", type = "select")
    private Integer statusId;

    @TableField(exist = false)
    private StatusPo userStatus;
    @TableField(value = "user_head_img")
    @LabelAndType(label = "用户头像", type = "uploader")
    private String userHeadImg;

}
