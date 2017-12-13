package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Get_User extends BaseSetingApi {

    MVP_Splash.ProvidedPresenterOps splash_presenter;
    Context _context;
    Activity mactivity;
    boolean mnet;
    ProgressDialog progressDialog;

    public Get_User(boolean net, MVP_Splash.ProvidedPresenterOps ppo, Context context, Activity activity) {
        splash_presenter = ppo;
        mactivity = activity;
        _context = context;
        mnet = net;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_User get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "User", null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    try {
                        int maxId = splash_presenter.getMaxId_Activity();
                        String Q1 = "insert into TbUser (ApplicationId,UserId,UserName,LoweredUserName,MobileAlias,IsAnonymous,LastActivityDate,Id_Lesson,Id_Language) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String appid = jsonObject.getString("XXX");
                            String userid = jsonObject.getString("XXX");
                            String username = jsonObject.getString("XXX");
                            String loweredusername = jsonObject.getString("XXX");
                            String mobilealias = jsonObject.getString("XXX");
                            String isanonymous = jsonObject.getString("XXX");
                            String lastactivitydate = jsonObject.getString("XXX");
                            String id_lesson = jsonObject.getString("XXX");
                            String id_language = jsonObject.getString("XXX");

                            // insert
                            //if(Id>maxId) {
                            //    insert = true;
                            //    Q1 = Q1.concat("('" + id + "','" + title + "','" + row + "')," );
                            //}
                            // update
                            //else {
                            //    String Q2="update TbFunction set Title='"+title+"',RowVersion='"+row+"' where _id="+Id;
                            //    splash_presenter.Insert_Activity(Q2);
                            //}
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            splash_presenter.Insert_Activity(Q1);
                        }
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        new PostError(_context,e.getMessage(), getMethodName()).postError();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    new ErrorVolley(_context).Error(volleyError,"get");
                    if (volleyError.networkResponse == null) {
                        if (volleyError.getClass().equals(TimeoutError.class)) {}
                    }
                }
            });
            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{}
        return null;
    }
}