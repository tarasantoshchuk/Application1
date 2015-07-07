package com.example.tarasantoshchuk.application1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends Activity {

    private EditText mEditTextString;
    private EditText mEditTextString1;
    private EditText mEditTextString2;
    private EditText mEditTextString3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mEditTextString = (EditText) findViewById(R.id.editTextString);
        mEditTextString1 = (EditText) findViewById(R.id.editTextString1);
        mEditTextString2 = (EditText) findViewById(R.id.editTextString2);
        mEditTextString3 = (EditText) findViewById(R.id.editTextString3);
        Button btnClass = (Button) findViewById(R.id.btnClass);
        Button btnString = (Button) findViewById(R.id.btnString);
        btnClass.setOnClickListener(new View.OnClickListener() {
            /**
             * puts string from textViewString to intent extras and starts new activity
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, RecipientActivity3.class);
                CustomClass custom = new CustomClass(
                        mEditTextString1.getText().toString(),
                        mEditTextString2.getText().toString(),
                        mEditTextString3.getText().toString()
                );
                Bundle bundle = RecipientActivity3.getStartExtras(custom);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnString.setOnClickListener(new View.OnClickListener() {
            /**
             * puts CustomClass instance made from strings in textViewString1-3 to intent extras
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, RecipientActivity3.class);
                Bundle bundle = RecipientActivity3
                        .getStartExtras(mEditTextString.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

