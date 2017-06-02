package com.example.xiaowai.cartoonpro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.MainActivity;
import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.FiveBean;
import com.google.gson.Gson;

public class HotWebViewActivity extends AppCompatActivity {

    private TextView tvtitle;
    private TextView tvpart;
    private RelativeLayout title_rela;
    private RelativeLayout title_bottom;
    private WebView wv;
    private String duixiang;
    private FiveBean.DataBean.ComicsBean comicsBean;
    private Button btnreturn;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_web_view);
        Intent intent = getIntent();
        duixiang = intent.getStringExtra("duixiang");
        position = intent.getIntExtra("position",-1);
        findView();
        initControl();
    }

    private void initControl() {
        Gson gson=new Gson();
        comicsBean = gson.fromJson(duixiang, FiveBean.DataBean.ComicsBean.class);
        tvtitle.setText(comicsBean.getTitle()+"");
        tvpart.setText(comicsBean.getTopic().getUpdate_status());
        wv.loadUrl(comicsBean.getUrl());
        wv.setWebViewClient(new WebViewClient());
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HotWebViewActivity.this,HotRelaActivity.class);
                intent.putExtra("duixiang",duixiang);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        tvtitle = (TextView) findViewById(R.id.textView7);
        tvpart = (TextView) findViewById(R.id.textView8);
        title_rela = (RelativeLayout) findViewById(R.id.hot_webview_title_rela);
        title_bottom = (RelativeLayout) findViewById(R.id.hot_webview_bottom);
        btnreturn = (Button) findViewById(R.id.button4);
        wv = (WebView) findViewById(R.id.hot_webview);
    }
}
