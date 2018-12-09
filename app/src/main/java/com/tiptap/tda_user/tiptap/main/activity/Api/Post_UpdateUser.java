package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.DB.Utility;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Post_UpdateUser extends BaseSetingApi {

    Context _context;
    Activity _activity;
    String _username;
    int _idlesson;
    boolean mnet;
    ProgressDialog progressDialog;

    public Post_UpdateUser(Context context, Activity activity, boolean net, String username, int idlesson) {
        _context = context;
        _activity = activity;
        mnet = net;
        _username = username;
        _idlesson = idlesson;
        progressDialog = new ProgressDialog(_activity);
    }

    public void post() throws JSONException {

        progressDialog.setMessage("لطفا صبر کنید ...");
        progressDialog.show();

        // create RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(_context);
        // create StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url+"User/UpdateUser", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    if(response.equals("true")) {
                        progressDialog.dismiss();
                        Toast.makeText(_context,"بروز رسانی با موفقیت انجام شد.",Toast.LENGTH_LONG).show();
                    }
                    else if(response.equals("false")){
                        progressDialog.dismiss();
                        Toast.makeText(_context,"خطایی رخ داده است.",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    progressDialog.dismiss();
                    new PostError(_context,e.getMessage(), Utility.getMethodName()).postError();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        new ErrorVolley(_context).Error(error, "post");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> json = new HashMap<String, String>();
                json.put("UserName",_username);
                json.put("Id_Lesson",_idlesson+"");
                return json;
            }
        };

        // add Policy and add StringRequest to RequestQueue
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}