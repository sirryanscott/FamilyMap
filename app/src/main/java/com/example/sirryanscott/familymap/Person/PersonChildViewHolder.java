package com.example.sirryanscott.familymap.Person;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.sirryanscott.familymap.R;

/**
 * Created by sirryanscott on 8/3/16.
 */
public class PersonChildViewHolder extends ChildViewHolder {

    public ImageView imageView;
    public TextView topPanel;
    public TextView bottomPanel;

    public PersonChildViewHolder(View myView) {
        super(myView);

        imageView = (ImageView)myView.findViewById(R.id.image);
        topPanel = (TextView)myView.findViewById(R.id.topPanelDetails);
        bottomPanel = (TextView) myView.findViewById(R.id.bottomPanelDetails);

    }
}
