package com.example.uas.fragment;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.uas.R;
import com.example.uas.adapter.ReportAdapter;
import com.example.uas.adapter.TimelineAdapter;
import com.example.uas.model.local.Company;
import com.example.uas.model.local.Report;
import com.example.uas.model.local.Timeline;
import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.CompanyViewModel;
import com.example.uas.viewModel.ReportViewModel;
import com.example.uas.viewModel.TimelineViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class DashboardFragment extends Fragment {
    @BindView(R.id.rv_reports)
    RecyclerView recyclerView;
    @BindView(R.id.company_name)
    TextView company_name;
    @BindView(R.id.company_email)
    TextView company_email;
    @BindView(R.id.company_phone)
    TextView company_phone;
    @BindView(R.id.company_supervisor_phone)
    TextView supervisior_phone;
    private ReportAdapter adapter;
    private ReportViewModel viewModel;
    private SharedPreferenceHelper helper;
    private CompanyViewModel viewCompany;
    public DashboardFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ReportViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getReport().observe(requireActivity(), observeViewModel);
        adapter = new ReportAdapter(getContext());
        viewCompany =  ViewModelProviders.of(requireActivity()).get(CompanyViewModel.class);
        viewCompany.init(helper.getAccessToken());
        viewCompany.getCompany().observe(requireActivity(), observeViewModel2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private Observer<List<Report>> observeViewModel = new Observer<List<Report>>() {
        @Override
        public void onChanged(List<Report> listReport) {
            if (listReport!= null) {
                adapter.setListReport(listReport);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                showLoading(false);
            }
        }
    };
    private Observer<List<Company>> observeViewModel2 = new Observer<List<Company>>() {
        @Override
        public void onChanged(List<Company> listCompany) {
            if (listCompany.size() > 0) {
                Company company = listCompany.get(0);
                company_name.setText(company.getName());
                company_email.setText(company.getEmail());
                company_phone.setText(company.getPhone());
                supervisior_phone.setText(company.getSupervisior_contact());
            }
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}