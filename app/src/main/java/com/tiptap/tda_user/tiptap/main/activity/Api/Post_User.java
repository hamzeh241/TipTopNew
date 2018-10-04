package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class Post_User extends BaseSetingApi {

    MVP_Login.ProvidedPresenterOps login_presenter;
    String choose;
    Context _context;
    Activity _activity;
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

    public void post() throws JSONException {

        progressDialog.setMessage("لطفا صبر کنید ...");
        progressDialog.show();

        // create RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(_context);
        // create StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url+"User", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // delete double "
                String result = response.substring(1,response.length()-1);
                try{
                    if(result.equals("ok")) {
                        progressDialog.dismiss();
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
                    else if(result.equals("نام کاربری تکراری است.")){
                        progressDialog.dismiss();
                        Toast.makeText(_context,"نام کاربری تکراری است.",Toast.LENGTH_LONG).show();
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
                json.put("Id_Lesson", _idlesson);
                json.put("UserName",_username);
                json.put("Email",_email);
                json.put("Password",_password);
                json.put("Id_Language",_idlanguage);
                json.put("Name",_name);
                json.put("LastName",_lastname);
                json.put("Age",_age);
                json.put("City",_city);
                json.put("Countery",_country);
                return json;
            }
        };

        // add Policy and add StringRequest to RequestQueue
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}