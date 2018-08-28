package com.tiptap.tda_user.tiptap.main.activity.DB;

public class Utility {
    public static String getMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String x=stackTraceElements[3].getMethodName();
        String x1=stackTraceElements[3].getClassName();
        return x+"_"+x1;
    }
}