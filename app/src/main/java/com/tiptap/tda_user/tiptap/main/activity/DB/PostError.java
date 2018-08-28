package com.tiptap.tda_user.tiptap.main.activity.DB;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import org.json.JSONObject;

public class PostError {

    public String url = "http://shahrvand.tdaapp.ir/api/";
    String MsgError;
    String FunName;
    Context context;

    public PostError(Context _context,String _MsgError,String _FunName) {
        context=_context;
        MsgError=_MsgError;
        FunName=_FunName;
    }

    public String postError() {

        PackageInfo pinfo = null;
        try {
            pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = pinfo.versionName;
        url+="error?"+"Name="+FunName+"&MeesageError="+MsgError+"&AndroidVersion="+ Build.VERSION.RELEASE+"&AppVersion="+versionName+"&AppId=3";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context,"__ok",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TipTop", "Error: " + error.getMessage());
               // new PostError(context,error.getMessage(),"ApiGetInfoApp+1").postError();
            }
        });
        SampleApp.getInstance().addToRequestQueue(jsonObjReq);
        return "0";
    }
}