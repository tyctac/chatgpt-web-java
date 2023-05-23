package com.hncboy.chatgpt.base.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hncboy
 * @date 2023/3/24 17:50
 * jackson ObjectMapper 工具类
 */
@Slf4j
@UtilityClass
public class StringUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * string to List
     * @param str
     * @param delimiter
     * @return
     */
    public static List<String> splitString(String str, String delimiter) {
        // Split the string using the specified delimiter and convert it to a list
        return Arrays.stream(str.split(delimiter))
                .collect(Collectors.toList());
    }

}
