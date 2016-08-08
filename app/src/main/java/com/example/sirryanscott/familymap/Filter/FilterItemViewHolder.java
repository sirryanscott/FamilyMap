package com.example.sirryanscott.familymap.Filter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sirryanscott.familymap.R;

/**
 * Created by sirryanscott on 8/8/16.
 */
public class FilterItemViewHolder extends RecyclerView.ViewHolder {
    public TextView eventName;

    public FilterItemViewHolder(View itemView) {
        super(itemView);

        eventName = (TextView) itemView.findViewById(R.id.filterChild);
    }
}
