package com.dangthuy.trolybabau.ui.recipe;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.databinding.FragmentCommonBinding;
import com.dangthuy.trolybabau.ui.base.BaseFragment;
import com.dangthuy.trolybabau.ui.information.InfomationFragment;
import com.dangthuy.trolybabau.ui.recipe.adapter.RecipeAdapter;
import com.dangthuy.trolybabau.ui.recipe.detail.DetailRecipeFragment;

import java.util.ArrayList;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class RecipeFragment extends BaseFragment<RecipeViewModel> {
    public static final String TAG = "RecipeFragment";
    private FragmentCommonBinding binding;
    private RecipeAdapter mRecipeAdapter;

    public static RecipeFragment newInstance() {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Class<RecipeViewModel> provideViewModelClass() {
        return RecipeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        binding = (FragmentCommonBinding) getBinding();
        if (getArguments() != null) {
            setLayoutView();
        }
        initAdapter();
        viewModel.getNutries().observe(this, nutris -> {
            loadingDialog.dismiss();
            mRecipeAdapter.setNewData(nutris);
        });
    }

    private void setLayoutView() {
        binding.toolBar.setLayoutView(ToolBarType.DEFAULT);
        binding.toolBar.setTitle(getString(R.string.tv_mon_an_ngon));
        binding.search.setVisibility(View.VISIBLE);
        binding.divider1.setVisibility(View.VISIBLE);
    }

    private void initAdapter() {
        mRecipeAdapter = new RecipeAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void setOnClickListener() {
        binding.toolBar.setListener(item -> getParentFragmentManager().popBackStack());
        mRecipeAdapter.setListener(item -> addFragment(R.id.container, DetailRecipeFragment.newInstance(item), DetailRecipeFragment.TAG, false));
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    viewModel.findByName(newText);
                }
                return true;
            }
        });
    }

    @Override
    protected void onRefreshData() {
        loadingDialog.show();
        new Handler().postDelayed(() -> viewModel.fetchData(), 300);
    }
}
