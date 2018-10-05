package com.knights.blockathonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 04-Oct-18.
 */

public class ListviewAdapter extends BaseAdapter {

    List<Doctor> doctors;
    Context context;
    LayoutInflater inflater;
    void setData(List<Doctor> docs, Context context)
    {
        this.doctors=docs;
        this.context=context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return doctors.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.listview_item,null);
        TextView text=view.findViewById(R.id.list_item);

        text.setText(doctors.get(position).firstName+" "+doctors.get(position).lastName);
        return view;
    }
}
