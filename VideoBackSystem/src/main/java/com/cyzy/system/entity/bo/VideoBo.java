package com.cyzy.system.entity.bo;

import com.cyzy.system.entity.po.VideoPo;
import com.cyzy.system.entity.vo.VideoVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class VideoBo {
    private VideoPo videoPo;

    public VideoVo getVideoVo() {
        String videoTypeLabel = videoPo.getVideoType().getVideoTypeLabel();
        //if (videoPo.getVideoType().getParentTypeId() != 0) {
        //    videoTypeLabel = videoPo.getVideoType().getParentType().getVideoTypeLabel() + "/" + videoTypeLabel;
        //}

        String playTimes;
        Integer videoPlayCount = videoPo.getVideoPlayCount();
        if (videoPlayCount >= 10000) {
            playTimes = videoPlayCount / 10000 + "万";
        } else {
            playTimes = String.valueOf(videoPlayCount);
        }

        String duration = null;
        Long videoDuration = videoPo.getVideoDuration();
        if (videoDuration > 60) {
            String min = videoDuration / 60 > 9 ? String.valueOf(videoDuration / 60) : "0" + (videoDuration / 60);
            String seconds = String.valueOf(videoDuration % 60);
            duration = min + ":" + seconds;
        }

        return new VideoVo(videoPo.getVideoCover(),
                videoPo.getCreateTime(),
                videoPo.getVideoIntroduction(),
                playTimes,
                videoTypeLabel,
                videoPo.getVideoStatus(),
                videoPo.getVideoSubtitle(),
                duration,
                videoPo.getVideoTitle(),
                videoPo.getUpdateTime(),
                videoPo.getVideoId(),
                videoPo.getVideoPath(),
                videoPo.getVideoVipOnly());
    }

}
