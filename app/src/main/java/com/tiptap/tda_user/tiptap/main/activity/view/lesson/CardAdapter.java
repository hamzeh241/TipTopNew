package com.tiptap.tda_user.tiptap.main.activity.view.lesson;

import android.support.v7.widget.CardView;

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 8;
    float getBaseElevation();
    CardView getCardViewAt(int position);
    int getCount();
}