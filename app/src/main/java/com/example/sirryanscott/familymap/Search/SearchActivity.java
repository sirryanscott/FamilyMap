package com.example.sirryanscott.familymap.Search;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

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
}
