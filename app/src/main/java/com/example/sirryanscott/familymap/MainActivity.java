package com.example.sirryanscott.familymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sirryanscott.familymap.Filter.FilterActivity;
import com.example.sirryanscott.familymap.Login.LoginFragment;
import com.example.sirryanscott.familymap.Map.FamilyMapFragment;
import com.example.sirryanscott.familymap.Search.SearchActivity;
import com.example.sirryanscott.familymap.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = LoginFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mainFragmentHolder, loginFragment).commit();
    }

    public void startMapFragment(){
        FamilyMapFragment familyMapFragment = FamilyMapFragment.newInstance();

        familyMapFragment.setFragmentForMain(true);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFragmentHolder, familyMapFragment).commit();
    }

    public void startSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);

        startActivity(intent);
    }

    public void startFilterActivity() {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }

    public void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
