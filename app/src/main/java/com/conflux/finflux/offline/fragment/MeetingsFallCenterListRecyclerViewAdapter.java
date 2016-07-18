package com.conflux.finflux.offline.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.offline.data.CenterWIthMeetingAndCheckedStatus;
import com.conflux.finflux.offline.fragment.MeetingsFallCenterListFragment.OnListFragmentInteractionListener;
import com.conflux.finflux.offline.fragment.dummy.DummyContent.DummyItem;
import com.conflux.finflux.util.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MeetingsFallCenterListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsFallCenterListRecyclerViewAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private final OnListFragmentInteractionListener mListener;
    private static MyClickListener myClickListener;
    private final SimpleDateFormat formatDay = new SimpleDateFormat("EEEE");
    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd MMMM yyyy");
    private final ArrayList<CenterWIthMeetingAndCheckedStatus> centerWIthMeetingAndCheckedStatuses;
    private final Context context;
    public MeetingsFallCenterListRecyclerViewAdapter(Context context,ArrayList<CenterWIthMeetingAndCheckedStatus> items, OnListFragmentInteractionListener listener) {
        this.context = context;
        centerWIthMeetingAndCheckedStatuses = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meetingsfallcenterlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String date = centerWIthMeetingAndCheckedStatuses.get(position).getDate();
        Logger.d(TAG,"the date is "+date);
        try {
            Date date1 = formatDate.parse(date);
            holder.textViewDate.setText(date);
            String day = formatDay.format(date1);
            holder.textViewDay.setText(day);
        }catch (Exception e){
            e.printStackTrace();
        }
        MeetingFallCenter meetingFallCenter = centerWIthMeetingAndCheckedStatuses.get(position).getMeetingFallCenter();
        holder.textViewCentername.setText(meetingFallCenter.getName());
        holder.textViewTotalDue.setText(String.valueOf(meetingFallCenter.getTotaldue()));
        holder.textViewTotalCollected.setText(String.valueOf(meetingFallCenter.getTotalCollected()));
        if(meetingFallCenter.getTotalCollected() != 0){
            holder.textViewStatus.setText(context.getText(R.string.collection_status_completed));
            holder.cardView.setClickable(false);
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.red_dark));
            holder.checkBox.setChecked(false);
            centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus = false;
        }else {
            holder.cardView.setClickable(true);
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.green));
            holder.textViewStatus.setText(context.getString(R.string.collection_status_can_download));
        }

        if(centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus){
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }

       /* holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return centerWIthMeetingAndCheckedStatuses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.center_card_view)
        CardView cardView;
        @Bind(R.id.checkbox_center_select)
        CheckBox checkBox;
        @Bind(R.id.text_view_date)
        TextView textViewDate;
        @Bind(R.id.text_view_day)
        TextView textViewDay;
        @Bind(R.id.text_view_center_name)
        TextView textViewCentername;
        @Bind(R.id.text_view_total_due)
        TextView textViewTotalDue;
        @Bind(R.id.text_view_total_collected)
        TextView textViewTotalCollected;
        @Bind(R.id.text_view_status)
        TextView textViewStatus;


        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
