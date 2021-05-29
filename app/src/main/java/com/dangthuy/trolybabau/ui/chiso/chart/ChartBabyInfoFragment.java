package com.dangthuy.trolybabau.ui.chiso.chart;

import android.graphics.Color;
import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.MyValueFormater;
import com.dangthuy.trolybabau.databinding.FragmentChartBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.chiso.BabyInforViewModel;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ChartBabyInfoFragment extends BaseFragment<BabyInforViewModel> {
    public static final String TAG = "ChartBabyInfoFragment";

    private FragmentChartBinding binding;

    public static ChartBabyInfoFragment newInstance() {
        ChartBabyInfoFragment fragment = new ChartBabyInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<BabyInforViewModel> provideViewModelClass() {
        return BabyInforViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView() {
        binding = (FragmentChartBinding) getBinding();
        if (getArguments() != null) {

        }
        viewModel.getLiveChart().observe(this, chart -> {
            setLayoutView(chart.getDates());
            setData(365, chart.getLowValues(), chart.getHightValues());
        });
    }

    private void setLayoutView(String[] dates) {
        binding.chart.setViewPortOffsets(80f, 0, 0, 80f);
        binding.chart.setBackgroundColor(getContext().getColor(R.color.transparent_background));

        // no description text
        binding.chart.getDescription().setEnabled(false);

        // enable touch gestures
        binding.chart.setTouchEnabled(true);

        // enable scaling and dragging
        binding.chart.setDragEnabled(true);
        binding.chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        binding.chart.setPinchZoom(false);

        binding.chart.setDrawGridBackground(true);
        binding.chart.setMaxHighlightDistance(300);
//        if (binding.chart.getRendererXAxis() <)
        binding.chart.setGridBackgroundColor(getContext().getColor(R.color.transparent_background));

//        else
//            binding.chart.setGridBackgroundColor(getContext().getResources().getColor(R.color.red));

        XAxis x = binding.chart.getXAxis();
//        x.setEnabled(false);
//        x.setLabelCount(5, true);
        x.setTextColor(Color.WHITE);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
//        x.setGridColor(getContext().getResources().getColor(R.color.blue));
        x.setAxisLineColor(Color.WHITE);

//        String[] val = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] val = new String[365];
        for (int i = 0; i < 365; i++) {
//            if (i % 100 == 0) {
//                val[i] = "" + (i / 100);
//            } else {
                val[i] = dates[i];
//            }
        }
        x.setValueFormatter(new MyValueFormater(val));

        YAxis y = binding.chart.getAxisLeft();
//        y.setTypeface(tfLight);
        y.setLabelCount(5, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        y.setXOffset(0);
        y.setDrawGridLines(false);
        y.setAxisLineColor(getContext().getColor(R.color.colorPrimary));
//        y.setSpaceBottom(20f);
        y.setAxisMinimum(0f);

        binding.chart.getAxisRight().setEnabled(false);
        binding.chart.getLegend().setEnabled(false);

        binding.chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        binding.chart.invalidate();
    }

    private void setData(int count, float[] lowRange, float[] hightRange) {

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * (range + 1)) + 20;
            float val = lowRange[i];
            values.add(new Entry(i, val));
        }

        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * (range + 1)) + 20;
            float val = hightRange[i];
            values2.add(new Entry(i, val));
        }

        LineDataSet set1, set2;

        if (binding.chart.getData() != null &&
                binding.chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) binding.chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) binding.chart.getData().getDataSetByIndex(1);
            set1.setValues(values);
            set2.setValues(values2);
            binding.chart.getData().notifyDataChanged();
            binding.chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(getContext().getColor(R.color.gray));
            set1.setDrawCircles(false);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(255);
            set1.setDrawFilled(true);
            set1.setFillColor(getContext().getColor(R.color.gray));
            set1.setHighLightColor(getContext().getColor(R.color.gray));
            set1.setDrawCircleHole(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    // change the return value here to better understand the effect
                    // return 0;
                    return binding.chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(getContext().getColor(R.color.gray));
            set2.setDrawCircles(false);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(255);
            set2.setDrawFilled(true);
            set2.setFillColor(getContext().getColor(R.color.gray));
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(getContext().getColor(R.color.gray));
            set2.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    // change the return value here to better understand the effect
                    // return 600;
                    return binding.chart.getAxisLeft().getAxisMaximum();
                }
            });

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets
            dataSets.add(set2);

            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            data.setDrawValues(false);

            // set data
            binding.chart.setData(data);
        }
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {
        viewModel.fetchChart();
    }
}
