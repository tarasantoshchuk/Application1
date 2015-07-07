package com.example.tarasantoshchuk.application1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipientActivity3 extends Activity {
    private static final String EXTRA_CLASS = "CLASS";
    private static final String EXTRA_STRING = "STRING";

    private TextView mTextViewString;
    private TextView mTextViewString1;
    private TextView mTextViewString2;
    private TextView mTextViewString3;

    /**
     * methods to get bundle with appropriate extras
     */
    public static Bundle getStartExtras(CustomClass custom) {
        Bundle bundle = new Bundle();
        if(custom != null) {
            bundle.putParcelable(EXTRA_CLASS, custom);
        }
        return bundle;
    }

    public static Bundle getStartExtras(String string) {
        Bundle bundle = new Bundle();
        if(string != null) {
            bundle.putString(EXTRA_STRING, string);
        }
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);
        mTextViewString = (TextView) findViewById(R.id.textViewString);
        mTextViewString1 = (TextView) findViewById(R.id.textViewString1);
        mTextViewString2 = (TextView) findViewById(R.id.textViewString2);
        mTextViewString3 = (TextView) findViewById(R.id.textViewString3);
        initTextView();
    }

    /**
     * gets extras from intent and update text views
     */
    private void initTextView() {
        Intent intent = getIntent();
        if(intent.getStringExtra(EXTRA_STRING) != null) {
            mTextViewString.setText(intent
                    .getStringExtra(EXTRA_STRING));
        }
        if(intent.getParcelableExtra(EXTRA_CLASS) != null) {
            CustomClass custom = intent
                    .getParcelableExtra(EXTRA_CLASS);
            mTextViewString1.setText(custom.getStr1());
            mTextViewString2.setText(custom.getStr2());
            mTextViewString3.setText(custom.getStr3());
        }
    }
}
