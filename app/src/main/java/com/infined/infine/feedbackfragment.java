package com.infined.infine;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import me.anwarshahriar.calligrapher.Calligrapher;

public class feedbackfragment extends Fragment {

    private Button EmailButton;
    private Button Share;
    private Button Rate;
    private TextView sharefeedback;
    Typeface font;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedbackfragment, container, false);


    EmailButton = view.findViewById(R.id.EmailButton);

    Share = view.findViewById(R.id.Share);
    Rate = view.findViewById(R.id.Rate);
    sharefeedback = view.findViewById(R.id.sharefeedback);

        font = Typeface.createFromAsset(getContext().getAssets(),"fonts/Montserrat-Regular.ttf");

        Share.setTypeface(font);
        Rate.setTypeface(font);
        EmailButton.setTypeface(font);
        sharefeedback.setTypeface(font);




        EmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SendFeedback.class));
            }
        });
        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }

            }
        });
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\nI Recently downloaded this app and I highly Recommend it!\n\n";
                    sAux = sAux + "http://play.google.com/store/apps/details?id=" +  getContext().getPackageName();
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    return view;
    }
}
