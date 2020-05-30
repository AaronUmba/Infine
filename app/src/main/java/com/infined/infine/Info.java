package com.infined.infine;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    private Typeface type;
    private TextView infodesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        infodesc = (TextView)findViewById(R.id.infodesc);

        infodesc.setTypeface(type);
    }
}
