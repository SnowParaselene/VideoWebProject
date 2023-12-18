package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 账号状态表
 */
@Data
@TableName(value = "t_account_status", autoResultMap = true)
public class StatusPo implements Serializable {
    public static final String STATUS_USING = "Using";

    public static final String STATUS_LOCKING = "Locking";

    @TableId
    private Integer tAccountStatusId;
    //private Date createTime;
    //private Date updateTime;
    private String statusLabel;
    private String statusValue;

}
