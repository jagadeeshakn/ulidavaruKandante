package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.createClient.data.CountryData;
import com.conflux.finflux.createClient.data.TalukaData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 9/1/2016.
 */
public class TalukaOptionsAdapter extends BaseAdapter {

    private Context context;
    private List<TalukaData> talukaDatas;
    private LayoutInflater layoutInflater;

    public TalukaOptionsAdapter(Context context,List<TalukaData> talukaDatas)
    {
        layoutInflater = LayoutInflater.from(context);
        this.talukaDatas = talukaDatas;
        this.context = context;

    }


    @Override
    public int getCount() {
        return talukaDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return talukaDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return talukaDatas.get(i).getTalukaId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.taluka_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.taluka_name.setText(talukaDatas.get(i).getTalukaName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.taluka_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.taluka_name.setText(talukaDatas.get(position).getTalukaName());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.tv_taluka_name)
        CheckedTextView taluka_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
