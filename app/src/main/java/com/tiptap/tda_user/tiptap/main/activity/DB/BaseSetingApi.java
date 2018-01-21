package com.tiptap.tda_user.tiptap.main.activity.DB;

import android.app.ProgressDialog;
import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;

public class BaseSetingApi {

    public String url = "http://tiptop.tdaapp.ir/api/";
    int socketTimeout = 30000;
    public RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    public static ProgressDialog pDialog;
    Context context;

    public BaseSetingApi(Context _context) {
        context=_context;
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("درحال دریافت اطلاعات از سرور");
        pDialog.setCancelable(false);
    }

    public BaseSetingApi() {}

    public static void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}