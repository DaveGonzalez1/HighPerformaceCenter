package com.example.personalrank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static final float MAX = 24, MIN = 4f;
    public static final int NB_QUALITIES = 5;
    private RadarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.chart);

        chart.setBackgroundColor(Color.BLACK);
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.WHITE);
        chart.setWebLineWidth(1f);
        chart.setWebColorInner(Color.WHITE);
        chart.setWebAlpha(100);

        setData();

        chart.animateXY(1400,1400, Easing.EasingOption.EaseInOutQuad, Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(15f);
        xAxis.setXOffset(0);
        xAxis.setYOffset(0);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String [] qualities = new String[]{"Fuerza", "Resistencia", "Velocidad", "Agilidad", "Rapidez"};

            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return qualities[(int) v % qualities.length];
            }
        });

        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(NB_QUALITIES, false);
        yAxis.setTextSize(15f);
        yAxis.setAxisMaximum(MAX);
        yAxis.setAxisMinimum(MIN);
        yAxis.setDrawLabels(false);

        Legend legend = chart.getLegend();
        legend.setTextSize(20f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(15f);
        legend.setYEntrySpace(9f);
        legend.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.actualizar :
//                setData();
//                chart.invalidate();
//                break;
//            case R.id.alternar :
//                for (IDataSet<?> set : chart.getData().getDataSets()){
//                    set.setDrawValues(!set.isDrawValuesEnabled());
//                }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void setData() {
        ArrayList<RadarEntry> warrior = new ArrayList<>();
        ArrayList<RadarEntry> warrior1 = new ArrayList<>();

        for (int i = 0; i < NB_QUALITIES; i++) {
            float val1 = (int) (Math.random() * MAX + MIN);
            warrior.add(new RadarEntry(val1));

//            float val2 = (int) (Math.random() * MAX + MIN);
//            warrior1.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(warrior,"");
        set1.setColor(Color.BLACK);
        set1.setValueTextSize(15f);
        set1.setFillColor(Color.RED);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(3f);
        set1.setDrawHighlightIndicators(false);
        set1.setDrawHighlightCircleEnabled(true);

//        RadarDataSet set2 = new RadarDataSet(warrior1,"Warrior B");
//        set2.setColor(Color.GREEN);
//        set2.setFillColor(Color.GREEN);
//        set2.setDrawFilled(true);
//        set2.setFillAlpha(180);
//        set2.setLineWidth(2f);
//        set2.setDrawHighlightIndicators(false);
//        set2.setDrawHighlightCircleEnabled(true);

//        ArrayList<IRadarDataSet> sets = new ArrayList<>();
//        sets.add(set1);
//        sets.add(set2);

        RadarData data = new RadarData(set1);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();

    }
}
