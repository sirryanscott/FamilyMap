package com.example.sirryanscott.familymap.Settings;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sirryanscott.familymap.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment settingsFragment = SettingsFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mainSettingsFragmentHolder, settingsFragment).commit();
    }
}
