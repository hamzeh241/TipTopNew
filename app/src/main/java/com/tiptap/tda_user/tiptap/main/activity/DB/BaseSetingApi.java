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
import com.tiptap.tda_user.tiptap.main.activity.view.activity.*;

public class BaseSetingApi {

    public String url = "http://tiptop.tdaapp.ir/api/";
    private int socketTimeout = 30000;
    protected RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    private static ProgressDialog pDialog;
    private Context context;
    private Activity _activity;

    public BaseSetingApi() {}

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

            case 1:
                A1.idlesson = id_l;
                A1.idfunction = func ;
                A1.activitynumber = 1;
                A1.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A1.class));
                mactivity.finish();
                break;

            case 2:
                A2.idlesson = id_l;
                A2.idfunction = func ;
                A2.activitynumber = 1;
                A2.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A2.class));
                mactivity.finish();
                break;

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

            case 10:
                A10.idlesson = id_l;
                A10.idfunction = func ;
                A10.activitynumber = 1;
                A10.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A10.class));
                mactivity.finish();
                break;

            case 11:
                A11.idlesson = id_l;
                A11.idfunction = func ;
                A11.activitynumber = 1;
                A11.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A11.class));
                mactivity.finish();
                break;

            case 12:
                A12.idlesson = id_l;
                A12.idfunction = func ;
                A12.activitynumber = 1;
                A12.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A12.class));
                mactivity.finish();
                break;

            case 13:
                A13.idlesson = id_l;
                A13.idfunction = func ;
                A13.activitynumber = 1;
                A13.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A13.class));
                mactivity.finish();
                break;

            case 14:
                A14.idlesson = id_l;
                A14.idfunction = func ;
                A14.activitynumber = 1;
                A14.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A14.class));
                mactivity.finish();
                break;

            case 15:
                A15.idlesson = id_l;
                A15.idfunction = func ;
                A15.activitynumber = 1;
                A15.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A15.class));
                mactivity.finish();
                break;

            case 16:
                A16.idlesson = id_l;
                A16.idfunction = func ;
                A16.activitynumber = 1;
                A16.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A16.class));
                mactivity.finish();
                break;

            case 17:
                A17.idlesson = id_l;
                A17.idfunction = func ;
                A17.activitynumber = 1;
                A17.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A17.class));
                mactivity.finish();
                break;

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

            case 21:
                A21.idlesson = id_l;
                A21.idfunction = func ;
                A21.activitynumber = 1;
                A21.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A21.class));
                mactivity.finish();
                break;

            case 22:
                A22.idlesson = id_l;
                A22.idfunction = func;
                A22.activitynumber = 1;
                A22.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A22.class));
                mactivity.finish();
                break;

            case 23:
                /*A23.idlesson = id_l;
                A23.idfunction = func ;
                A23.activitynumber = 1;
                A23.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A23.class));
                mactivity.finish();
                break;*/

            case 24:
                A24.idlesson = id_l;
                A24.idfunction = func;
                A24.activitynumber = 1;
                A24.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A24.class));
                mactivity.finish();
                break;

            case 25:
                A25.idlesson = id_l;
                A25.idfunction = func ;
                A25.activitynumber = 1;
                A25.Act_Status = "first";
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
                A31.idlesson = id_l;
                A31.idfunction = func ;
                A31.activitynumber = 1;
                A31.Act_Status = "first";
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

            case 36:
                /*A36.idlesson = id_l;
                A36.idfunction = func ;
                A36.activitynumber = 1;
                A36.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A36.class));
                mactivity.finish();
                break;*/

            case 37:
                A37.idlesson = id_l;
                A37.idfunction = func ;
                A37.activitynumber = 1;
                A37.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A37.class));
                mactivity.finish();
                break;

            case 38:
                A38.idlesson = id_l;
                A38.idfunction = func ;
                A38.activitynumber = 1;
                A38.Act_Status = "first";
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
                A40.idlesson = id_l;
                A40.idfunction = func;
                A40.activitynumber = 1;
                A40.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A40.class));
                mactivity.finish();
                break;

            case 41:
                A41.idlesson = id_l;
                A41.idfunction = func ;
                A41.activitynumber = 1;
                A41.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A41.class));
                mactivity.finish();
                break;

            case 42:
                A42.idlesson = id_l;
                A42.idfunction = func ;
                A42.activitynumber = 1;
                A42.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A42.class));
                mactivity.finish();
                break;

            case 43:
                A43.idlesson = id_l;
                A43.idfunction = func ;
                A43.activitynumber = 1;
                A43.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A43.class));
                mactivity.finish();
                break;

            case 44:
                A44.idlesson = id_l;
                A44.idfunction = func;
                A44.activitynumber = 1;
                A44.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A44.class));
                mactivity.finish();
                break;

            case 45:
                A45.idlesson = id_l;
                A45.idfunction = func;
                A45.activitynumber = 1;
                A45.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A45.class));
                mactivity.finish();
                break;

            case 46:
                A46.idlesson = id_l;
                A46.idfunction = func ;
                A46.activitynumber = 1;
                A46.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A46.class));
                mactivity.finish();
                break;

            case 47:
                A47.idlesson = id_l;
                A47.idfunction = func ;
                A47.activitynumber = 1;
                A47.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A47.class));
                mactivity.finish();
                break;

            case 48:
                A48.idlesson = id_l;
                A48.idfunction = func ;
                A48.activitynumber = 1;
                A48.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A48.class));
                mactivity.finish();
                break;

            case 49:
                A49.idlesson = id_l;
                A49.idfunction = func ;
                A49.activitynumber = 1;
                A49.Act_Status = "first";
                view.getContext().startActivity(new Intent(view.getContext(), A49.class));
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

    public static void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}