package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.DB.Utility;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Enter;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post_CheckLogin extends BaseSetingApi {

    MVP_Enter.ProvidedPresenterOps _presenter;
    Context _context;
    Activity _activity;
    String _username, _password;
    boolean mnet;
    ProgressDialog progressDialog;

    public Post_CheckLogin(MVP_Enter.ProvidedPresenterOps ppo, Context context, Activity activity, boolean net,
                           String username, String password) {

        _presenter = ppo;
        _context = context;
        _activity = activity;
        mnet = net;
        _username = username;
        _password = password;
        progressDialog = new ProgressDialog(_activity);
    }

    public void post() throws JSONException {

        progressDialog.setMessage("لطفا صبر کنید ...");
        progressDialog.show();

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("UserName", _username);
        jsonParams.put("Password", _password);

        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST, url+"User/CheckLogin", new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("UserName").equals("null")){
                                progressDialog.dismiss();
                                Toast.makeText(_context,"اطلاعات وارد شده صحیح نیست.",Toast.LENGTH_SHORT).show();
                            }else{
                                _presenter.Delete_User();
                                String Q1 = "insert into aspnet_Users (UserName,Password,Email,Id_Lesson,Id_Function,Id_Language,Name,LastName,Age,Address,City,Countery,Birthday) values ";

                                String UserName = response.getString("UserName");
                                String Password = response.getString("Password");
                                String Email = response.getString("Email");
                                if (Email.equals("") || Email.equals(null)) {
                                    Email = "";
                                }
                                String Id_Lesson = response.getString("Id_Lesson");
                                if (Id_Lesson.equals("") || Id_Lesson.equals(null)) {
                                    Id_Lesson = "";
                                }
                                String Id_Function = response.getString("TbLessonId_Function");
                                if (Id_Function.equals("") || Id_Function.equals(null)) {
                                    Id_Function = "";
                                }
                                String Id_Language = response.getString("Id_Language");
                                if (Id_Language.equals("") || Id_Language.equals(null)) {
                                    Id_Language = "";
                                }
                                String Name = response.getString("Name");
                                if (Name.equals("") || Name.equals(null)) {
                                    Name = "";
                                }
                                String LastName = response.getString("LastName");
                                if (LastName.equals("") || LastName.equals(null)) {
                                    LastName = "";
                                }
                                String Age = response.getString("Age");
                                if (Age.equals("") || Age.equals(null)) {
                                    Age = "";
                                }
                                String Address = response.getString("Address");
                                if (Address.equals("") || Address.equals(null)) {
                                    Address = "";
                                }
                                String City = response.getString("City");
                                if (City.equals("") || City.equals(null)) {
                                    City = "";
                                }
                                String Countery = response.getString("Countery");
                                if (Countery.equals("") || Countery.equals(null)) {
                                    Countery = "";
                                }
                                String Birthday = response.getString("Birthday");
                                if (Birthday.equals("") || Birthday.equals(null)) {
                                    Birthday = "";
                                }

                                Q1 = Q1.concat("('" + UserName + "','" + Password + "','" + Email + "','" + Id_Lesson + "','" + Id_Function + "','" + Id_Language + "','" + Name + "','" + LastName + "','" + Age + "','" + Address + "','" + City + "','" + Countery + "','" + Birthday + "');" );
                                _presenter.Insert_User(Q1);

                                progressDialog.dismiss();
                                _activity.finish();
                                _activity.startActivity(new Intent(_activity, Function.class));
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                        }
                    }
                },
                  new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                });
        SampleApp.getInstance().addToRequestQueue(strReq);
    }
}