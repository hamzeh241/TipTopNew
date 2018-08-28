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
import com.tiptap.tda_user.tiptap.main.activity.DB.Utility;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.CardPagerAdapter_L;
import com.tiptap.tda_user.tiptap.main.activity.view.lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.Cls.Set_Lesson;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class Get_Lesson extends BaseSetingApi {

    MVP_Lesson.ProvidedPresenterOps lesson_presenter;
    Context _context;
    Activity mactivity;
    int _Fid;
    int now_fid;
    ViewPager mViewPager;
    CardPagerAdapter_L mCardAdapter;
    ShadowTransformer mCardShadowTransformer;
    boolean mnet;
    ProgressDialog progressDialog;

    public Get_Lesson(int fid, int nfid, boolean net, MVP_Lesson.ProvidedPresenterOps ppo, Context context, Activity activity, ViewPager viewPager, CardPagerAdapter_L cardAdapter, ShadowTransformer shadowTransformer) {
        _Fid = fid;
        now_fid = nfid;
        lesson_presenter = ppo;
        _context = context;
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
                    url+ "Lesson?Id="+_Fid+"&rowVersion=0x0", null, new Response.Listener<JSONArray>() {
                //+lesson_presenter.getMaxRowV_Lesson()
                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    List<Integer> listLesson = lesson_presenter.ListLesson();
                    try {
                        String Q1 = "insert into TbLesson (_id,Id_Function,LessonNumber,RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String fid = jsonObject.getString("Id_Function");
                            String ln = jsonObject.getString("LessonNumber");
                            String row = "1";
                            int Id = Integer.parseInt(id);

                            int type = whatdo(listLesson, Id);
                            // insert
                            if(type == 1){
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + fid + "','" + ln + "','" + row + "')," );
                            }
                            // update
                            if(type == 2){
                                String Q2="update TbLesson set Id_Function='"+fid+"',LessonNumber='"+ln+"',RowVersion='"+row+"' where _id="+Id;
                                lesson_presenter.Insert_Lesson(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            lesson_presenter.Insert_Lesson(Q1);
                        }
                        Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_context,mactivity,now_fid,mViewPager,mCardAdapter,mCardShadowTransformer);
                        set_lesson.load();
                        progressDialog.dismiss();

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
                    new ErrorVolley(_context).Error(volleyError,"get");
                    if (volleyError.networkResponse == null) {
                        if (volleyError.getClass().equals(TimeoutError.class)) {
                            Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_context,mactivity,now_fid,mViewPager,mCardAdapter,mCardShadowTransformer);
                            set_lesson.load();
                        }
                    }
                }
            });
            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{
            Set_Lesson set_lesson = new Set_Lesson(lesson_presenter,_context,mactivity,now_fid,mViewPager,mCardAdapter,mCardShadowTransformer);
            set_lesson.load();
        }
        return null;
    }

    public int whatdo(List<Integer> listLesson, int id){
        int result = 1;
        for(int j=0 ; j < listLesson.size() ; j++) {
            if(listLesson.get(j) == id){
                result = 2;
            }
        }
        return result;
    }
}