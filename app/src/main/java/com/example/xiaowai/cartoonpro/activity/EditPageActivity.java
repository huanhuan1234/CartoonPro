package com.example.xiaowai.cartoonpro.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.UserBean;
import com.example.xiaowai.cartoonpro.db.MyDbHelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class EditPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView editReturn;
    private TextView editFinish;
    private ImageView imageEdit;
    private AlertDialog.Builder builder;
    private EditText editPageNickname;
    private EditText editPageSex;
    private AlertDialog.Builder diaglog;
    private RadioButton btnboy;
    private RadioButton btngirl;
    private RadioGroup rg_sexdialog;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private int rb_sex;
    private EditText mEditPageBirthday;
    private int mMYear;
    private int mMMonth;
    private int mMDay;
    private Calendar mCalendar;
    private MyDbHelper mHelper;
    private int mCurrentId;
    private Bitmap mBm;
    private Bitmap mBm1;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        findView();
        initControl();
    }

    private void initControl() {
        editReturn.setOnClickListener(this);
        editFinish.setOnClickListener(this);
        imageEdit.setOnClickListener(this);
        editPageSex.setOnClickListener(this);
        mEditPageBirthday.setOnClickListener(this);
    }

    private void findView() {
        editReturn = (ImageView) findViewById(R.id.editReturn);
        editFinish = (TextView) findViewById(R.id.editFinish);
        imageEdit = (ImageView) findViewById(R.id.imageEdit);
        editPageNickname = (EditText) findViewById(R.id.editPageNickname);
        editPageSex = (EditText) findViewById(R.id.editPageSex);
        mEditPageBirthday = (EditText) findViewById(R.id.editPageBirthday);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        edit = sp.edit();
        mHelper = new MyDbHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<UserBean> data = mHelper.findData(db);
        mCurrentId = sp.getInt("currentId", 0);
        for(UserBean user:data){
            if(user.getId()== mCurrentId){
                editPageNickname.setText(user.getNickname()+"");
                editPageSex.setText(user.getSex()+"");
                mEditPageBirthday.setText(user.getBirthday()+"");
                Bitmap bitmap = null;
                try {
                    if(user.getPic()!=null){
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(user.getPic()));
                        imageEdit.setImageBitmap(bitmap);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editFinish:
                Log.d("zzz",mCurrentId+"mCurrentId:");
                SQLiteDatabase db = mHelper.getReadableDatabase();
                UserBean ub=new UserBean();
                ub.setNickname(editPageNickname.getText().toString().trim());
                ub.setSex(editPageSex.getText().toString().trim());
                ub.setBirthday(mEditPageBirthday.getText().toString().trim());
                if(mUri !=null){
                    ub.setPic(mUri.toString());
                }
                mHelper.updatetData(db,mCurrentId,ub);
                edit.putBoolean("flag",true);
                edit.commit();
                Intent intent2 = getIntent();
                if(mUri!=null){
                    intent2.putExtra("tximage",mUri.toString());
                }
                intent2.putExtra("txnickname",editPageNickname.getText().toString().trim());
                setResult(200,intent2);
                finish();
                break;
            case R.id.editReturn:
                finish();
                break;
            case R.id.imageEdit:
                builder = new AlertDialog.Builder(this);
                View view=View.inflate(this,R.layout.editpage_alertdialog,null);
                TextView textCamera= (TextView) view.findViewById(R.id.textCamera);
                TextView textchoosePic= (TextView) view.findViewById(R.id.textchoosePic);
                textCamera.setOnClickListener(this);
                textchoosePic.setOnClickListener(this);
                builder.setView(view);
                builder.create().show();
                break;
            case R.id.textCamera:
                builder.create().dismiss();
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory("android.intent.category.DEFAULT");
                startActivityForResult(intent, 3);
                break;
            case R.id.textchoosePic:
                builder.create().dismiss();
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 1);
                break;
            case R.id.editPageBirthday:
                mCalendar = Calendar.getInstance();
                new DatePickerDialog(EditPageActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // TODO Auto-generated method stub
                        mMYear = year;
                        mMMonth = month;
                        mMDay = day;
                        //更新EditText控件日期 小于10加0
                        mEditPageBirthday.setText(new StringBuilder().append(mMYear).append("-")
                                .append((mMMonth + 1) < 10 ? 0 + (mMMonth + 1) : (mMMonth + 1))
                                .append("-")
                                .append((mMDay < 10) ? 0 + mMDay : mMDay) );
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH) ).show();
                break;
            case R.id.editPageSex:
                diaglog = new AlertDialog.Builder(this);
                View sexView=View.inflate(this,R.layout.editpage_sexdialog,null);
                diaglog.setView(sexView);
                diaglog.create().show();
                rg_sexdialog = (RadioGroup) sexView.findViewById(R.id.rg_sexdialog);
                btnboy = (RadioButton) sexView.findViewById(R.id.rg_sexbtnboy);
                btngirl = (RadioButton) sexView.findViewById(R.id.rg_sexbtngirl);
                rb_sex = sp.getInt("rb_sex", 0);
                rg_sexdialog.check(rb_sex);
                rg_sexdialog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.rg_sexbtnboy:
                                Drawable drawable= getResources().getDrawable(R.drawable.abc_btn_radio_to_on_mtrl_015);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                btnboy.setCompoundDrawables(null,null,drawable,null);
                                Drawable drawable2= getResources().getDrawable(R.drawable.abc_btn_radio_to_on_mtrl_000);
                                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                                btngirl.setCompoundDrawables(null,null,drawable2,null);
                                editPageSex.setText("男");
                                edit.putInt("rb_sex",R.id.rg_sexbtnboy);
                                edit.commit();
                                diaglog.create().dismiss();
                                break;
                            case R.id.rg_sexbtngirl:
                                Drawable drawablegirl= getResources().getDrawable(R.drawable.abc_btn_radio_to_on_mtrl_015);
                                drawablegirl.setBounds(0, 0, drawablegirl.getMinimumWidth(), drawablegirl.getMinimumHeight());
                                btngirl.setCompoundDrawables(null,null,drawablegirl,null);
                                Drawable drawableboy= getResources().getDrawable(R.drawable.abc_btn_radio_to_on_mtrl_000);
                                drawableboy.setBounds(0, 0, drawableboy.getMinimumWidth(), drawableboy.getMinimumHeight());
                                btnboy.setCompoundDrawables(null,null,drawableboy,null);
                                editPageSex.setText("女");
                                edit.putInt("rb_sex",R.id.rg_sexbtngirl);
                                edit.commit();
                                diaglog.create().dismiss();
                                break;
                        }
                    }
                });

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode==1){
            Uri uri = data.getData();
            crop(uri);
        }else if(requestCode==2){
            mBm = data.getParcelableExtra("data");
            mUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mBm, "", ""));
            Log.d("zzz", mUri.toString()+"");
            imageEdit.setImageBitmap(mBm);
        }else if(requestCode==3){
            mBm = data.getParcelableExtra("data");
            mUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mBm, "", ""));
            Log.d("zzz",mUri.toString()+"");
            imageEdit.setImageBitmap(mBm);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crop(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", false);


        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

}
