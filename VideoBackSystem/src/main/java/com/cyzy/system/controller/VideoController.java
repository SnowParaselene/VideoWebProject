package com.cyzy.system.controller;

import com.cyzy.system.entity.bo.VideoBo;
import com.cyzy.system.entity.vo.VideoVo;
import com.cyzy.system.service.IVideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    IVideoService videoService;

    @GetMapping("/{videoId}")
    public VideoVo getVideoFull(@PathVariable String videoId){
        VideoBo videoFull = videoService.getVideoFull(videoId);
        return videoFull.getVideoVo();
    }
}
