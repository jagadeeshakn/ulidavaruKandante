package com.conflux.finflux.collectionSheet.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 7/29/2016.
 */
public class GroupCollectionListAdapter extends RecyclerView.Adapter<GroupCollectionListAdapter.DataObjectHolder> {

    private Context mcontext;
    private ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapterList;
    private static MyClickListener myClickListener;

    public GroupCollectionListAdapter(Context context, ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters) {
        this.mcontext = context;
        this.collectionSheetDataForAdapterList = collectionSheetDataForAdapters;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_group_collection_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;

    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.tv_entity_name.setText(collectionSheetDataForAdapterList.get(position).getEntityName());
        holder.tv_attendence.setText(collectionSheetDataForAdapterList.get(position).getAttendanceType().getValue());
        holder.tv_client_id.setText(String.valueOf(collectionSheetDataForAdapterList.get(position).getEntityId()));
        holder.tv_center_total_due.setText(String.valueOf(collectionSheetDataForAdapterList.get(position).getDueAmount()));
        holder.tv_total_collection.setText(String.valueOf(collectionSheetDataForAdapterList.get(position).getCollectedAmount()));
    }

    @Override
    public int getItemCount() {
        return collectionSheetDataForAdapterList.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        @Bind(R.id.card_view)
        CardView center_card_view;
        @Bind(R.id.tv_entity_name)
        TextView tv_entity_name;
        @Bind(R.id.tv_client_id)
        TextView tv_client_id;
        @Bind(R.id.tv_attendence)
        TextView tv_attendence;
        @Bind(R.id.tv_total_collection)
        TextView tv_total_collection;
        @Bind(R.id.tv_center_total_due)
        TextView tv_center_total_due;

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);
        }

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Logger.i(getClass().getSimpleName(), "Adding Listener");
            itemView.setOnClickListener(this);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
