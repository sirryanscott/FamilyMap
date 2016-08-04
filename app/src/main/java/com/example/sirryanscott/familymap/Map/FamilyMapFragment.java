package com.example.sirryanscott.familymap.Map;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sirryanscott.familymap.Model.Event;
import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.Model.Person;
import com.example.sirryanscott.familymap.Person.PersonActivity;
import com.example.sirryanscott.familymap.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link FamilyMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyMapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap googleMap;
    private View myView;

    private String personID;
    private Event event;
    TextView nameInformation;
    TextView eventDetails;
    ImageView genderImageView;
    private HashMap<Marker, String> markerStringHashMap = new HashMap<>();

    public FamilyMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FamilyMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamilyMapFragment newInstance() {
        FamilyMapFragment fragment = new FamilyMapFragment();
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
        myView = inflater.inflate(R.layout.fragment_family_map, container, false);
        nameInformation = (TextView) myView.findViewById(R.id.nameInformation);
        eventDetails = (TextView) myView.findViewById(R.id.eventDetails);
        genderImageView = (ImageView) myView.findViewById(R.id.genderIcon);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        String eventId = getArguments().getString("eventId");
        if (eventId != null) {
            event = FamilyMapData.getInstance().getEventMap().get(eventId);
            setPersonTextViews(eventId);
        }


        LinearLayout eventDetails = (LinearLayout) myView.findViewById(R.id.allEventDetails);
        eventDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start Person Activity not Fragment
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("personId", personID);
                startActivity(intent);
            }
        });
        return myView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        for (Map.Entry<String, Event> iterator : FamilyMapData.getInstance().getEventMap().entrySet()) {
            Event event = iterator.getValue();
            Marker marker = googleMap.addMarker(
                    new MarkerOptions()
                            .position(event.getPosition()).icon(BitmapDescriptorFactory.defaultMarker(Color.BLACK)));
            markerStringHashMap.put(marker, event.getEventId());
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String eventId = markerStringHashMap.get(marker);
                setPersonTextViews(eventId);
                return false;
            }
        });
    }

    private void setPersonTextViews(String eventId) {
        if (eventId != null) {
            Event event = FamilyMapData.getInstance().getEventMap().get(eventId);
                    String personId = event.getPersonId();
                    this.personID = personId;
                    Person person = FamilyMapData.getInstance().getPersonMap().get(personId);

                    nameInformation.setText(person.getFullName());

                    eventDetails.setText(event.getFullDescription());

                    setGenderIcon(person);

                    //Todo: draw lines (relationships, events)

        }
    }

    private void setGenderIcon(Person person){
        Drawable genderIcon;
        String gender = person.getGender();
        if(gender.equals("m")){
            genderIcon = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).color(255).sizeDp(40);  //COLOR: Blue
        }
        else{
            genderIcon = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).color(16728448).sizeDp(40);  //COLOR: pink
        }

        genderImageView.setImageDrawable(genderIcon);
    }
}
