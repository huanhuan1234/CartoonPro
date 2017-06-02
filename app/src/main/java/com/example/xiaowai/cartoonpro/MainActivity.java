package com.example.xiaowai.cartoonpro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xiaowai.cartoonpro.fragment.fragmentComic;
import com.example.xiaowai.cartoonpro.fragment.fragmentDiscover;
import com.example.xiaowai.cartoonpro.fragment.fragmentMe;
import com.example.xiaowai.cartoonpro.fragment.fragmentSocial;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private fragmentComic fc=new fragmentComic();
    private fragmentDiscover fd=new fragmentDiscover();
    private fragmentSocial fs=new fragmentSocial();
    private fragmentMe fm=new fragmentMe();
    private RadioGroup radiogroup_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.rela_fragment,fc,"comic");
        transaction.commit();
        findView();
    }

    private void findView(){
        radiogroup_bottom = (RadioGroup) findViewById(R.id.radiogroup_bottom);
        radiogroup_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbCartoon:
                        manager.beginTransaction().replace(R.id.rela_fragment,fc,"comic").commit();
                        break;
                    case R.id.rbDiscover:
                        manager.beginTransaction().replace(R.id.rela_fragment,fd,"discover").commit();
                        break;
                    case R.id.rbSocial:
                        manager.beginTransaction().replace(R.id.rela_fragment,fs,"social").commit();
                        break;
                    case R.id.rbMine:
                        manager.beginTransaction().replace(R.id.rela_fragment,fm,"me").commit();
                        break;
                }
            }
        });
    }

}
