package com.example.sirryanscott.familymap.Person;

import android.graphics.drawable.Drawable;

/**
 * Created by sirryanscott on 8/3/16.
 */
public class Child{
    private String personId;
    private String eventId;
    private String topPanel;
    private String bottomPanel;
    private Drawable image;

    public Child(String topPanel, String bottomPanel, Drawable image){
        this.topPanel = topPanel;
        this.bottomPanel = bottomPanel;
        this.image = image;
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

    public String getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(String topPanel) {
        this.topPanel = topPanel;
    }

    public String getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(String bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
