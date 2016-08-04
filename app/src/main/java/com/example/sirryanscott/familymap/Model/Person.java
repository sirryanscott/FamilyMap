package com.example.sirryanscott.familymap.Model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.sirryanscott.familymap.Person.Parent;
import com.joanzapata.android.iconify.IconDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirryanscott on 7/28/16.
 */
public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private IconDrawable icon;
    private String spouseId;
    private String fatherId;
    private String motherId;
    private String descendant;
    private List<Event> events;
    private Person spouse;
    private Person Father;
    private Person Mother;
    private List<Person> children;

    public Person(){
        events = new ArrayList<>();
        children = new ArrayList<>();
    }

    public IconDrawable getIcon() {
        return icon;
    }

    public void setIcon(IconDrawable icon) {
        this.icon = icon;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFullName(){
        fullName = firstName + " " + lastName;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Person getFather() {
        return Father;
    }

    public void setFather(Person father) {
        Father = father;
    }

    public Person getMother() {
        return Mother;
    }

    public void setMother(Person mother) {
        Mother = mother;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(String spouseId) {
        this.spouseId = spouseId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }
}
