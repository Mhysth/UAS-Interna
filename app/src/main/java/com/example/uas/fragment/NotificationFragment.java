package com.example.uas.fragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.uas.R;

import java.util.Calendar;


public class NotificationFragment extends Fragment {

    EditText h, m;
    Button tm, rem;
    TimePickerDialog timePickerDialog;
    Calendar c;
    int current_h;
    int current_m;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        h = view.findViewById(R.id.hour);
        m = view.findViewById(R.id.minute);
        tm = view.findViewById(R.id.btn_set);
        rem = view.findViewById(R.id.btn_rem);
        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                current_h = c.get(Calendar.HOUR_OF_DAY);
                current_m = c.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        h.setText(String.format("", hourOfDay));
                        h.setText(String.format("", minute));
                    }
                }, current_h, current_m, false);
                timePickerDialog.show();
            }
        });
        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!h.getText().toString().isEmpty() && !m.getText().toString().isEmpty()) {
                    int hr = Integer.parseInt(h.getText().toString());
                    int mn = Integer.parseInt(h.getText().toString());
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, hr);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, mn);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Assignment Reminder");
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "There is no app that support this action!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Choose a time!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}