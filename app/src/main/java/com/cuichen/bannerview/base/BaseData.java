package com.cuichen.bannerview.base;


import com.cuichen.bannerview.R;

import java.util.Arrays;
import java.util.List;

public class BaseData {
   static List<Integer> imgs =  Arrays.asList(
    R.mipmap.advertise0,
    R.mipmap.advertise1,
    R.mipmap.advertise2,
    R.mipmap.advertise3,
    R.mipmap.advertise4,
    R.mipmap.image1,
    R.mipmap.image2,
    R.mipmap.image4,
    R.mipmap.image5,
    R.mipmap.image7,
    R.mipmap.image9
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
    public static List<Integer> getImgs(){
        return imgs;
    }

    public static List<Integer> getColors(){
        return cList;
    }
}
