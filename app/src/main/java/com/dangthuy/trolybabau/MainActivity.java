package com.dangthuy.trolybabau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.dangthuy.trolybabau.common.sharePrefs.SharedPrefsImpl;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.database.AppDatabase;
import com.dangthuy.trolybabau.databinding.ActivityMainBinding;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static AppDatabase appDatabase;
    private final String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.SYSTEM_ALERT_WINDOW};

    public interface IPermissionListener {
        void onPermissionDone(boolean isAccepted);
    }

    private IPermissionListener permissionListener;

    public void setPermissionListener(IPermissionListener permissionListener) {
        this.permissionListener = permissionListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.dangthuy.trolybabau.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPrefsImpl sharedPrefs = SharedPrefsImpl.newInstance(Constants.CACHE, this);
        if (sharedPrefs.get(Constants.SET_UP, Boolean.class)) {
            addFragment(MainFragment.newInstance());
        } else {
            ProfileFragment fragment = ProfileFragment.newInstance(true);
            fragment.setListener(() -> {
                getSupportFragmentManager().popBackStack();
                addFragment(MainFragment.newInstance());
            });
            addFragment(fragment);
        }
        initDatabase();
        initPermission();
    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, 200);
        }
    }

    public void requestPermission(int position) {
        if (ContextCompat.checkSelfPermission(this, permission[position]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, 300);
        } else {
            permissionListener.onPermissionDone(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, " " + requestCode);
        if (requestCode == 300) {
            permissionListener.onPermissionDone(grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }

    private void initDatabase() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pregnancy-database").build();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, fragment)
                .commit();
    }
}