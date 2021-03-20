package com.dangthuy.trolybabau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.dangthuy.trolybabau.common.sharePrefs.SharedPrefsImpl;
import com.dangthuy.trolybabau.common.utils.Constants;
import com.dangthuy.trolybabau.databinding.ActivityMainBinding;
import com.dangthuy.trolybabau.ui.main.MainFragment;
import com.dangthuy.trolybabau.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPrefsImpl sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sharedPrefs = SharedPrefsImpl.newInstance(Constants.CACHE, this);
        if (sharedPrefs.get(Constants.SET_UP, Boolean.class)) {
            addFragment(MainFragment.newInstance());
        } else {
            addFragment(ProfileFragment.newInstance());
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, fragment)
                .commit();
    }
}