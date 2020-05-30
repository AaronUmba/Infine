package com.infined.infine

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.infined.infine.R.id.*
import com.infined.infine.util.NotificationUtil
import com.infined.infine.Timer
import com.infined.infine.util.PrefUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_timer.*


import java.util.*

import kotlinx.android.synthetic.main.content_timer.*
import kotlinx.android.synthetic.main.fragment_timerfragment.*
import me.anwarshahriar.calligrapher.Calligrapher
import java.net.Inet4Address
import java.util.*

class Timer : AppCompatActivity() {

    companion object {
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerlengthseconds: Long = 0
    private var timerState = TimerState.Stopped

    private var secondsRemaining: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Timer"

        val calligrapher = Calligrapher(this)
        calligrapher.setFont(this, "fonts/Montserrat-Regular.ttf", true)

        TimerStartButton.setOnClickListener { _ ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }
        TimerPauseButton.setOnClickListener { _ ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }
        TimerStopButton.setOnClickListener { _ ->
            timer.cancel()
            onTimerFinished()

        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)
        return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        removeAlarm(this)
        NotificationUtil.hideTimerNotification(this)

    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(this, wakeUpTime)
        }
        else if (timerState == TimerState.Paused) {
            NotificationUtil.showTimerPaused(this)
        }
        PrefUtil.setPreviousTimerLengthSeconds(timerlengthseconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)

    }

    private fun initTimer() {
        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(this)
        else
            timerlengthseconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(this)
        if (alarmSetTime > 0)
            secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0)
            onTimerFinished()
        else if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()

    }


    private fun onTimerFinished(){
        timerState = TimerState.Stopped
        setNewTimerLength()

        progress_countdown.progress = 0

        PrefUtil.setSecondsRemaining(timerlengthseconds, this)
        secondsRemaining = timerlengthseconds

        updateButtons()
        updateCountdownUI()

    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()

    }

    private fun setNewTimerLength() {
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerlengthseconds = (lengthInMinutes * 60L)
        progress_countdown.max = timerlengthseconds.toInt()
    }

    private fun setPreviousTimerLength() {
        timerlengthseconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        progress_countdown.max = timerlengthseconds.toInt()

    }

    private fun updateCountdownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondStr = secondsInMinuteUntilFinished.toString()
        textView_countdown.text = "$minutesUntilFinished:${if (secondStr.length == 2) secondStr else "0" + secondStr}"
        progress_countdown.progress = (timerlengthseconds - secondsRemaining).toInt()


    }

    private fun updateButtons() {
        when (timerState) {
            TimerState.Running -> {
                TimerStartButton.isEnabled = false
                TimerPauseButton.isEnabled = true
                TimerStopButton.isEnabled = true
                TimerStartButton.visibility = View.INVISIBLE
                TimerPauseButton.visibility = View.VISIBLE
                TimerStopButton.visibility = View.VISIBLE
                distraction.visibility = View.VISIBLE
            }
            TimerState.Paused -> {
                TimerStartButton.isEnabled = true
                TimerPauseButton.isEnabled = false
                TimerStopButton.isEnabled = true
                TimerStartButton.visibility = View.VISIBLE
                TimerPauseButton.visibility = View.INVISIBLE
                TimerStopButton.visibility = View.INVISIBLE
                distraction.visibility = View.INVISIBLE
            }
            TimerState.Stopped -> {
                TimerStartButton.isEnabled = true
                TimerPauseButton.isEnabled = false
                TimerStopButton.isEnabled = false
                TimerStartButton.visibility = View.VISIBLE
                TimerPauseButton.visibility = View.INVISIBLE
                TimerStopButton.visibility = View.INVISIBLE
                distraction.visibility = View.INVISIBLE
            }
        }
    }
}

