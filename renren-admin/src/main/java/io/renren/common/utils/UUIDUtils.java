package io.renren.common.utils;

import java.util.UUID;

/**
 * uuid 主键生成工具类
 * Created by 小宇宙
 * on 2018/3/9
 */
public class UUIDUtils {

    /**
     * UUID生成
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }
}
