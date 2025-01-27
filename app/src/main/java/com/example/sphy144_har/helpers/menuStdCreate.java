/*
 * Η εφαρομγή αυτή αναπτύχθηκε στο πλαίσιο της εκπαίδευσης 
 * στην ΣΠΗΥ στο Τμήμα Αναλυτών - Προγραμματιστών 144 ΕΣ
 * 
 * Created on: 21 Ιαν 2025
 * Written by SIDIROPOULOS THISEAS
 */
package com.example.sphy144_har.helpers;

/**
 *
 * @author Thiseas <theseusid@gmail.com>
 */
public class menuStdCreate {
    public static menuTree createMenuTree(String menuName) {
        switch (menuName) {
            case "HLG":
                menuTree treeMenuHLG = new menuTree("HLG");
                treeMenuHLG.addMenuItem("/", new menuItem("CONSULT"));
                treeMenuHLG.addMenuItem("/", new menuItem("INIT"));
                treeMenuHLG.addMenuItem("/", new menuItem("MAN PREP"));
                treeMenuHLG.addMenuItem("MAN PREP", new menuItem("HLG PREP"));
                treeMenuHLG.addMenuItem("INIT", new menuItem("SET KEYS"));
                treeMenuHLG.addMenuItem("INIT", new menuItem("SUBSCR N"));
                treeMenuHLG.addMenuItem("INIT", new menuItem("Z TIME"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("YEAR"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("MONTH"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("DAY"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("HOUR"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("MINUTE"));
                treeMenuHLG.addMenuItem("Z TIME", new menuItem("SEC"));
                return treeMenuHLG;
            case "FHOP":
                menuTree treeMenuFHOP = new menuTree("FHOP");
                treeMenuFHOP.addMenuItem("/", new menuItem("CONSULT"));
                treeMenuFHOP.addMenuItem("/", new menuItem("INIT"));
                treeMenuFHOP.addMenuItem("/", new menuItem("MAN PREP"));
                treeMenuFHOP.addMenuItem("MAN PREP", new menuItem("STATION"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("SET KEYS"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("SUBSCR N"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("TIME"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("YEAR"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("MONTH"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("DAY"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("HOUR"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("MINUTE"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("SEC"));
                treeMenuFHOP.addMenuItem("MAN PREP", new menuItem("PREP"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("MODE"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("FREQ"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("HLC FREQ"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("F RANGES"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("F STEP"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("CRYPTO 1"));
                treeMenuFHOP.addMenuItem("CRYPTO 1", new menuItem("TRANSEC"));
                treeMenuFHOP.addMenuItem("CRYPTO 1", new menuItem("COMSEC"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("CRYPTO 2"));
                treeMenuFHOP.addMenuItem("CRYPTO 2", new menuItem("TRANSEC"));
                treeMenuFHOP.addMenuItem("CRYPTO 2", new menuItem("COMSEC"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("NET N"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("RATE 1"));
                treeMenuFHOP.addMenuItem("PREP", new menuItem("RATE 2"));
                treeMenuFHOP.addMenuItem("INIT", new menuItem("SET KEYS"));
                treeMenuFHOP.addMenuItem("INIT", new menuItem("SUBSCR N"));
                treeMenuFHOP.addMenuItem("INIT", new menuItem("Z TIME"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("YEAR"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("MONTH"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("DAY"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("HOUR"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("MINUTE"));
                treeMenuFHOP.addMenuItem("Z TIME", new menuItem("SEC"));
                treeMenuFHOP.addMenuItem("CONSULT", new menuItem("STATION"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("SET KEYS"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("SUBSCR N"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("TIME"));
                treeMenuFHOP.addMenuItem("STATION", new menuItem("TIME COUP"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("DATE"));
                treeMenuFHOP.addMenuItem("TIME", new menuItem("TIME"));
                treeMenuFHOP.addMenuItem("CONSULT", new menuItem("CONSULT N"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("MODE"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("FREQ"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("HLC FREQ"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("F RANGES"));
                treeMenuFHOP.addMenuItem("FREQ", new menuItem("F STEP"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("CRYPTO 1"));
                treeMenuFHOP.addMenuItem("CRYPTO 1", new menuItem("TRANSEC"));
                treeMenuFHOP.addMenuItem("CRYPTO 1", new menuItem("COMSEC"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("CRYPTO 2"));
                treeMenuFHOP.addMenuItem("CRYPTO 2", new menuItem("TRANSEC"));
                treeMenuFHOP.addMenuItem("CRYPTO 2", new menuItem("COMSEC"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("NET N"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("RATE 1"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("RATE 2"));
                treeMenuFHOP.addMenuItem("CONSULT N", new menuItem("SYNCHRO"));
                treeMenuFHOP.addMenuItem("SYNCHRO", new menuItem("MAINT"));
                treeMenuFHOP.addMenuItem("SYNCHRO", new menuItem("AUTO"));
                treeMenuFHOP.addMenuItem("SYNCHRO", new menuItem("TSCAN"));
                return treeMenuFHOP;
            case "SERV":
                menuTree servTree = new menuTree("SERV");
                servTree.addMenuItem("/", new menuItem("LEVEL"));
                servTree.addMenuItem("LEVEL", new menuItem("SUB", true));
                servTree.addMenuItem("LEVEL", new menuItem("NSC", true));
                servTree.addMenuItem("/", new menuItem("SQUELCH"));
                servTree.addMenuItem("SQUELCH", new menuItem("150 HZ"));
                servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 1", true));
                servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 2", true));
                servTree.addMenuItem("SQUELCH", new menuItem("LEVEL 3", true));
                servTree.addMenuItem("SQUELCH", new menuItem("NO SQUELCH", true));
                servTree.addMenuItem("/", new menuItem("SYNC"));
                servTree.addMenuItem("/", new menuItem("LN TEST"));
                servTree.addMenuItem("/", new menuItem("BEEP"));
                servTree.addMenuItem("BEEP", new menuItem("YES", true));
                servTree.addMenuItem("BEEP", new menuItem("NO", true));
                servTree.addMenuItem("/", new menuItem("HAILING"));
                servTree.addMenuItem("HAILING", new menuItem("HLG YES", true));
                servTree.addMenuItem("HAILING", new menuItem("HLG NO", true));
                servTree.addMenuItem("HAILING", new menuItem("HLC YES", true));
                servTree.addMenuItem("HAILING", new menuItem("HLC NO", true));
                servTree.addMenuItem("/", new menuItem("Voice"));
                servTree.addMenuItem("Voice", new menuItem("Delta 16", true));
                servTree.addMenuItem("Voice", new menuItem("VOC 4800", true));
                servTree.addMenuItem("Voice", new menuItem("VOC 2400", true));
                servTree.addMenuItem("Voice", new menuItem("VOC 800", true));
                servTree.addMenuItem("/", new menuItem("DT TYPE"));
                servTree.addMenuItem("DT TYPE", new menuItem("STD", true));
                servTree.addMenuItem("DT TYPE", new menuItem("ADT", true));
                servTree.addMenuItem("DT TYPE", new menuItem("EXT VOC", true));
                return servTree;
            case "AUTH":
                menuTree authTree = new menuTree("AUTH");
                authTree.addMenuItem("/", new menuItem("AUTH"));
                return authTree;
            case "ALRT":
                menuTree alrtTree = new menuTree("ALRT");
                alrtTree.addMenuItem("/", new menuItem("ALRT"));
                return alrtTree;
            case "MODE":
                menuTree modeTree = new menuTree("MODE");
                modeTree.addMenuItem("/", new menuItem("PROGRAM"));
                modeTree.addMenuItem("PROGRAM", new menuItem("FHOP", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("ORTHO", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("FSC", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("MIX", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("DFF", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("HLC", true));
                modeTree.addMenuItem("PROGRAM", new menuItem("SCANNING", true));
                return modeTree;
            case "IDLE":
                menuTree idleTree = new menuTree("IDLE");
                idleTree.addMenuItem("/", new menuItem("FHOP"));
                return idleTree;
            default:
                return null;
        }
    }

}
