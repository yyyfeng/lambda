package com.example.lambda.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName FileUtil
 * @Description 操作文件工具
 * @Author baojin
 * @Date 2019/9/18 14:39
 * @Version: v1.0 文件初始创建
 **/
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * @return java.lang.String
     * @Description 读取文件
     * @Param filePath  路径
     * @Author baojin
     * @date 2019/9/18 14:41
     * @Throw
     **/
    public static String readFile(String filePath) {
        String string;
        StringBuilder data = new StringBuilder();
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            while ((string = bufferedReader.readLine()) != null) {
                //每行用分号分割
                data.append(string);
            }
            LOGGER.info("文件读取成功，文件名称为：{}", filePath);
            return data.toString();
        } catch (Exception e) {
            LOGGER.error("文件读取失败，文件名称为：" + filePath, e);
//            throw new BusinessException("文件读取失败", e);
        }
        return "";

    }
}
