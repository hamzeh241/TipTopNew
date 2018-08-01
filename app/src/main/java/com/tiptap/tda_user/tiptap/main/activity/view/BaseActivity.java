package com.tiptap.tda_user.tiptap.main.activity.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A15;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A18;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A19;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A20;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A22;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A24;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A25;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A26;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A27;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A28;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A29;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A3;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A30;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A31;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A32;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A33;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A34;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A35;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A37;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A38;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A39;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A4;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A40;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A41;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A42;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A43;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A44;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A46;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A5;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A6;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A7;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A8;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.A9;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements MVP_Main.RequiredViewOps {


    public final int REQ_CODE_SPEECH_INPUT = 100;
    public String url_download = "http://tiptop.tdaapp.ir/image/";
    public static int idlesson;
    public static int idfunction;
    public static int activitynumber;
    public static String Act_Status;
    public static int idactivity;
    public TbActivity tbActivity;
    public int max,now_less;
    public String title1, path1, path2,title2;
    public SeekBar seekBar;
    public MediaPlayer mp, mpt, mpf;
    public int mpLength;
    public final Handler handler = new Handler();
    public Handler seekHandler = new Handler();
    public boolean end = false;
    public ProgressBar p;
    public int all;
    public TextView txt1,txt2,t1,t2,txt;
    public Button play,next;
    public List<TbActivityDetail> tbActivityDetailList;
    public ImageView img;
    public String you_say = "";
    public ImageView voice;
    public ImageLoader imageLoader;

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

    public String nice_string1 (String a){
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
        b = b.replace("’", "’");
        b = b.replace("'", "’");
        b = b.replace("\n", "");
        // apastrof
        for(int i=0 ; i<b.length() ; i++){
            if(b.charAt(i) == '’'){
                if(b.charAt(i+1) == 's'){
                    b = b.replace("’s", "is");
                }
                if(b.charAt(i+1) == 'm'){
                    b = b.replace("’m", "am");
                }
                if(b.charAt(i+1) == 'r'){
                    b = b.replace("’r", "are");
                }
            }
        }
        // lowerCase
        b = b.toLowerCase();
        return b;
    }

    public String nice_string2 (String a){
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
        b = b.replace("’", "’");
        b = b.replace("'", "’");
        b = b.replace("\n", "");
        // lowerCase
        b = b.toLowerCase();
        // apastrof
        for(int i=0 ; i<b.length() ; i++){
            if(b.charAt(i) == '’'){
                if(b.charAt(i+1) == 's'){
                    b = b.replace("’s", "is");
                }
                if(b.charAt(i+1) == 'm'){
                    b = b.replace("’m", "am");
                }
                if(b.charAt(i+1) == 'r'){
                    b = b.replace("’r", "are");
                }
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

    public void go_activity1(int id_at,String Status,int id_act){

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

    public void go_activity2(int id_at,String Status,int activitynumber){

        switch (id_at){

            case 1: break;
            case 2: break;

            case 3:
                A3.idlesson = idlesson ;
                A3.idfunction = idfunction ;
                A3.activitynumber = activitynumber;
                A3.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A3.class));
                break;

            case 4:
                A4.idlesson = idlesson ;
                A4.idfunction = idfunction ;
                A4.activitynumber = activitynumber;
                A4.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A4.class));
                break;

            case 5:
                A5.idlesson = idlesson ;
                A5.idfunction = idfunction ;
                A5.activitynumber = activitynumber;
                A5.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A5.class));
                break;

            case 6:
                A6.idlesson = idlesson ;
                A6.idfunction = idfunction ;
                A6.activitynumber = activitynumber;
                A6.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A6.class));
                break;

            case 7:
                A7.idlesson = idlesson ;
                A7.idfunction = idfunction ;
                A7.activitynumber = activitynumber;
                A7.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A7.class));
                break;

            case 8:
                A8.idlesson = idlesson ;
                A8.idfunction = idfunction ;
                A8.activitynumber = activitynumber;
                A8.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A8.class));
                break;

            case 9:
                A9.idlesson = idlesson ;
                A9.idfunction = idfunction ;
                A9.activitynumber = activitynumber;
                A9.Act_Status = "first";
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
                A15.activitynumber = activitynumber;
                A15.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A15.class));
                break;

            case 16: break;
            case 17: break;

            case 18:
                A18.idlesson = idlesson ;
                A18.idfunction = idfunction ;
                A18.activitynumber = activitynumber;
                A18.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A18.class));
                break;

            case 19:
                A19.idlesson = idlesson ;
                A19.idfunction = idfunction ;
                A19.activitynumber = activitynumber;
                A19.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A19.class));
                break;

            case 20:
                A20.idlesson = idlesson ;
                A20.idfunction = idfunction ;
                A20.activitynumber = activitynumber;
                A20.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A20.class));
                break;

            case 21: break;

            case 22:
                A22.idlesson = idlesson ;
                A22.idfunction = idfunction ;
                A22.activitynumber = activitynumber;
                A22.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A22.class));
                break;

            case 23: break;

            case 24:
                A24.idlesson = idlesson ;
                A24.idfunction = idfunction ;
                A24.activitynumber = activitynumber;
                A24.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A24.class));
                break;

            case 25:
                //A25.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A25.activitynumber = activitynumber;
                A25.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A25.class));
                break;

            case 26:
                A26.idlesson = idlesson ;
                A26.idfunction = idfunction ;
                A26.activitynumber = activitynumber;
                A26.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A26.class));
                break;

            case 27:
                A27.idlesson = idlesson ;
                A27.idfunction = idfunction ;
                A27.activitynumber = activitynumber;
                A27.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A27.class));
                break;

            case 28:
                A28.idlesson = idlesson ;
                A28.idfunction = idfunction ;
                A28.activitynumber = activitynumber;
                A28.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A28.class));
                break;

            case 29:
                A29.idlesson = idlesson ;
                A29.idfunction = idfunction ;
                A29.activitynumber = activitynumber;
                A29.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A29.class));
                break;

            case 30:
                A30.idlesson = idlesson ;
                A30.idfunction = idfunction ;
                A30.activitynumber = activitynumber;
                A30.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A30.class));
                break;

            case 31:
                //A31.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A31.activitynumber = activitynumber;
                //A31.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A31.class));
                break;

            case 32:
                A32.idlesson = idlesson ;
                A32.idfunction = idfunction ;
                A32.activitynumber = activitynumber;
                A32.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A32.class));
                break;

            case 33:
                A33.idlesson = idlesson ;
                A33.idfunction = idfunction ;
                A33.activitynumber = activitynumber;
                A33.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A33.class));
                break;

            case 34:
                A34.idlesson = idlesson ;
                A34.idfunction = idfunction ;
                A34.activitynumber = activitynumber;
                A34.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A34.class));
                break;

            case 35:
                A35.idlesson = idlesson ;
                A35.idfunction = idfunction ;
                A35.activitynumber = activitynumber;
                A35.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A35.class));
                break;

            case 36: break;

            case 37:
                A37.idlesson = idlesson ;
                A37.idfunction = idfunction ;
                A37.activitynumber = activitynumber;
                A37.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A37.class));
                break;

            case 38:
                //A38.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A38.activitynumber = activitynumber;
                //  A38.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A38.class));
                break;

            case 39:
                A39.idlesson = idlesson ;
                A39.idfunction = idfunction ;
                A39.activitynumber = activitynumber;
                A39.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A39.class));

                break;

            case 40:
                //A40.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A40.activitynumber = activitynumber;
                //  A40.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A40.class));
                break;

            case 41:
                //A41.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A41.activitynumber = activitynumber;
                //  A41.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A41.class));
                break;

            case 42:
                //A42.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A42.activitynumber = activitynumber;
                //  A42.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A42.class));
                break;

            case 43:
                //A43.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A43.activitynumber = activitynumber;
                //  A43.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A43.class));
                break;

            case 44:
                //A44.idlesson = idlesson ;
                // A.idfunction = idfunction ;
                //A44.activitynumber = activitynumber;
                //  A44.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A44.class));
                break;

            case 46:
                A46.idlesson = idlesson ;
                A46.idfunction = idfunction ;
                A46.activitynumber = activitynumber;
                A46.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A46.class));
                break;
        }
    }
    /*public void getImage(String path){
        path1=path;
        String img_url = url_download + path1;
        RequestQueue newRequest= Volley.newRequestQueue(BaseActivity.this);
        imageLoader = new ImageLoader(newRequest, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        img.setImageUrl(img_url,imageLoader);
    }*/
}