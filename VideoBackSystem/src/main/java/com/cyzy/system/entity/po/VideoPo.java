package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频信息表
 */
@Data
@TableName(value = "t_video",autoResultMap = true)
public class VideoPo implements Serializable {
    @TableId
    private Integer tVideoId;
    private String videoId;
    private Date createTime;
    private Date updateTime;
    private String videoTitle;
    private String videoSubtitle;
    private Boolean videoStatus;
    private String videoPath;
    private Long videoDuration;
    private String videoCover;
    private Integer videoPlayCount;
    private Boolean videoVipOnly;
    private String videoIntroduction;
    @TableField(exist = false)
    private VideoTypePo videoType;

}
