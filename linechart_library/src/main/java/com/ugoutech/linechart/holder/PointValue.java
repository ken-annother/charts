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
    private float yValue;
    private String xValue;


    public PointValue(float yValue, String xValue) {
        this.yValue = yValue;
        this.xValue = xValue;
    }


    public float getyValue() {
        return yValue;
    }


    public void setyValue(float yValue) {
        this.yValue = yValue;
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
                "yValue=" + yValue +
                ", xValue=" + xValue +
                '}';
    }
}
