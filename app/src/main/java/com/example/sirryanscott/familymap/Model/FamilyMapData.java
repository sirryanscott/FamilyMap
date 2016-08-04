package com.example.sirryanscott.familymap.Model;

import android.graphics.drawable.Drawable;
import android.util.ArraySet;

import com.example.sirryanscott.familymap.Login.LoginData;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by sirryanscott on 7/28/16.
 */
public class FamilyMapData {

    private TreeMap<String, Person> personMap;
    private TreeMap<String, Event> eventMap;
    private TreeMap<Marker, Event> markerEventTreeMap;
    private Set<String> uniqueEvents;

    private static FamilyMapData ourInstance = new FamilyMapData();

    public static FamilyMapData getInstance() {
        return ourInstance;
    }

    private FamilyMapData() {
        personMap = new TreeMap<>();
        eventMap = new TreeMap<>();
        markerEventTreeMap = new TreeMap<>();
        uniqueEvents = new HashSet<>();
    }

    public Set<String> getUniqueEvents() {
        return uniqueEvents;
    }

    public void setUniqueEvents(Set<String> uniqueEvents) {
        this.uniqueEvents = uniqueEvents;
    }

    public TreeMap<Marker, Event> getMarkerEventTreeMap() {
        return markerEventTreeMap;
    }

    public void setMarkerEventTreeMap(TreeMap<Marker, Event> markerEventTreeMap) {
        this.markerEventTreeMap = markerEventTreeMap;
    }

    public TreeMap<String, Person> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(TreeMap<String, Person> personMap) {
        this.personMap = personMap;
    }

    public TreeMap<String, Event> getEventMap() {
        return eventMap;
    }

    public void setEventMap(TreeMap<String, Event> eventMap) {
        this.eventMap = eventMap;
    }

    public boolean buildFamilyMapPeopleData(JSONArray jsonArray){
        if(jsonArray == null){
            return false;
        }
        else {
            for (int i = 0; i < jsonArray.length(); i++) {
                if(i == jsonArray.length()-1){
                    //set user data
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if(jsonObject.has("descendant")){
                            LoginData.getInstance().getCurrentUser().setDescendant(jsonObject.getString("descendant"));
                        }
                        if(jsonObject.has("firstName")){
                            LoginData.getInstance().getCurrentUser().setFirstName(jsonObject.getString("firstName"));
                        }
                        if(jsonObject.has("lastName")){
                            LoginData.getInstance().getCurrentUser().setLastName(jsonObject.getString("lastName"));
                        }
                        if(jsonObject.has("gender")){
                            LoginData.getInstance().getCurrentUser().setGender("gender");
                        }
                        if(jsonObject.has("spouse")){
                            LoginData.getInstance().getCurrentUser().setSpouseId("spouse");
                        }
                        if(jsonObject.has("father")){
                            LoginData.getInstance().getCurrentUser().setFatherId("father");
                        }
                        if(jsonObject.has("mother")){
                            LoginData.getInstance().getCurrentUser().setMotherId("mother");
                        }


                        LoginData.getInstance().getCurrentUser().setFullName();
                        personMap.put(LoginData.getInstance().getCurrentUser().getPersonId(), LoginData.getInstance().getCurrentUser());


                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                else {
                    //set personlist data
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Person person = new Person();

                        if(jsonObject.has("descendant")){
                            person.setDescendant(jsonObject.getString("descendant"));
                        }
                        if(jsonObject.has("personID")){
                            person.setPersonId(jsonObject.getString("personID"));
                        }
                        if(jsonObject.has("firstName")){
                            person.setFirstName(jsonObject.getString("firstName"));
                        }
                        if(jsonObject.has("lastName")){
                            person.setLastName(jsonObject.getString("lastName"));
                        }
                        if(jsonObject.has("gender")){
                            person.setGender(jsonObject.getString("gender"));
                        }
                        if(jsonObject.has("spouse")){
                            person.setSpouseId(jsonObject.getString("spouse"));
                        }
                        if(jsonObject.has("father")){
                            person.setFatherId(jsonObject.getString("father"));
                        }
                        if(jsonObject.has("mother")){
                            person.setMotherId(jsonObject.getString("mother"));
                        }


                        person.setFullName();
                        personMap.put(person.getPersonId(), person);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean buildFamilyMapEventData(JSONArray jsonArray){
        if(jsonArray == null){
            return false;
        }
        else {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Event event = new Event();

                    if(jsonObject.has("eventID")){
                        event.setEventId(jsonObject.getString("eventID"));
                    }
                    if(jsonObject.has("personID")){
                        event.setPersonId(jsonObject.getString("personID"));
                    }
                    if(jsonObject.has("latitude")){
                        String latitude = jsonObject.getString("latitude");
                        event.setLatitudeStr(latitude);
                        event.setLatitude(Double.parseDouble(latitude));
                    }
                    if(jsonObject.has("longitude")){
                        String longitude = jsonObject.getString("longitude");
                        event.setLongitudeStr(longitude);
                        event.setLongitude(Double.parseDouble(longitude));

                        event.setPosition(new LatLng(event.getLatitude(), event.getLongitude()));
                    }
                    if(jsonObject.has("country")){
                        event.setCountry(jsonObject.getString("country"));
                    }
                    if(jsonObject.has("city")){
                        event.setCity(jsonObject.getString("city"));
                    }
                    if(jsonObject.has("description")){
                        event.setDescription(jsonObject.getString("description"));
                    }
                    if(jsonObject.has("year")){
                        event.setYear(jsonObject.getString("year"));
                    }
                    if(jsonObject.has("descendant")){
                        event.setDescendantId(jsonObject.getString("descendant"));
                    }


                    event.createFullDescriptionString();

                    eventMap.put(event.getEventId(), event);

                    //Todo: a set of unique events is needed for color of markers and for filter
                    uniqueEvents.add(event.getDescription().toUpperCase());

                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    }

    public void linkEventsToPerson(){
        for(Map.Entry<String, Person> personIterator : getPersonMap().entrySet()){
            String personId = personIterator.getKey();
            Person person = personIterator.getValue();

            for(Map.Entry<String, Event> eventIterator : FamilyMapData.getInstance().getEventMap().entrySet()){
                Event event = eventIterator.getValue();

                if (event.getPersonId().equals(personId)){
                    person.getEvents().add(event);
                }
            }
        }
    }

    public void linkFamilyToPerson(){
        for(Map.Entry<String, Person> personIterator1 : getPersonMap().entrySet()){
            Person person1 = personIterator1.getValue();

            for(Map.Entry<String, Person> personIterator2 : getPersonMap().entrySet()){
                String personId2 = personIterator2.getKey();
                Person person2 = personIterator2.getValue();

                if(person1.getSpouseId() != null && person1.getSpouseId().equals(personId2)){
                    person1.setSpouse(person2);
                }
                if(person1.getFatherId() != null && person1.getFatherId().equals(personId2)){
                    person1.setFather(person2);
                    person2.getChildren().add(person1);
                }
                if(person1.getMotherId() != null && person1.getMotherId().equals(personId2)){
                    person1.setMother(person2);
                    person2.getChildren().add(person1);
                }

            }
        }
    }
}
