package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cyzy.system.utils.LabelAndType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员对象
 */
@Data
@TableName(value = "t_admin", autoResultMap = true)
public class AdminPo implements Serializable {
    @TableId
    private Integer tAdminId;

    @TableField
    @LabelAndType(label = "创建时间", type = "unchanged")
    private Date createTime;

    @TableField
    //@LabelAndType(label = "最后修改时间", type = "unchanged")
    private Date updateTime;

    @TableField
    @LabelAndType(label = "管理员ID", type = "unchanged")
    private String adminId;

    @TableField
    @LabelAndType(label = "管理员名称", type = "String")
    private String adminName;

    @TableField
    @LabelAndType(label = "管理员账号", type = "unchanged")
    private String adminAccount;

    @TableField
    private String adminPassword;

    @TableField(value = "admin_character")
    @LabelAndType(label = "账号角色", type = "select")
    private Integer characterId;

    @TableField(exist = false)
    private CharacterPo adminCharacter;

    @TableField(value = "admin_status")
    @LabelAndType(label = "账号状态", type = "select")
    private Integer statusId;

    @TableField(exist = false)
    private StatusPo adminStatus;

    @TableField(value = "admin_head_img")
    @LabelAndType(label = "管理员头像", type = "uploader")
    private String adminHeadImg;


}
