package com.tiptap.tda_user.tiptap.main.activity.view.activity;

public class a46_mod {

     /*switch (count){

            // 1 ja xali
            /////////////////////////////////  ad1 (1)  /////////////////////////////
            case 1:

                int baxsh11 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh11++;
                    }
                }

                switch (baxsh11){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }
                break;

            // 2 ja xali
            /////////////////////////////////  ad1 (2)  /////////////////////////////
            case 2:

                int baxsh21 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh21++;
                    }
                }

                switch (baxsh21){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad2 (2)  /////////////////////////////
                int baxsh22 = 0;
                for(int i=0 ; i<ad2.length() ; i++){
                    if(ad2.charAt(i) == ','){
                        baxsh22++;
                    }
                }

                switch (baxsh22){
                    // 1 baxsh
                    case 0:
                        z2 = ad2.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad2.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z2 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z2[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad2.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z2 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                break;

            // 3 ja xali
            /////////////////////////////////  ad1 (3)  /////////////////////////////
            case 3:

                int baxsh31 = 0;
                for(int i=0 ; i<ad1.length() ; i++){
                    if(ad1.charAt(i) == ','){
                        baxsh31++;
                    }
                }

                switch (baxsh31){
                    // 1 baxsh
                    case 0:
                        z1 = ad1.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad1.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z1 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad1.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z1 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z1[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad2 (3)  /////////////////////////////
                int baxsh32 = 0;
                for(int i=0 ; i<ad2.length() ; i++){
                    if(ad2.charAt(i) == ','){
                        baxsh32++;
                    }
                }

                switch (baxsh32){
                    // 1 baxsh
                    case 0:
                        z2 = ad2.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad2.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z2 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z1[count2] = x[i] + " " +y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad2.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z2 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z2[count3] = b[i] + " " + c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }

                /////////////////////////////////  ad3 (3) /////////////////////////////
                int baxsh33 = 0;
                for(int i=0 ; i<ad3.length() ; i++){
                    if(ad3.charAt(i) == ','){
                        baxsh33++;
                    }
                }

                switch (baxsh33){
                    // 1 baxsh
                    case 0:
                        z3 = ad3.split("/");
                        break;

                    // 2 baxsh
                    case 1:
                        String[] s = ad3.split(",");
                        String[] x = s[0].split("/");
                        String[] y = s[1].split("/");

                        z3 = new String[x.length * y.length];
                        int count2 = 0;
                        for (int i = 0; i < x.length; i++) {
                            for (int j = 0; j < y.length; j++) {
                                z3[count2] = x[i] + " " + y[j];
                                count2++;
                            }
                        }
                        break;

                    // 3 baxsh
                    case 2:
                        String[] a = ad3.split(",");
                        String[] b = a[0].split("/");
                        String[] c = a[1].split("/");
                        String[] d = a[2].split("/");

                        z3 = new String[b.length * c.length* d.length];
                        int count3 = 0;
                        for (int i = 0; i < b.length ; i++) {
                            for (int j = 0; j < c.length ; j++) {
                                for(int k = 0 ; k < d.length ; k++){
                                    z3[count3] = b[i] + " " +c[j] + " " + d[k];
                                    count3++;
                                }
                            }
                        }
                        break;
                }
                break;
        }*/




