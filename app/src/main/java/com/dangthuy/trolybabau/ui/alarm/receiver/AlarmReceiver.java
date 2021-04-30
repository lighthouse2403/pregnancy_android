package com.dangthuy.trolybabau.ui.alarm.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.dangthuy.trolybabau.MainActivity;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.Alarm;

/**
 * Created by nhongthai on 4/27/2021.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.ALARM_ACTION)) {
            String data = intent.getStringExtra(Constants.ALARM_DATA);
            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setClassName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity");
            i.setComponent(new ComponentName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity"));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(Constants.ALARM_DATA, data);
            context.startActivity(i);
        }
    }
}
