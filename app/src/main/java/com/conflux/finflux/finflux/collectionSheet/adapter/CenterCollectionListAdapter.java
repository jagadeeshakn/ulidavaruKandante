package com.conflux.finflux.finflux.collectionSheet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class CenterCollectionListAdapter extends RecyclerView.Adapter<CenterCollectionListAdapter.DataObjectHolder> {


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        @Override
        public void onClick(View view) {

        }

        public DataObjectHolder(View itemView) {
            super(itemView);

        }
    }
}
