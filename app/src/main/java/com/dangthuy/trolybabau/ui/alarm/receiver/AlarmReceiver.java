package com.dangthuy.trolybabau.ui.alarm.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dangthuy.trolybabau.MainActivity;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.Alarm;

/**
 * Created by nhongthai on 4/27/2021.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "pregnancy";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.ALARM_ACTION)) {
            String data = intent.getStringExtra(Constants.ALARM_DATA);
//            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(Intent.ACTION_SEND);
////            i.setClassName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity");
//            i.setComponent(new ComponentName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity"));
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.putExtra(Constants.ALARM_DATA, data);
//            context.startActivity(i);
            createNotificationChannel(context);
            sendNotification(context, data);
        }
    }

    private void sendNotification(Context context, String data) {
        Intent i = new Intent(Intent.ACTION_SEND);
//            i.setClassName("pregnancy.beacon","com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity");
        i.setComponent(new ComponentName("pregnancy.beacon", "com.dangthuy.trolybabau.ui.alarm.receiver.AlarmActivity"));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra(Constants.ALARM_DATA, data);
        Log.d("thainh","sendNotification() data " + data);
//        context.startActivity(i);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_app_83_5x83_5)
                .setContentTitle("Nhắc nhở")
                .setContentText(data)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(data))
                .setFullScreenIntent(pendingIntent, true)
                .setContentIntent(pendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(11, builder.build());
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "pregnancy";
            String description = "alarm";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
