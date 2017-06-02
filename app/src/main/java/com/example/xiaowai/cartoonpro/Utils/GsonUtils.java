package com.example.xiaowai.cartoonpro.Utils;

import com.google.gson.Gson;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class GsonUtils {
    private static Gson gson;
    static{
        if(gson==null){
            gson=new Gson();
        }
    }
    private GsonUtils(){

    }
    public static <T> T analysisGson(String json,Class<T> tclass){
        T t=null;
        if(json!=null){
            t = gson.fromJson(json,tclass);
        }
        return t;
    }
}
