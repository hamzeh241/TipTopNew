package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardPagerAdapter_L;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.Cls.Set_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import com.tiptap.tda_user.tiptap.main.activity.Model.Lesson_Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Get_Lesson extends BaseSetingApi {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context context;
    Activity mactivity;
    int _id;
    ViewPager mViewPager;
    CardPagerAdapter_L mCardAdapter;
    ShadowTransformer mCardShadowTransformer;
    boolean mnet;
    ProgressDialog progressDialog;

    public Get_Lesson(int id, boolean net, MVP_Lesson.ProvidedPresenterOps ppo, Activity activity, ViewPager viewPager, CardPagerAdapter_L cardAdapter, ShadowTransformer shadowTransformer) {
        _id = id;
        lesson_presenter = ppo;
        mactivity = activity;
        mViewPager = viewPager;
        mCardAdapter = cardAdapter;
        mCardShadowTransformer = shadowTransformer;
        mnet = net;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Lesson get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "Lesson?Id="+_id+"&rowVersion=0x0", null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    try {
                        int maxId = lesson_presenter.getMaxId_Lesson();
                        String Q1 = "insert into TbLesson (_id,Id_Function,LessonNumber,RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String fid = jsonObject.getString("Id_Function");
                            String ln = jsonObject.getString("LessonNumber");
                            String row = "1";
                            int Id = Integer.parseInt(id);
                            // insert
                            if(Id>maxId) {
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + fid + "','" + ln + "','" + row + "')," );
                            }
                            // update
                            else {
                                String Q2="update TbLesson set Id_Function='"+fid+"',LessonNumber='"+ln+"',RowVersion='"+row+"' where _id="+Id;
                                lesson_presenter.Insert_Lesson(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            lesson_presenter.Insert_Lesson(Q1);
                        }
                        Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_id,mViewPager,mCardAdapter,mCardShadowTransformer);
                        set_lesson.load();
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        new PostError(context,e.getMessage(), getMethodName()).postError();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    new ErrorVolley(context).Error(volleyError,"get");
                    if (volleyError.networkResponse == null) {
                        if (volleyError.getClass().equals(TimeoutError.class)) {
                            Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_id,mViewPager,mCardAdapter,mCardShadowTransformer);
                            set_lesson.load();
                        }
                    }
                }
            });
            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{
            Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_id,mViewPager,mCardAdapter,mCardShadowTransformer);
            set_lesson.load();
        }
        return null;
    }
}