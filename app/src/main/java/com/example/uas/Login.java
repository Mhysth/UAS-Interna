package com.example.uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas.utils.SharedPreferenceHelper;
import com.example.uas.viewModel.LoginViewModel;

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
    String mail, pass;

    private LoginViewModel viewModel;
    private SharedPreferenceHelper helper;
    AlphaAnimation klik = new AlphaAnimation(1F, 0.6F);
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        editEmail = findViewById(R.id.edtEmail);
        editPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.login_btn);

        TextWatcher TW = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mail = editEmail.getText().toString().trim();
                pass = editPassword.getText().toString().trim();
                btnLogin.setEnabled(!mail.isEmpty() && !pass.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        editEmail.addTextChangedListener(TW);
        editPassword.addTextChangedListener(TW);

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
    /*@OnClick({R.id.login_btn})
    public void onClick(View view) {
        view.startAnimation(klik);
        new AlertDialog.Builder(Login.this)
                .setTitle("Konfirmasi")
                .setMessage("Are you sure to want to login ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        // dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //dialog.cancel();
                                if (!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                                    String email = editEmail.getText().toString().trim();
                                    String password = editPassword.getText().toString().trim();
                                    viewModel.login(email, password).observe(Login.this, tokenResponse -> {
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
                .show();*/
    //case R.id.tvReg:
    //    NavDirections actions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
    //   Navigation.findNavController(view).navigate(actions);
    //   break;
}