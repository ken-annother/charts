package com.ugoutech.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;
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
    private View mInflated;
    private ImageView mBg;


    public MarkView(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    private void initView() {
        mInflated = View.inflate(mContext, R.layout.markview, this);


        mBg = (ImageView) mInflated.findViewById(R.id.bg);
        mLabel = (TextView) mInflated.findViewById(R.id.label);

        mInflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout
                .LayoutParams.WRAP_CONTENT));
        mInflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED));

        // measure(getWidth(), getHeight());
        mInflated.layout(0, 0, mInflated.getMeasuredWidth(), mInflated.getMeasuredHeight());
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
    }


    /**
     * 重新测量和布局控件
     */
    public void refreshContent() {
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }


    /**
     * 在指定的位置上画图
     *
     * @param canvas
     * @param posX
     * @param posY
     */
    public void draw(Canvas canvas, float posX, float posY) {
        int saveId = canvas.save();
        // translate to the correct position and draw
        canvas.translate(posX, posY);
        draw(canvas);
        canvas.restoreToCount(saveId);
    }


    /**
     * 设置标记视图的朝向
     *
     * @param b
     */
    public void setShowDownShape(boolean b) {

        if (b) {
            mBg.setBackgroundResource(R.drawable.marker);
        } else {
            mBg.setBackgroundResource(R.drawable.marker2);
        }

    }
}
