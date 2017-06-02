package com.example.xiaowai.cartoonpro.Utils;

import com.example.xiaowai.cartoonpro.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class ImageLoaderOptionsUtils {
    public static DisplayImageOptions imageOptions(int imageId){
        DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder().showImageOnLoading(imageId).showImageForEmptyUri(R.drawable.ic_launcher).build();
        return displayImageOptions;
    }
}
