package com.tiptap.tda_user.tiptap.main.activity.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;

import java.util.List;

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
    String title1, path1, path2,title2;
    SeekBar seekBar;
    public MediaPlayer mp, mpt, mpf;
    int mpLength;
    final Handler handler = new Handler();
    Handler seekHandler = new Handler();
    boolean end = false;
    ProgressBar p;
    int all;
    TextView txt1,txt2,t1,t2,txt;
    Button play,next;
    List<TbActivityDetail> tbActivityDetailList;
    ImageView img;

    String you_say = "";
    ImageView voice;


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
    public String nice_string (String a){
        // space in first or end
        String b = a.trim();
        // other space
        b = b.replace(" ", "");
        // other
        b = b.replace(".", "");
        b = b.replace("!", "");
        b = b.replace("?", "");
        b = b.replace("؟", "");
        b = b.replace(",", "");
        b = b.replace("\n", "");
        // lowerCase
        b = b.toLowerCase();
        // apastrof
        b = b.replace("'", "’");
        for(int i=0 ; i<b.length() ; i++){
            if(b.charAt(i) == '’'){
                if(b.charAt(i+1) == 's'){
                    b = b.replace("’s", "is");
                }
               /* if(b.charAt(i+1) == 'm'){
                    b = b.replace("’m", "am");
                }
                if(b.charAt(i+1) == 'r'){
                    b = b.replace("’r", "are");
                }*/
            }
        }
        return b;
    }
    public void SeekBarProgressUpdater() {
        seekBar.setProgress((int)(((float)mp.getCurrentPosition()/mpLength)*100));
        if (mp.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    SeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
    }
    public Context getActivityContext() {
        return this;
    }
    public Context getAppContext() {  return getApplicationContext(); }
    public void go_activity(int id_at,String Status,int id_act){

//        switch (id_at){
//
//            case 1: break;
//            case 2: break;
//
//            case 3:
//                A3.idlesson = id_l;
//                A3.idfunction = func ;
//                A3.activitynumber = 1;
//                A3.Act_Status = Status;
//                view.getContext().startActivity(Status, A3.class));
//                mactivity.finish();
//                break;
//
//            case 4:
//                A4.idlesson = id_l;
//                A4.idfunction = func;
//                A4.activitynumber = 1;
//                A4.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A4.class));
//                mactivity.finish();
//                break;
//
//            case 5:
//                A5.idlesson = id_l;
//                A5.idfunction = func;
//                A5.activitynumber = 1;
//                A5.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A5.class));
//                mactivity.finish();
//                break;
//
//            case 6:
//                A6.idlesson = id_l;
//                A6.idfunction = func;
//                A6.activitynumber = 1;
//                A6.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A6.class));
//                mactivity.finish();
//                break;
//
//            case 7:
//                A7.idlesson = id_l;
//                A7.idfunction = func;
//                A7.activitynumber = 1;
//                A7.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A7.class));
//                mactivity.finish();
//                break;
//
//            case 8:
//                A8.idlesson = id_l;
//                A8.idfunction = func;
//                A8.activitynumber = 1;
//                A8.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A8.class));
//                mactivity.finish();
//                break;
//
//            case 9:
//                A9.idlesson = id_l;
//                A9.idfunction = func;
//                A9.activitynumber = 1;
//                A9.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A9.class));
//                mactivity.finish();
//                break;
//
//            case 10: break;
//            case 11: break;
//            case 12: break;
//            case 13: break;
//            case 14: break;
//
//            case 15:
//                A15.idlesson = id_l;
//                A15.idfunction = func ;
//                A15.activitynumber = 1;
//                A15.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A15.class));
//                mactivity.finish();
//                break;
//
//            case 16: break;
//            case 17: break;
//
//            case 18:
//                A18.idlesson = id_l;
//                A18.idfunction = func;
//                A18.activitynumber = 1;
//                A18.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A18.class));
//                mactivity.finish();
//                break;
//
//            case 19:
//                A19.idlesson = id_l;
//                A19.idfunction = func;
//                A19.activitynumber = 1;
//                A19.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A19.class));
//                mactivity.finish();
//                break;
//
//            case 20:
//                A20.idlesson = id_l;
//                A20.idfunction = func ;
//                A20.activitynumber = 1;
//                A20.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A20.class));
//                mactivity.finish();
//                break;
//
//            case 21: break;
//
//            case 22:
//                A22.idlesson = id_l;
//                A22.idfunction = func;
//                A22.activitynumber = 1;
//                A22.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A22.class));
//                mactivity.finish();
//                break;
//
//            case 23: break;
//
//            case 24:
//                A24.idlesson = id_l;
//                A24.idfunction = func;
//                A24.activitynumber = 1;
//                A24.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A24.class));
//                mactivity.finish();
//                break;
//
//            case 25:
//                //A25.idlesson = id_l;
//                //A25.idfunction = func ;
//                //A25.activitynumber = 1;
//                //A25.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A25.class));
//                break;
//
//            case 26:
//                A26.idlesson = id_l;
//                A26.idfunction = func;
//                A26.activitynumber = 1;
//                A26.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A26.class));
//                mactivity.finish();
//                break;
//
//            case 27:
//                A27.idlesson = id_l;
//                A27.idfunction = func ;
//                A27.activitynumber = 1;
//                A27.Act_Status = Status;
//
//                view.getContext().startActivity(new Intent(view.getContext(), A27.class));
//                mactivity.finish();
//                break;
//
//            case 28:
//                A28.idlesson = id_l;
//                A28.idfunction = func;
//                A28.activitynumber = 1;
//                A28.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A28.class));
//                mactivity.finish();
//                break;
//
//            case 29:
//                A29.idlesson = id_l;
//                A29.idfunction = func;
//                A29.activitynumber = 1;
//                A29.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A29.class));
//                mactivity.finish();
//                break;
//
//            case 30:
//                A30.idlesson = id_l;
//                A30.idfunction = func;
//                A30.activitynumber = 1;
//                A30.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A30.class));
//                mactivity.finish();
//                break;
//
//            case 31:
//                //A31.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A31.activitynumber = 1;
//                //A31.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A31.class));
//                mactivity.finish();
//                break;
//
//            case 32:
//                A32.idlesson = id_l;
//                A32.idfunction = func;
//                A32.activitynumber = 1;
//                A32.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A32.class));
//                mactivity.finish();
//                break;
//
//            case 33:
//                A33.idlesson = id_l;
//                A33.idfunction = func;
//                A33.activitynumber = 1;
//                A33.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A33.class));
//                mactivity.finish();
//                break;
//
//            case 34:
//                A34.idlesson = id_l;
//                A34.idfunction = func;
//                A34.activitynumber = 1;
//                A34.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A34.class));
//                mactivity.finish();
//                break;
//
//            case 35:
//                A35.idlesson = id_l;
//                A35.idfunction = func;
//                A35.activitynumber = 1;
//                A35.Act_Status =Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A35.class));
//                mactivity.finish();
//                break;
//
//            case 36: break;
//
//            case 37:
//                A37.idlesson = id_l;
//                A37.idfunction = func ;
//                A37.activitynumber = 1;
//                A37.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A37.class));
//                mactivity.finish();
//                break;
//
//            case 38:
//                //A38.idlesson = id_l;
//                //A38.idfunction = idfunction ;
//                //A38.activitynumber = 1;
//                //A38.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A38.class));
//                mactivity.finish();
//                break;
//
//            case 39:
//                A39.idlesson = id_l;
//                A39.idfunction = func;
//                A39.activitynumber = 1;
//                A39.Act_Status = Status;
//                view.getContext().startActivity(new Intent(view.getContext(), A39.class));
//                mactivity.finish();
//                break;
//
//            case 40:
//                //A40.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A40.activitynumber = 1;
//                //    A15.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A40.class));
//                mactivity.finish();
//                break;
//
//            case 41:
//                //A41.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A41.activitynumber = 1;
//                //    A15.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A41.class));
//                mactivity.finish();
//                break;
//
//            case 42:
//                //A42.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A42.activitynumber = 1;
//                //    A15.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A42.class));
//                mactivity.finish();
//                break;
//
//            case 43:
//                //A43.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A43.activitynumber = 1;
//                //     A15.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A43.class));
//                mactivity.finish();
//                break;
//
//            case 44:
//                //A44.idlesson = id_l;
//                //A.idfunction = idfunction ;
//                //A44.activitynumber = 1;
//                //    A15.Act_Status = "first";
//                view.getContext().startActivity(new Intent(view.getContext(), A44.class));
//                mactivity.finish();
//                break;
//        }
        switch (id_at) {

            case 1: break;
            case 2: break;

            case 3:
                A3.idlesson = idlesson ;
                A3.idfunction = idfunction ;
                A3.idactivity = id_act;
                A3.Act_Status = Status;               
                startActivity(new Intent(getApplicationContext(),  A3.class));
                this.finish();
                break;

            case 4:
                A4.idlesson = idlesson ;
                A4.idfunction = idfunction ;
                A4.idactivity = id_act;
                A4.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A4.class));
                break;

            case 5:
                A5.idlesson = idlesson ;
                A5.idfunction = idfunction ;
                A5.idactivity = id_act;
                A5.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A5.class));
                break;

            case 6:
                A6.idlesson = idlesson ;
                A6.idfunction = idfunction ;
                A6.idactivity = id_act;
                A6.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A6.class));
                break;

            case 7:
                A7.idlesson = idlesson ;
                A7.idfunction = idfunction ;
                A7.idactivity = id_act;
                A7.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A7.class));
                break;

            case 8:
                A8.idlesson = idlesson ;
                A8.idfunction = idfunction ;
                A8.idactivity = id_act;
                A8.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A8.class));
                break;

            case 9:
                A9.idlesson = idlesson ;
                A9.idfunction = idfunction ;
                A9.idactivity = id_act;
                A9.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A9.class));
                break;

            case 10: break;
            case 11: break;
            case 12: break;
            case 13: break;
            case 14: break;

            case 15:
                A15.idlesson = idlesson ;
                A15.idfunction = idfunction ;
                A15.idactivity = id_act;
                A15.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A15.class));
                break;

            case 16: break;
            case 17: break;

            case 18:
                A18.idlesson = idlesson ;
                A18.idfunction = idfunction ;
                A18.idactivity = id_act;
                A18.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A18.class));
                break;

            case 19:
                A19.idlesson = idlesson ;
                A19.idfunction = idfunction ;
                A19.idactivity = id_act;
                A19.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A19.class));
                break;

            case 20:
                A20.idlesson = idlesson ;
                A20.idfunction = idfunction ;
                A20.idactivity = id_act;
                A20.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A20.class));
                break;

            case 21: break;

            case 22:
                A22.idlesson = idlesson ;
                A22.idfunction = idfunction ;
                A22.idactivity = id_act;
                A22.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A22.class));
                break;

            case 23: break;

            case 24:
                A24.idlesson = idlesson ;
                A24.idfunction = idfunction ;
                A24.idactivity = id_act;
                A24.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A24.class));
                break;

            case 25:
                //A25.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A25.class));
                break;

            case 26:
                A26.idlesson = idlesson ;
                A26.idfunction = idfunction ;
                A26.idactivity = id_act;
                A26.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A26.class));
                break;

            case 27:
                A27.idlesson = idlesson ;
                A27.idfunction = idfunction ;
                A27.idactivity = id_act;
                A27.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A27.class));
                break;

            case 28:
                A28.idlesson = idlesson ;
                A28.idfunction = idfunction ;
                A28.idactivity = id_act;
                A28.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A28.class));
                break;

            case 29:
                A29.idlesson = idlesson ;
                A29.idfunction = idfunction ;
                A29.idactivity = id_act;
                A29.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A29.class));
                break;

            case 30:
                A30.idlesson = idlesson ;
                A30.idfunction = idfunction ;
                A30.idactivity = id_act;
                A30.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A30.class));
                break;

            case 31:
                //A31.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A31.class));
                break;

            case 32:
                A32.idlesson = idlesson ;
                A32.idfunction = idfunction ;
                A32.idactivity = id_act;
                A32.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A32.class));
                break;

            case 33:
                A33.idlesson = idlesson ;
                A33.idfunction = idfunction ;
                A33.idactivity = id_act;
                A33.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A33.class));
                break;

            case 34:
                A34.idlesson = idlesson ;
                A34.idfunction = idfunction ;
                A34.idactivity = id_act;
                A34.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A34.class));
                break;

            case 35:
                A35.idlesson = idlesson ;
                A35.idfunction = idfunction ;
                A35.idactivity = id_act;
                A35.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A35.class));
                break;

            case 36: break;

            case 37:
                A37.idlesson = idlesson ;
                A37.idfunction = idfunction ;
                A37.idactivity = id_act;
                A37.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A37.class));
                break;

            case 38:
                //A38.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A38.class));
                break;

            case 39:
                A39.idlesson = idlesson ;
                A39.idfunction = idfunction ;
                A39.idactivity = id_act;
                A39.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A39.class));

                break;

            case 40:
                //A40.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A40.class));
                break;

            case 41:
                //A41.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A41.class));
                break;

            case 42:
                //A42.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A42.class));
                break;

            case 43:
                //A43.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A43.class));
                break;

            case 44:
                //A44.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A6.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A44.class));
                break;

            case 46:
                A46.idlesson = idlesson ;
                A46.idfunction = idfunction ;
                A46.idactivity = id_act;
                A46.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A46.class));
                break;
        }
    }
}
