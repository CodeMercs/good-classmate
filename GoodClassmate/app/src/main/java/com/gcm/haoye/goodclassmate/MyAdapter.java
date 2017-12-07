package com.gcm.haoye.goodclassmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kancheng on 2017/12/7.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    public List<Comment> list;

    public MyAdapter(Context context,List<Comment> values) {
        this.list = values;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Comment getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getList_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.test,parent,false);
            TextView view_id = convertView.findViewById(R.id.list_id);
            TextView view_name = convertView.findViewById(R.id.name);
            TextView view_money = convertView.findViewById(R.id.money);
            holder.view_id = view_id;
            holder.view_name = view_name;
            holder.view_money = view_money;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comment comment = list.get(position);
        holder.view_id.setText(comment.getList_id()+"");
        holder.view_money.setText(comment.getMoney()+"");
        holder.view_name.setText(comment.getName());
        return convertView;
    }
    class ViewHolder{
        TextView view_id;
        TextView view_name ;
        TextView view_money ;
    }
}
