package com.infined.infine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.infined.infine.util.NotificationUtil
import com.infined.infine.util.PrefUtil

class TimerExpiredReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(Timer.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
