package com.koma.weather.utils.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by koma on 7/4/16.
 */
public class ImageLoader {
    public static void load(Context context, @DrawableRes int imageRes, ImageView view) {
        Glide.with(context).load(imageRes).crossFade().into(view);
    }


    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
