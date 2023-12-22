package com.cyzy.system.service.impl;

import com.cyzy.system.entity.po.VideoPo;
import com.cyzy.system.mapper.VideoMapper;
import com.cyzy.system.service.IToolService;
import com.cyzy.system.thread.RemoveCaptcha;
import com.cyzy.system.utils.CodePicture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class ToolServiceImpl implements IToolService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    VideoMapper videoMapper;

    @Value("${video.resourcePath:\\}")
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
    public void sentVideoFile(HttpServletRequest request, HttpServletResponse response, String vid) {
            //File file = new File(resourcePath+path);
            ////String absolutePath = file.getAbsolutePath();
            //
            ////校验文件是否存在
            //if (!file.exists() || file.isDirectory()) return;
            ////获取文件
            //FileInputStream fis=new FileInputStream(file);
            //BufferedInputStream bis=new BufferedInputStream(fis);
            //
            //BufferedOutputStream bos=new BufferedOutputStream(fos);
            //
            //int size;
            //byte[] buffer=new byte[10240];
            //while((size=bis.read(buffer))!=-1){
            //    bos.write(buffer, 0, size);
            //}
            ////刷新此缓冲的输出流，保证数据全部都能写出
            //bos.flush();
            //bis.close();
            //bos.close();

        VideoPo videoPo = videoMapper.selectOneFull(vid);

        response.reset();
        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");

        try {
            //获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            File file = new File(resourcePath+videoPo.getVideoPath());
            if(file.exists()){
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();
                //播放
                if(rangeString != null){

                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    //设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    //设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    //设置此次相应返回的数据范围
                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
                    //返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    //设定文件读取开始位置（以字节为单位）
                    targetFile.seek(range);
                }else {//下载

                    //设置响应头，把文件名字设置好
                    response.setHeader("Content-Disposition", "attachment; filename="+file.getName() );
                    //设置文件长度
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    //解决编码问题
                    response.setHeader("Content-Type","application/octet-stream");
                }


                byte[] cache = new byte[1024 * 300];
                int flag;
                while ((flag = targetFile.read(cache))!=-1){
                    outputStream.write(cache, 0, flag);
                }
            }else {
                String message = "file:"+file.getName()+" not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }

            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }


    }
}
