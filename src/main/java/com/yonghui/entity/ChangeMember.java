package com.yonghui.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-29-16:42
 * @Home: http://aragron.com
 */
@Data
public class ChangeMember implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     *
     */
    private Integer iid;

    /**
     * 会员卡号
     */
    private String memberId;

    /**
     * 总积分
     */
    private BigDecimal integral;

    /**
     * 消息推送状态-广告竞价系统使用(default 'N')
     */
    private String statusFlag;

    /**
     * 是否同步积分给微信 Y/N/SENDING (default 'N')
     */
    private String wechatBonusFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 消费时间，有可能消费两次，取最后一次进行同步
     */
    private Date lastChangeTime;

    public ChangeMember(Integer iid, String memberId, BigDecimal integral, String statusFlag, String wechatBonusFlag, Date createTime, Date updateTime, Date lastChangeTime) {
        this.iid = iid;
        this.memberId = memberId;
        this.integral = integral;
        this.statusFlag = statusFlag;
        this.wechatBonusFlag = wechatBonusFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastChangeTime = lastChangeTime;
    }

    public ChangeMember() {
    }
}
