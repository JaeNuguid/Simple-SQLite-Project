package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){
            @Override
            public void run(){
            try{
                sleep(5000);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
               finish();
            }catch (Exception e){
                e.printStackTrace();
            }

            }
        };
        thread.start();
    }
}
