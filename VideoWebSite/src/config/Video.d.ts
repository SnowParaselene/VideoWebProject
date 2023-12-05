/**
 * 视频
 */
export type Video = {
    /**
     * 封面，视频封面静态资源路径
     */
    cover: string;
    /**
     * 创建时间，稿件创建时间
     */
    createTime?: string;
    /**
     * 简介，稿件简介
     */
    describe?: string;
    /**
     * 播放量，稿件的播放次数
     */
    playTimes: string;
    /**
     * 子标题，稿件副标题
     */
    subTitle: string;
    /**
     * 时长，视频时长
     */
    time: string;
    /**
     * 标题，稿件主标题
     */
    title: string;
    /**
     * 修改时间，稿件更改时间
     */
    updateTime?: string;
    /**
     * 稿件ID，稿件唯一标识符
     */
    vid: string;
    /**
     * 视频，视频内容静态资源路径
     */
    video?: string;
    /**
     * 是否vip专享，是否为VIP专享视频
     */
    vipOnly: boolean;
}

