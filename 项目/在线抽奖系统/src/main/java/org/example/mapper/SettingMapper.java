package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.base.BaseMapper;
import org.example.model.Setting;

@Mapper
public interface SettingMapper extends BaseMapper<Setting> {
    Setting selectByUserId(Integer id);

    int updateByUserId(@Param("batchNumber") Integer batchNumber, @Param("userId") Integer userId);

    Integer selectIdByUserId(Integer id);
}