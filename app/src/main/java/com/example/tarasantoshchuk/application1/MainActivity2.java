package com.example.tarasantoshchuk.application1;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity2 extends Activity {
    /**
     * duration of animation that moves txtMovable
     */
    private static final int DURATION = 1000;

    /**
     * variable for TextView txtMovable from activity_main layout
     */
    private TextView txtMovable;

    /**
     * sets content view
     * initializes txtMovable TextView
     * sets OnTouchListener for relativeLayout from activity_main
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        txtMovable = (TextView) findViewById(R.id.txtMovable);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            /**
             * starts animation via ValueAnimator to move txtMovable from current position
             * to event (touch) position
             */
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                ValueAnimator animatorX = ObjectAnimator
                        .ofFloat(txtMovable, "X", txtMovable.getX(), event.getX());
                ValueAnimator animatorY = ObjectAnimator
                        .ofFloat(txtMovable, "Y", txtMovable.getY(), event.getY());
                animatorX.setDuration(DURATION);
                animatorY.setDuration(DURATION);
                animatorX.start();
                animatorY.start();
                return true;
            }
        });
    }
}