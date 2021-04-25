package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.NutriResponse;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class RecipeRepository extends BaseRepository{
    public RecipeRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadNutriListener {
        void onLoadNutriFinished(NutriResponse response);
    }

    public void loadNutrition(int raw, KnowledgeRepository.LoadNutriListener listener) {
        loadDataFromRaw(NutriResponse.class, raw, listener::onLoadNutriFinished);
    }
}
