package org.example.service;

import org.example.mapper.MemberMapper;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> queryBySettingId(Integer id) {
        return memberMapper.selectBySettingId(id);
    }
}
