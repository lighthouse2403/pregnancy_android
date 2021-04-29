package com.dangthuy.trolybabau.ui.alarm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.dangthuy.trolybabau.MainActivity;

/**
 * Created by nhongthai on 4/27/2021.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("dangthuy_alarm")) {
            String text = intent.getStringExtra("data");
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setClassName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
