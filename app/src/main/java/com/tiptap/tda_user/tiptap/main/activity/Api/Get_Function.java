package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tiptap.tda_user.tiptap.common.SampleApp;
import com.tiptap.tda_user.tiptap.main.activity.Cls.Set_Function;
import com.tiptap.tda_user.tiptap.main.activity.DB.Utility;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Function;
import com.tiptap.tda_user.tiptap.main.activity.view.Dialog_Error;
import com.tiptap.tda_user.tiptap.main.activity.view.Dialog_TimeOut;
import com.tiptap.tda_user.tiptap.main.activity.view.function.Function_Adapter;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import com.tiptap.tda_user.tiptap.main.activity.DB.PostError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Get_Function extends BaseSetingApi {

    private MVP_Function.ProvidedPresenterOps function_presenter;
    private int _id_function;
    private Context _context;
    private Activity mactivity;
    boolean mnet;
    ProgressDialog progressDialog;
    RecyclerView mRecyclerView;
    Function_Adapter mAdapter;
    ArrayList<String> Data;
    ArrayList<Integer> Id;

    public Get_Function(int id_function, boolean net, MVP_Function.ProvidedPresenterOps ppo, Context context, Activity activity, RecyclerView r, Function_Adapter fa, ArrayList<String> d, ArrayList<Integer> i) {
        _id_function = id_function;
        function_presenter = ppo;
        mactivity = activity;
        _context = context;
        mRecyclerView = r;
        mAdapter = fa;
        Data = d;
        Id = i;
        mnet = net;
        progressDialog = new ProgressDialog(mactivity);
        get();
    }

    public Get_Function get() {
        if(mnet){
            progressDialog.setMessage("در حال دریافت اطلاعات از سرور ...");
            progressDialog.show();
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                    Request.Method.GET, url+ "Function?rowVersion=0x0", null,
                    new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    boolean insert = false;
                    List<Integer> listFunction = function_presenter.ListFunction();
                    try {
                        String Q1 = "insert into TbFunction (_id,Title,RowVersion) values ";
                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("C_id");
                            String title = jsonObject.getString("Title");
                            String row = "1";

                            int Id = Integer.parseInt(id);

                            int type = whatdo(listFunction, Id);
                            // insert
                            if(type == 1){
                                insert = true;
                                Q1 = Q1.concat("('" + id + "','" + title + "','" + row + "')," );
                            }
                            // update
                            if(type == 2){
                                String Q2="update TbFunction set Title='"+title+"',RowVersion='"+row+"' where _id="+Id;
                                function_presenter.Insert_Function(Q2);
                            }
                        }
                        if(insert) {
                            Q1 = Q1.substring(0, Q1.trim().length() - 1).concat(";");
                            function_presenter.Insert_Function(Q1);
                        }
                        Set_Function set_function = new Set_Function(function_presenter,mactivity,_id_function,mRecyclerView,mAdapter,Data,Id);
                        set_function.load();
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        //e.printStackTrace();
                        Dialog_Error de = new Dialog_Error(mactivity);
                        de.show();
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
                            progressDialog.dismiss();
                            Dialog_TimeOut dr = new Dialog_TimeOut(mactivity);
                            dr.show();
                            //Set_Function set_function = new Set_Function(function_presenter,mactivity,_id_function,mRecyclerView,mAdapter,Data,Id);
                            //set_function.load();
                        }
                    }
                }
            });
            SampleApp.getInstance().addToRequestQueue(jsonObjReq);

        }else{
           // Set_Function set_function = new Set_Function(function_presenter,mactivity,_id_function,mRecyclerView,mAdapter,Data,Id);
           // set_function.load();
            // no internet connection
            Toast.makeText(_context, "دسترسی به اینترنت امکان پذیر نیست", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public int whatdo(List<Integer> listFunction, int id){
        int result = 1;
        for(int j=0 ; j < listFunction.size() ; j++) {
            if(listFunction.get(j) == id){
                result = 2;
            }
        }
        return result;
    }
}