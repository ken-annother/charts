package com.soongkun.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soongkun.linechart.holder.PointValue;
import com.soongkun.linechart.view.LineChartView;
import com.soongkun.linechart.view.MarkView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private LineChartView mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (LineChartView) findViewById(R.id.chart);

        MarkView markView = new MarkView(this);
        mChart.setMarkView(markView);

        initChart();

    }


    private void initChart() {
        List<PointValue> values = new ArrayList<>();

        values.add(new PointValue(120, "09-07"));
        values.add(new PointValue(45, "09-08"));
        values.add(new PointValue(0, "09-09"));
        values.add(new PointValue(67, "09-10"));
        values.add(new PointValue(33, "09-11"));
        values.add(new PointValue(89, "09-12"));
        values.add(new PointValue(200, "09-13"));

        mChart.setData(values);
    }
}
