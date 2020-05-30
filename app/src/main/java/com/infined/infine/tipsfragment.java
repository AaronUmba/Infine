package com.infined.infine;

import android.content.Context;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.graphics.Typeface;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;
import me.anwarshahriar.calligrapher.Calligrapher;

import static kotlin.text.Typography.section;

import com.infined.infine.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;


public class tipsfragment extends Fragment {

    private ExpandableTextView expandableTextView;
    private ExpandableTextView expandableTextView2;
    private ExpandableTextView expandableTextView3;
    private ExpandableTextView expandableTextView4;
    private ExpandableTextView expandableTextView5;
    private Typeface type;

    String longText = "Getting Rid of Distractions\n"+"" +
            "\n" +
            "Eliminating distractions is paramount to staying focused. It is impossible to be doing work and using your phone or watching Television simultaneously, so getting rid of exterior distractions will allow you to focus on the work at hand. For example, if you are using Social Media regularly, it's important to turn off notifications so you won't be tempted to use it. One distraction may lead to another, and before you know it, you are swept up into a sea of diversions.";
    String longText2 = "Allocate Time\n"+"" +
            "\n" +
            "Allocating time for tasks gives you a timed objective for when you want to complete work. For example, you may want to spend 30 minutes with a History Essay and 15 minutes with a Mathematics Assignment. This planning will allow you to organise your time more effectively, subsequently leaving more time for entertainment or other activities. To help with this, use the Scheduler.";
    String longText3 = "Take Regular Breaks\n"+"" +
            "\n" +
            "Working for hours on end is not an efficient way to endeavor in a task due to the fact that your mind is under constant stress from work. Taking regular 5 - 10-minute breaks allows for you to re-compose yourself, clear your head and review what you've accomplished and what needs doing.";
    String longText4 = "Set Clear Goals\n"+"" +
            "\n" +
            "Setting clear goals helps tremendously when working on larger tasks. For example, Breaking down a larger assignment into smaller, less significant chunks allows you to methodically approach a task and come up with better solutions. By doing this, you are not only eliminating the idea that the task is too large and therefore impossible, you are also stopping yourself from becoming distracted with irrelevant tasks. To help with this, use the To-do Objective Lister.";
    String longText5 = "Organise your Day\n"+"" +
            "\n" +
            "Organising your day is very significant when attempting to adopt a more efficient workflow. For example, you may want to wake up at a certain time to do a number of tasks, then take an hour for resting. By doing this you remove any pointless tasks in your day and focus on the real tasks at hand, thus giving you more time in your day.";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tipsfragment, container, false);

        type = Typeface.createFromAsset(getContext().getAssets(),"fonts/Montserrat-Regular.ttf");


        expandableTextView = (ExpandableTextView)view.findViewById(R.id.View1);
        expandableTextView.setText(longText);

        expandableTextView2 = (ExpandableTextView)view.findViewById(R.id.View2);
        expandableTextView2.setText(longText2);

        expandableTextView3 = (ExpandableTextView)view.findViewById(R.id.View3);
        expandableTextView3.setText(longText3);

        expandableTextView4 = (ExpandableTextView)view.findViewById(R.id.View4);
        expandableTextView4.setText(longText4);

        expandableTextView5 = (ExpandableTextView)view.findViewById(R.id.View5);
        expandableTextView5.setText(longText5);




        return view;
    }
}

