package com.cyzy.system.thread;

import org.springframework.data.redis.core.RedisTemplate;

public class RemoveCaptcha implements Runnable{
    private final RedisTemplate<String,Object> redisTemplate;
    private final String token ;

    public RemoveCaptcha(RedisTemplate<String,Object> redisTemplate, String token) {
        this.redisTemplate = redisTemplate;
        this.token = token;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            redisTemplate.opsForHash().delete("captcha",token);
            System.out.println("删除结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
