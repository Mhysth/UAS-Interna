package com.example.uas.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.uas.R;
import com.example.uas.model.Timeline;
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
        Glide.with(context).load(timeline.)
    }
    @NonNull
    @Override
    public int getItemCount() {
        return getListTimeline().size();
    }
    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView day;
        TextView time;
        TextView date;
        ImageView idtctr;
        TextView tdtctr;

        CardViewViewHolder(View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.std_image_day);
            day =itemView.findViewById(R.id.std_day);
            time =itemView.findViewById(R.id.std_time);
            date =itemView.findViewById(R.id.std_date);
            idtctr =itemView.findViewById(R.id.img_detector);
            tdtctr =itemView.findViewById(R.id.text_detector);
        }
    }
}