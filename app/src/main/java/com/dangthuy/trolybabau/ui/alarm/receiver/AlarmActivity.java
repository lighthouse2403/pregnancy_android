package com.dangthuy.trolybabau.ui.alarm.receiver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.dialog.AlarmDialog;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.model.Alarm;

/**
 * Created by nhongthai on 4/27/2021.
 */
public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = "AlarmActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_alarm);
        showDialog();
    }

    private void showDialog() {
        Intent intent = getIntent();
        String note = intent.getStringExtra(Constants.ALARM_DATA);
        AlarmDialog dialog = new AlarmDialog(this);
        dialog.setTextAlarm(note);
        dialog.setListener(() -> {
            dialog.dismiss();
            this.finish();
        });
        dialog.show();
    }
}
