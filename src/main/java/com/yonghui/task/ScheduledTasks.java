package com.yonghui.task;

import com.yonghui.service.ChangeMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-29-15:41
 * @Home: http://aragron.com
 */
@Component
@Slf4j
public class ScheduledTasks {
    @Autowired
    private ChangeMemberService changeMemberService;

    @Scheduled(fixedRate = 1000 * 60 * 5)
    //@Scheduled(cron = "0 55 4 * * ?")
    //@Transactional(value = "tm", propagation = Propagation.REQUIRED)
    public void scheduledTask() {
        try {
            changeMemberService.changeMemberTask();
        } catch (Exception e) {
            log.error("会员定时任务出错", e);
        }
    }

}
