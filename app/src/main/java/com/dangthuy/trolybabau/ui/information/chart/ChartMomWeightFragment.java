package com.dangthuy.trolybabau.ui.information.chart;

import android.graphics.Color;
import android.os.Bundle;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.MyValueFormater;
import com.dangthuy.trolybabau.databinding.FragmentChartBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.information.InfomartionViewModel;
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
public class ChartMomWeightFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "ChartMomWeightFragment";
    private FragmentChartBinding binding;

    public static ChartMomWeightFragment newInstance() {
        ChartMomWeightFragment fragment = new ChartMomWeightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<InfomartionViewModel> provideViewModelClass() {
        return InfomartionViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView() {
        binding = (FragmentChartBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        float[] val = {0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 0, 12};
        setData(11, val);
    }

    private void setLayoutView() {
        binding.chart.setViewPortOffsets(0, 0, 0, 0);
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

        binding.chart.setDrawGridBackground(false);
        binding.chart.setMaxHighlightDistance(300);
//        if (binding.chart.getRendererXAxis() <)
        binding.chart.setGridBackgroundColor(getContext().getResources().getColor(R.color.yellow));
//        else
//            binding.chart.setGridBackgroundColor(getContext().getResources().getColor(R.color.red));

        XAxis x = binding.chart.getXAxis();
//        x.setEnabled(false);
//        x.setLabelCount(6, false);
        x.setTextColor(Color.WHITE);
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setDrawGridLines(false);
        x.setGridColor(getContext().getResources().getColor(R.color.blue));
        x.setAxisLineColor(Color.WHITE);
        String[] val = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        x.setValueFormatter(new MyValueFormater(val));

        YAxis y = binding.chart.getAxisLeft();
//        y.setTypeface(tfLight);
        y.setLabelCount(5, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        y.setXOffset(0);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
//        y.setSpaceBottom(20f);
        y.setAxisMinimum(0f);

        binding.chart.getAxisRight().setEnabled(false);
        binding.chart.getLegend().setEnabled(false);

        binding.chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        binding.chart.invalidate();
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void onRefreshData() {

    }

    private void setData(int count, float[] range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * (range + 1)) + 20;
            float val = range[i];
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (binding.chart.getData() != null &&
                binding.chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) binding.chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            binding.chart.getData().notifyDataChanged();
            binding.chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return binding.chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
//            data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            binding.chart.setData(data);
        }
    }
}
