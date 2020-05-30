package com.infined.infine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.infined.infine.AlarmActivity;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

import me.anwarshahriar.calligrapher.Calligrapher;

public class timerfragment extends Fragment {
    
    private CardView TimerButton;
    private CardView ToDoButton;
    private CardView ReminderButton;
    private TextView TimerText;
    private TextView TimerDesc;
    private TextView ToDoText;
    private TextView ToDoDesc;
    private TextView ReminderText;
    private TextView ReminderDesc;
    private Typeface type;
    private Typeface typebold;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timerfragment, container, false);

        typebold = Typeface.createFromAsset(getContext().getAssets(),"fonts/Montserrat-Medium.ttf");
        type = Typeface.createFromAsset(getContext().getAssets(),"fonts/Montserrat-Regular.ttf");

        TimerText = view.findViewById(R.id.TimerText);
        TimerDesc = view.findViewById(R.id.TimerDesc);
        ToDoText = view.findViewById(R.id.ToDoText);
        ToDoDesc = view.findViewById(R.id.ToDoDesc);
        ReminderText = view.findViewById(R.id.ScheduleText);
        ReminderDesc = view.findViewById(R.id.ScheduleDesc);

        TimerText.setTypeface(typebold);
        ToDoText.setTypeface(typebold);
        ReminderText.setTypeface(typebold);
        TimerDesc.setTypeface(type);
        ToDoDesc.setTypeface(type);
        ReminderDesc.setTypeface(type);

        TimerButton = view.findViewById(R.id.TimerButton);
        ToDoButton = view.findViewById(R.id.ToDoButton);
        ReminderButton = view.findViewById(R.id.ReminderButton);

        ToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ToDo.class));
            }
        });

        TimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Timer.class));

            }
        });
        ReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AlarmActivity.class));

            }
        });
        return view;
    }
}

