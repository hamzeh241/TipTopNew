package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.DB.Utility;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Get_Activity extends BaseSetingApi {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity mactivity;
    int func;
    int _id;
    ProgressDialog progressDialog;
    View _view;
    boolean IsNet;

    public Get_Activity(int id, MVP_Lesson.ProvidedPresenterOps ppo, Context context, Activity activity, View view, int nid, boolean _isNet) {
        lesson_presenter = ppo;
        mactivity = activity;
        _context = context;
        func=nid;
        _id = id;
        _view = view;
        IsNet=_isNet;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Activity get() {
        if(IsNet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            String username = lesson_presenter.userName();
            String encodedString = null;
            try {
                encodedString = URLEncoder.encode(username, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String u = url+ "Activity?id="+_id+"&rowVersion="+lesson_presenter.getMaxRowV_Lesson()+"&userName="+encodedString;
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    u, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    List<Integer> listActivity = lesson_presenter.ListActivity();
                    try {
                        String Q1 = "insert into TbActivity (_id, Id_Lesson, ActivityNumber, Id_ActivityType, Title1, Title2, Path1, Path2, IsNote, RowVersion, Status) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String idlesson = jsonObject.getString("Id_Lesson");
                            String activitynumber = jsonObject.getString("ActivityNumber");
                            String idactivitytype = jsonObject.getString("Id_ActivityType");
                            String title1 = jsonObject.getString("Title1");
                            String title2 = jsonObject.getString("Title2");
                            String path1 = jsonObject.getString("Path1");
                            String path2 = jsonObject.getString("Path2");
                            String isnote = jsonObject.getString("IsNote");
                            String rowversion = "1";
                            int status = 0;

                            int Id = Integer.parseInt(id);

                            int type = whatdo(listActivity, Id);
                            // insert
                            if(type == 1){
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + idlesson + "','" + activitynumber + "','" + idactivitytype + "','" + title1 + "','" + title2 + "','" + path1 + "','" + path2 + "','" + isnote + "','" + rowversion + "','" + status + "')," );
                            }
                            // update
                            if(type == 2){
                                String Q2="update TbActivity set Id_Lesson='"+idlesson+"',ActivityNumber='"+activitynumber+"',Id_ActivityType='"+idactivitytype+"',Title1='"+title1+"',Title2='"+title2+"',Path1='"+path1+"',Path2='"+path2+"',IsNote='"+isnote+"',RowVersion='"+rowversion+"',Status='"+status+"' where _id="+Id;
                                lesson_presenter.Insert_Activity(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            lesson_presenter.Insert_Activity(Q1);
                        }
                        progressDialog.dismiss();
                        new Get_ActivityDetail(_id,lesson_presenter, _context, mactivity,_view,func,IsNet);

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        new PostError(_context,e.getMessage(), Utility.getMethodName()).postError();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    String x = volleyError.getMessage();
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

    public int whatdo(List<Integer> listActivity, int id){
        int result = 1;
        for(int j=0 ; j < listActivity.size() ; j++) {
            if(listActivity.get(j) == id){
                result = 2;
            }
        }
        return result;
    }
}