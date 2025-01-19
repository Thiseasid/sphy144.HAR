package com.example.sphy144_har.helpers;

import java.util.Stack;

public class menuTree {

    private menuItem rootMenu;
    private Stack<menuItem> navigationStack;

    //Constractor
    public menuTree() {
        this.rootMenu = new menuItem("/", null, false);
        this.navigationStack = new Stack<>();
        navigationStack.add(rootMenu);
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

    public void resetNavigation() {
        navigationStack.clear();
        navigationStack.push(rootMenu);
    }

    public void navigateTo(menuItem menu) {
        if (this.getCurrentMenu().getMenuChildren().contains(menu)){
        navigationStack.push(menu);
        }
    }
    
    public void navigateTo(String menu) {
        if(this.getCurrentMenu().getMenuChildrenNames().contains(menu)){
            navigationStack.push(findMenuItemByNameChildren(this.getCurrentMenu(), menu));
        }
        
    }

    public void navigateBack() {
        if (navigationStack.size() > 1) { //TEST TEST
            navigationStack.pop();
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
