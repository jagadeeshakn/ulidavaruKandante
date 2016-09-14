package com.conflux.finflux.collectionSheet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CodeValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Praveen J U on 8/25/2016.
 */
public class AttendanceTypesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CodeValue> attendanceTypes;
    private LayoutInflater layoutInflater;

    public AttendanceTypesAdapter(Context context, List<CodeValue> attendanceTypes) {
        layoutInflater = LayoutInflater.from(context);
        this.attendanceTypes = new ArrayList<>();
        this.attendanceTypes.addAll(attendanceTypes);
        this.context = context;
    }

    @Override
    public int getCount() {
        return attendanceTypes.size();
    }

    @Override
    public Object getItem(int i) {
        return attendanceTypes.get(i);
    }

    @Override
    public long getItemId(int i) {
        if (attendanceTypes.get(i) != null) {
            return attendanceTypes.get(i).getId();
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.single_item_checked_text_view, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.officeName.setText(attendanceTypes.get(i).getValue());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.single_item_checked_text_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.officeName.setText(attendanceTypes.get(position).getValue());
        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.text1)
        CheckedTextView officeName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
