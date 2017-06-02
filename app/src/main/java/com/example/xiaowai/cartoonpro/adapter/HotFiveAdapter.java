package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.Utils.ImageLoaderOptionsUtils;
import com.example.xiaowai.cartoonpro.activity.HotRelaActivity;
import com.example.xiaowai.cartoonpro.activity.HotWebViewActivity;
import com.example.xiaowai.cartoonpro.bean.FiveBean;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class HotFiveAdapter  extends BaseAdapter{
    private List<FiveBean.DataBean.ComicsBean> list;
    private Context context;
    private RelativeLayout rela;
    private String duixiang;
    private int location;
    private Gson gson;

    public HotFiveAdapter(List<FiveBean.DataBean.ComicsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final HotViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.fragment_comic_hot_five_item,null);
            holder=new HotViewHolder();
            holder.rela = (RelativeLayout) convertView.findViewById(R.id.rela_comic_hot_item);
            holder.img= (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView= (TextView) convertView.findViewById(R.id.textView);
            holder.textView2= (TextView) convertView.findViewById(R.id.textView2);
            holder.textView3= (TextView) convertView.findViewById(R.id.textView3);
            holder.textView4= (TextView) convertView.findViewById(R.id.textView4);
            holder.textView5= (TextView) convertView.findViewById(R.id.textView5);
            holder.textView6= (TextView) convertView.findViewById(R.id.textView6);
            holder.btndz=(Button)convertView.findViewById(R.id.button2);
            convertView.setTag(holder);
        }else{
           holder= (HotViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getLabel_text()+"");
        GradientDrawable myGrad = (GradientDrawable)holder.textView.getBackground();
        myGrad.setColor(Color.parseColor(list.get(position).getLabel_color()));
        holder.textView2.setText(list.get(position).getTopic().getTitle()+"");
        holder.textView3.setText(list.get(position).getTopic().getUpdate_status()+"");
        holder.textView4.setText(list.get(position).getTitle()+"");
        holder.textView5.setText(position+1+"");
        holder.textView6.setText(list.get(position).getComments_count()+"");
        ImageLoader.getInstance().displayImage(list.get(position).getCover_image_url(),holder.img, ImageLoaderOptionsUtils.imageOptions(R.mipmap.ic_launcher));
        gson = new Gson();
        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HotRelaActivity.class);
                duixiang = gson.toJson(list.get(position));
                location=position;
                intent.putExtra("duixiang",duixiang);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        holder.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, HotWebViewActivity.class);
                duixiang = gson.toJson(list.get(position));
                location=position;
                i.putExtra("duixiang",duixiang);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, HotWebViewActivity.class);
                duixiang = gson.toJson(list.get(position));
                location=position;
                i.putExtra("duixiang",duixiang);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
        holder.btndz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textView5.getText().toString().trim().equals(position+1+"")){
                    holder.btndz.setBackgroundResource(R.drawable.ic_common_praise_highlighted_like_pressed);
                    holder.textView5.setText(position+2+"");
                    ScaleAnimation sa=new ScaleAnimation(1,2,1,2, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    sa.setDuration(200);
                    holder.btndz.startAnimation(sa);
                }else{
                    holder.btndz.setBackgroundResource(R.drawable.ic_common_praise_highlighted_like_normal);
                    holder.textView5.setText(position+1+"");
                    ScaleAnimation sa=new ScaleAnimation(1,2,1,2, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    sa.setDuration(200);
                    holder.btndz.startAnimation(sa);
                }
            }
        });
        return convertView;
    }
}
