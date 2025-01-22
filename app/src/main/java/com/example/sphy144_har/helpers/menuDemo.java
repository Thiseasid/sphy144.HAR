/*
 * Η εφαρομγή αυτή αναπτύχθηκε στο πλαίσιο της εκπαίδευσης 
 * στην ΣΠΗΥ στο Τμήμα Αναλυτών - Προγραμματιστών 144 ΕΣ
 * 
 * Created on: 19 Ιαν 2025
 * Written by SIDIROPOULOS THISEAS
 */
package com.example.sphy144_har.helpers;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Thiseas <theseusid@gmail.com>
 */
public class menuDemo {

    public static void main(String[] args) {

        menuTree tree = menuStdCreate.createMenuTree("FHOP");

        Scanner keyboard = new Scanner(System.in);
        int inp = 1;
        while (inp != 0) {
            System.out.println("\n0 Exit\n1 Current Selection\n2 Next\n3 Back\n4 Enter");
            inp = keyboard.nextInt();
            switch (inp) {
                case 0:
                    break;
                case 1:
                    System.out.println(tree.getCurrentSelectionName());
                    break;
                case 2:
                    tree.navigateNext();
                    System.out.println(tree.getCurrentSelectionName());
                    break;
                case 3:
                    tree.navigateBack();
                    System.out.println(tree.getCurrentSelectionName());
                    break;
                case 4:
                    tree.navigateToSelection();
                    System.out.println(tree.getCurrentSelectionName());
                    break;

            }

        }
    }
}
