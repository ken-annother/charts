package com.ugoutech.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ugoutech.linechart.holder.PointValue;
import com.ugoutech.linechart.view.LineChartView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private LineChartView mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (LineChartView) findViewById(R.id.chart);

        initChart();

    }


    private void initChart() {
        List<PointValue> values = new ArrayList<>();

        values.add(new PointValue(121,"09-07"));
        values.add(new PointValue(0,"09-08"));
        values.add(new PointValue(500,"09-09"));
        values.add(new PointValue(42,"09-10"));
        values.add(new PointValue(67,"09-11"));
        values.add(new PointValue(123,"09-12"));
        values.add(new PointValue(55,"09-13"));

        mChart.setData(values);
    }
}
