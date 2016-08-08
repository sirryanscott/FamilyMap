package com.example.sirryanscott.familymap.Filter;

/**
 * Created by sirryanscott on 8/8/16.
 */
public class FilterListItem {
    private String eventName;

    public FilterListItem(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
