package com.cyzy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyzy.system.entity.bo.AdminBo;
import com.cyzy.system.entity.bo.CharacterBo;
import com.cyzy.system.entity.po.AdminPo;
import com.cyzy.system.entity.po.StatusPo;
import com.cyzy.system.entity.po.CharacterPo;
import com.cyzy.system.mapper.AdminMapper;
import com.cyzy.system.mapper.CharacterMapper;
import com.cyzy.system.service.IAdminService;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    AdminMapper adminMapper;

    @Resource
    CharacterMapper characterMapper;

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 管理员登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public AdminBo login(String account, String password) {
        //查看redis是否缓存有该账户数据
        BoundHashOperations adminPoMap = redisTemplate.boundHashOps("adminPo");
        //Object adminPo1 = redisTemplate.opsForHash().get("adminPo", account);
        Object adminPo1 = adminPoMap.get(account);
        //没有缓存
        if (adminPo1 == null) {
            //无缓存
            //数据库查询数据
            //根据账号搜索
            LambdaQueryWrapper<AdminPo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AdminPo::getAdminAccount, account);
            AdminPo adminPo = adminMapper.selectFullOne(queryWrapper);

            //不存在用户
            if (adminPo == null) {
                adminPoMap.put(account, "null");
                return null;
            }
            //更新缓存
            adminPoMap.put(account,adminPo);
            //检验密码
            if (!adminPo.getAdminPassword().equals(password)) return null;
            //判断账号是否被锁定
            if (StatusPo.STATUS_LOCKING.equals(adminPo.getAdminStatus().getStatusValue())) return null;
            return new AdminBo(adminPo);
        }
        //有缓存，但值为“Null”
        if (adminPo1 instanceof String) return null;
        //有缓存
        AdminPo adminPo = (AdminPo) adminPo1;
        //检验账号密码
        if (!adminPo.getAdminPassword().equals(password)) return null;
        //判断账号是否被锁定
        if (StatusPo.STATUS_LOCKING.equals(adminPo.getAdminStatus().getStatusValue())) return null;
        return new AdminBo(adminPo);

    }

    @Override
    public CharacterBo getMenuMap(Integer characterId) {
        LambdaQueryWrapper<CharacterPo> qw = new LambdaQueryWrapper<>();
        qw.eq(CharacterPo::getTCharacterId, characterId);
        CharacterPo characterPo = characterMapper.selectAuthorities(qw);
        CharacterBo characterBo = new CharacterBo(characterPo);

        return characterBo;
    }
}
