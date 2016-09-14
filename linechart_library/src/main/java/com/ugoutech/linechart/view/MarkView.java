package com.ugoutech.linechart.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.ugoutech.linechart.R;


/**
 * @描述: 标记视图
 * @项目名: charts
 * @包名: com.ugoutech.linechart.view
 * @类名:
 * @作者: soongkun
 * @创建时间: 2016/4/29 13:16
 */

public class MarkView extends RelativeLayout {

    private final Context mContext;


    public MarkView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        View inflate = View.inflate(mContext, R.layout.markview, this);
    }
}
