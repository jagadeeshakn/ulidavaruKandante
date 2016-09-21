package com.conflux.finflux.createClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.createClient.data.OptionsType;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 9/1/2016.
 */
public class AddressTypeOptionsAdapter extends BaseAdapter {

    private Context context;
    private List<OptionsType> addressTypes;
    private LayoutInflater layoutInflater;

    public AddressTypeOptionsAdapter(Context context,List<OptionsType> addressOption)
    {
        layoutInflater = LayoutInflater.from(context);
        this.addressTypes = addressOption;
        this.context = context;

    }

    @Override
    public int getCount() {
        return addressTypes.size();
    }

    @Override
    public Object getItem(int i) {
        return addressTypes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return addressTypes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.address_spinner_selected_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.addresstype_name.setText(addressTypes.get(i).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.address_spinner_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.addresstype_name.setText(addressTypes.get(position).getName());
        return convertView;
    }

    public static class ViewHolder
    {
        @Bind(R.id.tv_addresstype_name)
        CheckedTextView addresstype_name;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

