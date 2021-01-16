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

import com.bumptech.glide.Glide;
import com.example.uas.R;
import com.example.uas.adapter.TimelineAdapter;
import com.example.uas.model.local.Company;
import com.example.uas.model.local.Timeline;
import com.example.uas.model.local.User;
import com.example.uas.utils.SharedPreferenceHelper;

import com.example.uas.viewModel.CompanyViewModel;
import com.example.uas.viewModel.TimelineViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimelineFragment extends Fragment {

    @BindView(R.id.rv_timeline)
    RecyclerView recyclerView;
   /* @BindView(R.id.company_name)
    TextView company_name;*/

    private TimelineAdapter adapter;
    //test
    private TimelineViewModel viewModel;
    private CompanyViewModel viewCompany;
    private SharedPreferenceHelper helper;

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //test
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TimelineViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getEvents().observe(requireActivity(), observeViewModel);

        viewCompany =  ViewModelProviders.of(requireActivity()).get(CompanyViewModel.class);
        viewCompany.init(helper.getAccessToken());
       //viewCompany.getCompany().observe(requireActivity(), observeViewModel2);

        adapter = new TimelineAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private Observer<List<Timeline>> observeViewModel = new Observer<List<Timeline>>() {
        @Override
        public void onChanged(List<Timeline> listTimeline) {
            if (listTimeline!= null) {
                adapter.setListTimeline(listTimeline);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                showLoading(false);
            }
        }
    };

   /* private Observer<List<Company>> observeViewModel2 = new Observer<List<Company>>() {
        @Override
        public void onChanged(List<Company> listCompany) {
            if (listCompany!= null) {
                Company company = listCompany.get(0);
                company_name.setText(company.getName());
            }
        }
    };*/

    private void showLoading(Boolean state) {
        if (state) {
            recyclerView.setVisibility(View.GONE);
            //loading.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            //loading.setVisibility(View.GONE);
        }
    }
}