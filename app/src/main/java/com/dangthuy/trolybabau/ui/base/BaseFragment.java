package com.dangthuy.trolybabau.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.dialog.LoadingDialog;

/**
 * Created by nhongthai on 8/3/2020.
 */
public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    private static final String TAG = "BaseFragment";

    protected Context context;
    protected T viewModel;
    protected ViewDataBinding binding;
    protected LoadingDialog loadingDialog;
    protected IOnBackPressed listener;

    public void setListener(IOnBackPressed listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(provideViewModelClass());
        viewModel.getLiveData().observe(this, isUpdate -> {
            if (isUpdate){
                Log.d(TAG, "isUpdate " + isUpdate);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        loadingDialog = new LoadingDialog(requireActivity());
        initView();
        setOnClickListener();
        onRefreshData();
        return view;
    }

    @Override
    public void onDestroyView() {
        if (listener != null) {
            listener.onClick();
        }
        super.onDestroyView();
    }

    protected abstract Class<T> provideViewModelClass();

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView();

    protected abstract void setOnClickListener();

    protected abstract void onRefreshData();

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void addFragment(int layoutId, Fragment fragment, String tag, boolean isReplace){
        if (getActivity() != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right
                    );
            if(!isReplace) {
                fragmentTransaction.add(layoutId, fragment)
                        .addToBackStack(tag)
                        .commit();
            } else {
                fragmentTransaction.replace(layoutId, fragment)
                        .addToBackStack(tag)
                        .commit();
            }
        }
    }

    public void showToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}
