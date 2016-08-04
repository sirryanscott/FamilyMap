package com.example.sirryanscott.familymap.Person;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by sirryanscott on 8/3/16.
 */
public class Parent implements ParentListItem {
    private String title;
    private List<Child> children;

    public Parent(List<Child> children){
        this.children = children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<?> getChildItemList() {
        return children;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
