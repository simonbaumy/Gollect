package com.example.gollect.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gollect.GollectCollection;
import com.example.gollect.R;

import java.util.List;



public class CollectionAdapter extends ArrayAdapter<GollectCollection> {



    public CollectionAdapter(Context context, int resource, List<GollectCollection> collectionList)
    {
        super(context,resource,collectionList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        GollectCollection collection = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.collection_cell, parent, false);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.collectionName);
        TextView tv2 = (TextView) convertView.findViewById(R.id.collectionGoal);
        TextView tv3 = (TextView) convertView.findViewById(R.id.collectionType);



        tv1.setText(collection.getName());
        tv2.setText(collection.getGoal());
        tv3.setText(collection.getType());


        return convertView;
    }
}
