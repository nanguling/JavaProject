package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.base.BaseMapper;
import org.example.model.Record;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    int batchInsert(@Param("awardId") Integer awardId, @Param("memberIds") List<Integer> memberIds);

    int deleteByMemberId(Integer memberId);

    int deleteByAwardId(Integer awardId);

    int deleteByUserId(Integer id);
}