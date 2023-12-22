package com.cyzy.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoVo {
    /**
     * 封面，视频封面静态资源路径
     */
    private String cover;

    /**
     * 创建时间，稿件创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 简介，稿件简介
     */
    private String describe;

    /**
     * 播放量，稿件的播放次数
     */
    private String playTimes;

    /**
     * 稿件类型，分类
     */
    private String sort;

    /**
     * 稿件状态，稿件当前状态，属于上线还是下线
     */
    private Boolean status;

    /**
     * 子标题，稿件副标题
     */
    private String subTitle;

    /**
     * 时长，视频时长
     */
    private String time;

    /**
     * 标题，稿件主标题
     */
    private String title;

    /**
     * 修改时间，稿件更改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 稿件ID，稿件唯一标识符
     */
    private String vid;

    /**
     * 视频，视频内容静态资源路径
     */
    private String video;

    /**
     * 是否vip专享，是否为VIP专享视频
     */
    private Boolean vipOnly;

}
