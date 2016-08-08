package com.example.sirryanscott.familymap.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sirryanscott.familymap.Model.Event;
import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.Model.Person;
import com.example.sirryanscott.familymap.Person.Child;
import com.example.sirryanscott.familymap.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myView;
    private EditText searchBox;
    private ImageView searchImage;
    private String searchResult;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        myView = inflater.inflate(R.layout.fragment_search, container, false);

        searchBox = (EditText) myView.findViewById(R.id.searchField);
        searchImage = (ImageView) myView.findViewById(R.id.searchButton);
        searchImage.setImageResource(R.drawable.ic_search_black_24dp);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResult = searchBox.getText().toString();

                setRecyclerView(searchResult);

            }
        });
        return myView;
    }

    private void setRecyclerView(String searchResult) {
        if (searchResult != null) {
            List<Child> personResults = new LinkedList<>();
            List<Child> eventResults = new LinkedList<>();

            for (Map.Entry<String, Person> personEntry : FamilyMapData.getInstance().getPersonMap().entrySet()) {
                Person person = personEntry.getValue();
                if (person.getFullName().toLowerCase().contains(searchResult)) {
                    IconDrawable iconDrawable;
                    if (person.getGender().toLowerCase().equals("f")) {
                        iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).color(16728448).sizeDp(40);
                    } else {
                        iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).color(255).sizeDp(40);
                    }


                    Child child = new Child(
                            person.getFullName(),
                            "",
                            iconDrawable
                    );
                    child.setPersonId(person.getPersonId());
                    personResults.add(child);
                }
            }

            for (Map.Entry<String, Event> eventEntry : FamilyMapData.getInstance().getEventMap().entrySet()) {
                Event event = eventEntry.getValue();
                Person currentPerson = FamilyMapData.getInstance().getPersonMap().get(event.getPersonId());
                if (event.getFullDescription().contains(searchResult)) {
                    Child child = new Child(
                            event.getFullDescription(),
                            currentPerson.getFullName(),
                            new IconDrawable(getActivity(), Iconify.IconValue.fa_map_marker).color(8421504).sizeDp(40) //color gray
                    );

                    child.setEventId(event.getEventId());
                    personResults.add(child);
                }
            }


            RecyclerView peopleRecyclerView = (RecyclerView) myView.findViewById(R.id.peopleResults);
//            RecyclerView eventRecyclerView = (RecyclerView) myView.findViewById(R.id.eventResults);

            SearchAdapter peopleSearchAdapter = new SearchAdapter(personResults);
//            SearchAdapter eventSearchAdapter = new SearchAdapter(eventResults);

            peopleRecyclerView.setAdapter(peopleSearchAdapter);
//            eventRecyclerView.setAdapter(eventSearchAdapter);

            peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            eventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}
