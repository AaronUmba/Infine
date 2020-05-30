package com.infined.infine

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.infined.infine.util.NotificationUtil
import com.infined.infine.util.PrefUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import kotlinx.android.synthetic.main.content_timer.*
import me.anwarshahriar.calligrapher.Calligrapher
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(R.anim.fade_in_q, R.anim.fade_out_q)
        val toolbar = findViewById<Toolbar>(R.id.toolbarmain)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Infine"
        val calligrapher = Calligrapher(this)
        calligrapher.setFont(this, "fonts/Montserrat-Regular.ttf", true)

        configureTabLayout()

    }


    private fun configureTabLayout() {

        val adapter = PagerAdapter(supportFragmentManager, tabs.tabCount)
        MainViewPager.adapter = adapter

        MainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                MainViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                val intent = Intent(this, Info::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



