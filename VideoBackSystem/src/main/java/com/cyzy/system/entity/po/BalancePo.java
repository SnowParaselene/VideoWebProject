package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户余额
 */
@Data
public class BalancePo {
    @TableId
    private Integer tUserBalanceId;
    private Date createTime;
    private Date updateTime;
    private String userId;
    private Double remainingSum;
}
