package com.example.uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.model.local.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CardViewViewHolder> {
    private Context context;
    private List<Company> listCompany;

    private List<Company> getListCompany() {
        return listCompany;
    }

    public void setListCompany(List<Company> listCompany) {
        this.listCompany = listCompany;
        notifyDataSetChanged();
    }

    public CompanyAdapter(Context context) {
        this.listCompany = new ArrayList<Company>();
        this.context = context;
    }
    @NonNull
    @Override
    public CompanyAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_timeline, parent, false);
        return new CompanyAdapter.CardViewViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.CardViewViewHolder holder, int position) {
        Company company = getListCompany().get(position);
        holder.name.setText(company.getName());
        holder.email.setText(company.getEmail());
        holder.phone.setText(company.getPhone());
        holder.supervisior_contact.setText(company.getSupervisior_contact());
    }

    @Override
    public int getItemCount() {
        return getListCompany().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, supervisior_contact;

        CardViewViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.company_name);
            email = itemView.findViewById(R.id.company_email);
            phone = itemView.findViewById(R.id.company_phone);
            supervisior_contact = itemView.findViewById(R.id.company_supervisor_phone);
        }
    }
}