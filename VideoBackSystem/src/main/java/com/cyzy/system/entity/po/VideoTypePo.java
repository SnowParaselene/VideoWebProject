package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 视频类型表
 */
@Data
@TableName(value = "t_video_type",autoResultMap = true)
public class VideoTypePo implements Serializable {
    @TableId
    private Integer tVideoTypeId;
    //private Date createTime;
    //private Date updateTime;

    private String videoTypeLabel;
    private String videoTypeValue;
    private Integer parentTypeId;
    @TableField(exist = false)
    private VideoTypePo parentType;
}
