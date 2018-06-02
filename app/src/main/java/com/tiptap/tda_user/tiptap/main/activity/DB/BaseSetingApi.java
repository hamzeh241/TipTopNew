package com.tiptap.tda_user.tiptap.main.activity.DB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A15;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A18;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A19;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A22;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A24;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A25;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A26;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A27;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A28;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A29;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A3;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A30;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A31;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A33;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A34;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A35;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A37;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A38;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A39;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A4;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A40;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A41;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A42;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A43;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A44;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A5;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A6;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A8;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A9;

public class BaseSetingApi {

    public String url = "http://tiptop.tdaapp.ir/api/";
    private int socketTimeout = 30000;
    protected RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    private static ProgressDialog pDialog;
    private Context context;

    private Activity _activity;
    public BaseSetingApi(Context _context,Activity activity) {
        context=_context;
        _activity=activity;
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("درحال دریافت اطلاعات از سرور");
        pDialog.setCancelable(false);
    }
    public BaseSetingApi(Context _context) {
        context=_context;

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("درحال دریافت اطلاعات از سرور");
        pDialog.setCancelable(false);
    }
    public void go_activity(View view, int id_at, int id_l,int func, Activity mactivity){

        switch (id_at){

            case 1: break;
            case 2: break;

            case 3:
                A3.idlesson = id_l;
                A3.idfunction = func ;
                A3.activitynumber = 1;
                A3.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A3.class));
                mactivity.finish();
                break;

            case 4:
                A4.idlesson = id_l;
                A4.idfunction = func;
                A4.activitynumber = 1;
                A4.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A4.class));
                mactivity.finish();
                break;

            case 5:
                A5.idlesson = id_l;
                A5.idfunction = func;
                A5.activitynumber = 1;
                A5.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A5.class));
                mactivity.finish();
                break;

            case 6:
                A6.idlesson = id_l;
                A6.idfunction = func;
                A6.activitynumber = 1;
                A6.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A6.class));
                mactivity.finish();
                break;

            case 7:
                A7.idlesson = id_l;
                A7.idfunction = func;
                A7.activitynumber = 1;
                A7.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A7.class));
                mactivity.finish();
                break;

            case 8:
                A8.idlesson = id_l;
                A8.idfunction = func;
                A8.activitynumber = 1;
                A8.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A8.class));
                mactivity.finish();
                break;

            case 9:
                A9.idlesson = id_l;
                A9.idfunction = func;
                A9.activitynumber = 1;
                A9.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A9.class));
                mactivity.finish();
                break;

            case 10: break;
            case 11: break;
            case 12: break;
            case 13: break;
            case 14: break;

            case 15:
                A15.idlesson = id_l;
                A15.idfunction = func ;
                A15.activitynumber = 1;
                A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A15.class));
                mactivity.finish();
                break;

            case 16: break;
            case 17: break;

            case 18:
                A18.idlesson = id_l;
                A18.idfunction = func;
                A18.activitynumber = 1;
                A18.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A18.class));
                mactivity.finish();
                break;

            case 19:
                A19.idlesson = id_l;
                A19.idfunction = func;
                A19.activitynumber = 1;
                A19.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A19.class));
                mactivity.finish();
                break;

            case 20:
                A20.idlesson = id_l;
                A20.idfunction = func ;
                A20.activitynumber = 1;
                A20.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A20.class));
                mactivity.finish();
                break;

            case 21: break;

            case 22:
                A22.idlesson = id_l;
                A22.idfunction = func;
                A22.activitynumber = 1;
                A22.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A22.class));
                mactivity.finish();
                break;

            case 23: break;

            case 24:
                A24.idlesson = id_l;
                A24.idfunction = func;
                A24.activitynumber = 1;
                A24.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A24.class));
                mactivity.finish();
                break;

            case 25:
                //A25.idlesson = id_l;
                //A25.idfunction = func ;
                //A25.activitynumber = 1;
                //A25.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A25.class));
                break;

            case 26:
                A26.idlesson = id_l;
                A26.idfunction = func;
                A26.activitynumber = 1;
                A26.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A26.class));
                mactivity.finish();
                break;

            case 27:
                A27.idlesson = id_l;
                A27.idfunction = func ;
                A27.activitynumber = 1;
                A27.Act_Status = "first";

                view.getContext().startActivity(new Intent(view.getContext(), A27.class));
                mactivity.finish();
                break;

            case 28:
                A28.idlesson = id_l;
                A28.idfunction = func;
                A28.activitynumber = 1;
                A28.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A28.class));
                mactivity.finish();
                break;

            case 29:
                A29.idlesson = id_l;
                A29.idfunction = func;
                A29.activitynumber = 1;
                A29.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A29.class));
                mactivity.finish();
                break;

            case 30:
                A30.idlesson = id_l;
                A30.idfunction = func;
                A30.activitynumber = 1;
                A30.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A30.class));
                mactivity.finish();
                break;

            case 31:
                //A31.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A31.activitynumber = 1;
                //A31.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A31.class));
                mactivity.finish();
                break;

            case 32:
                A32.idlesson = id_l;
                A32.idfunction = func;
                A32.activitynumber = 1;
                A32.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A32.class));
                mactivity.finish();
                break;

            case 33:
                A33.idlesson = id_l;
                A33.idfunction = func;
                A33.activitynumber = 1;
                A33.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A33.class));
                mactivity.finish();
                break;

            case 34:
                A34.idlesson = id_l;
                A34.idfunction = func;
                A34.activitynumber = 1;
                A34.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A34.class));
                mactivity.finish();
                break;

            case 35:
                A35.idlesson = id_l;
                A35.idfunction = func;
                A35.activitynumber = 1;
                A35.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A35.class));
                mactivity.finish();
                break;

            case 36: break;

            case 37:
                A37.idlesson = id_l;
                A37.idfunction = func ;
                A37.activitynumber = 1;
                A37.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A37.class));
                mactivity.finish();
                break;

            case 38:
                //A38.idlesson = id_l;
                //A38.idfunction = idfunction ;
                //A38.activitynumber = 1;
                //A38.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A38.class));
                mactivity.finish();
                break;

            case 39:
                A39.idlesson = id_l;
                A39.idfunction = func;
                A39.activitynumber = 1;
                A39.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A39.class));
                mactivity.finish();
                break;

            case 40:
                //A40.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A40.activitynumber = 1;
                //    A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A40.class));
                mactivity.finish();
                break;

            case 41:
                //A41.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A41.activitynumber = 1;
                //    A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A41.class));
                mactivity.finish();
                break;

            case 42:
                //A42.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A42.activitynumber = 1;
                //    A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A42.class));
                mactivity.finish();
                break;

            case 43:
                //A43.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A43.activitynumber = 1;
                //     A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A43.class));
                mactivity.finish();
                break;

            case 44:
                //A44.idlesson = id_l;
                //A.idfunction = idfunction ;
                //A44.activitynumber = 1;
                //    A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A44.class));
                mactivity.finish();
                break;
        }
    }
        public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
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