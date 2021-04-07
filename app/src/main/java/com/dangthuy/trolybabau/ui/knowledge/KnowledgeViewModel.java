package com.dangthuy.trolybabau.ui.knowledge;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.data.model.Knowledge;
import com.dangthuy.trolybabau.data.model.Nutri;
import com.dangthuy.trolybabau.data.repository.KnowledgeRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class KnowledgeViewModel extends BaseViewModel {
    //Knowledge
    public static final String TRUOC_THAI_KY = "Trước thai kỳ";
    public static final String TRONG_THAI_KY = "Trong thai kỳ";
    public static final String CHUYEN_DA_VA_DA_SINH = "Chuyển dạ và đã sinh";
    public static final String SAU_SINH = "Sau sinh";
    public static final String CHIA_SE_KINH_NGHIEM = "Chia sẻ kinh nghiệm";
    public static final String DINH_DUONG = "Dinh dưỡng";

    private final MutableLiveData<List<HomeMenu>> menus = new MutableLiveData<>();
    private final MutableLiveData<List<Knowledge>> knowledges = new MutableLiveData<>();
    private final MutableLiveData<List<Nutri>> nutries = new MutableLiveData<>();

    private String mMenu;
    private int mNutri;
    private KnowledgeRepository.LoadNutriListener nutriListener = response -> {
        if (response != null) {
            if(response.getFruits() != null) {
                nutries.postValue(response.getFruits());
            }
        }
    };

    public KnowledgeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(TRUOC_THAI_KY, AppCompatResources.getDrawable(mContext, R.drawable.before_pregnancy)));
        list.add(new HomeMenu(TRONG_THAI_KY, AppCompatResources.getDrawable(mContext, R.drawable.pregnancy_week)));
        list.add(new HomeMenu(CHUYEN_DA_VA_DA_SINH, AppCompatResources.getDrawable(mContext, R.drawable.laborandbirth)));
        list.add(new HomeMenu(SAU_SINH, AppCompatResources.getDrawable(mContext, R.drawable.after_pregnancy)));
        list.add(new HomeMenu(CHIA_SE_KINH_NGHIEM, AppCompatResources.getDrawable(mContext, R.drawable.doctor_list)));
        list.add(new HomeMenu(DINH_DUONG, AppCompatResources.getDrawable(mContext, R.drawable.nutritionbar)));

        menus.postValue(list);
    }

    public MutableLiveData<List<HomeMenu>> getMenus() {
        return menus;
    }

    public void setMenu(String title) {
        this.mMenu = title;
    }

    public String getmMenu() {
        return mMenu;
    }

    public void fetchDataDetail() {
        ArrayList<Knowledge> list;
        switch (mMenu) {
            case TRUOC_THAI_KY:
                list = new ArrayList<>();
                list.add(new Knowledge());
                list.add(new Knowledge());
                list.add(new Knowledge());
                knowledges.postValue(list);
                break;
        }
    }

    public MutableLiveData<List<Knowledge>> getKnowledges() {
        return knowledges;
    }

    public void setNutri(int position) {
        this.mNutri = position;
    }

    public MutableLiveData<List<Nutri>> getNutries() {
        return nutries;
    }

    public void fetchDataNutri() {
        KnowledgeRepository repository = new KnowledgeRepository(mContext);
        ArrayList<Nutri> list;
        switch (mNutri) {
            case 0: //Food

                break;
            case 1: //Fruit
                repository.loadFruit(R.raw.fruit, nutriListener);
                break;
            case 2: //Vitamin

                break;
        }
    }
}
