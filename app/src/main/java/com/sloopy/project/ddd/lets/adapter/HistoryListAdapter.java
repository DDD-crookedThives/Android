package com.sloopy.project.ddd.lets.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.data.source.HistoryData;

import java.util.ArrayList;

public class HistoryListAdapter extends BaseAdapter
{
    LayoutInflater inflater = null;
    private ArrayList<HistoryData> history_data = null;
    private int nListCnt = 0;

    public HistoryListAdapter(ArrayList<HistoryData> _oData)
    {
        history_data = _oData;
        nListCnt = history_data.size();
    }

    @Override
    public int getCount()
    {
        Log.i("TAG", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.history_listview_item, parent, false);
        }

        TextView dogName = (TextView) convertView.findViewById(R.id.dogName);
        TextView rpt_ymd = (TextView) convertView.findViewById(R.id.rpt_ymd);
        TextView achieve = (TextView) convertView.findViewById(R.id.achieve);
        TextView acheiveRate = (TextView) convertView.findViewById(R.id.acheiveRate);

        dogName.setText(history_data.get(position).dogName);
        rpt_ymd.setText(history_data.get(position).rpt_ymd);
        achieve.setText(history_data.get(position).ahieve);
        acheiveRate.setText(history_data.get(position).acheveRate);

        return convertView;
    }
}