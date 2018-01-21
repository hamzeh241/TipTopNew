package com.tiptap.tda_user.tiptap.main.activity.Api;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.main.activity.DB.BaseSetingApi;
import com.tiptap.tda_user.tiptap.main.activity.DB.ErrorVolley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class Post_IdLesson extends BaseSetingApi {

    Context _context;
    String user_name;
    int lesson_id;
    String Result = "";

    public Post_IdLesson( Context context, String username, int lessonid ) {
        _context = context;
        user_name = username;
        lesson_id = lessonid;
    }

    public String post() throws JSONException {
        JSONObject _jsonBody=new JSONObject();
        _jsonBody.put("UserName",user_name);
        _jsonBody.put("Id_Lesson",lesson_id);
        RequestQueue requestQueue = Volley.newRequestQueue(_context);
        final String requestBody = _jsonBody.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url+"User", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("200")) {
                    Result = response;
                }
                else {
                    Toast.makeText(_context,"اتمام این درس ذخیره نشد",Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hidepDialog();
                        new ErrorVolley(_context).Error(error, "post");
                        Result = error.toString();
                    }
                })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

        return Result;
    }
}