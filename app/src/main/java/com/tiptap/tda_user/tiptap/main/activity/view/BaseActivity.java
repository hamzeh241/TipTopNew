package com.tiptap.tda_user.tiptap.main.activity.view;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.tiptap.tda_user.tiptap.main.activity.Interface.MVP_Main;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivity;
import com.tiptap.tda_user.tiptap.main.activity.ViewModel.TbActivityDetail;
import com.tiptap.tda_user.tiptap.main.activity.view.activity.*;
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
    public TextView txt1,txt2,t1,t2,txt,txt3;
    public Button next, play, isplay;
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
            // when the last charecter have '
            if(i == b.length()-1){
            }else{
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
        return b;
    }

    /*public void SeekBarProgressUpdater() {
        seekBar.setProgress((int)(((float)mp.getCurrentPosition()/mpLength)*100));
        if (mp.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    SeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
    }*/

    public Context getActivityContext() {
        return this;
    }

    public Context getAppContext() {  return getApplicationContext(); }

    public void go_activity1(int id_at,String Status,int id_act){

        switch (id_at) {

            case 1:
                A1.idlesson = idlesson ;
                A1.idfunction = idfunction ;
                A1.idactivity = id_act;
                A1.Act_Status = Status;
                startActivity(new Intent(getApplicationContext(),  A1.class));
                this.finish();
                break;

            case 2:
                A2.idlesson = idlesson ;
                A2.idfunction = idfunction ;
                A2.idactivity = id_act;
                A2.Act_Status = Status;
                startActivity(new Intent(getApplicationContext(),  A2.class));
                this.finish();
                break;

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

            case 10:
                A10.idlesson = idlesson ;
                A10.idfunction = idfunction ;
                A10.idactivity = id_act;
                A10.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A10.class));
                break;

            case 11:
                A11.idlesson = idlesson ;
                A11.idfunction = idfunction ;
                A11.idactivity = id_act;
                A11.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A11.class));
                break;

            case 12:
                A12.idlesson = idlesson ;
                A12.idfunction = idfunction ;
                A12.idactivity = id_act;
                A12.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A12.class));
                break;

            case 13:
                A13.idlesson = idlesson ;
                A13.idfunction = idfunction ;
                A13.idactivity = id_act;
                A13.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A13.class));
                break;

            case 14:
                A14.idlesson = idlesson ;
                A14.idfunction = idfunction ;
                A14.idactivity = id_act;
                A14.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A14.class));
                break;

            case 15:
                A15.idlesson = idlesson ;
                A15.idfunction = idfunction ;
                A15.idactivity = id_act;
                A15.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A15.class));
                break;

            case 16:
                A16.idlesson = idlesson ;
                A16.idfunction = idfunction ;
                A16.idactivity = id_act;
                A16.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A16.class));
                break;

            case 17:
                A17.idlesson = idlesson ;
                A17.idfunction = idfunction ;
                A17.idactivity = id_act;
                A17.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A17.class));
                break;

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

            case 21:
                A21.idlesson = idlesson ;
                A21.idfunction = idfunction ;
                A21.idactivity = id_act;
                A21.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A21.class));
                break;

            case 22:
                A22.idlesson = idlesson ;
                A22.idfunction = idfunction ;
                A22.idactivity = id_act;
                A22.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A22.class));
                break;

            case 23:
               /* A23.idlesson = idlesson ;
                A23.idfunction = idfunction ;
                A23.idactivity = id_act;
                A23.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A23.class));
                break;*/

            case 24:
                A24.idlesson = idlesson ;
                A24.idfunction = idfunction ;
                A24.idactivity = id_act;
                A24.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A24.class));
                break;

            case 25:
                A25.idlesson = idlesson ;
                A25 .idfunction = idfunction ;
                A25.idactivity = id_act;
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
                A31.idlesson = idlesson ;
                A31.idfunction = idfunction ;
                A31.idactivity = id_act;
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

            case 36:
                /*A36.idlesson = idlesson ;
                A36.idfunction = idfunction ;
                A36.idactivity = id_act;
                A36.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A36.class));
                break;*/

            case 37:
                A37.idlesson = idlesson ;
                A37.idfunction = idfunction ;
                A37.idactivity = id_act;
                A37.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A37.class));
                break;

            case 38:
                A38.idlesson = idlesson ;
                A38.idfunction = idfunction ;
                A38.idactivity = id_act;
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
                A40.idlesson = idlesson ;
                A40.idfunction = idfunction ;
                A40.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A40.class));
                break;

            case 41:
                A41.idlesson = idlesson ;
                A41.idfunction = idfunction ;
                A41.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A41.class));
                break;

            case 42:
                A42.idlesson = idlesson ;
                A42.idfunction = idfunction ;
                A42.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A42.class));
                break;

            case 43:
                A43.idlesson = idlesson ;
                A43.idfunction = idfunction ;
                A43.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A43.class));
                break;

            case 44:
                A44.idlesson = idlesson ;
                A44.idfunction = idfunction ;
                A44.idactivity = id_act;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A44.class));
                break;

            case 45:
                A45.idlesson = idlesson ;
                A45.idfunction = idfunction ;
                A45.idactivity = id_act;
                A45.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A45.class));
                break;

            case 46:
                A46.idlesson = idlesson ;
                A46.idfunction = idfunction ;
                A46.idactivity = id_act;
                A46.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A46.class));
                break;

            case 47:
                A47.idlesson = idlesson ;
                A47.idfunction = idfunction ;
                A47.idactivity = id_act;
                A47.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A47.class));
                break;

            case 48:
                A48.idlesson = idlesson ;
                A48.idfunction = idfunction ;
                A48.idactivity = id_act;
                A48.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A48.class));
                break;

            case 49:
                A49.idlesson = idlesson ;
                A49.idfunction = idfunction ;
                A49.idactivity = id_act;
                A49.Act_Status = Status;
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A49.class));
                break;
        }
    }

    public void go_activity2(int id_at,String Status,int activitynumber){

        switch (id_at){

            case 1:
                A1.idlesson = idlesson ;
                A1.idfunction = idfunction ;
                A1.activitynumber = activitynumber;
                A1.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A1.class));
                break;

            case 2:
                A2.idlesson = idlesson ;
                A2.idfunction = idfunction ;
                A2.activitynumber = activitynumber;
                A2.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A2.class));
                break;

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

            case 10:
                A10.idlesson = idlesson ;
                A10.idfunction = idfunction ;
                A10.activitynumber = activitynumber;
                A10.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A10.class));
                break;

            case 11:
                A11.idlesson = idlesson ;
                A11.idfunction = idfunction ;
                A11.activitynumber = activitynumber;
                A11.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A11.class));
                break;

            case 12:
                A12.idlesson = idlesson ;
                A12.idfunction = idfunction ;
                A12.activitynumber = activitynumber;
                A12.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A12.class));
                break;

            case 13:
                A13.idlesson = idlesson ;
                A13.idfunction = idfunction ;
                A13.activitynumber = activitynumber;
                A13.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A13.class));
                break;

            case 14:
                A14.idlesson = idlesson ;
                A14.idfunction = idfunction ;
                A14.activitynumber = activitynumber;
                A14.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A14.class));
                break;

            case 15:
                A15.idlesson = idlesson ;
                A15.idfunction = idfunction ;
                A15.activitynumber = activitynumber;
                A15.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A15.class));
                break;

            case 16:
                A16.idlesson = idlesson ;
                A16.idfunction = idfunction ;
                A16.activitynumber = activitynumber;
                A16.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A16.class));
                break;

            case 17:
                A17.idlesson = idlesson ;
                A17.idfunction = idfunction ;
                A17.activitynumber = activitynumber;
                A17.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A17.class));
                break;

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

            case 21:
                A21.idlesson = idlesson ;
                A21.idfunction = idfunction ;
                A21.activitynumber = activitynumber;
                A21.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A21.class));
                break;

            case 22:
                A22.idlesson = idlesson ;
                A22.idfunction = idfunction ;
                A22.activitynumber = activitynumber;
                A22.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A22.class));
                break;

            case 23:
                /*A23.idlesson = idlesson ;
                A23.idfunction = idfunction ;
                A23.activitynumber = activitynumber;
                A23.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A23.class));
                break;*/

            case 24:
                A24.idlesson = idlesson ;
                A24.idfunction = idfunction ;
                A24.activitynumber = activitynumber;
                A24.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A24.class));
                break;

            case 25:
                A25.idlesson = idlesson ;
                A25.idfunction = idfunction ;
                A25.activitynumber = activitynumber;
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
                A31.idlesson = idlesson ;
                A31.idfunction = idfunction ;
                A31.activitynumber = activitynumber;
                A31.Act_Status = "first";
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

            case 36:
                /*A36.idlesson = idlesson ;
                A36.idfunction = idfunction ;
                A36.activitynumber = activitynumber;
                A36.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A36.class));
                break;*/

            case 37:
                A37.idlesson = idlesson ;
                A37.idfunction = idfunction ;
                A37.activitynumber = activitynumber;
                A37.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A37.class));
                break;

            case 38:
                A38.idlesson = idlesson ;
                A38.idfunction = idfunction ;
                A38.activitynumber = activitynumber;
                A38.Act_Status = "first";
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
                A40.idlesson = idlesson ;
                A40.idfunction = idfunction ;
                A40.activitynumber = activitynumber;
                A40.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A40.class));
                break;

            case 41:
                A41.idlesson = idlesson ;
                A41.idfunction = idfunction ;
                A41.activitynumber = activitynumber;
                A41.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A41.class));
                break;

            case 42:
                A42.idlesson = idlesson ;
                A42.idfunction = idfunction ;
                A42.activitynumber = activitynumber;
                A42.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A42.class));
                break;

            case 43:
                A43.idlesson = idlesson ;
                A43.idfunction = idfunction ;
                A43.activitynumber = activitynumber;
                A43.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A43.class));
                break;

            case 44:
                A44.idlesson = idlesson ;
                A44.idfunction = idfunction ;
                A44.activitynumber = activitynumber;
                A44.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A44.class));
                break;

            case 45:
                A45.idlesson = idlesson ;
                A45.idfunction = idfunction ;
                A45.activitynumber = activitynumber;
                A45.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A45.class));
                break;

            case 46:
                A46.idlesson = idlesson ;
                A46.idfunction = idfunction ;
                A46.activitynumber = activitynumber;
                A46.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A46.class));
                break;

            case 47:
                A47.idlesson = idlesson ;
                A47.idfunction = idfunction ;
                A47.activitynumber = activitynumber;
                A47.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A47.class));
                break;

            case 48:
                A48.idlesson = idlesson ;
                A48.idfunction = idfunction ;
                A48.activitynumber = activitynumber;
                A48.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A48.class));
                break;

            case 49:
                A49.idlesson = idlesson ;
                A49.idfunction = idfunction ;
                A49.activitynumber = activitynumber;
                A49.Act_Status = "first";
                this.finish();
                startActivity(new Intent(getApplicationContext(),  A49.class));
                break;
        }
    }
}