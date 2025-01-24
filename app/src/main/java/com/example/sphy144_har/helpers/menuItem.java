/*
 * Η εφαρομγή αυτή αναπτύχθηκε στο πλαίσιο της εκπαίδευσης 
 * στην ΣΠΗΥ στο Τμήμα Αναλυτών - Προγραμματιστών 144 ΕΣ
 * 
 * Created on: 19 Ιαν 2025
 * Written by SIDIROPOULOS THISEAS
 */
package com.example.sphy144_har.helpers;

/**
 *
 * @author Thiseas <theseusid@gmail.com>
 */

import java.util.ArrayList;
import java.util.List;

public class menuItem {

    private String menuName;
    private menuItem menuParent = null;
    private boolean menuIsSelectable = false;
    private boolean menuActiveates = false;
    private List<menuItem> menuChildren = new ArrayList<>();


    //Constractors
    public menuItem(String menuName){
        this.menuName = menuName;
    }
    public menuItem(String menuName, menuItem menuParent){
        this.menuParent = menuParent;
        this.menuName = menuName;
    }
    public menuItem(String menuName, boolean isSelectable){
        this.menuIsSelectable = isSelectable;
        this.menuName = menuName;
    }
    public menuItem(String menuName, menuItem menuParent, boolean isSelectable){
        this.menuParent = menuParent;
        this.menuName = menuName;
        this.menuIsSelectable = isSelectable;
    }

    //Getters
    public String getMenuName() {
        return menuName;
    }

    public String getMenuParentName() {
        return menuParent.getMenuName();
    }
    
    public menuItem getMenuParent() {
        return menuParent;
    }

    public boolean isSelectable() {
        return menuIsSelectable;
    }

    public boolean isSelected() {
        return menuIsSelected;
    }

    public List<String> getMenuChildrenNames() {
        List<String> result = new ArrayList<>();
        if (menuChildren != null) {
            for (menuItem child : menuChildren) {
                result.add(child.getMenuName());
            }
        }
        return result;
    }
    
    public List<menuItem> getMenuChildren() {
        List<menuItem> result = new ArrayList<>();
        if (menuChildren != null) {
            for (menuItem child : menuChildren) {
                result.add(child);
            }
        }
        return result;
    }

    //Setters
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuParent(menuItem menuParent) {
        this.menuParent = menuParent;
    }

    public void setMenuIsSelectable(boolean menuIsSelectable) {
        this.menuIsSelectable = menuIsSelectable;
    }

    public void setMenuIsSelected(boolean menuIsSelected) {
        this.menuIsSelected = menuIsSelected;
    }

    //equals
    public boolean equals(menuItem other) {
        return this.menuName.equals(other.getMenuName());
    }
    
    //Methods
    public void addMenuChild(menuItem child) {
        this.menuChildren.add(child);
        child.setMenuParent(this);
    }

    public void removeMenuChildren(menuItem child) {
        if (this.menuChildren.contains(child)){
            this.menuChildren.remove(child);
            child.setMenuParent(null);
        }    
    }
    
    

}
