package com.conflux.finflux.collectionSheet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 8/3/2016.
 */
public class ClientLoanReypaymentAdapter extends RecyclerView.Adapter<ClientLoanReypaymentAdapter.DataObjectHolder> {
    @Override
    public ClientLoanReypaymentAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ClientLoanReypaymentAdapter.DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnFocusChangeListener {
        View view;
        public DataObjectHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @Override
        public void onFocusChange(View view, boolean b) {

        }
    }
}
