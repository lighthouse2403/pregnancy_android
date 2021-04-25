package com.dangthuy.trolybabau.ui.recipe.detail;

import android.os.Bundle;
import android.view.View;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.FragmentDetailCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.recipe.RecipeFragment;
import com.dangthuy.trolybabau.ui.recipe.RecipeViewModel;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class DetailRecipeFragment extends BaseFragment<RecipeViewModel> {
    public static final String TAG = "DetailRecipeFragment";
    private FragmentDetailCommonBinding binding;

    public static DetailRecipeFragment newInstance(Nutri item) {
        DetailRecipeFragment fragment = new DetailRecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, item);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Class<RecipeViewModel> provideViewModelClass() {
        return RecipeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentDetailCommonBinding) getBinding();
        if (getArguments() != null) {
            if (getArguments().getParcelable(TAG) != null) {
                viewModel.setItemRecipe(getArguments().getParcelable(TAG));
            }
            setLayoutView();
        }
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(viewModel.getmNutri().getName());
        binding.tvContent.setText(viewModel.getmNutri().getFullDescription());
        binding.tvContent.setVisibility(View.GONE);
        binding.webView.setVisibility(View.VISIBLE);
        binding.webView.setBackgroundColor(getContext().getColor(R.color.transparent_background));
        binding.webView.loadDataWithBaseURL(null, viewModel.getmNutri().getFullDescription(), "text/html; charset=UTF-8", "utf-8", null);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
    }

    @Override
    protected void onRefreshData() {

    }
}
