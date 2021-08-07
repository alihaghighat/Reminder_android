package com.example.reminder;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar =findViewById(R.id.progressbar);
        textView =findViewById(R.id.darsadSplash);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnmite();





    }



    public void progressAnmite(){
        ProgressBarAnimations amin = new ProgressBarAnimations( this , progressBar ,textView ,0f ,100f);
        amin.setDuration(8000);
        progressBar.setAnimation(amin);

    }
}