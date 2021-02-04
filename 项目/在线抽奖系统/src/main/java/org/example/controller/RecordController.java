package org.example.controller;

import org.example.model.User;
import org.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    //抽奖：某个奖项下抽奖，一次抽多个人（插入多条抽奖记录）
    @PostMapping("/add/{awardId}")
    public Object add(@PathVariable Integer awardId, @RequestBody List<Integer> memberIds) {
        int res = recordService.add(awardId,memberIds);
        return null;
    }

    @GetMapping("/delete/member")
    public Object deleteByMemberId(Integer memberId) {
        int res = recordService.deleteByMemberId(memberId);
        return null;
    }

    @GetMapping("/delete/award")
    public Object deleteByAwardId(Integer awardId) {
        int res = recordService.deleteByAwardId(awardId);
        return null;
    }

    @GetMapping("/delete/setting")
    public Object deleteBySetting(HttpSession session) {
        User user = (User) session.getAttribute("user");
        //获取userid--->关联settingid--->关联memberid以及awardid
        //awardid，memberid删除关联的record
        int n = recordService.deleteByUserId(user.getId());
        return null;
    }
}
