package com.example.sirryanscott.familymap.Settings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirryanscott.familymap.HttpClient;
import com.example.sirryanscott.familymap.Login.LoginData;
import com.example.sirryanscott.familymap.MainActivity;
import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.R;

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
//                singleton = constructor;
                String authorization = LoginData.getInstance().getCurrentUser().getAuthorization();
                String personId = LoginData.getInstance().getCurrentUser().getPersonId();


//                LoginData.getInstance().getCurrentUser().setAuthorization(authorization);
//                LoginData.getInstance().getCurrentUser().setPersonId(personId);


                FamilyMapData.getInstance().clearData();


                GetPeopleData getPeopleData = new GetPeopleData();
                getPeopleData.execute();
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


}
