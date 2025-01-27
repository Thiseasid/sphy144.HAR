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
import java.util.Stack;

public class menuTree {

    private menuItem rootMenu;
    private Stack<menuItem> navigationStack;
    private List<menuItem> currentList;
    private int currentSelection = 0;
    

    //Constractor
    public menuTree(String rootName) {
        this.rootMenu = new menuItem(rootName, null, false);
        this.navigationStack = new Stack<>();
        this.navigationStack.add(rootMenu);
        this.currentList = new ArrayList<>();
    }

    //Getter
    public menuItem getRootMenu() { // Do not use!
        return rootMenu;
    }

    //Navigation Methods
    public int getMenuCurrentDepth() {
        return navigationStack.size();
    }

    public menuItem getCurrentMenu() {
        return navigationStack.peek();
    }
    public String getCurrentMenuName() {
        return navigationStack.peek().getMenuName();
    }
    
    public String getCurrentSelectionName(){
        if (!navigationStack.peek().isSelectable()){
            return navigationStack.peek().getMenuChildrenNames().get(currentSelection);
        }else{
            return navigationStack.peek().getMenuName();
        }
    }
    
    public menuItem getCurrentSelection(){
        if (!navigationStack.peek().isSelectable()){
            return navigationStack.peek().getMenuChildren().get(currentSelection);
        }else{
            return null;
        }
    }
    
    public void updateCurrentList(){
        if(!currentList.isEmpty()){
            currentList.clear();
        }
        
        currentList.addAll(navigationStack.peek().getMenuChildren());
        currentSelection = 0;
    }
    

    public void resetNavigation() {
        navigationStack.clear();
        navigationStack.push(rootMenu);
        this.updateCurrentList();
    }

    public void navigateTo(menuItem menu) {
        if (this.getCurrentMenu().getMenuChildren().contains(menu)){
        navigationStack.push(menu);
        this.updateCurrentList();
        }
    }
    
    public void navigateTo(String menu) {
        if(this.getCurrentMenu().getMenuChildrenNames().contains(menu)){
            navigationStack.push(findMenuItemByNameChildren(this.getCurrentMenu(), menu));
            this.updateCurrentList();
        }
    }
    
    public void navigateToSelection(){
        if (!navigationStack.peek().isSelectable()){
            navigationStack.add(navigationStack.peek().getMenuChildren().get(currentSelection));
            this.updateCurrentList();
        }
    }

    public void navigateBack() {
        if (navigationStack.size() > 1) { //TEST TEST
            navigationStack.pop();
            this.updateCurrentList();
        }
    }
    
    public void navigateNext(){
        if (currentSelection >= navigationStack.peek().getMenuChildrenNames().size()-1){
            currentSelection = 0;
        }else{
            currentSelection += 1;
        }
    }

    // Menu Methods
    public menuItem findMenuItemByNameDepth(menuItem root, String target) {
        if (root == null) {
            return null;
        }
        if (root.getMenuName().equals(target)) {
            return root;
        }

        for (menuItem child : root.getMenuChildren()) {
            menuItem found = findMenuItemByNameDepth(child, target);
            if (found != null && found.getMenuName().equals(target)) {
                return found;
            }
        }
        return null;
    }
    
    public menuItem findMenuItemByNameChildren(menuItem root, String target) {
        if (root.getMenuChildren().isEmpty()){
            return null;
        }
        for (menuItem child : root.getMenuChildren()) {
            if (child.getMenuName().equals(target)) {
                return child;
            }
        }
        return null;
    }


    public void addMenuItem(menuItem parent, menuItem child) {
        parent.addMenuChild(child);
    }

    public void addMenuItem(String parentName, menuItem child) { //Not really needed
        menuItem parent = findMenuItemByNameDepth(rootMenu, parentName);
        if (parent != null) {
            parent.addMenuChild(child);
        }
    }

}
