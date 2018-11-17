package com.example.delano.flashycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class add_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        //Able to finish intent
        findViewById(R.id.cancel_add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //On save button, transfer data to main activity
        findViewById(R.id.save_add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.questionTextField)).getText().toString();
                String answer =  ((EditText) findViewById(R.id.answerTextField)).getText().toString();

                Intent data = new Intent();
                data.putExtra("string1", question);
                data.putExtra("string2", answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
