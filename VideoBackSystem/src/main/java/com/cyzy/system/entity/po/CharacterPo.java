package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 管理员角色表
 */
@Data
@TableName(value = "t_character", autoResultMap = true)
public class CharacterPo implements Serializable {
    @TableId
    private Integer tCharacterId;
    //private Date createTime;
    //private Date updateTime;
    private String characterLabel;
    private String characterValue;

    @TableField(exist = false)
    private ArrayList<AuthorityPo> authorities;

}
