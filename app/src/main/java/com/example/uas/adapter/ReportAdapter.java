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
import com.example.uas.model.Report;
import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.CardViewViewHolder> {
    private Context context;
    private List<Report> listReport;
    private List<Report> getListReport() {
        return listReport;
    }
    public void setListReport(List<Report> listReport) {
        this.listReport = listReport;
    }
    public ReportAdapter(Context context) {
        this.listReport = new ArrayList<Report>();
        this.context = context;
    }
    @NonNull
    @Override
    public ReportAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_adapter, parent, false);
        return new ReportAdapter.CardViewViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.CardViewViewHolder holder, int position) {
        Report report = getListReport().get(position);
        Glide.with(context).load(report.)
    }
    @NonNull
    @Override
    public int getItemCount() {
        return getListReport().size();
    }
    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView day;
        TextView report;

        CardViewViewHolder(View itemView){
            super(itemView);
            img =itemView.findViewById(R.id.std_image_report);
            day =itemView.findViewById(R.id.std_day_report);
            report =itemView.findViewById(R.id.std_report);
        }
    }
}