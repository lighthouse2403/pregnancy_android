package com.dangthuy.trolybabau.ui.recipe;

import android.app.Application;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.data.repository.KnowledgeRepository;
import com.dangthuy.trolybabau.data.repository.RecipeRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class RecipeViewModel extends BaseViewModel {
    private final MutableLiveData<List<Nutri>> nutries = new MutableLiveData<>();
    private List<Nutri> mNutriList;
    private boolean isSearch;
    private final RecipeRepository.LoadNutriListener recipeListener = response -> {
        if (response != null && response.getFood() != null) {
            nutries.postValue(response.getFood());
            if (!isSearch) {
                mNutriList = response.getFood();
            }
        }
    };
    private Nutri mNutri;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        this.isSearch = false;
        new RecipeRepository(mContext).loadNutrition(R.raw.food, recipeListener);
    }

    public MutableLiveData<List<Nutri>> getNutries() {
        return nutries;
    }

    public void setItemRecipe(Nutri nutri) {
        this.mNutri = nutri;
    }

    public Nutri getmNutri() {
        return mNutri;
    }

    public void findByName(String newText) {
        isSearch = true;
        List<Nutri> list = new ArrayList<>();
        for (Nutri nutri : mNutriList) {
            if (nutri.getName().toLowerCase().contains(newText)) {
                list.add(nutri);
            }
        }
        nutries.postValue(list);
    }
}
