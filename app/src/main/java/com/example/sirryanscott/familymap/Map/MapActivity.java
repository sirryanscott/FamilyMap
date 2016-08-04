package com.example.sirryanscott.familymap.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sirryanscott.familymap.R;

public class MapActivity extends AppCompatActivity {
    private String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            eventId = (String) b.get("eventId");

            //start a map Fragment
            FamilyMapFragment familyMapFragment = FamilyMapFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString("eventId", eventId);

            familyMapFragment.setArguments(bundle);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.mainMapFragmentHolder, familyMapFragment).commit();
        }
    }
}
