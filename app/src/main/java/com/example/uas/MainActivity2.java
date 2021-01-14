package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_cmp;
    Button btn_cmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_cmp = findViewById(R.id.company_name);
        btn_cmp = findViewById(R.id.btn_to_main);
        btn_cmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct();
            }
        });

    }
    void mainAct(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}