package org.example.controller;

import org.example.model.Award;
import org.example.model.Member;
import org.example.model.Setting;
import org.example.model.User;
import org.example.service.AwardService;
import org.example.service.MemberService;
import org.example.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private MemberService memberService;

    /**
     * 进入抽奖设置页面，初始化的接口，返回页面所有需要的数据
     * setting对象中的属性：batchNumber
     * setting对象目前没有的属性：
     *  1.user（用户信息）
     *  2.award（奖项列表：根据setting_id查询）
     *  3.member（抽奖人员列表，根据setting_id查询）
     */
    @GetMapping("/query")
    public Object query(HttpSession session) {//已经登录，所以可以直接使用HttpSession
        //获取session中的user
        User user = (User) session.getAttribute("user");
        //通过user_id查询关联的setting信息
        Setting setting = settingService.queryByUserId(user.getId());
        //把user设置到setting的新增属性user中
        setting.setUser(user);
        //根据setting_id查询award列表，设置到setting新增属性awards中
        List<Award> awards = awardService.queryBySettingId(setting.getId());
        setting.setAwards(awards);
        //根据setting_id查询member列表，设置到setting新增属性members中
        List<Member> members = memberService.queryBySettingId(setting.getId());
        setting.setMembers(members);
        return setting;
    }
}
