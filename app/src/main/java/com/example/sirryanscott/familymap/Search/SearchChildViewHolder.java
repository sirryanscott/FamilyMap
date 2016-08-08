package com.example.sirryanscott.familymap.Search;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.sirryanscott.familymap.R;

/**
 * Created by sirryanscott on 8/8/16.
 */
public class SearchChildViewHolder extends ChildViewHolder {
    private String personId;
    private String eventId;

    public ImageView imageView;
    public TextView topPanel;
    public TextView bottomPanel;
    public LinearLayout entireLayout;

    public SearchChildViewHolder(View myView) {
        super(myView);


        entireLayout = (LinearLayout) myView.findViewById(R.id.childDetails);
        imageView = (ImageView) myView.findViewById(R.id.image);
        topPanel = (TextView) myView.findViewById(R.id.topPanelDetails);
        bottomPanel = (TextView) myView.findViewById(R.id.bottomPanelDetails);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eventId != null) {
                    //start map activity
                    ((SearchActivity) view.getContext()).startMapActivity(eventId);
                } else {
                    //start person activity
                    ((SearchActivity) view.getContext()).startPersonActivity(personId);
                }
            }
        });
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
