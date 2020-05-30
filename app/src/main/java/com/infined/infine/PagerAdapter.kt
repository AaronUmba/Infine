package com.infined.infine

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment? {
       when (position) {
           0 -> return timerfragment()
           1 -> return tipsfragment()
           2 -> return feedbackfragment()
           else -> return null
       }
    }

    override fun getCount(): Int {
        return tabCount
    }


}