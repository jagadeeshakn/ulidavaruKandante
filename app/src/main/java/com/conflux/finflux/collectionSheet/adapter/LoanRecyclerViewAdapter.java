package com.conflux.finflux.collectionSheet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Praveen J U on 8/25/2016.
 */
public class LoanRecyclerViewAdapter extends RecyclerView.Adapter<LoanRecyclerViewAdapter.DataObjectHolder>{
        private static OnItemFocusedListnerClass onItemFocusedListnerClass;
        LayoutInflater layoutInflater;
        Context context;
        private static List<Loan> loans;
        private final String TAG = getClass().getSimpleName();
        private final String en;
        private final String code;

        public LoanRecyclerViewAdapter(Context context, List<Loan> loans,String en, String code) {
            this.en = en;
            this.code = code;
            this.layoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.loans = loans;
        }

        @Override
        public LoanRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_loan_repayment_sheet, viewGroup, false);

            DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
            return dataObjectHolder;
        }

        @Override
        public void onBindViewHolder(LoanRecyclerViewAdapter.DataObjectHolder dataObjectHolder, int position) {

            if(position%2==1) {
                dataObjectHolder.view.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                dataObjectHolder.view.setBackgroundColor(Color.parseColor("#ebebeb"));
            }

            Loan loan = loans.get(position);

            dataObjectHolder.tv_loan_product_short_name.setText(loan.getProductShortName());
            //String due_amount = CurrencyFormatter.getCurrencyFormat(en, code, loan.getTotalDue());
            dataObjectHolder.tv_due.setText(String.valueOf(loan.getTotalDue()));
            dataObjectHolder.et_collected_amount.setTag(loans.get(position).getLoanId());
            dataObjectHolder.et_collected_amount.setText(String.valueOf(loan.getTotalCollected()));
        }

        @Override
        public int getItemCount() {
            return this.loans.size();
        }

        public static class DataObjectHolder extends RecyclerView.ViewHolder
                implements View
                .OnFocusChangeListener {
            @Bind(R.id.tv_loan_product_short_name)
            TextView tv_loan_product_short_name;
            @Bind(R.id.tv_due) TextView tv_due;
            @Bind(R.id.et_collected_amount)
            EditText et_collected_amount;
            View view;


            public DataObjectHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
                Logger.i(getClass().getSimpleName(), "Adding Listener");
                et_collected_amount.setOnFocusChangeListener(this);
            }


            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Logger.e(getClass().getSimpleName(),"on focused at "+view.getId()+" position "+getAdapterPosition());
                    onItemFocusedListnerClass.onItemFocused(getAdapterPosition(), view);
                }
            }
        }



        public void onFoucListener(OnItemFocusedListnerClass onItemFocusedListnerClass) {
            this.onItemFocusedListnerClass = onItemFocusedListnerClass;
        }

        public interface OnItemFocusedListnerClass {
            public void onItemFocused(int position, View v);
        }

}
