package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.LoginViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText editEmail;
    @BindView(R.id.edtPassword)
    EditText editPassword;
    @BindView(R.id.login_btn)
    Button btnLogin;
    @BindView(R.id.check_password)
    CheckBox check_pass;


    private LoginViewModel viewModel;
    private SharedPreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);
       // Objects.requireNonNull((this).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        //TODO: Place viewModel implementation here
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        helper = SharedPreferenceHelper.getInstance(this);

        check_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                    String email = editEmail.getText().toString().trim();
                    String password = editPassword.getText().toString().trim();
                    viewModel.login(email, password).observe(this, tokenResponse -> {
                        if (tokenResponse != null) {
                            helper.saveAccessToken(tokenResponse.getAuthorization());
                            Intent onBoard = new Intent(Login.this, MainActivity.class);
                            onBoard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                            startActivity(onBoard);
                            finish();
                        }
                    });
                }
                break;
            //case R.id.tvReg:
            //    NavDirections actions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
            //   Navigation.findNavController(view).navigate(actions);
            //   break;
        }
    }


}