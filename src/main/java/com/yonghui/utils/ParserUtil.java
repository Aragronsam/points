package com.yonghui.utils;

import com.alibaba.fastjson.JSON;
import com.yonghui.entity.ChangeMember;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-30-06:25
 * @Home: http://aragron.com
 */
@Slf4j
public class ParserUtil {

    /**
     * 功能描述: jdom解析xml
     *
     * @param filePath xml源文件路径
     * @return
     */
    public static Map<String, Object> parser(String filePath) {
        List<ChangeMember> changeMembers = new ArrayList<>();
        List<ChangeMember> changeMembers_error = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        try {
            Assert.notNull(filePath);

            SAXBuilder saxBuilder = new SAXBuilder();
            /*InputStream resourceAsStream = ParserUtil.class.getClassLoader().getResourceAsStream(fileName);*/
            ArrayList<File> xmlFiles = (ArrayList<File>) ParserUtil.getXMLs(new File(filePath));
            if (xmlFiles == null || xmlFiles.isEmpty()) {
                log.info("源文件目录无xml文件");
                return null;
            }
            log.info("xmlFiles是: {}" + JSON.toJSONString(xmlFiles));
            File file = xmlFiles.get(0);
            if (file == null) {
                log.info("xml源文件为空");
                return null;
            }
            Document document = saxBuilder.build(file);
            if (document == null) {
                log.info("document为空");
                return null;
            }
            //获取跟元素
            Element root = document.getRootElement();
            List<Element> elements = root.getChildren("credit");
            if (elements.isEmpty()) {
                log.info("elements为空");
                return null;
            }
            for (Element element : elements) {
                ChangeMember changeMember = new ChangeMember();

                changeMember.setMemberId(element.getChildText("memberNo"));
                changeMember.setIntegral(new BigDecimal(element.getChildText("available")));
                changeMember.setLastChangeTime(new Date(Long.valueOf(element.getChildText("timestamp"))));
                if (StringUtils.isBlank(element.getChildText("memberNo")) ||
                        StringUtils.isBlank(element.getChildText("available")) ||
                        StringUtils.isBlank(element.getChildText("timestamp"))) {
                    changeMembers_error.add(changeMember);
                    continue;
                }

                changeMember.setIid(1);
                changeMember.setCreateTime(new Date());
                changeMember.setUpdateTime(new Date());
                changeMember.setStatusFlag("N");
                changeMember.setWechatBonusFlag("N");

                changeMembers.add(changeMember);
            }

            log.info("解析xml返回结果为, changeMembers为：{}, changeMembers_error为：{}",
                    JSON.toJSONString(changeMembers),
                    JSON.toJSONString(changeMembers_error));
            result.put("changeMembers", changeMembers);
            result.put("changeMembers_error", changeMembers_error);
            result.put("currentHandleXml", file);
            return result;
        } catch (Exception e) {
            log.error("jdom解析xml错误", e);
            throw new RuntimeException("jdom解析xml出错", e);
        }
    }


    /**
     * 功能描述: 根据固定大小对集合进行分组
     *
     * @param list     原始集合
     * @param quantity 分组大小
     * @return
     */
    public static List groupListByQuantity(List list, int quantity) {
        if (list == null || list.size() == 0) {
            return list;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
            count += quantity;
        }

        return wrapList;
    }

    /**
     * 移动文件
     *
     * @param sourceFilePath 源文件路径
     * @param targetFilePath 目标文件路径
     * @param targetFileName 目标文件名称
     */
    public static void moveFile(String sourceFilePath, String targetFilePath, String targetFileName) {
        if (StringUtils.isBlank(sourceFilePath)) {
            log.info("源文件路径不能为空");
            return;
        }
        try {
            File sourceFile = new File(sourceFilePath);
            if (sourceFile.renameTo(new File(targetFilePath + targetFileName))) {
                log.error("移动文件成功");
            } else {
                log.error("移动文件失败");
                throw new RuntimeException("移动文件失败");
            }
        } catch (Exception e) {
            log.error("移动文件出错", e);
            throw new RuntimeException("移动文件出错");
        }
    }

    /**
     * 获取指定目录下的xml文件
     *
     * @param f 文件路径
     * @return xml文件
     */
    public static List<File> getXMLs(File f) {
        ArrayList<File> files = new ArrayList<>();
        File[] arr = f.listFiles(new FileFilter(){
            @Override
            public boolean accept(File ff) {
                return ff.getName().endsWith(Constants.FILE_SUFFIX) ? true : false;
            }
        });
        for (File ff : arr) {
            if (ff.isDirectory())
                getXMLs(ff);
            else
                files.add(ff);
        }
        return files;
    }
}
