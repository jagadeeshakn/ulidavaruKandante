package com.conflux.finflux.collectionSheet.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TimeFormatException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.util.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class CenterCollectionListAdapter extends RecyclerView.Adapter<CenterCollectionListAdapter.DataObjectHolder> {

    private final  String TAG= getClass().getSimpleName();

    private Context context;
    private List<MeetingFallCenter> centerCollectionData;
    private static MyClickListener myClickListener;

    public CenterCollectionListAdapter(Context context, List<MeetingFallCenter> meetingFallCenters){

       this.context =context;
        this.centerCollectionData=meetingFallCenters;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_center_collection_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.tv_center_name.setText(centerCollectionData.get(position).getName().trim());
        holder.tv_center_total_due.setText(String.valueOf(centerCollectionData.get(position).getTotaldue()));
        holder.tv_total_collection.setText(String.valueOf(centerCollectionData.get(position).getTotalCollected()));
        holder.tv_meeting_location.setText(centerCollectionData.get(position).getCollectionMeetingCalendar().getLocation());
        try {
            Integer millis = centerCollectionData.get(position).getCollectionMeetingCalendar().getMeetingTime().getiLocalMillis();
            if (millis != 0) {
                long second = (millis / 1000) % 60;
                long minute = (millis / (1000 * 60)) % 60;
                long hour = (millis / (1000 * 60 * 60)) % 12;

                String meetingTime = String.format("%02d:%02d", hour, minute);
                Logger.d(TAG, "Meeting time is    " + meetingTime);
                holder.tv_meeting_time.setText(meetingTime);
            }
        }catch (TimeFormatException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return centerCollectionData.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        @Bind(R.id.card_view)
        CardView center_card_view;
        @Bind(R.id.tv_center_name)
        TextView tv_center_name;
        @Bind(R.id.tv_meeting_time)
        TextView tv_meeting_time;
        @Bind(R.id.tv_meeting_location)
        TextView tv_meeting_location;
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
