package com.dangthuy.trolybabau.data.repository;

import android.content.Context;

import com.dangthuy.trolybabau.data.response.KnowledgeResponse;
import com.dangthuy.trolybabau.data.response.NutriResponse;

/**
 * Created by nhongthai on 4/7/2021.
 */
public class KnowledgeRepository extends BaseRepository {
    public KnowledgeRepository(Context mContext) {
        super(mContext);
    }

    public interface LoadNutriListener {
        void onLoadNutriFinished(NutriResponse response);
    }

    public interface LoadKnowledgeListener {
        void onLoadKnowledgeFinished(KnowledgeResponse response);
    }

    public void loadNutrition(int raw, LoadNutriListener listener) {
        loadDataFromRaw(NutriResponse.class, raw, listener::onLoadNutriFinished);
    }

    public void loadKnowledgeListener(int raw, LoadKnowledgeListener listener) {
        loadDataFromRaw(KnowledgeResponse.class, raw, listener::onLoadKnowledgeFinished);
    }
}
