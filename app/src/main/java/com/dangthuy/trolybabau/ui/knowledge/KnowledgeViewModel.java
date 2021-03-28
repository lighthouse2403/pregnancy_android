package com.dangthuy.trolybabau.ui.knowledge;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.data.model.HomeMenu;
import com.dangthuy.trolybabau.data.model.Knowledge;
import com.dangthuy.trolybabau.data.model.Nutri;
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

    public KnowledgeViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<HomeMenu> list = new ArrayList<>();
        list.add(new HomeMenu(TRUOC_THAI_KY, ""));
        list.add(new HomeMenu(TRONG_THAI_KY, ""));
        list.add(new HomeMenu(CHUYEN_DA_VA_DA_SINH, ""));
        list.add(new HomeMenu(SAU_SINH, ""));
        list.add(new HomeMenu(CHIA_SE_KINH_NGHIEM, ""));
        list.add(new HomeMenu(DINH_DUONG, ""));

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
        ArrayList<Nutri> list;
        switch (mNutri) {
            case 0: //Food
                list = new ArrayList<>();
                list.add(new Nutri());
                list.add(new Nutri());
                list.add(new Nutri());
                nutries.postValue(list);
                break;
            case 1: //Fruit

                break;
            case 2: //Vitamin

                break;
        }
    }
}
