package com.example.rk2;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/05/10.
 * author : 殷成龙(Administrator)
 * function :
 */

public class adapter extends BaseAdapter {
    Context context;
    List<bean.ResultBean.DataBean> list;
    private ViewHolder3 holder3;
    private ViewHolder2 holder2;
    private ViewHolder1 holder1;

    public adapter(Context context, List<bean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
    public int getItemViewType(int position) {
       if (position%3==1){
           return 0;
       }else if(position%3==2){
           return 1;
       }else{
           return 2;
       }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if(type==0){
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item3,null);
                holder3 = new ViewHolder3();
                holder3.item3_img1 = convertView.findViewById(R.id.item3_img1);
                holder3.item3_img2 = convertView.findViewById(R.id.item3_img2);
                holder3.item3_img3 = convertView.findViewById(R.id.item3_img3);
                convertView.setTag(holder3);
            }else{
                holder3 = (ViewHolder3) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder3.item3_img1);
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(),holder3.item3_img2);
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s03(),holder3.item3_img3);
        }else if(type==1){
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item2,null);
                holder2 = new ViewHolder2();
                holder2.item2_img1 = convertView.findViewById(R.id.item2_img1);
                holder2.item2_img2 = convertView.findViewById(R.id.item2_img2);
                convertView.setTag(holder2);
            }else{
                holder2 = (ViewHolder2) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder2.item2_img1);
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(),holder2.item2_img2);
        }else{
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item1,null);
                holder1 = new ViewHolder1();
                holder1.item1_img = convertView.findViewById(R.id.item1_img);
                convertView.setTag(holder1);
            }else{
                holder1 = (ViewHolder1) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder1.item1_img);
        }
        return convertView;
    }
    class ViewHolder1{
        ImageView item1_img;
    }
    class ViewHolder2{
        ImageView item2_img1;
        ImageView item2_img2;
    }
    class ViewHolder3{
        ImageView item3_img1;
        ImageView item3_img2;
        ImageView item3_img3;
    }
}
