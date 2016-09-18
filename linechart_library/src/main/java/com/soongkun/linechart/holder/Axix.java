package com.soongkun.linechart.holder;

import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * @描述: 坐标系
 * @项目名: charts
 * @包名: com.ugoutech.linechart.holder
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public class Axix {

    private final float mStartX;
    private final float mStartY;
    private final float mEndY;
    private final float mEndX;
    private final int mDatasetNum;
    private final float mMaxiumYValue;
    private final float mMixiumYValue;

    //原点X坐标
    private float orgX;
    //原点Y坐标
    private float orgY;

    //每一个X的宽度
    public float perX;
    //每一个Y的宽度
    public float perY;


    /**
     * @param startX       坐标系起点X轴
     * @param startY       坐标系起点Y轴
     * @param endX         坐标系终点X轴
     * @param endY         坐标系终点Y轴
     * @param datasetNum   数据集的数目
     * @param mixiumYValue 最小值
     * @param maxiumYValue 最大值
     */
    public Axix(float startX, float startY, float endX, float endY, int datasetNum, float mixiumYValue, float
            maxiumYValue) {
        this.mStartX = startX;
        this.mStartY = startY;
        this.mEndX = endX;
        this.mEndY = endY;

        this.mDatasetNum = datasetNum;
        this.mMaxiumYValue = maxiumYValue;
        this.mMixiumYValue = mixiumYValue;

        perX = (endX - startX) / (datasetNum + 1);
        perY = (startY - endY) / maxiumYValue;

        orgX = perX;
        orgY = startY;

    }


    /**
     * 画出坐标系
     *
     * @param canvas
     * @param paint
     */
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(0, mStartY, mEndX, mStartY, paint);
    }


    /**
     * 根据数据的序号和Y值，计算
     *
     * @param index  在列表中的序号
     * @param yValue 对应的真实值
     * @return 返回cannvas坐标系中的坐标数据，第一个是X轴坐标，第二个是Y轴坐标
     */
    public float[] calcPriv(int index, float yValue) {
        float[] prov = new float[2];

        prov[0] = index * perX + getOrgPriv()[0];
        prov[1] = getOrgPriv()[1] - yValue * perY;

        return prov;
    }


    /**
     * 获取原点的坐标
     *
     * @return 第一个是X轴坐标，第二个是Y轴坐标
     */
    public float[] getOrgPriv() {
        float[] prov = new float[2];
        prov[0] = orgX;
        prov[1] = orgY;
        return prov;
    }

}

