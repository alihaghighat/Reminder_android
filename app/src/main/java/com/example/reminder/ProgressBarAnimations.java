package com.example.reminder;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimations extends Animation {
    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private  float from;
    private  float to;

    public ProgressBarAnimations (Context context ,ProgressBar progressBar ,TextView textView, float from ,float to){
        this.context = context;
        this.progressBar=progressBar;
        this.textView= textView;
        this.from = from;
        this.to =to;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float vlalue= from +(to - from) * interpolatedTime;
        progressBar.setProgress((int) vlalue);
        textView.setText((int) vlalue + " %");
        if (to == vlalue) {
            Intent intent = new Intent(context, home.class);
            context.startActivity(intent);
        }
    }
}
