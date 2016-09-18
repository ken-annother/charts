package com.ugoutech.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.ugoutech.linechart.holder.Axix;
import com.ugoutech.linechart.holder.PointValue;
import com.ugoutech.linechart.utils.TestUtils;
import com.ugoutech.linechart.utils.Utils;

import java.util.ArrayList;
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

    //触摸灵敏度参数
    private int touchSence = 60;

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
    private Paint dotPaintWhite;

    //提示标记
    private MarkView mMarkView;

    //画标记
    private boolean mCanDraw;

    //标记的X位置
    private float mMarkViewPosX;
    //标记的Y位置
    private float mMarkViewPosY;


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

        dotPaintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaintWhite.setColor(Color.WHITE);
        dotPaintWhite.setStrokeWidth(Utils.convertDpToPixel(mContext, 8f));
        dotPaintWhite.setStyle(Paint.Style.FILL);


        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xFFFF7700);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setPathEffect(new CornerPathEffect(20));
        linePaint.setStrokeWidth(Utils.convertDpToPixel(mContext, 2f));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;

        if (initOriginDot()) {
            mAxix.draw(mCanvas, axixPaint);
            drawLabel();
            drawValueLine();
            drawDots();
        }

        if (mMarkView != null && mCanDraw) {
            mMarkView.draw(mCanvas, mMarkViewPosX, mMarkViewPosY);
        }

    }


    /**
     * 画数据点
     */
    private void drawDots() {
        int datasetSize = mDataSets.size();
        for (int i = 0; i < datasetSize; i++) {
            float[] priv = mAxix.calcPriv(i, mDataSets.get(i).getyValue());

            //画出了白色空隙数据点
            mCanvas.drawCircle(priv[0], priv[1], 15, dotPaintWhite);

            //画出了数据点
            mCanvas.drawCircle(priv[0], priv[1], 10, dotPaint);

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
            float[] priv = mAxix.calcPriv(i, mDataSets.get(i).getyValue());

            //画出了垂线
            mCanvas.drawLine(priv[0], priv[1], priv[0], mAxix.getOrgPriv()[1], axixPaint);

            if (i == 0) {
                path.moveTo(priv[0], priv[1]);
            } else {
                path.lineTo(priv[0], priv[1]);
            }
        }

        mCanvas.drawPath(path, linePaint);


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

            float max = mDataSets.get(0).getyValue();
            float min = mDataSets.get(0).getyValue();
            for (int i = 1; i < datasetSize; i++) {
                if (mDataSets.get(i).getyValue() > max) max = mDataSets.get(i).getyValue();
                if (mDataSets.get(i).getyValue() < min) min = mDataSets.get(i).getyValue();
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


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //如果没有绑定数据，则不执行
        if (mDataSets == null || mDataSets.size() == 0) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                int index = checkHint(x, y);
                handleHintEffect(index);

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_MOVE:

                break;
        }


        return super.onTouchEvent(event);
    }


    /**
     * 处理击中后的效果
     *
     * @param index 如果是-1，则没有击中
     */
    private void handleHintEffect(int index) {

        if (index >= 0) {
//            TestUtils.showToast(mContext, "Index : " + index + "被击中了");
            mMarkView.setLabel(mDataSets.get(index).getyValue() + "");
            determineMarkViewPos(index);
            mCanDraw = true;

        } else {
            mCanDraw = false;
        }

        invalidate();

    }


    /**
     * 确定当前标记的显示位置
     *
     * @param index
     */
    private void determineMarkViewPos(int index) {

        int width = mMarkView.getWidth();
        int height = mMarkView.getHeight();

        Log.d(TAG, "标记的 width" + width);
        Log.d(TAG, "标记的 height" + height);


        float[] priv = mAxix.calcPriv(index, mDataSets.get(index).getyValue());

        if (priv[1] - height >= 0) {
            mMarkView.setShowDownShape(true);
            mMarkView.refreshContent();

            mMarkViewPosX = priv[0] - width / 2;
            mMarkViewPosY = priv[1] - height;

        } else {        //标记超出了上线，则应该将标记调转方向

            mMarkView.setShowDownShape(false);
            mMarkView.refreshContent();

            mMarkViewPosX = priv[0] - width / 2;
            mMarkViewPosY = priv[1];
        }


    }


    /**
     * 监测点击碰撞 触到的点
     *
     * @return 如果是-1，则没有击中
     */
    private int checkHint(float touchx, float touchy) {
        List<Integer> hintList = new ArrayList();

        for (int i = 0; i < mDataSets.size(); i++) {
            float[] priv = mAxix.calcPriv(i, mDataSets.get(i).getyValue());

            float absX = Math.abs(touchx - priv[0]);
            float absY = Math.abs(touchy - priv[1]);

            float delta = touchSence * touchSence - absX * absX - absY * absY;

            if (delta >= 0) {       //如果符合则加入触发的列表中
                hintList.add(i);
            }

        }

        if (hintList.size() == 1) {
            return hintList.get(0);
        } else {
            return -1;
        }

    }


    /**
     * 设置提示的View
     *
     * @param markView
     */
    public void setMarkView(MarkView markView) {
        mMarkView = markView;
    }
}

