package com.example.uas.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.Login;
import com.example.uas.MainActivity;
import com.example.uas.R;
import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.AccountViewModel;
import com.example.uas.viewModel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountFragment extends Fragment {

    @BindView(R.id.std_name)
    TextView name;
    @BindView(R.id.std_nim)
    TextView nim;
    @BindView(R.id.std_email)
    TextView email;
    @BindView(R.id.std_image)
    ImageView image;
    @BindView(R.id.logout_btn)
    Button fabLogout;

    //private AccountViewModel viewModel;
    private EventViewModel viewModel;
    //private EventAdapter adapter;
    private SharedPreferenceHelper helper;

    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //showLoading(true);
        //TODO: Place viewModel implementation here
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        //viewModel = ViewModelProviders.of(requireActivity()).get(AccountViewModel.class);
        viewModel = ViewModelProviders.of(requireActivity()).get(EventViewModel.class);
        viewModel.init(helper.getAccessToken());
        //viewModel.getEvents().observe(requireActivity(), observeViewModel);
        //rvEvent.setLayoutManager(new LinearLayoutManager(getActivity()));
        // adapter = new EventAdapter(getActivity());
    }
    @OnClick(R.id.logout_btn)
    public void logout(View view) {
        if (view.getId() == R.id.logout_btn) {
            viewModel.logout().observe(requireActivity(), message -> {
                if (!message.isEmpty()) {
                    helper.clearPref();
                    Intent onBoard = new Intent(getActivity(), Login.class);
                    onBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    startActivity(onBoard);
                }
            });
        }
    }
}