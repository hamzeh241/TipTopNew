package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.view.login.Login;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class Post_User extends BaseSetingApi {
    MVP_Login.ProvidedPresenterOps login_presenter;
    String choose;
    Context _context;
    Activity _activity;
    String Result = "";
    String user_name, name_inapp, _email, _password;
    int language_id;
    boolean mnet;


    public Post_User(MVP_Login.ProvidedPresenterOps ppo, Context context, Activity activity,
                     String username, String nameinapp, String email, String password, int lid,boolean net) {
        login_presenter = ppo;
        choose = "login";
        _context = context;
        _activity = activity;
        user_name = username;
        name_inapp = nameinapp;
        _email = email;
        _password = password;
        language_id = lid;
        mnet = net;
    }

    public String post() throws JSONException {
        JSONObject _jsonBody=new JSONObject();
        _jsonBody.put("UserName",user_name);
        _jsonBody.put("NameInApp",name_inapp);
        _jsonBody.put("Email", _email);
        _jsonBody.put("Password",_password);
        _jsonBody.put("Id_Language",language_id);
        RequestQueue requestQueue = Volley.newRequestQueue(_context);
        final String requestBody = _jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url+"User", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("200")) {
                    Result = response;
                    if(login_presenter.CountUser()==0) {
                        String Q = "insert into aspnet_Users (UserName,NameInApp,Password,Email,Id_Language) values ('" + user_name + "','" + name_inapp + "','" + _password + "','" + _email + "','" + language_id + "')";
                        login_presenter.Insert_User(Q);
                    }
                    else
                    {
                        String Q2="update aspnet_Users set UserName='"+user_name+"',NameInApp='"+name_inapp+"',Password='"+_password+"',Email='"+_email+"',Id_Language='"+language_id;
                        login_presenter.Insert_User(Q2);
                    }
                    new Get_Glossary(mnet, login_presenter, _context, _activity, language_id);
                }
                else {
                    Toast.makeText(_context,"خطا در ارسال اطلاعات به سرور",Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidepDialog();
                        new ErrorVolley(_context).Error(error, "post");
                        Result =error.toString();
                    }
                })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

        return Result;
    }
}