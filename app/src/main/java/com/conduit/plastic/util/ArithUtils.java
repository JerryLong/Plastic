package com.conduit.plastic.util;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

public class ArithUtils {
    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @return 两个参数的和
//     */
//    public static int ratio9_16(int value1){
//        double d=value1*9/16;
//        Logger.i("==scrolll=value22="+d);
//        BigDecimal b2 = new BigDecimal(Double.valueOf(d));
//        Logger.i(div(9,16,3)+"==scrolll=value1="+value1);
////        return b1.multiply(b2).intValue();
//    }

    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static float div(int value1, int value2, int scale)  {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
           return 0;
        }
        Logger.i(value1+"===scrolll==="+value2);
        double d=value1/value2;
        BigDecimal b1 = new BigDecimal(Double.valueOf(d));
        Logger.i("===scrolll==="+b1.floatValue());
//        return b1.divide(b2, scale).floatValue();
        return b1.setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
    }
}