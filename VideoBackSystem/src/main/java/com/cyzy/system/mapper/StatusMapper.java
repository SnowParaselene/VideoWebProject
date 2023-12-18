package com.cyzy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyzy.system.entity.po.StatusPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatusMapper extends BaseMapper<StatusPo> {
}
