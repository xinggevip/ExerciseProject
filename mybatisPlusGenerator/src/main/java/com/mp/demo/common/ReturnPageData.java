package com.mp.demo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页返回数据公共类
 */
public class ReturnPageData {

    public static Map<String, Object> fillingData(long count, Object list) {
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("data", list);
        return map;
    }
}
