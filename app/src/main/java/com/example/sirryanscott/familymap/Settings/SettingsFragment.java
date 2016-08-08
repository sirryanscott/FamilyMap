package com.example.sirryanscott.familymap.Settings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sirryanscott.familymap.HttpClient;
import com.example.sirryanscott.familymap.MainActivity;
import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.R;
import com.google.android.gms.maps.GoogleMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myView;
    private TextView logout;
    private TextView resyncData;
    private Spinner spinner;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = (TextView) myView.findViewById(R.id.logout);
        resyncData = (TextView) myView.findViewById(R.id.ReSyncData);

        setSpinners();
        spinner.setSelection(FamilyMapData.getInstance().getSpinnerSelection());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        resyncData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FamilyMapData.getInstance().clearData();


                GetPeopleData getPeopleData = new GetPeopleData();
                getPeopleData.execute();
            }
        });

        spinner = (Spinner) myView.findViewById(R.id.mapTypeSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        FamilyMapData.getInstance().setGoogleMapType(GoogleMap.MAP_TYPE_NORMAL);
                        FamilyMapData.getInstance().setSpinnerSelection(0);
                        break;
                    case 1:
                        FamilyMapData.getInstance().setGoogleMapType(GoogleMap.MAP_TYPE_HYBRID);
                        FamilyMapData.getInstance().setSpinnerSelection(1);
                        break;
                    case 2:
                        FamilyMapData.getInstance().setGoogleMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        FamilyMapData.getInstance().setSpinnerSelection(2);
                        break;
                    case 3:
                        FamilyMapData.getInstance().setGoogleMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        FamilyMapData.getInstance().setSpinnerSelection(3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return myView;

    }


    public class GetPeopleData extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new HttpClient();

            boolean result = httpClient.peopleData();

            return result;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                GetEventData getEventData = new GetEventData();
                getEventData.execute();

            }
        }
    }

    public class GetEventData extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new HttpClient();

            boolean result = httpClient.eventData();
            return result;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                getActivity().finish();
            }
        }
    }

    private void setSpinners() {
        //SET LIFE STORY COLOR SPINNER
        Spinner lifeStorySpinner = (Spinner) myView.findViewById(R.id.lifeStoryLinesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> lifeStoryAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.line_color_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        lifeStoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lifeStorySpinner.setAdapter(lifeStoryAdapter);


        //SET FAMILY TREE LINES SPINNER
        Spinner familyTreeLineSpinner = (Spinner) myView.findViewById(R.id.familyTreeLinesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> familyTreeLineAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.line_color_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        familyTreeLineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        familyTreeLineSpinner.setAdapter(familyTreeLineAdapter);


        //SET SPOUSE LINE SPINNER
        Spinner spouseLineSpinner = (Spinner) myView.findViewById(R.id.spouseLinesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spouseLineAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.line_color_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spouseLineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spouseLineSpinner.setAdapter(spouseLineAdapter);


        //SET MAP-TYPE SPINNER
        spinner = (Spinner) myView.findViewById(R.id.mapTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.map_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
