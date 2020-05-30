package com.infined.infine;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.anwarshahriar.calligrapher.Calligrapher;

public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;
    String Stay_Focused;
    String Work_Smarter;
    String Organise_Your_Time;


    public int[] slide_images = {
            R.drawable.ic_alarm_white_24dp,
            R.drawable.ic_work_white_24dp,
            R.drawable.ic_event_note_white_24dp
    };
    public String[] slide_headings = {
            "Stay Focused",
            "Work Smarter",
            "Organise Your Time"
    };
    public String[] slide_desc = {
            "An environment without distractions is imperative to being focused, and currently in our society, our phones are the biggest distraction we possess.",
            "Rather than working harder and for longer, it is better to work more methodically and efficiently to get the most out of your time.",
            "The ability to be organised and manage time efficiently is paramount to being productive and getting your work done."
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {

        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;

    }

    @Override
    public Object instantiateItem(ViewGroup contanier, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, contanier, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView header = view.findViewById(R.id.header);
        TextView description = view.findViewById(R.id.description);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/Montserrat-Regular.ttf");
        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/Montserrat-Bold.ttf");
        imageView.setImageResource(slide_images[position]);
        header.setText(slide_headings[position]);
        description.setText(slide_desc[position]);
        description.setTypeface(type);
        header.setTypeface(type2);
        contanier.addView(view);

        return view;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView description;
        TextView header;

        if (convertView == null) {
            description = (TextView)layoutInflater.inflate(R.layout.slide_layout, parent, false);
            header = (TextView)layoutInflater.inflate(R.layout.slide_layout, parent,false);
        } else {
            description = (TextView)convertView;
            header = (TextView)convertView;
        }

        description.setText(slide_desc[position]);
        header.setText(slide_headings[position]);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/Montserrat-Regular.ttf");
        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/Montserrat-Bold.ttf");
        description.setTypeface(type);
        header.setTypeface(type2);

        return description;

    }


    @Override
    public void destroyItem(ViewGroup contanier, int postition, Object object){
        contanier.removeView((RelativeLayout)object);
    }
}