package com.cyzy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyzy.system.entity.bo.UserBo;
import com.cyzy.system.entity.po.StatusPo;
import com.cyzy.system.entity.po.UserPo;
import com.cyzy.system.mapper.UserMapper;
import com.cyzy.system.service.IUserService;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    RedisTemplate redisTemplate;

    @Resource
    UserMapper userMapper;

    @Override
    public UserBo login(String account, String password) {
        //查看redis是否缓存有用户信息
        BoundHashOperations userPoMap = redisTemplate.boundHashOps("userPo");
        Object userPo1 = userPoMap.get(account);
        //无缓存
        if (userPo1 == null) {
            //从数据库查询
            LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserPo::getUserAccount, account);
            UserPo userPo = userMapper.selectOne(queryWrapper);
            //判断是否存在用户
            if (userPo == null) {
                //写入到redis
                userPoMap.put(account, "null");
                return null;
            }
            //更新缓存
            userPoMap.put(account, userPo);
            //校验密码
            if (!password.equals(userPo.getUserPassword())) return null;
            //判断是否锁定
            if (StatusPo.STATUS_LOCKING.equals(userPo.getUserStatus().getStatusValue())) return null;
            return new UserBo(userPo);
        }
        //有缓存
        //缓存为“null”
        if (userPo1 instanceof String) return null;
        //缓存存在用户数据
        UserPo userPo = (UserPo) userPo1;
        //校验密码
        if (!password.equals(userPo.getUserPassword())) return null;
        return new UserBo(userPo);
    }
}
