package com.cyzy.system.service.impl;

import com.cyzy.system.service.IToolService;
import com.cyzy.system.thread.RemoveCaptcha;
import com.cyzy.system.utils.CodePicture;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class ToolServiceImpl implements IToolService {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Object[] generateCaptchaImage(int width, int height) {
        //生成图片
        Object[] imageInfo = CodePicture.createImage(width, height);

        //获取验证码字符串，存入redis
        String imgStr = (String) imageInfo[0];
        String token = String.valueOf(UUID.randomUUID());
        BoundHashOperations<String, Object, Object> captchaMap = redisTemplate.boundHashOps("captcha");
        captchaMap.put(token,imgStr);

        //设置定时任务使指定验证码失效
        RemoveCaptcha removeCaptcha = new RemoveCaptcha(redisTemplate, token);
        Thread thread = new Thread(removeCaptcha);
        thread.start();

        imageInfo[0] = token;

        return imageInfo;
    }
}
