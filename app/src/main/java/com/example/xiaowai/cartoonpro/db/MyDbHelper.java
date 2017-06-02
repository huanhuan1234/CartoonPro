package com.example.xiaowai.cartoonpro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xiaowai.cartoonpro.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/28
 */

public class MyDbHelper extends SQLiteOpenHelper{
    public MyDbHelper(Context context ) {
        super(context, "acountDb", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table account(id integer primary key autoincrement,username text,userpwd text,nickname text,sex text,birthday text,pic text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertData(SQLiteDatabase db, UserBean ub){
        ContentValues values=new ContentValues();
        values.put("username",ub.getUsername());
        values.put("userpwd",ub.getUserpwd());
        db.insert("account",null,values);
    }

    public static void updatetData(SQLiteDatabase db, int id,UserBean ub){
        ContentValues values=new ContentValues();
        values.put("nickname",ub.getNickname());
        values.put("sex",ub.getSex());
        values.put("birthday",ub.getBirthday());
        if(ub.getPic()!=null){
            values.put("pic",ub.getPic());
        }
        db.update("account",values,"id="+id,null);
    }

    public static List<UserBean> findData(SQLiteDatabase db){
        Cursor cursor = db.query("account", null, null, null, null, null, null);
        List<UserBean> list=new ArrayList<>();
        while(cursor.moveToNext()){
            UserBean ub=new UserBean();
            String username = cursor.getString(cursor.getColumnIndex("username"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String userpwd = cursor.getString(cursor.getColumnIndex("userpwd"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String pic = cursor.getString(cursor.getColumnIndex("pic"));
            ub.setId(id);
            ub.setUsername(username);
            ub.setUserpwd(userpwd);
            ub.setNickname(nickname);
            ub.setSex(sex);
            ub.setBirthday(birthday);
            ub.setPic(pic);
            list.add(ub);
        }
        return list;
    }

}
