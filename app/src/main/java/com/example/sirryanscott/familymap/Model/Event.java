package com.example.sirryanscott.familymap.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by sirryanscott on 7/28/16.
 */
public class Event implements Comparable<Event> {
    private String eventId;
    private String personId;
    private String latitudeStr;
    private String longitudeStr;
    private double latitude;
    private double longitude;
    private LatLng position;
    private Marker marker;
    private float color;
    private String country;
    private String city;
    private String description;
    private String year;
    private String descendantId;
    private String fullDescription;

    public float getColor() {
        return color;
    }

    public void setColor(float color) {
        this.color = color;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLatitudeStr() {
        return latitudeStr;
    }

    public void setLatitudeStr(String latitude) {
        this.latitudeStr = latitude;
    }

    public String getLongitudeStr() {
        return longitudeStr;
    }

    public void setLongitudeStr(String longitude) {
        this.longitudeStr = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void createFullDescriptionString(){
        StringBuilder descriptionString = new StringBuilder();
        if(description != null){
            descriptionString.append(description + ": ");
        }
        if(city != null){
            descriptionString.append(city + ", ");
        }
        if(country != null){
            descriptionString.append(country + " ");
        }
        if(year != null){
            descriptionString.append("(" + year + ")");
        }

        setFullDescription(descriptionString.toString());
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescendantId() {
        return descendantId;
    }

    public void setDescendantId(String descendantId) {
        this.descendantId = descendantId;
    }

    @Override
    public int compareTo(Event that) {
        int result;
        if (that.getDescription().equals("birth")) {
            result = 1;
        } else if (that.getDescription().equals("death")) {
            result = -1;
        } else if (Integer.parseInt(that.getYear()) < Integer.parseInt(this.getYear())) {
            result = 1;
        } else if (Integer.parseInt(that.getYear()) > Integer.parseInt(this.getYear())) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }
}
