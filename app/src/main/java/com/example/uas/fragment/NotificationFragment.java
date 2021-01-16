package com.example.uas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.uas.R;


public class NotificationFragment extends Fragment {

    EditText h, m;
    Button rem;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        h = view.findViewById(R.id.hour);
        m = view.findViewById(R.id.minute);
        rem = view.findViewById(R.id.btn_rem);
        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hr = Integer.parseInt(h.getText().toString());
                int mn = Integer.parseInt(h.getText().toString());
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, hr);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, mn);
                if (hr <= 24 && mn <= 60) {
                    startActivity(intent);
                } else if (hr > 24) {
                    Toast.makeText(getActivity(), "Hour can't be 24 above!", Toast.LENGTH_SHORT).show();
                } else if (mn > 60) {
                    Toast.makeText(getActivity(), "Minute can't be 24 above!", Toast.LENGTH_SHORT).show();
                } else if (hr > 24 && mn > 60) {
                    Toast.makeText(getActivity(), "Hour can't be 24 above and Minute can't be 60 above!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Invalid Time!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}