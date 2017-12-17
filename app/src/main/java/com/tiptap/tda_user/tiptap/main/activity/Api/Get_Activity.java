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
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Get_Activity extends BaseSetingApi {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity mactivity;
    boolean mnet;
    int _id;
    ProgressDialog progressDialog;

    public Get_Activity(int id, boolean net, MVP_Lesson.ProvidedPresenterOps ppo, Context context, Activity activity) {
        lesson_presenter = ppo;
        mactivity = activity;
        _context = context;
        mnet = net;
        _id = id;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Activity get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "Activity?id="+_id+"rowVersion=0x0", null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    try {
                        int maxId = lesson_presenter.getMaxId_Activity();

                        String Q1 = "insert into TbActivity (_id, Id_Lesson, ActivityNumber, Id_ActivityType, Title1, Title2, Path1, Path2, IsNote, RowVersion) values ";
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
                            int Id = Integer.parseInt(id);

                            // insert
                            if(Id>maxId) {
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + idlesson + "','" + activitynumber + "','" + idactivitytype + "','" + title1 + "','" + title2 + "','" + path1 + "','" + path2 + "','" + isnote + "','" + rowversion + "')," );
                            }

                            // update
                            else {
                                String Q2="update TbActivity set Id_Lesson='"+idlesson+"',ActivityNumber='"+activitynumber+"',Id_ActivityType='"+idactivitytype+"',Title1='"+title1+"',Title2='"+title2+"',Path1='"+path1+"',Path1='"+path2+"',IsNote='"+isnote+"',RowVersion='"+rowversion+"' where _id="+Id;
                                lesson_presenter.Insert_Activity(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            lesson_presenter.Insert_Activity(Q1);
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