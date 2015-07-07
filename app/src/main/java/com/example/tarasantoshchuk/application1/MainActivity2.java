package com.example.tarasantoshchuk.application1;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity2 extends Activity {
    /**
     * duration of animation that moves mTxtMovable
     */
    private static final int DURATION = 1000;

    /**
     * variable for TextView mTxtMovable from activity_main2 layout
     */
    private TextView mTxtMovable;

    /**
     * sets content view
     * initializes mTxtMovable TextView
     * sets OnTouchListener for relativeLayout from activity_main2
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         * variable for button btnGoTo3 from activity_main2 layout
         */
        Button btnGoTo3 = (Button) findViewById(R.id.btnGoTo3);
        btnGoTo3.setOnClickListener(new View.OnClickListener() {
            /**
             * starts next task's main activity
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mTxtMovable = (TextView) findViewById(R.id.txtMovable);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            /**
             * starts animation via ValueAnimator to move mTxtMovable from current position
             * to event (touch) position
             */
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                ValueAnimator animatorX = ObjectAnimator
                        .ofFloat(mTxtMovable, "X", mTxtMovable.getX(), event.getX());
                ValueAnimator animatorY = ObjectAnimator
                        .ofFloat(mTxtMovable, "Y", mTxtMovable.getY(), event.getY());
                animatorX.setDuration(DURATION);
                animatorY.setDuration(DURATION);
                animatorX.start();
                animatorY.start();
                return true;
            }
        });
    }
}