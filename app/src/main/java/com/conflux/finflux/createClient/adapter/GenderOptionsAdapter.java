package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.createClient.data.OptionsType;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 8/16/2016.
 */
public class GenderOptionsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<OptionsType> genderOptions;

    public GenderOptionsAdapter(Context context, ArrayList<OptionsType> genderList) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.genderOptions = genderList;
    }

    @Override
    public int getCount() {
        return genderOptions.size();
    }

    @Override
    public Object getItem(int i) {
        return genderOptions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return genderOptions.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.gender_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_gender_name.setText(genderOptions.get(i).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.gender_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_gender_name.setText(genderOptions.get(position).getName());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.tv_gender_name)
        CheckedTextView tv_gender_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
