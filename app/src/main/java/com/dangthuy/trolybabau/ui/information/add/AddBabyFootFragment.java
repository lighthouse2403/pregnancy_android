package com.dangthuy.trolybabau.ui.information.add;

import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.ContextCompat;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.customview.ToolBar;
import com.dangthuy.trolybabau.common.utils.DateUtils;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.FragmentBabyFootAddBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.bottom_sheet.BottomSheetDateDialog;
import com.dangthuy.trolybabau.ui.information.InfomartionViewModel;

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
    private ToolBar.OnItemToolBarClickListener toolbarListener = item -> {
        switch (item) {
            case BACK:
                getParentFragmentManager().popBackStack();;
                break;
            case TITLE:
                BottomSheetDateDialog dialog = BottomSheetDateDialog.newInstance(true);
                dialog.setListener((year, month, day, hour, minute) -> {
                    viewModel.setDate(year, month, day, hour, minute);
                    binding.toolBar.setTitle(DateUtils.formatDate(viewModel.getmDate()));
                    dialog.dismiss();
                });
                dialog.show(getChildFragmentManager(), BottomSheetDateDialog.TAG);
                break;
        }
    };

    public interface IAddListener {
        void onSaved();
    }

    private IAddListener addListener;

    public void setAddListener(IAddListener listener) {
        this.addListener = listener;
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
        binding.toolBar.setLayoutView(ToolBarType.BACK_TITLE);
        viewModel.setDate(new Date());
        binding.toolBar.setTitle(DateUtils.formatDate(new Date()));
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(toolbarListener);
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
                addListener.onSaved();
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
            viewModel.setmHourFoot(0);
            viewModel.setmMinFoot(0);
            viewModel.setmSecondFoot(0);
            binding.tvTime.setText("00:00:00");
            viewModel.setCount(0);
            Handler handler = new Handler();
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(() -> {
                        viewModel.setmSecondFoot(viewModel.getmSecondFoot() + 1);
                        if (viewModel.getmSecondFoot() % 60 == 0) {
                            viewModel.setmSecondFoot(0);
                            viewModel.setmMinFoot(viewModel.getmMinFoot() + 1);
                            if (viewModel.getmMinFoot() % 60 == 0) {
                                viewModel.setmMinFoot(0);
                                viewModel.setmHourFoot(viewModel.getmHourFoot() + 1);
                                if (viewModel.getmHourFoot() % 24 == 0) {
                                    viewModel.setmHourFoot(0);
                                }
                            }
                        }
                        String timeText = ((viewModel.getmHourFoot() < 10) ? "0" + viewModel.getmHourFoot() : "" + viewModel.getmHourFoot()) + ":" + ((viewModel.getmMinFoot() < 10) ? "0" + viewModel.getmMinFoot() : "" + viewModel.getmMinFoot()) + ":" + ((viewModel.getmSecondFoot() < 10) ? "0" + viewModel.getmSecondFoot() : "" + viewModel.getmSecondFoot());
                        binding.tvTime.setText("");
                        binding.tvTime.setText(timeText);
                    });
                }
            };
            mTimer.schedule(mTimerTask, 0, 1000);
            binding.ivPause.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.pause));
        } else {
            if (mTimer != null) {
                mTimer.cancel();
            }
            if (mTimerTask != null) {
                mTimerTask.cancel();
            }
            binding.ivPause.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));
        }
        mIsPlay = isPlay;
    }

    @Override
    protected void onRefreshData() {

    }
}
