package com.cyzy.system.controller;

import com.cyzy.system.service.IToolService;
import com.cyzy.system.utils.MyTools;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/tool")
public class ToolController {
    @Resource
    MyTools myTools;

    @Resource
    IToolService toolService;

    @GetMapping("/captcha")
    public void getCaptchaImage(@RequestParam(name = "width") int width,
                                @RequestParam(name = "height") int height,
                                HttpServletResponse response){
        Object[] objects = toolService.generateCaptchaImage(width,height);
        response.addCookie(new Cookie("token", (String) objects[0]));
        try {
            ImageIO.write((BufferedImage)objects[1],"png",response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/video")
    public void getVideoFile(HttpServletRequest request, HttpServletResponse response, String videoPath){
        boolean authToken = myTools.authToken(request);
        if (!authToken) {
            response.setStatus(400);
            return;
        }

        //视频资源存储信息
        response.reset();

        try {
            toolService.getVideoFile(videoPath,response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(400);
        }
    }


}
