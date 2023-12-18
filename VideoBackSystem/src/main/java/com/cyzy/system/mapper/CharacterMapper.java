package com.cyzy.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cyzy.system.entity.po.CharacterPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CharacterMapper extends BaseMapper<CharacterPo> {
    @ResultMap("com.cyzy.system.mapper.CharacterMapper.characterFull")
    @Select("SELECT * FROM t_character c\n" +
            "LEFT JOIN t_character_authority ca\n" +
            "ON c.t_character_id=ca.character_id\n" +
            "LEFT JOIN t_authority a\n" +
            "ON ca.authority_id=a.t_authority_id\n" +
            "${ew.customSqlSegment}")
    CharacterPo selectAuthorities(@Param(Constants.WRAPPER) Wrapper wrapper);
}
