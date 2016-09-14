package com.ugoutech.linechart.utils;

import android.view.View;


/**
 * @描述: 开发测试工具
 * @项目名: charts
 * @包名: com.ugoutech.linechart.utils
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public abstract class TestUtils {
    private TestUtils(){}

    public static String transMode(int measureMode){
        if(measureMode == View.MeasureSpec.AT_MOST){
            return "AT_MOST";
        }else if(measureMode == View.MeasureSpec.EXACTLY){
            return "EXACTLY";
        }else{
            return "UNSPECIFIED";
        }

    }

}
