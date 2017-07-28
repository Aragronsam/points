package com.yonghui.utils;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-07-02-20:15
 * @Home: http://aragron.com
 */
public interface Constants {
    /**
     * 集合分组固定大小
     */
    int CRITICAL = 1000;

    /**
     * 文件后缀
     */
    String FILE_SUFFIX = ".xml";

    /**
     * xml源文件地址
     */
    String SOURCE_XML_FILEPATH = "/Users/aragron/Desktop/points/sources";
    //String SOURCE_XML_FILEPATH = "/CRM1001/PENDING";

    /**
     * xml处理成功存放目录
     */
    String SUCCESS_HANDLE_DIR = "/CRM1001/PENDING/success/";
    //String SUCCESS_HANDLE_DIR = "/Users/aragron/Desktop/points/success/";

    /**
     * xml处理失败存放目录
     */
    String ERROR_HANDLE_DIR = "/CRM1001/PENDING/error/";
    //String ERROR_HANDLE_DIR = "/Users/aragron/Desktop/points/error";

}
