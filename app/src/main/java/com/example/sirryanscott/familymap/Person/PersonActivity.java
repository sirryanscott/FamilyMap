package com.example.sirryanscott.familymap.Person;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.Model.Person;
import com.example.sirryanscott.familymap.R;

public class PersonActivity extends AppCompatActivity {
    private String personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Intent intent = getIntent();
        Bundle b =  intent.getExtras();

        if(b != null){
            personId = (String) b.get("personId");

            //start a person fragment
            PersonFragment personFragment = PersonFragment.newInstance();


            Bundle bundle = new Bundle();
            bundle.putString("personId", personId);
            personFragment.setArguments(bundle);


            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.mainFragmentHolder, personFragment).commit();
        }
    }
}
