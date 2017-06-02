package com.example.xiaowai.cartoonpro.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.UserBean;
import com.example.xiaowai.cartoonpro.db.MyDbHelper;

import java.util.List;

public class HotFollowActivity extends AppCompatActivity {

    private EditText etpwd;
    private EditText etphone;
    private Button followReturn;
    private ImageView img;
    private Button btnlogin;
    private TextView tverror;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private MyDbHelper helper;
    private Boolean currentBoo=false;
    private TextView tvregister;
    private int mCurrentId;
    private String pic;
    private String nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_follow);
        findView();
        initControl();
    }

    private void initControl() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        edit = sp.edit();
        followReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etphone.setFocusable(true);
        etpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    img.setImageResource(R.drawable.ic_login_invisible);
                }
            }
        });
        etphone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    img.setImageResource(R.drawable.ic_login_visible);
                }
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etphone.getText().toString().trim()) || TextUtils.isEmpty(etpwd.getText().toString().trim())){
                    tverror.setText("密码账号不能为空");
                }else{
                    SQLiteDatabase db = helper.getReadableDatabase();
                    List<UserBean> data = helper.findData(db);
                    for(UserBean ub:data){
                        if(ub.getUsername().equals(etphone.getText().toString().trim())&& ub.getUserpwd().equals(etpwd.getText().toString().trim())){
                            currentBoo=true;
                            mCurrentId = ub.getId();
                            pic = ub.getPic();
                            nickname = ub.getNickname();
                            break;
                        }
                    }
                    Log.d("zzz",mCurrentId+"mCurrentId:");
                    Log.d("zzz",currentBoo+"currentBoo:");
                    if(currentBoo){
                        tverror.setText("");
                        edit.putBoolean("flag",true);
                        edit.putInt("currentId",mCurrentId);
                        edit.commit();
                        Intent intent2 = getIntent();
                        intent2.putExtra("tximage",pic);
                        intent2.putExtra("txnickname",nickname);
                        setResult(202,intent2);
                        finish();
                    }else{
                        tverror.setText("账号或密码输入错误");
                    }
                }
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HotFollowActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findView() {
        helper = new MyDbHelper(this);
        etpwd = (EditText) findViewById(R.id.edipwd);
        etphone = (EditText) findViewById(R.id.editphone);
        followReturn = (Button) findViewById(R.id.hot_follow_return);
        img = (ImageView) findViewById(R.id.imageView2);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        tverror = (TextView) findViewById(R.id.textError);
        tvregister = (TextView) findViewById(R.id.textRegister);
    }

}
