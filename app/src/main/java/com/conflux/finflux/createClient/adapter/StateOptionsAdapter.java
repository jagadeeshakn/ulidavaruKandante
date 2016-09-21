package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.createClient.data.CountryData;
import com.conflux.finflux.createClient.data.StateData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 8/16/2016.
 */
public class StateOptionsAdapter extends BaseAdapter {
    private Context context;
    private List<StateData> stateDatas;
    private LayoutInflater layoutInflater;

    public StateOptionsAdapter(Context context,List<StateData> stateDatas)
    {
        layoutInflater = LayoutInflater.from(context);
        this.stateDatas = stateDatas;
        this.context = context;

    }


    @Override
    public int getCount() {
        return stateDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return stateDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return stateDatas.get(i).getStateId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.state_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.state_name.setText(stateDatas.get(i).getStateName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.state_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.state_name.setText(stateDatas.get(position).getStateName());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.tv_state_name)
        CheckedTextView state_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
