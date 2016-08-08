package com.example.sirryanscott.familymap.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sirryanscott.familymap.Person.Child;
import com.example.sirryanscott.familymap.R;

import java.util.List;

/**
 * Created by sirryanscott on 8/8/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchChildViewHolder> {
    private List<Child> childListItem;

    public SearchAdapter(List<Child> childListItem) {
        this.childListItem = childListItem;
    }

    @Override
    public SearchChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.child_layout, parent, false);
        return new SearchChildViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(SearchChildViewHolder holder, int position) {
        Child child = (Child) childListItem.get(position);

        holder.topPanel.setText(child.getTopPanel());
        holder.bottomPanel.setText(child.getBottomPanel());
        holder.imageView.setImageDrawable(child.getImage());
        if (child.getEventId() != null) {
            holder.setEventId(child.getEventId());
        } else {
            holder.setPersonId(child.getPersonId());
        }
    }

    @Override
    public int getItemCount() {
        return childListItem.size();
    }
}
