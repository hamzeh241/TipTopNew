package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Login;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class Get_Glossary extends BaseSetingApi {

    MVP_Login.ProvidedPresenterOps login_presenter;
    Context _context;
    Activity mactivity;
    boolean mnet;
    ProgressDialog progressDialog;
    int _id;

    public Get_Glossary(boolean net, MVP_Login.ProvidedPresenterOps ppo, Context context, Activity activity, int id) {
        login_presenter = ppo;
        mactivity = activity;
        _context = context;
        mnet = net;
        _id = id;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Glossary get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url+ "Glossary?id="+_id+"&rowVersion="+login_presenter.getMaxRowV_Glossary(), null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    List<Integer> listGlossary = login_presenter.ListGlossary();
                    try {
                        String Q1 = "insert into TbGlossary (_id,Id_Language,TableName,ColumeName,RowNumber,NewTitle,RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String id_language = jsonObject.getString("ColumeName");
                            String table_name = jsonObject.getString("Id_Language");
                            String colume_name = jsonObject.getString("NewTitle");
                            String row_number = jsonObject.getString("RowNumber");
                            String new_title = jsonObject.getString("RowVersion");
                            String row_version = jsonObject.getString("TableName");

                            int Id = Integer.parseInt(id);

                            int type = whatdo(listGlossary, Id);
                            // insert
                            if(type == 1){
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + id_language + "','" + table_name + "','" + colume_name + "','" + row_number + "','" + new_title + "','" + row_version + "')," );
                            }
                            // update
                            if(type == 2){
                                String Q2="update TbGlossary set Id_Language='"+id_language+"',TableName='"+table_name+"',ColumeName='"+colume_name+"',RowNumber='"+row_number+"',NewTitle='"+new_title+"',RowVersion='"+row_version+"' where _id="+Id;
                                login_presenter.Insert_Glossary(Q2);
                            }
                        }

                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            login_presenter.Insert_Glossary(Q1);
                        }
                        progressDialog.dismiss();
                        mactivity.finish();
                        mactivity.startActivity(new Intent(mactivity, Function.class));

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
                        if (volleyError.getClass().equals(TimeoutError.class)) {}
                    }
                }
            });

            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{}

        return null;
    }

    public int whatdo(List<Integer> listGlossary, int id){
        int result = 1;
        for(int j=0 ; j < listGlossary.size() ; j++) {
            if(listGlossary.get(j) == id){
                result = 2;
            }
        }
        return result;
    }
}