package com.cyzy.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_video_type_relation")
public class VideoTypeRelationPo implements Serializable {
    @TableId
    private Integer tVideoTypeRelationId;

    private String videoId;
    private Integer videoTypeId;

}
