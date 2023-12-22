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

        return new VideoVo(videoPo.getVideoCover(),
                videoPo.getCreateTime(),
                videoPo.getVideoIntroduction(),
                videoPo.getVideoPlayCount(),
                videoTypeLabel,
                videoPo.getVideoStatus(),
                videoPo.getVideoSubtitle(),
                videoPo.getVideoDuration(),
                videoPo.getVideoTitle(),
                videoPo.getUpdateTime(),
                videoPo.getVideoId(),
                videoPo.getVideoPath(),
                videoPo.getVideoVipOnly());
    }

}
