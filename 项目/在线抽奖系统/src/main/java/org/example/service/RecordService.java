package org.example.service;

import org.example.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordMapper recordMapper;

    public int add(Integer awardId, List<Integer> memberIds) {
        return recordMapper.batchInsert(awardId,memberIds);
    }

    public int deleteByMemberId(Integer memberId) {
        return recordMapper.deleteByMemberId(memberId);
    }

    public int deleteByAwardId(Integer awardId) {
        return recordMapper.deleteByAwardId(awardId);
    }

    public int deleteByUserId(Integer id) {
        return recordMapper.deleteByUserId(id);
    }
}
