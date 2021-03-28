package com.dangthuy.trolybabau.ui.settings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.Setting;
import com.dangthuy.trolybabau.data.model.Wallpaper;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 3/28/2021.
 */
public class SettingViewModel extends BaseViewModel {
    private final MutableLiveData<List<Setting>> settings = new MutableLiveData<>();
    private final MutableLiveData<List<Wallpaper>> wallpapers = new MutableLiveData<>();
    private List<Wallpaper> wallpaperList;

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        ArrayList<Setting> list = new ArrayList<>();
        list.add(new Setting(Setting.WALLPAPER, ""));
        list.add(new Setting(Setting.SHARE_APP, ""));
        list.add(new Setting(Setting.VIP, ""));
        settings.postValue(list);
    }

    public void fetchDataWallpaper() {
        wallpaperList = new ArrayList<>();
        wallpaperList.add(new Wallpaper(mContext.getResources().getColor(R.color.green), false));
        wallpaperList.add(new Wallpaper(mContext.getResources().getColor(R.color.pink), false));
        wallpaperList.add(new Wallpaper(mContext.getResources().getColor(R.color.yellow), false));
        int position = sharedPrefs.get(Constants.WALLPAPER, Integer.class);
        wallpaperList.get(position).setSelect(true);
        wallpapers.postValue(wallpaperList);
    }

    public MutableLiveData<List<Setting>> getSettings() {
        return settings;
    }

    public MutableLiveData<List<Wallpaper>> getWallpapers() {
        return wallpapers;
    }

    public void changeWallpaper(Wallpaper item, int pos) {
        sharedPrefs.put(Constants.WALLPAPER, pos);
        fetchDataWallpaper();
    }
}
