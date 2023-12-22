package com.cyzy.system.service.impl;

import com.cyzy.system.service.IToolService;
import com.cyzy.system.thread.RemoveCaptcha;
import com.cyzy.system.utils.CodePicture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.UUID;

@Service
public class ToolServiceImpl implements IToolService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Value("${video.resourcePath:`\\`}")
    String resourcePath;

    @Override
    public Object[] generateCaptchaImage(int width, int height) {
        //生成图片
        Object[] imageInfo = CodePicture.createImage(width, height);

        //获取验证码字符串，存入redis
        String imgStr = (String) imageInfo[0];
        String token = String.valueOf(UUID.randomUUID());
        BoundHashOperations<String, Object, Object> captchaMap = redisTemplate.boundHashOps("captcha");
        captchaMap.put(token, imgStr);

        //设置定时任务使指定验证码失效
        RemoveCaptcha removeCaptcha = new RemoveCaptcha(redisTemplate, token);
        Thread thread = new Thread(removeCaptcha);
        thread.start();

        imageInfo[0] = token;

        return imageInfo;
    }

    @Override
    public void getVideoFile(String path,OutputStream fos) throws IOException {
            File file = new File(resourcePath+path);
            //String absolutePath = file.getAbsolutePath();

            //校验文件是否存在
            if (!file.exists() || file.isDirectory()) return;
            //获取文件
            FileInputStream fis=new FileInputStream(file);
            BufferedInputStream bis=new BufferedInputStream(fis);

            BufferedOutputStream bos=new BufferedOutputStream(fos);

            int size;
            byte[] buffer=new byte[10240];
            while((size=bis.read(buffer))!=-1){
                bos.write(buffer, 0, size);
            }
            //刷新此缓冲的输出流，保证数据全部都能写出
            bos.flush();
            bis.close();
            bos.close();


    }
}
