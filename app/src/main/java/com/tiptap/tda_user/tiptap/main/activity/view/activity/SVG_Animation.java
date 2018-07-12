package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tiptap.tda_user.tiptap.R;

public class SVG_Animation extends Fragment {

    public static ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.svg_animation, container, false);
        img = (ImageView) view.findViewById(R.id.img);
        return view;
    }
}