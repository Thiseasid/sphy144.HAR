/*
 * Η εφαρομγή αυτή αναπτύχθηκε στο πλαίσιο της εκπαίδευσης
 * στην ΣΠΗΥ στο Τμήμα Αναλυτών - Προγραμματιστών 144 ΕΣ
 *
 * Created on: 19 Ιαν 2025
 * Written by SIDIROPOULOS THISEAS
 */
package com.example.sphy144_har.helpers;

import java.util.Scanner;

/**
 *
 * @author Thiseas <theseusid@gmail.com>
 */
public class menuDemo {
    public static void main(String[] args) {

        menuTree treeMenu = new menuTree();
        treeMenu.addMenuItem("/", new menuItem("CONSULT"));
        treeMenu.addMenuItem("/", new menuItem("INIT"));
        treeMenu.addMenuItem("/", new menuItem("MAN PREP"));
        treeMenu.addMenuItem("MAN PREP", new menuItem("HLG PREP"));
        treeMenu.addMenuItem("INIT", new menuItem("SET KEYS"));
        treeMenu.addMenuItem("INIT", new menuItem("SUBSCR N"));
        treeMenu.addMenuItem("INIT", new menuItem("Z TIME"));
        treeMenu.addMenuItem("Z TIME", new menuItem("YEAR"));
        treeMenu.addMenuItem("Z TIME", new menuItem("MONTH"));
        treeMenu.addMenuItem("Z TIME", new menuItem("DAY"));
        treeMenu.addMenuItem("Z TIME", new menuItem("HOUR"));
        treeMenu.addMenuItem("Z TIME", new menuItem("MINUTE"));
        treeMenu.addMenuItem("Z TIME", new menuItem("SEC"));

        menuTree servTree = new menuTree();
        servTree.addMenuItem("/", new menuItem("LEVEL"));
        servTree.addMenuItem("LEVEL", new menuItem("SUB"));
        servTree.addMenuItem("LEVEL", new menuItem("NSC"));
        servTree.addMenuItem("/", new menuItem("SQUELCH"));
        servTree.addMenuItem("SQUELCH", new menuItem("150 HZ"));
        servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 1"));
        servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 2"));
        servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 3"));
        servTree.addMenuItem("SQUELCH", new menuItem("NO SQUELCH"));
        servTree.addMenuItem("/", new menuItem("SYNC"));
        servTree.addMenuItem("/", new menuItem("LN TEST"));
        servTree.addMenuItem("/", new menuItem("BEEP"));
        servTree.addMenuItem("BEEP", new menuItem("YES"));
        servTree.addMenuItem("BEEP", new menuItem("NO"));
        servTree.addMenuItem("/", new menuItem("HAILING"));
        servTree.addMenuItem("HAILING", new menuItem("HLG YES"));
        servTree.addMenuItem("HAILING", new menuItem("HLG NO"));
        servTree.addMenuItem("HAILING", new menuItem("HLC YES"));
        servTree.addMenuItem("HAILING", new menuItem("HLC NO"));
        servTree.addMenuItem("/", new menuItem("Voice"));
        servTree.addMenuItem("Voice", new menuItem("Delta 16"));
        servTree.addMenuItem("Voice", new menuItem("VOC 4800"));
        servTree.addMenuItem("Voice", new menuItem("VOC 2400"));
        servTree.addMenuItem("Voice", new menuItem("VOC 800"));
        servTree.addMenuItem("/", new menuItem("DT TYPE"));
        servTree.addMenuItem("DT TYPE", new menuItem("STD"));
        servTree.addMenuItem("DT TYPE", new menuItem("ADT"));
        servTree.addMenuItem("DT TYPE", new menuItem("EXT VOC"));

        menuTree authTree = new menuTree();
        authTree.addMenuItem("/", new menuItem("AUTH"));

        menuTree alrtTree = new menuTree();
        alrtTree.addMenuItem("/", new menuItem("ALRT"));

        menuTree modeTree = new menuTree();
        modeTree.addMenuItem("/", new menuItem("PROGRAM"));
        modeTree.addMenuItem("PROGRAM", new menuItem("FHOP"));
        modeTree.addMenuItem("PROGRAM", new menuItem("ORTHO"));
        modeTree.addMenuItem("PROGRAM", new menuItem("FSC"));
        modeTree.addMenuItem("PROGRAM", new menuItem("MIX"));
        modeTree.addMenuItem("PROGRAM", new menuItem("DFF"));
        modeTree.addMenuItem("PROGRAM", new menuItem("HLC"));
        modeTree.addMenuItem("PROGRAM", new menuItem("SCANNING"));

        menuTree idleTree = new menuTree();
        idleTree.addMenuItem("/", new menuItem("FHOP"));

        Scanner keyboard = new Scanner(System.in);
        int inp = 1;
        while (inp != 0){
            System.out.println("\n0 Exit\n1 Current Selection\n2 Next\n3 Back\n4 Enter");
            inp = keyboard.nextInt();
            switch (inp){
                case 0:
                    break;
                case 1:
                    System.out.println(treeMenu.getCurrentSelectionName());
                    break;
                case 2:
                    treeMenu.navigateNext();
                    System.out.println(treeMenu.getCurrentSelectionName());
                    break;
                case 3:
                    treeMenu.navigateBack();
                    System.out.println(treeMenu.getCurrentSelectionName());
                    break;
                case 4:
                    treeMenu.navigateToSelection();
                    System.out.println(treeMenu.getCurrentSelectionName());
                    break;
            }
        }







    }
}
