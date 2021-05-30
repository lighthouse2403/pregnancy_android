package com.dangthuy.trolybabau.listener;

/**
 * Created by nhongthai on 5/31/2021.
 */
public interface IClickItemListener <T> {
    void onClick(T item, int position, boolean status);
}
