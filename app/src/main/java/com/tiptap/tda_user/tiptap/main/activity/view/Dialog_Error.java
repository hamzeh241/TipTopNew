package com.tiptap.tda_user.tiptap.main.activity.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.tiptap.tda_user.tiptap.R;

public class Dialog_Error extends Dialog implements View.OnClickListener {

    Activity c;
    Button btn;

    public Dialog_Error(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.err);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn:
                dismiss();
                c.finish();
                c.startActivity(c.getIntent());
                break;
        }
    }
}