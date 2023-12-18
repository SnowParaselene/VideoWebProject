package com.cyzy.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cyzy.system.entity.po.AdminPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<AdminPo> {
    String sqlStr = "SELECT a.*,c.character_label,c.character_value,s.status_label,s.status_value " +
            "FROM t_admin a " +
            "LEFT JOIN t_character c " +
            "ON a.admin_character=c.t_character_id " +
            "LEFT JOIN t_account_status s " +
            "ON a.admin_status=s.t_account_status_id";
    String wrapperStr = "SELECT * FROM ( " + sqlStr +" ) as tempQueryTable ${ew.customSqlSegment}";

    @ResultMap("com.cyzy.system.mapper.AdminMapper.adminFull")
    @Select(wrapperStr)
    AdminPo selectFullOne(@Param(Constants.WRAPPER) Wrapper<AdminPo> wrapper);
}
