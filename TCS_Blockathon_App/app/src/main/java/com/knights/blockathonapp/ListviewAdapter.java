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

    List<Doctor> quotes;
    Context context;
    LayoutInflater inflater;
    void setData(List<Doctor> quotes, Context context)
    {
        this.quotes=quotes;
        this.context=context;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return quotes.size();
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

        TextView quote=view.findViewById(R.id.quote);
        TextView author=view.findViewById(R.id.author);

        quote.setText(quotes.get(position).quote);
        author.setText("-"+quotes.get(position).author);

        return view;
    }
}
