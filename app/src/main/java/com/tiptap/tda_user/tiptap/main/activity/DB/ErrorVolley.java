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
            ErrorMsg="error_network_timeout  /  "+error.getMessage()+"  /  "+_ctx.getClass();
        } else if (error instanceof AuthFailureError) {
            ErrorMsg="AuthFailureError  /  "+error.getMessage()+"  /  "+_ctx.getClass();
        } else if (error instanceof ServerError) {
            ErrorMsg="ServerError  /  "+error.getMessage()+"  /  "+_ctx.getClass();
        } else if (error instanceof NetworkError) {
            ErrorMsg="NetworkError  /  "+error.getMessage()+"  /  "+_ctx.getClass();
        } else if (error instanceof ParseError) {
            ErrorMsg="ParseError  /  "+error.getMessage()+"  /  "+_ctx.getClass();
        }
        new PostError(_ctx,ErrorMsg, MethodName+"___volleyError").postError();
    }
}