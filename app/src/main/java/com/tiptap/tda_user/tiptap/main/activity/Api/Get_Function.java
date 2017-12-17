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
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.CardPagerAdapter_F;
import com.tiptap.tda_user.tiptap.main.activity.view.function_lesson.ShadowTransformer;
import com.tiptap.tda_user.tiptap.main.activity.Cls.Set_Function;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.tiptap.tda_user.tiptap.common.SampleApp.getMethodName;

public class Get_Function extends BaseSetingApi {

    MVP_Function.ProvidedPresenterOps function_presenter;
    int _id_function;
    Context _context;
    Activity mactivity;
    ViewPager mViewPager;
    CardPagerAdapter_F mCardAdapter;
    ShadowTransformer mCardShadowTransformer;
    boolean mnet;
    ProgressDialog progressDialog;

    public Get_Function(int id_function, boolean net, MVP_Function.ProvidedPresenterOps ppo, Context context, Activity activity, ViewPager viewPager, CardPagerAdapter_F cardAdapter, ShadowTransformer shadowTransformer) {
        _id_function = id_function;
        function_presenter = ppo;
        mactivity = activity;
        _context = context;
        mViewPager = viewPager;
        mCardAdapter = cardAdapter;
        mCardShadowTransformer = shadowTransformer;
        mnet = net;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Function get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "Function?rowVersion="+function_presenter.getMaxRowV_Function(), null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    try {
                        int maxId = function_presenter.getMaxId_Function();
                        String Q1 = "insert into TbFunction (_id,Title,RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String title = jsonObject.getString("Title");
                            String row = "1";
                            int Id = Integer.parseInt(id);
                            // insert
                            if(Id>maxId) {
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + title + "','" + row + "')," );
                            }
                            // update
                            else {
                                String Q2="update TbFunction set Title='"+title+"',RowVersion='"+row+"' where _id="+Id;
                                function_presenter.Insert_Function(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            function_presenter.Insert_Function(Q1);
                        }
                        Set_Function set_function = new Set_Function(_id_function,function_presenter,mViewPager,mCardAdapter,mCardShadowTransformer);
                        set_function.load();
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
                        if (volleyError.getClass().equals(TimeoutError.class)) {
                            Set_Function set_function = new Set_Function(_id_function,function_presenter,mViewPager,mCardAdapter,mCardShadowTransformer);
                            set_function.load();
                        }
                    }
                }
            });
            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{
            Set_Function set_function = new Set_Function(_id_function,function_presenter,mViewPager,mCardAdapter,mCardShadowTransformer);
            set_function.load();
        }
        return null;
    }
}