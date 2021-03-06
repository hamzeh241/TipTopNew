package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
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
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.CardPagerAdapter_L;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Get_ActivityDetail extends BaseSetingApi {

    CardPagerAdapter_L cl;
    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity mactivity;
int func;
    int _id;
    ProgressDialog progressDialog;
    View _view;
    boolean IsNet;

    public Get_ActivityDetail(int id,  MVP_Lesson.ProvidedPresenterOps ppo, Context context, Activity activity,View view,int nid,boolean _isNet) {
        lesson_presenter = ppo;
        mactivity = activity;
        _context = context;
        func=nid;
        _id = id;
        _view = view;
        IsNet=_isNet;
        progressDialog = new ProgressDialog(mactivity);
        cl=new CardPagerAdapter_L();
        get();
    }

    public Get_ActivityDetail get() {
        if(IsNet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            String userName = lesson_presenter.userName();
            String encodedString = null;
            try {
                encodedString = URLEncoder.encode(userName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "ActivityDetail?id="+_id+"&userName="+encodedString, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    List<Integer> listActivityDetail = lesson_presenter.ListActivityDetail();
                    try {
                        String Q1 = "insert into TbActivityDetail (_id, Path1, Path2, Id_Activity, Title1, Title2, IsAnswer, OrferAnswer, OrderPreview, RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String orderperview = jsonObject.getString ("OrderPreview");
                            String idactivity = jsonObject.getString ("Id_Activity");
                            String orferanswer = jsonObject.getString ("OrferAnswer");
                            String title1 = jsonObject.getString("Title1");
                            String title2 = jsonObject.getString("Title2");
                            String path1 = jsonObject.getString("Path1");
                            String path2 = jsonObject.getString("Path2");
                            String isanswer = jsonObject.getString("IsAnswer");
                            String rowversion = "1";

                            int Id = Integer.parseInt(id);

                            int type = whatdo(listActivityDetail, Id);
                            // insert
                            if(type == 1){
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + path1 + "','" + path2 + "','" + idactivity + "','" + title1 + "','" + title2 + "','" + isanswer + "','" + orferanswer + "','" + orderperview + "','" + rowversion + "')," );
                            }
                            // update
                            if(type == 2){
                                String Q2="update TbActivityDetail set Path1='"+path1+"',Path2='"+path2+"',Id_Activity='"+idactivity+"',Title1='"+title1+"',Title2='"+title2+"',IsAnswer='"+isanswer+"',OrferAnswer='"+orferanswer+"',OrderPreview='"+orderperview+"',RowVersion='"+rowversion+"' where _id="+Id;
                                lesson_presenter.Insert_Activity(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            lesson_presenter.Insert_Activity(Q1);
                        }
                        progressDialog.dismiss();
                        int id_activity_type = lesson_presenter.activity_Type(_id);
                        go_activity(_view, id_activity_type, _id,func,mactivity);

                    } catch (JSONException e) {
                        Toast.makeText(_context, "JSONException : "+ e.getCause()+e.getMessage() , Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        e.printStackTrace();
                        new PostError(_context,e.getMessage(), Utility.getMethodName()).postError();
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

    public int whatdo(List<Integer> listActivityDetail, int id){
        int result = 1;
        for(int j=0 ; j < listActivityDetail.size() ; j++) {
            if(listActivityDetail.get(j) == id){
                result = 2;
            }
        }
        return result;
    }
}
