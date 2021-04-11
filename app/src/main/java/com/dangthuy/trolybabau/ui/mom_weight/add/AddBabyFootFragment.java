package com.dangthuy.trolybabau.ui.mom_weight.add;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentBabyFootAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.mom_weight.InfomartionViewModel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nhongthai on 3/31/2021.
 */
public class AddBabyFootFragment extends BaseFragment<InfomartionViewModel> {
    public static final String TAG = "AddBabyFootFragment";
    private FragmentBabyFootAddBinding binding;
    private boolean mIsPlay = false;
    private Timer mTimer;
    private TimerTask mTimerTask;

    public interface IAddBabyFootListener {
        void onSaved();
    }

    private IAddBabyFootListener addBabyFootListener;

    public void setAddBabyFootListener(IAddBabyFootListener listener) {
        this.addBabyFootListener = listener;
    }

    public static AddBabyFootFragment newInstance() {
        AddBabyFootFragment fragment = new AddBabyFootFragment();
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
        return R.layout.fragment_baby_foot_add;
    }

    @Override
    protected void initView() {
        binding = (FragmentBabyFootAddBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(DateUtils.formatDate(new Date()));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        binding.ivPause.setOnClickListener(view -> doTimer(!mIsPlay));
        binding.btnCount.setOnClickListener(view -> {
            if (mIsPlay) {
                viewModel.setCount(viewModel.getCount() + 1);
                binding.tvNumber.setText(String.valueOf(viewModel.getCount()));
            } else {
                showToast(getString(R.string.tv_warning_begin_record));
            }
        });
        binding.btnSave.setOnClickListener(view -> {
            if (!mIsPlay && viewModel.getCount() != 0 && (viewModel.getmHour() != 0 || viewModel.getmMin() != 0 || viewModel.getmSecond() != 0)) {
                viewModel.saveToDb();
                addBabyFootListener.onSaved();
                getParentFragmentManager().popBackStack();
            } else {
                if (mIsPlay) {
                    showToast(getString(R.string.tv_warning_finish_record));
                } else {
                    showToast(getString(R.string.tv_warning_no_have_any_foot));
                }
            }
        });
    }

    private void doTimer(boolean isPlay) {
        if (isPlay) {
            viewModel.setmHour(0);
            viewModel.setmMin(0);
            viewModel.setmSecond(0);
            binding.tvTime.setText("00:00:00");
            viewModel.setCount(0);
            Handler handler = new Handler();
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(() -> {
                        viewModel.setmSecond(viewModel.getmSecond() + 1);
                        if (viewModel.getmSecond() % 60 == 0) {
                            viewModel.setmSecond(0);
                            viewModel.setmMin(viewModel.getmMin() + 1);
                            if (viewModel.getmMin() % 60 == 0) {
                                viewModel.setmMin(0);
                                viewModel.setmHour(viewModel.getmHour() + 1);
                                if (viewModel.getmHour() % 24 == 0) {
                                    viewModel.setmHour(0);
                                }
                            }
                        }
                        String timeText = ((viewModel.getmHour() < 10) ? "0" + viewModel.getmHour() : "" + viewModel.getmHour()) + ":" + ((viewModel.getmMin() < 10) ? "0" + viewModel.getmMin() : "" + viewModel.getmMin()) + ":" + ((viewModel.getmSecond() < 10) ? "0" + viewModel.getmSecond() : "" + viewModel.getmSecond());
                        binding.tvTime.setText("");
                        binding.tvTime.setText(timeText);
                    });
                }
            };
            mTimer.schedule(mTimerTask, 0, 1000);
        } else {
            if (mTimer != null) {
                mTimer.cancel();
            }
            if (mTimerTask != null) {
                mTimerTask.cancel();
            }
        }
        mIsPlay = isPlay;
    }

    @Override
    protected void onRefreshData() {

    }
}
