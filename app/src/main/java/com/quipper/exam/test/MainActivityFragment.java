package com.quipper.exam.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.TimeZone;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public interface Callback {
        void load();
        ImageLoader getImageLoader();
    }

    private static final SimpleDateFormat IMAGE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHH", Locale.US);
    private static final SimpleDateFormat LABEL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:00", Locale.US);
    Date dateToShow = new Date(new Date().getTime() - 30 * 60 * 1000);
    static {
        TimeZone jst = TimeZone.getTimeZone("GMT+09:00");
        IMAGE_TIME_FORMAT.setTimeZone(jst);
    }

    private Button loadButton;
    private ImageView earthImage;
    private TextView dateText;
    private Callback callback;
    private DisplayImageOptions options;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        loadButton = (Button) view.findViewById(R.id.load_button);
//        earthImage = (ImageView) view.findViewById(R.id.earth_image);
        dateText = (TextView) view.findViewById(R.id.date_text);
        setDateLabel(LABEL_FORMAT.format(dateToShow));
//        loadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        if (callback != null) {
//            callback.load();
//        }
//            }
//        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callback = (Callback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        callback = null;
        earthImage = null;
        dateText = null;
        loadButton = null;
    }

//    public void showImage(Bitmap bitmap) {
//        earthImage.setImageBitmap(bitmap);
//    }

    public void setDateLabel(CharSequence dateLabel) {
        dateText.setText(dateLabel);
    }
}
