package com.example.xiaowai.cartoonpro.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.UserBean;
import com.example.xiaowai.cartoonpro.db.MyDbHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register_return;
    private EditText registerphone;
    private EditText registerpwd;
    private Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        initControl();
    }

    private void initControl() {
        register_return.setOnClickListener(this);
        btnregister.setOnClickListener(this);
    }

    private void findView() {
        register_return = (Button) findViewById(R.id.register_return);
        registerphone = (EditText) findViewById(R.id.registerphone);
        registerpwd = (EditText) findViewById(R.id.registerpwd);
        btnregister = (Button) findViewById(R.id.btnregister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_return:
                finish();
                break;
            case R.id.btnregister:
                MyDbHelper helper=new MyDbHelper(this);
                SQLiteDatabase db = helper.getReadableDatabase();
                UserBean ub=new UserBean();
                ub.setUsername(registerphone.getText().toString().trim());
                ub.setUserpwd(registerpwd.getText().toString().trim());
                helper.insertData(db,ub);
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT);
                registerphone.setText("");
                registerpwd.setText("");
                finish();
                break;
        }
    }
}
