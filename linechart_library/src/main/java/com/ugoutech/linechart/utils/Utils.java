package com.ugoutech.linechart.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


/**
 * 工具类
 */
public class Utils {

    private Utils() {}


    /**
     * Dp 2 px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int convertDpToPixel(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * SP 2 PX
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int convertSpToPixel(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 根据设备信息获取当前分辨率下指定单位对应的像素大小；
     * px,dip,sp -> px
     */
    public static float getRawSize(Context c, int unit, float size) {
        Resources r;
        if (c == null) {
            r = Resources.getSystem();
        } else {
            r = c.getResources();
        }
        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }


    /**
     * 获取参数的Mode
     *
     * @param measureSpec
     * @return
     */
    public static int getMeasureSpecMode(int measureSpec) {
        return View.MeasureSpec.getMode(measureSpec);
    }


    /**
     * 获取擦书的Size
     *
     * @param measureSpec
     * @return
     */
    public static int getMeasureSpecSize(int measureSpec) {
        return View.MeasureSpec.getSize(measureSpec);
    }


    public static float getPaintTextHeight(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();

        float topY = fontMetrics.top;
        float ascentY = fontMetrics.ascent;
        float descentY = fontMetrics.descent;
        float bottomY = fontMetrics.bottom;
        float leading = fontMetrics.leading;

        Log.d("fontMetrics", "topY     is:" + topY);
        Log.d("fontMetrics", "ascentY  is:" + ascentY);
        Log.d("fontMetrics", "descentY is:" + descentY);
        Log.d("fontMetrics", "bottomY  is:" + bottomY);
        Log.d("fontMetrics", "leading  is:" + leading);

        return bottomY - topY;
    }
}
