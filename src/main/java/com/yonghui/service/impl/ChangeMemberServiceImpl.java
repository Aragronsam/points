package com.yonghui.service.impl;

import com.alibaba.fastjson.JSON;
import com.yonghui.entity.ChangeMember;
import com.yonghui.mapper.ChangeMemberMapper;
import com.yonghui.service.ChangeMemberService;
import com.yonghui.utils.Constants;
import com.yonghui.utils.ParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-30-17:18
 * @Home: http://aragron.com
 */
@Service
@Slf4j
@Transactional(value = "tm", propagation = Propagation.REQUIRED)
public class ChangeMemberServiceImpl implements ChangeMemberService{
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ChangeMemberMapper changeMemberMapper;

    /**
     * 会员定时任务
     */
    @Override
    public void changeMemberTask() {
        try {
            HashMap<String, Object> result = (HashMap<String, Object>) ParserUtil.parser(Constants.SOURCE_XML_FILEPATH);
            if (result == null) {
                log.info("changeMemberTask中result为空");
                return;
            }
            List<ChangeMember> changeMembers = (List<ChangeMember>) result.get("changeMembers");
            List<ChangeMember> changeMembers_error = (List<ChangeMember>) result.get("changeMembers_error");
            File currentHandleXml = (File) result.get("currentHandleXml");
            if (changeMembers_error != null &&
                    !changeMembers_error.isEmpty() &&
                    currentHandleXml != null) {
                ParserUtil.moveFile(Constants.SOURCE_XML_FILEPATH + "/" + currentHandleXml.getName(),
                        Constants.ERROR_HANDLE_DIR,
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "_" + currentHandleXml.getName());
                log.info("xml数据不完整");
                throw new RuntimeException("xml数据不完整");
            }
            if (changeMembers == null || changeMembers.isEmpty()) {
                log.info("changeMemberTask中changeMembers为空");
                return;
            }
            log.info("xml数据changeMembers为: {}" , JSON.toJSONString(changeMembers));
            /*List<ChangeMember> changeMembers = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                changeMembers.add(new ChangeMember( i, i + "", new BigDecimal(i), i + "", 1 + "", new Date(), new Date(), new Date()));
            }*/
            List<List<ChangeMember>> groupList = ParserUtil.groupListByQuantity(changeMembers, Constants.CRITICAL);
            List<ChangeMember> itemList;
            for (int i = 0; i < groupList.size(); i++) {
                itemList = groupList.get(i);
                if (itemList != null && !itemList.isEmpty())
                    changeMemberMapper.insertBatch(itemList);
            }
            ParserUtil.moveFile(Constants.SOURCE_XML_FILEPATH + "/" + currentHandleXml.getName(),
                    Constants.SUCCESS_HANDLE_DIR,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "_" + currentHandleXml.getName());
            log.info("会员定时任务changeMemberTask执行成功");
        } catch (Exception e) {
            log.error("会员定时任务changeMemberTask出错", e);
            throw new RuntimeException("会员定时任务changeMemberTask出错", e);
        }
    }
}
