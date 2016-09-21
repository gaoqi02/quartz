package com.gaoqi.rc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by qigao212074 on 2016/8/26.
 */
public class JsonParser {

    static Logger logger = LoggerFactory.getLogger(JsonParser.class);
    /**
     * ObjectMapper线程安全，可以作为单例使用
     */
    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象解析为json数据
     *
     * @param obj 对象
     * @return
     */
    public static String write(Object obj) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("解析对象至json数据失败", e);
        }
        return result;
    }

    /**
     * 读取父节点下指定子节点，并将其解析为指定类型对象
     *
     * @param node     父节点
     * @param type     类型
     * @param pathName 子节点key链
     * @param <T>      范型
     * @return
     */
    public static <T> T read(JsonNode node, Class<T> type, String... pathName) {

        JsonNode child = read(node, pathName);
        try {
            return objectMapper.readValue(child.toString(), type);
        } catch (IOException e) {
            logger.error("节点类型转换失败:node=" + child + ", type=" + type, e);
            return null;
        }
    }

    /**
     * 深层解析父节点下的子节点
     *
     * @param node     父节点
     * @param pathName 子节点key链
     * @return
     */
    public static JsonNode read(JsonNode node, String... pathName) {

        JsonNode child = node;
        try {
            for (String path : pathName) {
                child = child.path(path);
            }
        } catch (Exception e) {
            logger.error("读取子节点失败:" + Arrays.toString(pathName), e);
            return null;
        }
        return child;
    }

    /**
     * 读取父节点下指定子节点
     *
     * @param node     父节点
     * @param pathName 子节点key链
     * @return
     */
    public static JsonNode read(String node, String... pathName) {

        if (node == null) {
            return null;
        }

        try {
            return read(objectMapper.readTree(node), pathName);
        } catch (Exception e) {
            logger.error("读取子节点失败:" + Arrays.toString(pathName), e);
            return null;
        }
    }

    public static <T> T read(String json, Class<T> type, String... pathName) {
        JsonNode child = read((String)json, pathName);

        try {
            return objectMapper.readValue(child.toString(), type);
        } catch (IOException var5) {
            logger.error("节点类型转换失败:node=" + child + ", type=" + type, var5);
            return null;
        }
    }

    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        Map map = readValue("{\"website\":\"open-open.com\", \"g\":\"asd\"}",Map.class);

        System.out.print("sadads");
    }


}
