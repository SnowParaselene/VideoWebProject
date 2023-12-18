package com.cyzy.system.service.impl;

import com.cyzy.system.entity.bo.UserBo;
import com.cyzy.system.service.IVipService;
import org.springframework.stereotype.Service;

@Service
public class VipServiceImpl implements IVipService {
    @Override
    public UserBo getUserVipInfo(UserBo user) {
        return user;
    }
}
