package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.Bean_V_hot;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/30
 */

public class V_listAdapter extends BaseAdapter{

        private Context context;
        private Bean_V_hot bean_v_hot;

        public V_listAdapter(Context context, Bean_V_hot bean_v_hot) {

            this.context = context;
            this.bean_v_hot = bean_v_hot;

        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.listview_item_page3, null);
                viewHolder = new ViewHolder();

                viewHolder.textnick = (TextView) convertView.findViewById(R.id.nick_text);

                viewHolder.textcontent = (TextView) convertView.findViewById(R.id.content_text);
                viewHolder.image_main = (ImageView) convertView.findViewById(R.id.image_content);
                viewHolder.image_head = (ImageView) convertView.findViewById(R.id.head_image);
                viewHolder.pra_num = (TextView) convertView.findViewById(R.id.text_commom_num_vhot);
                viewHolder.com_num = (TextView) convertView.findViewById(R.id.text_pra_num_vhot);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textcontent.setText(bean_v_hot.getData().getFeeds().get(position).getContent().getText());
//                viewHolder.image_main =
            viewHolder.pra_num.setText(bean_v_hot.getData().getFeeds().get(position).getLikes_count() + "");
            viewHolder.com_num.setText(bean_v_hot.getData().getFeeds().get(position).getComments_count()+"");
            viewHolder.textnick.setText(bean_v_hot.getData().getFeeds().get(position).getUser().getNickname());

            ImageLoader.getInstance().displayImage(bean_v_hot.getData().getFeeds().get(position).getUser().getAvatar_url(), viewHolder.image_head, new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_common_placeholder_m).build());
            return convertView;
        }


        class ViewHolder {
            TextView textnick;
            TextView textcontent;
            TextView com_num;
            TextView pra_num;
            ImageView image_head;
            ImageView image_main;

        }
    }


