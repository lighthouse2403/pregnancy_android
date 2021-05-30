package com.dangthuy.trolybabau.listener;

import java.util.List;

/**
 * Created by nhongthai on 5/31/2021.
 */
public interface ILoadListener <T>{
    void onLoaded(List<T> list);
}
