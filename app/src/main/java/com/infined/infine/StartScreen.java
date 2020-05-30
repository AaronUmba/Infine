package com.infined.infine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infined.infine.MainActivity;
import com.infined.infine.R;
import com.infined.infine.SliderAdapter;

import me.anwarshahriar.calligrapher.Calligrapher;

public class StartScreen extends AppCompatActivity {


        private ViewPager mSliderPager;
        private LinearLayout mDotLayout;

        private TextView[] mDots;
        private SliderAdapter sliderAdapter;

        private Button mBack;
        private Button mNext;
        private Button mFinal;

        private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Montserrat-Regular.ttf",true);


        mSliderPager = findViewById(R.id.SliderPager);
        mDotLayout = findViewById(R.id.DotLayout);

        mNext = findViewById(R.id.Next);
        mBack = findViewById(R.id.Back);
        mFinal = findViewById(R.id.Final);


        sliderAdapter = new SliderAdapter(this);

        mSliderPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);

        mSliderPager.addOnPageChangeListener((viewlistener));

        //OnClickListeners

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSliderPager.setCurrentItem(mCurrentPage + 1);

            }


        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSliderPager.setCurrentItem(mCurrentPage - 1);

            }
        });
        mFinal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartScreen.this, MainActivity.class));
            }
        });
    }
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDarker1));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorLightGrey3));
        }
    }
    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }
        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            mCurrentPage = i;

            if(i == 0){
                mNext.setEnabled(true);
                mBack.setEnabled(false);
                mFinal.setEnabled(false);
                mBack.setVisibility(View.INVISIBLE);
                mFinal.setVisibility(View.INVISIBLE);

                mFinal.setText("");

            }   else if (i == mDots.length -1 ){

                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mFinal.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mFinal.setVisibility(View.VISIBLE);

                mNext.setText("");
                mBack.setText("Back");
                mFinal.setText("Begin");

            }  else {

                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mFinal.setEnabled(false);
                mBack.setVisibility(View.VISIBLE);
                mFinal.setVisibility(View.INVISIBLE);

                mNext.setText("Next");
                mBack.setText("Back");
                mFinal.setText("Begin");
            }
        }
        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };
}