package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.createClient.data.CountryData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 8/16/2016.
 */
public class CountryOptionsAdapter extends BaseAdapter {

    private Context context;
    private List<CountryData> countryDatas;
    private LayoutInflater layoutInflater;

    public CountryOptionsAdapter(Context context,List<CountryData> countryDatas)
    {
        layoutInflater = LayoutInflater.from(context);
        this.countryDatas = countryDatas;
        this.context = context;

    }


    @Override
    public int getCount() {
        return countryDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return countryDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return countryDatas.get(i).getCountryId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.country_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.country_name.setText(countryDatas.get(i).getCountryName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.country_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.country_name.setText(countryDatas.get(position).getCountryName());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.tv_country_name)
        CheckedTextView country_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
