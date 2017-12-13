package com.tiptap.tda_user.tiptap.main.activity.DB;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

public class ErrorVolley {

    public ErrorVolley(Context _ctx) {
        this._ctx = _ctx;
    }
    Context _ctx;

    public void Error(com.android.volley.VolleyError error,String MethodName) {
        String ErrorMsg="";
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            ErrorMsg="error_network_timeout";
        } else if (error instanceof AuthFailureError) {
            ErrorMsg="AuthFailureError";
        } else if (error instanceof ServerError) {
            ErrorMsg="ServerError";
        } else if (error instanceof NetworkError) {
            ErrorMsg="NetworkError";
        } else if (error instanceof ParseError) {
            ErrorMsg="ParseError";
        }
        new PostError(_ctx,ErrorMsg, MethodName+"___volleyError").postError();
    }
}