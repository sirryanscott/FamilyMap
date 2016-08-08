package com.example.sirryanscott.familymap.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.sirryanscott.familymap.Map.MapActivity;
import com.example.sirryanscott.familymap.Person.PersonActivity;
import com.example.sirryanscott.familymap.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchFragment searchFragment = SearchFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.mainSearchFragmentHolder, searchFragment).commit();
    }

    public void startPersonActivity(String personId) {
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra("personId", personId);
        startActivity(intent);
    }

    public void startMapActivity(String eventId) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("eventId", eventId);
        startActivity(intent);
    }
}
