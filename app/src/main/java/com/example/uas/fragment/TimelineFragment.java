package com.example.uas.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uas.R;
import com.example.uas.adapter.TimelineAdapter;
import com.example.uas.model.local.Timeline;
import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.TimelineViewModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class TimelineFragment extends Fragment {
    @BindView(R.id.rv_timeline)
    RecyclerView recyclerView;
    private TimelineAdapter adapter;
    private TimelineViewModel viewModel;
    private SharedPreferenceHelper helper;
    public TimelineFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TimelineViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getTimeline().observe(requireActivity(), observeViewModel);
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
    private void showLoading(Boolean state) {
        if (state) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}