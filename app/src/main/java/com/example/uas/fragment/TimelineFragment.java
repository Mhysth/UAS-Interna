package com.example.uas.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uas.R;
import com.example.uas.adapter.TimelineAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimelineFragment extends Fragment {

    @BindView(R.id.rv_timeline)
    RecyclerView recyclerView;

    private TimelineAdapter adapter;

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

        adapter = new TimelineAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}