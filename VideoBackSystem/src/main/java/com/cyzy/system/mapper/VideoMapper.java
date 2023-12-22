package com.cyzy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyzy.system.entity.po.VideoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VideoMapper extends BaseMapper<VideoPo> {
    @ResultMap(value = "com.cyzy.system.mapper.VideoMapper.videoFull")
    @Select("SELECT * FROM t_video v\n" +
            "LEFT JOIN t_video_type_relation vt\n" +
            "ON v.video_id=vt.video_id\n" +
            "LEFT JOIN t_video_type t\n" +
            "ON vt.video_type_id=t.t_video_type_id\n" +
            "WHERE v.video_id=#{videoId}")
    VideoPo selectOneFull(String videoId);
}
