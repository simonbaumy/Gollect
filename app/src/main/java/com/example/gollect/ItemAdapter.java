package com.example.gollect;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, int resource, List<Item> collectionList)
    {
        super(context,resource,collectionList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Item item = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cell, parent, false);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.item_name);
        TextView tv2 = (TextView) convertView.findViewById(R.id.item_type);
        TextView tv3 = (TextView) convertView.findViewById(R.id.item_description);
        TextView tv4 = (TextView) convertView.findViewById(R.id.item_date);
       // ImageView iv = (ImageView) convertView.findViewById(R.id.item_image);




        //iv.setImageResource(item.getBitmap());
        tv1.setText(item.getName());
        tv2.setText(item.getType());
        tv3.setText(item.getDescription());
        tv4.setText(item.getDate());





        return convertView;
    }
}
