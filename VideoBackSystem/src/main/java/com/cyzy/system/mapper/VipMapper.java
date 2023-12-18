package com.cyzy.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cyzy.system.entity.po.VipPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VipMapper extends BaseMapper<VipPo> {
    @Select("SELECT * FROM t_user_vip uv\n" +
            "LEFT JOIN t_vip v\n" +
            "ON uv.vip_id = v.t_vip_id\n" +
            "${ew.customSqlSegment}")
    @ResultMap("com.cyzy.system.mapper.VipMapper.userVip")
    VipPo getUserVip(@Param(Constants.WRAPPER) Wrapper<VipPo> wrapper);
}
