package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表
 */
@Data
@TableName(value = "t_vip",autoResultMap = true)
public class VipPo implements Serializable {
    @TableId
    private Integer tVipId;
    //private Date updateTime;
    //private Date createTime;
    private String vipLabel;
    private String vipValue;
    @TableField(exist = false)
    private Date expirationTime;
}
