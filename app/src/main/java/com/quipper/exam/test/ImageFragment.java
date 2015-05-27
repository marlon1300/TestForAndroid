package com.quipper.exam.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marlon1300 on 5/28/2015.
 */
public class ImageFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_pager_container, container, false);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.view_pager);
        pager.setAdapter(new ImageAdapter(getActivity()));
        pager.setCurrentItem(0);
        return rootView;
    }
}
