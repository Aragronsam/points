package com.yonghui.mapper;

import com.yonghui.Application;
import com.yonghui.entity.ChangeMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-29-17:47
 * @Home: http://aragron.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ChangeMemberMapperTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ChangeMemberMapper changeMemberMapper;

    @Test
    //@Rollback
    public void test_init() throws Exception {
        //新增记录
        changeMemberMapper.findById(1);
        changeMemberMapper.insert(new ChangeMember( 2, "2", new BigDecimal(2), "2", "2", new Date(), new Date(), new Date()));
        ChangeMember changeMember = changeMemberMapper.findByMemberId("2");
        Assert.assertEquals(2, changeMember.getIid().longValue());

    }

    @Test
    public void test_findByMemberId() throws Exception {
        ChangeMember changeMember = changeMemberMapper.findByMemberId("1-110567");
        System.out.println(changeMember);
    }
}