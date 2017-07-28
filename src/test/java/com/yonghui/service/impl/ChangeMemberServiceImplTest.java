package com.yonghui.service.impl;

import com.yonghui.Application;
import com.yonghui.service.ChangeMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-07-01-23:31
 * @Home: http://aragron.com
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = Application.class)
public class ChangeMemberServiceImplTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ChangeMemberService changeMemberService;

    @Test
    //@Transactional(value = "tm", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changeMemberTask1() throws Exception {
        long start = System.currentTimeMillis();
        changeMemberService.changeMemberTask();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间: " + (end - start) / 1000);
    }

}