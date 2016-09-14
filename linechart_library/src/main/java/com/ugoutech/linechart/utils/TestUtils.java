package com.ugoutech.linechart.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


/**
 * @描述: 开发测试工具
 * @项目名: charts
 * @包名: com.ugoutech.linechart.utils
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public abstract class TestUtils {


    private static Toast sToast;


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


    public static void showToast(Context context,String txt){
        if(sToast == null){
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }

        sToast.setText(txt);
        sToast.show();
    }

}
