package com.ugoutech.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.ugoutech.linechart.holder.Axix;
import com.ugoutech.linechart.holder.PointValue;
import com.ugoutech.linechart.utils.TestUtils;
import com.ugoutech.linechart.utils.Utils;

import java.util.List;


/**
 * @描述: 折线图
 * @项目名: charts
 * @包名: com.ugoutech.linechart
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public class LineChartView extends ViewGroup {

    private static final String TAG = "LineChartView";

    private Paint axixPaint;
    private Context mContext;

    //画布
    private Canvas mCanvas;

    /**
     * 坐标轴下方的标签
     */
    private Paint labelPaint;

    /**
     * 画布的宽高
     */
    private int mCanvasWidth;
    private int mCanvasHeight;
    private float mLabelPaintTextHeight;

    //标签的高度
    private float mLabelHeight;

    //底部标签下面的padding
    private int mBottomPadding = 30;
    //坐标系顶部的距离
    private int mTopPadding = 30;

    //是否显示x轴的标签代替值
    private boolean mEnableShowLabel;

    //要显示的数据集
    private List<PointValue> mDataSets;

    /**
     * 每一个格子的宽度
     */
    private float mSepWidthPer;
    private Axix mAxix;
    private Paint dotPaint;
    private Paint linePaint;


    public LineChartView(Context context) {
        super(context);
        init(context);
    }


    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(left, top, right, bottom);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode = Utils.getMeasureSpecMode(widthMeasureSpec);
        int widthMeasureSize = Utils.getMeasureSpecSize(widthMeasureSpec);
        int heightMeasureMode = Utils.getMeasureSpecMode(heightMeasureSpec);
        int heightMeasureSize = Utils.getMeasureSpecSize(heightMeasureSpec);

        Log.d(TAG, "widthMeasureMode : " + TestUtils.transMode(widthMeasureMode));
        Log.d(TAG, "widthMeasureSize : " + widthMeasureSize);
        Log.d(TAG, "heightMeasureMode : " + TestUtils.transMode(heightMeasureMode));
        Log.d(TAG, "heightMeasureSize : " + heightMeasureSize);

        mCanvasWidth = widthMeasureSize;
        mCanvasHeight = heightMeasureSize;

        setMeasuredDimension(widthMeasureSize, heightMeasureSize);
    }


    private void init(Context context) {
        this.mContext = context;

        axixPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        axixPaint.setColor(0xFFFFC0CB);
        axixPaint.setStrokeWidth(Utils.convertDpToPixel(mContext, 1f));


        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(0xFFFF3E96);
        labelPaint.setTextSize(Utils.convertSpToPixel(mContext, 12f));
        mLabelPaintTextHeight = Utils.getPaintTextHeight(labelPaint);
        mLabelHeight = mLabelPaintTextHeight * 1.3f;


        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setColor(Color.RED);
        dotPaint.setStrokeWidth(Utils.convertDpToPixel(mContext, 5f));
        dotPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xFFFF7700);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(Utils.convertDpToPixel(mContext, 2f));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;

        if (initOriginDot()) {
            mAxix.draw(mCanvas, axixPaint);
            drawLabel();
            drawValueLine();

        }

    }


    /**
     * 画数据集的线段
     */
    private void drawValueLine() {
        int datasetSize = mDataSets.size();

        Path path = new Path();
        path.reset();

        for (int i = 0; i < datasetSize; i++) {
            float[] priv = mAxix.calcPriv(i, mDataSets.get(i).getyVauue());

            //画出了垂线
            mCanvas.drawLine(priv[0], priv[1], priv[0], mAxix.getOrgPriv()[1], axixPaint);

            if(i == 0){
                path.moveTo(priv[0],priv[1]);
            }else{
                path.lineTo(priv[0],priv[1]);
            }
        }

        mCanvas.drawPath(path,linePaint);


//        float[] privBack = mAxix.calcPriv(i + 1, mDataSets.get(i + 1).getyVauue());
//        mCanvas.drawLine(priv[0], priv[1], privBack[0], privBack[1], linePaint);
//            //画出了数据点
//            mCanvas.drawCircle(priv[0], priv[1], 10, dotPaint);
//

    }


    /**
     * 初始化坐标系
     *
     * @return 成功返回True 失败返回false
     */
    private boolean initOriginDot() {
        if (mDataSets == null || mDataSets.size() == 0) {
            return false;
        } else {
            int datasetSize = mDataSets.size();

            float max = mDataSets.get(0).getyVauue();
            float min = mDataSets.get(0).getyVauue();
            for (int i = 1; i < datasetSize; i++) {
                if (mDataSets.get(i).getyVauue() > max) max = mDataSets.get(i).getyVauue();
                if (mDataSets.get(i).getyVauue() < min) min = mDataSets.get(i).getyVauue();
            }

            mAxix = new Axix(0, mCanvasHeight - mLabelHeight - mBottomPadding, mCanvasWidth, mTopPadding,
                    datasetSize, min, max);
            return true;
        }
    }


    /**
     * 画标签
     */
    private void drawLabel() {
        float paintTextHeight = Utils.getPaintTextHeight(labelPaint);
        Log.d(TAG, "画笔文字的高度是：" + paintTextHeight);

        //原点的坐标
        float[] orgPriv = mAxix.getOrgPriv();

        for (int i = 0; i < mDataSets.size(); i++) {
            float txtWidth = labelPaint.measureText(mDataSets.get(i).getxValue());
            mCanvas.drawText(mDataSets.get(i).getxValue(), orgPriv[0] + i * mAxix.perX - txtWidth / 2, orgPriv[1] +
                    mLabelPaintTextHeight, labelPaint);
        }


    }


    /**
     * 画轴线的笔的颜色
     *
     * @param color
     */
    public void setAxixPaintColor(int color) {
        axixPaint.setColor(color);
    }


    /**
     * 轴线笔的大小
     *
     * @param textsize
     */
    public void setAxixPaintSize(float textsize) {
        axixPaint.setTextSize(textsize);
    }


    /**
     * 轴线笔的宽度
     *
     * @param width
     */
    public void setAxixPaintStrokeWidth(float width) {
        axixPaint.setStrokeWidth(width);
    }


    /**
     * 设置数据集
     *
     * @param values
     */
    public void setData(List<PointValue> values) {
        mDataSets = values;
    }


    public void setEnableShowLabel(boolean isShow) {
        mEnableShowLabel = isShow;
    }


    public void setBottomPadding(int bottomPadding) {
        this.mBottomPadding = bottomPadding;
    }


    public void setTopPadding(int topPadding) {
        this.mTopPadding = topPadding;
    }
}

