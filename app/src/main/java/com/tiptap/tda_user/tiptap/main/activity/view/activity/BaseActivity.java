package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;

/**
 * Created by kurdprogrammer on 5/31/2018.
 */

public class BaseActivity extends AppCompatActivity {
   public final int REQ_CODE_SPEECH_INPUT = 100;
   public String url_download = "http://tiptop.tdaapp.ir/image/";
    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    public static String Act_Status;
    public static int idactivity;
    TbActivity tbActivity;
    int max,now_less;
 public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
//
//    @Override
//    public Context getActivityContext() {
//        return this;
//    }
//
//    @Override
//    public Context getAppContext() {
//        return getApplicationContext();
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        back();
//    }
//
//    @Override
//    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        seekBar.setSecondaryProgress(percent);
//    }
//    private void SeekBarProgressUpdater() {
//        seekBar.setProgress((int)(((float)mp.getCurrentPosition()/mpLength)*100));
//        if (mp.isPlaying()) {
//            Runnable notification = new Runnable() {
//                public void run() {
//                    SeekBarProgressUpdater();
//                }
//            };
//            handler.postDelayed(notification,1000);
//        }
//    }
}
