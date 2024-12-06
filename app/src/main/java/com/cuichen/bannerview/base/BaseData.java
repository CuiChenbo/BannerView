package com.cuichen.bannerview.base;


import com.cuichen.bannerview.R;
import com.cuichen.bannerview.adapter.RvAdapter;

import java.util.Arrays;
import java.util.List;

public class BaseData {
    static List<Integer> imgs = Arrays.asList(
            R.mipmap.banner13,
            R.mipmap.banner14,
            R.mipmap.banner5,
            R.mipmap.banner7,
            R.mipmap.banner9,
            R.mipmap.banner12,
            R.mipmap.banner8
    );

    static List<Integer> cList = Arrays.asList(
            R.color.teal_700,
            R.color.c_f90,
            R.color.teal_200,
            R.color.purple_700,
            R.color.purple_500,
            R.color.c_red,
            R.color.teal_700,
            R.color.purple_200
    );

    public static List<Integer> getImgs() {
        return imgs;
    }

    public static List<Integer> getImgs_size2() {
        return imgs.subList(0,2);
    }

    public static List<Integer> getColors() {
        return cList;
    }


}
