package com.example.uas.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uas.R;
import com.example.uas.model.local.Report;
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
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Report report = getListReport().get(position);
        holder.rp.setText(report.getTitle());
        String test = "1";
        if (report.getStats().equalsIgnoreCase("0")){
            holder.ch.setText("Pending");
        }else if (report.getStats().equalsIgnoreCase("1")){
            holder.ch.setText("Success");
        }else if (report.getStats().equalsIgnoreCase("2")){
            holder.ch.setText("Rejected");
        }
    }
    @Override
    public int getItemCount() {
        return getListReport().size();
    }
    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView rp, ch, im;
        CardViewViewHolder(View itemView) {
            super(itemView);
            rp = itemView.findViewById(R.id.std_report);
            ch = itemView.findViewById(R.id.std_check);
        }
    }
}