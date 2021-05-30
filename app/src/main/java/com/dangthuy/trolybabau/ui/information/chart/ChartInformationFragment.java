package com.dangthuy.trolybabau.ui.information.chart;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.ContextCompat;

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
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

/**
 * Created by nhongthai on 3/29/2021.
 */
public class ChartInformationFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "ChartMomWeightFragment";
    private FragmentChartBinding binding;

    public static ChartInformationFragment newInstance(int type) {
        ChartInformationFragment fragment = new ChartInformationFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, type);
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
            viewModel.setType(getArguments().getInt(TAG));
        }
        viewModel.getLiveChart().observe(this, chart -> {
            setLayoutView(chart.getDates());
            setData(365, chart.getLowValues());
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
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setGridColor(getContext().getResources().getColor(R.color.blue));
        x.setAxisLineColor(Color.WHITE);

        x.setValueFormatter(new MyValueFormater(dates));

        YAxis y = binding.chart.getAxisLeft();
//        y.setTypeface(tfLight);
        y.setLabelCount(5, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
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
        if (viewModel.getmType() == InfomartionViewModel.TYPE_BABY) {
            new Handler().postDelayed(() -> viewModel.fetchBabyFootDb(), 1000);
        } else if (viewModel.getmType() == InfomartionViewModel.TYPE_MOM) {
            new Handler().postDelayed(() -> viewModel.fetchMomWeightDb(), 1000);
        }
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

            set1.setDrawIcons(false);

            // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(1f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return binding.chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }
            set1.setFillColor(getContext().getColor(R.color.white));

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            binding.chart.setData(data);
        }
    }
}
