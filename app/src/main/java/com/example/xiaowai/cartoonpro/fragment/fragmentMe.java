package com.example.xiaowai.cartoonpro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.activity.BackActivity;
import com.example.xiaowai.cartoonpro.activity.EditPageActivity;
import com.example.xiaowai.cartoonpro.activity.HotFollowActivity;
import com.example.xiaowai.cartoonpro.bean.UserBean;
import com.example.xiaowai.cartoonpro.db.MyDbHelper;

import java.io.IOException;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentMe  extends Fragment{

    private View view;
    private ImageView me_tx;
    private TextView me_nickname;
    private MyDbHelper mHelper;
    private int mCurrentId;
    private boolean mFlag;
    private SharedPreferences mSp;
    private TextView setting_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me,null);
        findView();
        initControl();
        return view;
    }

    private void initControl() {
        mSp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        mFlag = mSp.getBoolean("flag", false);
        mCurrentId = mSp.getInt("currentId", 0);
        mHelper = new MyDbHelper(getActivity());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<UserBean> data = mHelper.findData(db);
        for(UserBean user:data){
            if(user.getId()== mCurrentId){
                me_nickname.setText(user.getNickname()+"");
                try {
                    if(user.getPic()!=null){
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(user.getPic()));
                        me_tx.setImageBitmap(bitmap);
                    }
                 } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        initListener();
    }

    private void initListener() {
        if(mFlag){
            me_tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), EditPageActivity.class);
                    startActivityForResult(intent,20);
                }
            });
            setting_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), BackActivity.class);
                    startActivityForResult(intent,21);
                }
            });
        }else{
            me_tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), HotFollowActivity.class);
                    startActivity(intent);
                    mFlag = mSp.getBoolean("flag", false);
                    mCurrentId = mSp.getInt("currentId", 0);
                    startActivityForResult(intent,22);
                }
            });
        }
    }

    private void findView(){
        me_tx = (ImageView) view.findViewById(R.id.me_tx);
        me_nickname = (TextView) view.findViewById(R.id.me_nickname);
        setting_text = (TextView) view.findViewById(R.id.setting_text);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==20 && resultCode==200){
            String tximage = data.getStringExtra("tximage");
            String txnickname = data.getStringExtra("txnickname");
            me_nickname.setText(txnickname+"");
            Bitmap bitmap = null;
            try {
                if(tximage!=null){
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(tximage));
                    me_tx.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFlag = mSp.getBoolean("flag", false);
            initListener();
        }else if(requestCode==21 && resultCode==201){
            me_nickname.setText("请登录");
            me_tx.setImageResource(R.drawable.ic_personal_headportrait);
            mFlag = mSp.getBoolean("flag", false);
            initListener();
        }else if(requestCode==22 && resultCode==202){
            String tximage = data.getStringExtra("tximage");
            String txnickname = data.getStringExtra("txnickname");
            me_nickname.setText(txnickname+"");
            Bitmap bitmap = null;
            try {
                if(tximage!=null){
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(tximage));
                    me_tx.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFlag = mSp.getBoolean("flag", false);
            initListener();
        }
    }
}
