package org.example.service;

import org.example.mapper.AwardMapper;
import org.example.model.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService {

    @Autowired
    private AwardMapper awardMapper;

    public List<Award> queryBySettingId(Integer id) {
        return awardMapper.selectBySettingId(id);
    }
}
