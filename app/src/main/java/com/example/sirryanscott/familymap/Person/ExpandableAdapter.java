package com.example.sirryanscott.familymap.Person;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.sirryanscott.familymap.R;

import java.util.List;

/**
 * Created by sirryanscott on 8/3/16.
 */
public class ExpandableAdapter extends ExpandableRecyclerAdapter<PersonParentViewHolder, PersonChildViewHolder> {

    private LayoutInflater mInflater;
    public ExpandableAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList){
        super(parentItemList);

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PersonParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View parentView = mInflater.inflate(R.layout.parent_layout, parentViewGroup, false);
        return new PersonParentViewHolder(parentView);
    }

    @Override
    public PersonChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childView = mInflater.inflate(R.layout.child_layout, childViewGroup, false);
        return new PersonChildViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(PersonParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Parent parent = (Parent) parentListItem;
        parentViewHolder.mtitleTextView.setText(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(PersonChildViewHolder childViewHolder, int position, Object childListItem) {
        Child child = (Child) childListItem;
        childViewHolder.topPanel.setText(child.getTopPanel());
        childViewHolder.bottomPanel.setText(child.getBottomPanel());
        childViewHolder.imageView.setImageDrawable(child.getImage());
    }
}
