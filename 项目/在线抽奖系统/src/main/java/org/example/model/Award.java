package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 奖项
 */
@Getter
@Setter
@ToString
public class Award {
    
    private Integer id;

    /**
     * 奖项名称
     */
    private String name;

    /**
     * 奖项人数
     */
    private Integer count;

    /**
     * 奖品
     */
    private String award;

    /**
     * 抽奖设置id
     */
    private Integer settingId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 中奖人员的id集合
     */
    private List<Integer> luckyMemberIds;
}