package com.cuichen.banner_loop;

public class LoopUtils {
    public static int getRealPosition(int position, int pageSize) {
        return pageSize == 0 ? 0 : (position + pageSize) % pageSize;
    }
}
