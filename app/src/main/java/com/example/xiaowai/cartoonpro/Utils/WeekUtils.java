package com.example.xiaowai.cartoonpro.Utils;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/23
 */

    import java.util.ArrayList;
    import java.util.Calendar;

    public class WeekUtils {
        public static ArrayList<String> getWeek(){
            String[] weeks = {"  周一  ", "  周二  ", "  周三  ", "  周四  ", "  周五  ", "  周六  ", "  周日  "};
            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_WEEK)-1;
            ArrayList<String> mDataList = new ArrayList<>();
            mDataList.clear();
            for (int i = 0; i < 5; i++) {
                if (today > 6) {
                    today = today - 7;
                }
                mDataList.add(weeks[today]);
                today++;
            }
            mDataList.add("  昨天  ");
            mDataList.add("  今天  ");
            return mDataList;
        }
    }


