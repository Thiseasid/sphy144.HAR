package com.example.har.helpers;


public class menuDemo {
    public static void main(String[] args) {
        
        menuItem a = new menuItem("a");
        menuItem b = new menuItem("b");
        menuItem c = new menuItem("c");
        menuItem d = new menuItem("d");
        menuTree tree = new menuTree();
        tree.addMenuItem("/", a);
        tree.addMenuItem("/", b);
        tree.addMenuItem(a, c);
        tree.addMenuItem(c, d);
        System.out.println(tree.getCurrentMenu().getMenuName());
        for (menuItem child : tree.getCurrentMenu().getMenuChildren()){
            System.out.print(child.getMenuName()+", ");
        }
        System.out.println("");
        tree.navigateTo("c");
        tree.navigateTo("d");
        System.out.println(tree.getCurrentMenu().getMenuName());
        
        
        
        
        
        
        
    }
}

