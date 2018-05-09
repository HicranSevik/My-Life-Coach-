package com.sevik.hicran.deneme2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class kapak extends Activity {
    private static int SPLASH_TIME_OUT=4000;


            @Override
            public void onCreate(Bundle icicle) {
                super.onCreate(icicle);
                setContentView(R.layout.activity_kapak);

                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(3000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(kapak.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                };
                timerThread.start();
            }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


            }


