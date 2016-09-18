package com.ugoutech.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private TextView mLabel;


    public MarkView(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    private void initView() {
        View inflated = View.inflate(mContext, R.layout.markview, this);

        mLabel = (TextView) inflated.findViewById(R.id.label);

        inflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout
                .LayoutParams.WRAP_CONTENT));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED));

        // measure(getWidth(), getHeight());
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    /**
     * 设置标签的内容
     *
     * @param label
     */
    public void setLabel(String label) {
        mLabel.setText(label);
        refreshContent();
    }


    /**
     * 重新测量和布局控件
     */
    public void refreshContent() {
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }
}
