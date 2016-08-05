package com.example.sirryanscott.familymap.Person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sirryanscott.familymap.MainActivity;
import com.example.sirryanscott.familymap.Model.Event;
import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.Model.Person;
import com.example.sirryanscott.familymap.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myView;
    private Person currentPerson;


    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_person, container, false);

        String personId = getArguments().getString("personId");

        if(personId != null) {
            currentPerson = FamilyMapData.getInstance().getPersonMap().get(personId);

            TextView firstName = (TextView) myView.findViewById(R.id.firstName);
            firstName.setText(currentPerson.getFirstName());

            TextView lastName = (TextView) myView.findViewById(R.id.lastName);
            lastName.setText(currentPerson.getLastName());

            TextView gender = (TextView) myView.findViewById(R.id.gender);
            if(currentPerson.getGender().equals("m")) {
                gender.setText("Male");
            }
            else {
                gender.setText("Female");
            }
        }


        setExpandableView();
        return myView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_map_or_person_activity, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_topButton:
                //Todo: code perfect for logging out
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setExpandableView(){
        List<Child> childrenEvent = new LinkedList<>();
        List<Child> childrenFamily = new LinkedList<>();


        //get event children
        for(Event event : currentPerson.getEvents()){
            Child child = new Child(
                    event.getFullDescription(),
                    currentPerson.getFullName(),
                    new IconDrawable(getActivity(), Iconify.IconValue.fa_map_marker).color(8421504).sizeDp(40) //color gray
            );
            child.setEventId(event.getEventId());
            childrenEvent.add(child);
        }



        //get family children
        if(currentPerson.getSpouse() != null){
            Person spouse = currentPerson.getSpouse();

            //set spouse gender icon
            IconDrawable iconDrawable;
            if(spouse.getGender().equals("f")){
                iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).color(16728448).sizeDp(40);
            }
            else{
                iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).color(255).sizeDp(40);
            }


            Child childSpouse =  new Child(
                    spouse.getFullName(),
                    "Spouse",
                    iconDrawable
            );

            childSpouse.setPersonId(spouse.getPersonId());
            childrenFamily.add(childSpouse);
        }

        if(currentPerson.getFather() != null){
            Person father = currentPerson.getFather();
            IconDrawable iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).color(255).sizeDp(40);

            Child childFather = new Child(
                    father.getFullName(),
                    "Father",
                    iconDrawable
            );

            childFather.setPersonId(father.getPersonId());
            childrenFamily.add(childFather);
        }

        if(currentPerson.getMother() != null){
            Person mother = currentPerson.getMother();
            IconDrawable iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).color(16728448).sizeDp(40);

            Child childMother = new Child(
                    mother.getFullName(),
                    "Mother",
                    iconDrawable
            );

            childMother.setPersonId(mother.getPersonId());
            childrenFamily.add(childMother);
        }


        if(currentPerson.getChildren() != null) {
            for (Person personChild : currentPerson.getChildren()) {
                String childType;
                IconDrawable iconDrawable;

                if(personChild.getGender().equals("m")) {
                    childType = "Son";
                    iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).color(255).sizeDp(40);
                }
                else{
                    childType = "Daughter";
                    iconDrawable = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).color(16728448).sizeDp(40);
                }
                Child child = new Child(
                        personChild.getFullName(),
                        childType,
                        iconDrawable
                );

                child.setPersonId(personChild.getPersonId());
                childrenFamily.add(child);
            }
        }

        Parent parent1 = new Parent(childrenEvent);
        parent1.setTitle("LIFE EVENTS");
        Parent parent2 = new Parent(childrenFamily);
        parent2.setTitle("FAMILY");
        List<Parent> parents = Arrays.asList(parent1, parent2);

        RecyclerView recyclerView = (RecyclerView)myView.findViewById(R.id.events);
        ExpandableAdapter adapter = new ExpandableAdapter(getActivity(), parents);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
