package com.example.sirryanscott.familymap;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sirryanscott.familymap.Login.LoginFragment;
import com.example.sirryanscott.familymap.Map.FamilyMapFragment;

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
}
