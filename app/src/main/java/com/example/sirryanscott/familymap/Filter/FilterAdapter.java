package com.example.sirryanscott.familymap.Filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sirryanscott.familymap.R;

import java.util.List;

/**
 * Created by sirryanscott on 8/8/16.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterItemViewHolder> {
    private LayoutInflater mInflater;
    private List<String> uniqueEvents;

    public FilterAdapter(List<String> uniqueEvents) {
        this.uniqueEvents = uniqueEvents;
    }

    @Override
    public FilterItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.filter_child_layout, parent, false);
        return new FilterItemViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(FilterItemViewHolder holder, int position) {
        String eventName = uniqueEvents.get(position);

        holder.eventName.setText(eventName);
    }

    @Override
    public int getItemCount() {
        return uniqueEvents.size();
    }
}
