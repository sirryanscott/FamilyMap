package com.example.sirryanscott.familymap.Person;

import android.view.View;
import android.widget.TextView;

import com.example.sirryanscott.familymap.R;

/**
 * Created by sirryanscott on 8/3/16.
 */
public class PersonParentViewHolder extends com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder {
    public TextView mtitleTextView;

    public PersonParentViewHolder(View myView){
        super(myView);

        mtitleTextView = (TextView) itemView.findViewById(R.id.parent_list_item_title_text_view);
    }
}
