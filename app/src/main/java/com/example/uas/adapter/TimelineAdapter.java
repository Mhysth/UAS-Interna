package com.example.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.model.local.Timeline;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.CardViewViewHolder> {
    private Context context;
    private List<Timeline> listTimeline;
    private List<Timeline> getListTimeline() {
        return listTimeline;
    }
    public void setListTimeline(List<Timeline> listTimeline) {
        this.listTimeline = listTimeline;
    }
    public TimelineAdapter(Context context) {
        this.listTimeline = new ArrayList<Timeline>();
        this.context = context;
    }
    @NonNull
    @Override
    public TimelineAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_adapter, parent, false);
        return new TimelineAdapter.CardViewViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Timeline timeline = getListTimeline().get(position);
        holder.date.setText(timeline.getDate());
        holder.time.setText(timeline.getTime());
        holder.title.setText(timeline.getTitle());
        holder.desc.setText(timeline.getDesc());
       /* holder.itemView.setOnClickListener(v -> {
            NavDirections action = TimelineFragmentDirections.actionDetailFragment(null, timeline);
            Navigation.findNavController(v).navigate(action);
        });*/
    }
    @NonNull
    @Override
    public int getItemCount() {
        return getListTimeline().size();
    }
    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView date;
        TextView title;
        TextView desc;
        ImageView idtctr;
        TextView tdtctr;

        CardViewViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.std_time);
            date = itemView.findViewById(R.id.std_date);
            title = itemView.findViewById(R.id.std_title);
            desc = itemView.findViewById(R.id.std_desc);
            idtctr = itemView.findViewById(R.id.img_detector);
            tdtctr = itemView.findViewById(R.id.text_detector);
        }
    }
}