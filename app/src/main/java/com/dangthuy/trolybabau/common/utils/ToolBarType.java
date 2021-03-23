package com.dangthuy.trolybabau.common.utils;

/**
 * Created by nhongthai on 21/03/2021.
 */
public enum ToolBarType {
    SETUP(0, "Thông tin dự sinh"),
    EXPECT(1, "Thông tin dự sinh"),
    BABY_INFO(2, "Thông tin thai nhi"),
    BABY_INFO_TAB(3, "Thông tin thai nhi"),
    DEFAULT(4, "");

    private int type;
    private String name;

    ToolBarType(int i, String s) {
        this.type = i;
        this.name = s;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ToolBarType{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
