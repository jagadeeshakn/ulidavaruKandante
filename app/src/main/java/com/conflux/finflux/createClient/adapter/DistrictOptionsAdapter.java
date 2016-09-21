package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.createClient.data.CountryData;
import com.conflux.finflux.createClient.data.DistrictData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 8/16/2016.
 */
public class DistrictOptionsAdapter extends BaseAdapter {

    private Context context;
    private List<DistrictData> districtDatas;
    private LayoutInflater layoutInflater;

    public DistrictOptionsAdapter(Context context,List<DistrictData> districtDatas)
    {
        layoutInflater = LayoutInflater.from(context);
        this.districtDatas = districtDatas;
        this.context = context;

    }


    @Override
    public int getCount() {
        return districtDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return districtDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return districtDatas.get(i).getDistrictId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.district_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.district_name.setText(districtDatas.get(i).getDistrictName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.district_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.district_name.setText(districtDatas.get(position).getDistrictName());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.tv_district_name)
        CheckedTextView district_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
