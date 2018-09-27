package io.renren.common.utils;

import java.util.UUID;

/**
 * 订单号生成
 */
public class NoUtils {
    public static String genOrderNo(){

        String machineId = "A"; // 最大支持到9

        int hashCodeV = UUID.randomUUID().toString().hashCode();

        if(hashCodeV < 0) {

            hashCodeV = -hashCodeV;

        }

// %015d：0表示前面补0，15表示长度为15，d 表示参数为正整数

        return machineId + String.format("%09d", hashCodeV);

    }


    public static String getModeNo(){

        int machineId = 1; // 最大支持到9

        int hashCodeV = UUID.randomUUID().toString().hashCode();

        if(hashCodeV < 0) {

            hashCodeV = -hashCodeV;

        }

      // %015d：0表示前面补0，15表示长度为15，d 表示参数为正整数

        return "PA"+machineId + String.format("%09d", hashCodeV);

    }
}
