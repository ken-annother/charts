package com.ugoutech.linechart.holder;

/**
 * @描述: 数据点
 * @项目名: charts
 * @包名: com.ugoutech.linechart.holder
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public class PointValue {
    private float yVauue;
    private String xValue;


    public PointValue(float yVauue, String xValue) {
        this.yVauue = yVauue;
        this.xValue = xValue;
    }


    public float getyVauue() {
        return yVauue;
    }


    public void setyVauue(float yVauue) {
        this.yVauue = yVauue;
    }


    public String getxValue() {
        return xValue;
    }


    public void setxValue(String xValue) {
        this.xValue = xValue;
    }


    @Override
    public String toString() {
        return "PointValue{" +
                "yVauue=" + yVauue +
                ", xValue=" + xValue +
                '}';
    }
}
