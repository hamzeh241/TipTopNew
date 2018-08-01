package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class Post_User extends BaseSetingApi {

    MVP_Login.ProvidedPresenterOps login_presenter;
    String choose;
    Context _context;
    Activity _activity;
    String Result = "";
    String _username, _password, _email, _idlesson, _idlanguage, _name, _lastname, _age, _city, _country, _sex;
    boolean mnet;
    ProgressDialog progressDialog;

    public Post_User(MVP_Login.ProvidedPresenterOps ppo, Context context, Activity activity, boolean net, String idlesson,
                     String username, String email, String password, String idlanguage, String sex,
                     String name, String lastname, String age, String city, String country) {
        login_presenter = ppo;
        choose = "login";
        _context = context;
        _activity = activity;
        mnet = net;
        _username = username;
        _password = password;
        _email = email;
        _idlesson = idlesson;
        _idlanguage = idlanguage;
        _name = name;
        _lastname = lastname;
        _age = age;
        _sex = sex;
        _city = city;
        _country = country;
        progressDialog = new ProgressDialog(_activity);
    }

    public String post() throws JSONException {

        progressDialog.setMessage("لطفا صبر کنید ...");
        progressDialog.show();

        JSONObject _jsonBody=new JSONObject();
        _jsonBody.put("Id_Lesson",_idlesson);
        _jsonBody.put("UserName",_username);
        _jsonBody.put("Email",_email);
        _jsonBody.put("Password",_password);
        _jsonBody.put("Id_Language",_idlanguage);
        _jsonBody.put("Name",_name);
        _jsonBody.put("LastName",_lastname);
        _jsonBody.put("Age",_age);
        _jsonBody.put("City",_city);
        _jsonBody.put("Countery",_country);

        RequestQueue requestQueue = Volley.newRequestQueue(_context);
        final String requestBody = _jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url+"User", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("200")) {
                    progressDialog.dismiss();
                    Result = response;
                    if(login_presenter.CountUser()==0) {
                        String Q = "insert into aspnet_Users (Id_Lesson,UserName,Email,Password,Id_Language,Name,LastName,Age,City,Countery) values ('" +
                                _idlesson + "','" + _username + "','" + _email + "','" + _password + "','" + _idlanguage + "','" + _name + "','" + _lastname + "','" + _age + "','" + _city + "','" + _country + "')";
                        login_presenter.Insert_User(Q);
                    }
                    else {
                        String Q2="update aspnet_Users set Id_Lesson='"+_idlesson+"',UserName='"+_username+"',Email='"+_email+"',Password='"+_password+"',Id_Language='"+_idlanguage+"',Name='"+_name+"',LastName='"+_lastname+"',Age='"+_age+"',City='"+_city+"',Countery='"+_country;
                        login_presenter.Insert_User(Q2);
                    }
                    progressDialog.dismiss();
                    _activity.finish();
                    _activity.startActivity(new Intent(_activity, Function.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(_context,"خطا در ارسال اطلاعات به سرور",Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        new ErrorVolley(_context).Error(error, "post");
                        Result = error.toString();
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