package com.dangthuy.trolybabau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;

import com.dangthuy.trolybabau.common.sharePrefs.SharedPrefsImpl;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.data.database.AppDatabase;
import com.dangthuy.trolybabau.databinding.ActivityMainBinding;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPrefsImpl sharedPrefs;
    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefs = SharedPrefsImpl.newInstance(Constants.CACHE, this);
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
    }

    private void initDatabase() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "baby-foot").build();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, fragment)
                .commit();
    }
}