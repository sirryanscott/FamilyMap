package com.example.sirryanscott.familymap;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sirryanscott.familymap.Login.LoginFragment;
import com.example.sirryanscott.familymap.Map.FamilyMapFragment;
import com.example.sirryanscott.familymap.Model.Person;
import com.example.sirryanscott.familymap.Person.PersonActivity;
import com.example.sirryanscott.familymap.Person.PersonFragment;

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

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFragmentHolder, familyMapFragment).commit();
    }

    public void startPersonActivity(String personId){
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra("personId", personId);
        startActivity(intent);
    }
}