    // String w = title1; // "..., I’m David.\r\nhello, my name … Fred. \r\nnice to meet...\r\nnice… you too"
        /*String w = "..., I’m David.hello, my name … Fred. nice to meet...nice… you too";

        String now = "txt";

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int added = 1;

        for(int i=0 ; i<total ; i++){

            final int finalId_e = id_e;

            // start
            if(i == 0 ){
                if(start == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    e[id_e].addTextChangedListener(new CheckEdit());
                    if(added < 3){
                        l[0].addView(e[id_e]);
                    }
                    if(added > 2 && added < 6){
                        l[1].addView(e[id_e]);
                    }
                    if(added == 6 || added == 7){
                        l[2].addView(e[id_e]);
                    }
                    if(added > 7 && added < 11){
                        l[3].addView(e[id_e]);
                    }
                    added++;
                    id_e++;
                }

                if(start == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    if(added < 3){
                        l[0].addView(t[id_t]);
                    }
                    if(added > 2 && added < 6){
                        l[1].addView(t[id_t]);
                    }
                    if(added == 6 || added == 7){
                        l[2].addView(t[id_t]);
                    }
                    if(added > 7 && added < 11){
                        l[3].addView(t[id_t]);
                    }
                    added++;
                    now = "edt";
                    id_w++;
                    id_t++;
                }
            }

            if(i!=0 && i!=total-1){
                // textview
                switch (now) {
                    case "txt":
                        t[id_t] = new TextView(this);
                        t[id_t].setLayoutParams(params);
                        t[id_t].setText(list_w[id_w]);
                        t[id_t].setTextSize(16);
                        if(added < 3){
                            l[0].addView(t[id_t]);
                        }
                        if(added > 2 && added < 6){
                            l[1].addView(t[id_t]);
                        }
                        if(added == 6 || added == 7){
                            l[2].addView(t[id_t]);
                        }
                        if(added > 7 && added < 11){
                            l[3].addView(t[id_t]);
                        }
                        added++;
                        now = "edt";
                        id_w++;
                        id_t++;
                        break;

                    case "edt":
                        e[id_e] = new EditText(this);
                        e[id_e].setLayoutParams(params);
                        e[id_e].setEms(4);
                        e[id_e].setTextSize(16);
                        e[id_e].addTextChangedListener(new CheckEdit());
                        if(added < 3){
                            l[0].addView(e[id_e]);
                        }
                        if(added > 2 && added < 6){
                            l[1].addView(e[id_e]);
                        }
                        if(added == 6 || added == 7){
                            l[2].addView(e[id_e]);
                        }
                        if(added > 7 && added < 11){
                            l[3].addView(e[id_e]);
                        }
                        added++;
                        now = "txt";
                        id_e++;
                        break;
                }
            }

            // end
            if(i == total-1){
                if(end == 1){
                    e[id_e] = new EditText(this);
                    e[id_e].setLayoutParams(params);
                    e[id_e].setEms(4);
                    e[id_e].setTextSize(16);
                    e[id_e].addTextChangedListener(new CheckEdit());
                    if(added < 3){
                        l[0].addView(e[id_e]);
                    }
                    if(added > 2 && added < 6){
                        l[1].addView(e[id_e]);
                    }
                    if(added == 6 || added == 7){
                        l[2].addView(e[id_e]);
                    }
                    if(added > 7 && added < 11){
                        l[3].addView(e[id_e]);
                    }
                    added++;
                    id_e++;
                }

                if(end == 0){
                    t[id_t] = new TextView(this);
                    t[id_t].setLayoutParams(params);
                    t[id_t].setText(list_w[id_w]);
                    t[id_t].setTextSize(16);
                    if(added < 3){
                        l[0].addView(t[id_t]);
                    }
                    if(added > 2 && added < 6){
                        l[1].addView(t[id_t]);
                    }
                    if(added == 6 || added == 7){
                        l[2].addView(t[id_t]);
                    }
                    if(added > 7 && added < 11){
                        l[3].addView(t[id_t]);
                    }
                    added++;
                    now = "edt";
                    id_w++;
                    id_t++;
                }

            }
        }*/

        ////////////////////////////////////////////////////////////////////////////////////////////////////
    /*boolean answer[];
        switch (count){
            case 1:
                answer = new boolean[1];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                if(answer[0]){
                    final_answer = true;
                }
                break;

            case 2 :
                answer = new boolean[2];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                for(int i = 0 ; i < z2.length ; i++){
                    String a = nice_string(e[1].getText().toString());
                    String b = nice_string(z2[i]);
                    if(a.equals(b)){
                        answer[1] = true;
                    }
                }
                if(answer[0]){
                    if(answer[1]){
                        final_answer = true;
                    }
                }
                break;

            case 3:
                answer = new boolean[3];
                for(int i = 0 ; i < z1.length ; i++){
                    String a = nice_string(e[0].getText().toString());
                    String b = nice_string(z1[i]);
                    if(a.equals(b)){
                        answer[0] = true;
                    }
                }
                for(int i = 0 ; i < z2.length ; i++){
                    String a = nice_string(e[1].getText().toString());
                    String b = nice_string(z2[i]);
                    if(a.equals(b)){
                        answer[1] = true;
                    }
                }

                for(int i = 0 ; i < z3.length ; i++){
                    String a = nice_string(e[2].getText().toString());
                    String b = nice_string(z3[i]);
                    if(a.equals(b)){
                        answer[2] = true;
                    }
                }
                if(answer[0]){
                    if(answer[1]) {
                        if (answer[2]) {
                            final_answer = true;
                        }
                    }
                }
                break;
        }*/
}
