package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表对象
 */
@Data
@TableName(value = "t_authority", autoResultMap = true)
public class AuthorityPo implements Serializable {
    @TableId
    private Integer tAuthorityId;
    //private Date createTime;
    //private Date updateTime;

    private String authorityLabel;
    private String authorityValue;

}
