package com.quipper.exam.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by marlon1300 on 5/28/2015.
 */
public class ImageAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private DisplayImageOptions options;
    private String[] images = new String[]{};
    MainActivityFragment.Callback callback;
    private static final SimpleDateFormat IMAGE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHH", Locale.US);

    ImageAdapter(Context context) {
        Date dateToShow = new Date(new Date().getTime() - 30 * 60 * 1000);
        String url = String.format("http://www.jma.go.jp/jp/gms/imgs/5/infrared/1/%s00-00.png",
                IMAGE_TIME_FORMAT.format(dateToShow));
        images = new String[]{url};
        inflater = LayoutInflater.from(context);
        callback = (MainActivityFragment.Callback) context;
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(android.R.drawable.stat_notify_error)
                .showImageOnFail(android.R.drawable.stat_notify_error)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.view_pager_item, view, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.view_pager_item_image);
        final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
        ImageLoader.getInstance().displayImage(images[position], imageView, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toast.makeText(view.getContext(), failReason.getCause().getMessage(), Toast.LENGTH_SHORT).show();
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spinner.setVisibility(View.GONE);
            }
        });
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
