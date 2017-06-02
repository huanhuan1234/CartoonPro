package com.example.xiaowai.cartoonpro.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xiaowai.cartoonpro.R;

public class BackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        initView();
    }

    private void initView() {
        back_btn = (Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("currentId",0);
                edit.putBoolean("flag",false);
                edit.commit();
                Intent intent2 = getIntent();
                setResult(201,intent2);
                finish();
                break;
        }
    }
}
