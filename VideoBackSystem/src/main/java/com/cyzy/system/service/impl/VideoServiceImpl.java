package com.cyzy.system.service.impl;

import com.cyzy.system.entity.bo.VideoBo;
import com.cyzy.system.entity.po.VideoPo;
import com.cyzy.system.mapper.VideoMapper;
import com.cyzy.system.service.IVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoServiceImpl implements IVideoService {
    @Resource
    VideoMapper videoMapper;

    @Override
    public VideoBo getVideoFull(String videoId) {
        VideoPo videoPo = videoMapper.selectOneFull(videoId);
        if (videoPo == null) return null;
        return new VideoBo(videoPo);
    }
}
