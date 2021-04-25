package com.dangthuy.trolybabau.ui.baby_name;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.BabyName;
import com.dangthuy.trolybabau.data.model.BabyNameDetail;
import com.dangthuy.trolybabau.data.repository.BabyNameRepository;
import com.dangthuy.trolybabau.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nhongthai on 4/24/2021.
 */
public class BabyNameViewModel extends BaseViewModel {
    public static final int NONE = 0;
    public static final int LOVED = 1;
    public static final int UNLOVED = -1;
    private final MutableLiveData<List<BabyName>> babyNames = new MutableLiveData<>();
    private final MutableLiveData<List<BabyNameDetail>> babyNameDetails = new MutableLiveData<>();
    private final MutableLiveData<BabyNameDetail> babyNameDetail = new MutableLiveData<>();
    private Map<String, List<BabyName>> babyNameMap;
    private List<BabyName> babyNameList;
    private String firstNameLoved, lastNameLoved;
    private final BabyNameRepository.LoadHomeListener babyNameListener = response -> {
        if (response != null && response.getBabyNames() != null) {
            babyNames.postValue(response.getBabyNames());
        }
    };
    private int mTab;
    private String character;
    private boolean isSpinner;
    private int mPosition;

    public BabyNameViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        mTab = 0;
        new BabyNameRepository(mContext).loadBabyName(babyNameListener, R.raw.babyname);
    }

    public MutableLiveData<List<BabyName>> getBabyNames() {
        return babyNames;
    }

    public void setBabyNames(List<BabyName> babyNames) {
        this.babyNameList = babyNames;
        babyNameMap = new HashMap<>();
        for (BabyName babyName : babyNameList) {
            List<BabyName> list;
            if (babyNameMap.get(babyName.getFirstCharacter()) == null) {
                list = new ArrayList<>();
            } else {
                list = babyNameMap.get(babyName.getFirstCharacter());
            }
            list.add(babyName);
            babyNameMap.put(babyName.getFirstCharacter(), list);
        }
    }

    public List<BabyName> getBabyNameList() {
        return babyNameList;
    }

    public void setTab(int tab) {
        this.mTab = tab;
    }

    public void fetchBabyName() {
        this.firstNameLoved = sharedPrefs.get(Constants.FIRST_NAME, String.class);
        this.lastNameLoved = sharedPrefs.get(Constants.LAST_NAME, String.class);
        if (character != null) {
            if (isSpinner) {
                findByFirstCharacter();
            } else {
                findByText();
            }
        }
    }

    private void findByFirstCharacter() {
        List<BabyNameDetail> listDetail = new ArrayList<>();
        if (babyNameMap != null && babyNameMap.get(character) != null) {
            for (BabyName babyName : babyNameMap.get(character)) {
                String[] split;
                if (mTab == 0) {
                    split = babyName.getFirstNameBoy().split(",");
                } else {
                    split = babyName.getFirstNameGirl().split(",");
                }
                for (String s : split) {
                    if (!s.isEmpty()) {
                        boolean isLoved = false;
                        if (firstNameLoved != null && !firstNameLoved.isEmpty() && lastNameLoved != null && !lastNameLoved.isEmpty()) {
                            String[] splitFirst = firstNameLoved.split(",");
                            String[] splitLast = lastNameLoved.split(",");
                            for (int i = 0; i < splitFirst.length; i++) {
                                if (splitFirst[i].equals(s) && splitLast[i].equals(babyName.getLastName())) {
                                    listDetail.add(new BabyNameDetail(babyName.getLastName(), s, LOVED));
                                    isLoved = true;
                                    break;
                                }
                            }
                        }
                        if (!isLoved) {
                            listDetail.add(new BabyNameDetail(babyName.getLastName(), s, UNLOVED));
                        }
                    }
                }
            }
            babyNameDetails.postValue(listDetail);
        }
    }

    public MutableLiveData<List<BabyNameDetail>> getBabyNameDetails() {
        return babyNameDetails;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void findByText() {
        List<BabyNameDetail> listDetail = new ArrayList<>();
        for (BabyName babyName : babyNameList) {
            String[] split;
            if (mTab == 0) {
                split = babyName.getFirstNameBoy().split(",");
            } else {
                split = babyName.getFirstNameBoy().split(",");
            }
            for (String s : split) {
                if (s.toLowerCase().contains(character) || babyName.getLastName().toLowerCase().contains(character)) {
                    if (firstNameLoved != null && !firstNameLoved.isEmpty() && lastNameLoved != null && !lastNameLoved.isEmpty()) {
                        String[] splitFirst = firstNameLoved.split(",");
                        String[] splitLast = lastNameLoved.split(",");
                        for (int i = 0; i < splitFirst.length; i++) {
                            if (splitFirst[i].equals(s) && splitLast[i].equals(babyName.getLastName())) {
                                listDetail.add(new BabyNameDetail(babyName.getLastName(), s, LOVED));
                            } else {
                                listDetail.add(new BabyNameDetail(babyName.getLastName(), s, UNLOVED));
                            }
                        }
                    } else {
                        listDetail.add(new BabyNameDetail(babyName.getLastName(), s, UNLOVED));
                    }
                }
            }
        }
        babyNameDetails.postValue(listDetail);
    }

    public void setIsSpinner(boolean isSpinner) {
        this.isSpinner = isSpinner;
    }

    public void fetchLoveBabyName() {
        this.firstNameLoved = sharedPrefs.get(Constants.FIRST_NAME, String.class);
        this.lastNameLoved = sharedPrefs.get(Constants.LAST_NAME, String.class);
        if (firstNameLoved != null && !firstNameLoved.isEmpty() && lastNameLoved != null && !lastNameLoved.isEmpty()) {
            List<BabyNameDetail> listDetail = new ArrayList<>();
            String[] splitFirst = firstNameLoved.split(",");
            String[] splitLast = lastNameLoved.split(",");
            for (int i = 0; i < splitFirst.length; i++) {
                listDetail.add(new BabyNameDetail(splitLast[i], splitFirst[i], NONE));
            }
            babyNameDetails.postValue(listDetail);
        }
    }

    public void saveItem(BabyNameDetail item, int position) {
        this.mPosition = position;
        this.firstNameLoved = sharedPrefs.get(Constants.FIRST_NAME, String.class);
        this.lastNameLoved = sharedPrefs.get(Constants.LAST_NAME, String.class);
        item.setStatus(item.getStatus() * -1);
        if (item.getStatus() == UNLOVED) {
            String[] splitFirst = firstNameLoved.split(",");
            String[] splitLast = lastNameLoved.split(",");
            firstNameLoved = "";
            lastNameLoved = "";
            for (int i = 0; i < splitFirst.length; i++) {
                if (splitFirst[i].equals(item.getFirstName()) && splitLast[i].equals(item.getLastName())) {
                    continue;
                }
                this.firstNameLoved += firstNameLoved.isEmpty() ? splitFirst[i] : "," + splitFirst[i];
                this.lastNameLoved += lastNameLoved.isEmpty() ? splitLast[i] : "," + splitLast[i];
            }
        } else {
            this.firstNameLoved += firstNameLoved.isEmpty() ? item.getFirstName() : "," + item.getFirstName();
            this.lastNameLoved += lastNameLoved.isEmpty() ? item.getLastName() : "," + item.getLastName();
        }
        sharedPrefs.put(Constants.FIRST_NAME, firstNameLoved);
        sharedPrefs.put(Constants.LAST_NAME, lastNameLoved);
        babyNameDetail.postValue(item);
    }

    public MutableLiveData<BabyNameDetail> getBabyNameDetail() {
        return babyNameDetail;
    }

    public int getmPosition() {
        return mPosition;
    }
}
