package com.ugoutech.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.ugoutech.linechart.holder.PointValue;
import com.ugoutech.linechart.view.LineChartView;
import com.ugoutech.linechart.view.MarkView;

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

        values.add(new PointValue((float) (Math.random() * 1000), "09-07"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-08"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-09"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-10"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-11"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-12"));
        values.add(new PointValue((float) (Math.random() * 1000), "09-13"));

        mChart.setData(values);
    }
}
