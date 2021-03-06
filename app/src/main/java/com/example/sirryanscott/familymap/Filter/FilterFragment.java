package com.example.sirryanscott.familymap.Filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.sirryanscott.familymap.Model.FamilyMapData;
import com.example.sirryanscott.familymap.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link FilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myView;
    private TextView fatherSide;
    private ToggleButton fatherToggle;


    public FilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
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
        myView = inflater.inflate(R.layout.fragment_filter, container, false);

        setRecyclerView();
        return myView;
    }

    private void setRecyclerView() {
        List<String> eventNames = new LinkedList<>();

        for (Map.Entry<String, Float> allUniqueEvents : FamilyMapData.getInstance().getUniqueEvents().entrySet()) {
            eventNames.add(allUniqueEvents.getKey() + " EVENTS");
        }

        RecyclerView recyclerView = (RecyclerView) myView.findViewById(R.id.uniqueEvents);

        FilterAdapter filterAdapter = new FilterAdapter(eventNames);

        recyclerView.setAdapter(filterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
