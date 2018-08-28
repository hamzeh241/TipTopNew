package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tiptap.tda_user.tiptap.R;

public class Fragment_False extends Fragment {

    public static TextView txt_false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_false, container, false);
        txt_false = (TextView)view.findViewById(R.id.answer);
        return view;
    }
}