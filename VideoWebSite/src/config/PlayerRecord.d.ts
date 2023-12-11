/**
 * PlayerRecord
 */
export type PlayerRecord = {
    /**
     * 观看时间，在什么时候观看了该视频
     */
    playTime: string;
    /**
     * 视频ID，观看视频的ID
     */
    vid: string;
    /**
     * 视频名称，观看的时长名称
     */
    videoName: string;
    /**
     * 观看时长，在该视频观看了多长时间
     */
    viewingDuration: string;
}