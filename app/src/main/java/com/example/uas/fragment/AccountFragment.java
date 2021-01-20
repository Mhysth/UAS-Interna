package com.example.uas.fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.uas.Login;
import com.example.uas.R;
import com.example.uas.model.local.User;
import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.AccountViewModel;
import com.example.uas.viewModel.TimelineViewModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AccountFragment extends Fragment {
    @BindView(R.id.std_name)
    TextView name;
    @BindView(R.id.std_nim)
    TextView nim;
    @BindView(R.id.std_gender)
    TextView gender;
    @BindView(R.id.std_line)
    TextView line;
    @BindView(R.id.std_phone)
    TextView phone;
    @BindView(R.id.std_batch)
    TextView batch;
    @BindView(R.id.std_email)
    TextView email;
    @BindView(R.id.std_image)
    ImageView image;
    @BindView(R.id.logout_btn)
    Button fabLogout;
    private TimelineViewModel viewModel;
    private AccountViewModel userViewModel;
    private SharedPreferenceHelper helper;
    AlphaAnimation klik = new AlphaAnimation(1F, 0.6F);
    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //TODO: Place viewModel implementation here
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        //viewModel = ViewModelProviders.of(requireActivity()).get(AccountViewModel.class);
        viewModel = ViewModelProviders.of(requireActivity()).get(TimelineViewModel.class);
        viewModel.init(helper.getAccessToken());
        userViewModel = ViewModelProviders.of(requireActivity()).get(AccountViewModel.class);
        userViewModel.init(helper.getAccessToken());
        userViewModel.getUser().observe(requireActivity(), observeViewModel);
    }
    private Observer<List<User>> observeViewModel = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> listUser) {
            if (listUser != null) {
                User user = listUser.get(0);
                name.setText(user.getName());
                nim.setText(user.getNim());
                gender.setText(user.getGender());
                line.setText(user.getLine_account());
                phone.setText(user.getPhone());
                batch.setText(user.getPeriod_id());
                //Glide.with(getActivity()).load(BASE_IMAGE_URL+ user.getPhoto()).into(image);
                Glide.with(getActivity()).load(user.getPhoto()).into(image);
                //batch.setText(user.getBatch());
                email.setText(user.getEmail());
            }
        }
    };
    @OnClick(R.id.logout_btn)
    public void logout(View view) {
        view.startAnimation(klik);
        new AlertDialog.Builder(getActivity())
                .setTitle("Konfirmasi")
                .setMessage("Are you sure to want to logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        // dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //dialog.cancel();
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
                        }, 2000);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }
}