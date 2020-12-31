package com.testdome;

import java.util.*;

public class MergeNames {

    public static String[] uniqueNamesWithList(String[] names1, String[] names2) {
        ArrayList<String> uniqueNames = new ArrayList<>();
        for (String name : names1) {
            if (!uniqueNames.contains(name)) {
                uniqueNames.add(name);
            }
        }
        for (String name : names2) {
            if (!uniqueNames.contains(name)) {
                uniqueNames.add(name);
            }
        }
        return uniqueNames.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNamesWithSet(names1, names2)));
        // should print Ava, Emma, Olivia, Sophia
    }

    private static String[] uniqueNamesWithSet(String[] names1, String[] names2) {
        Set<String> uniqueNames = new HashSet<>();
        Collections.addAll(uniqueNames, names1);
        Collections.addAll(uniqueNames, names2);
        return uniqueNames.toArray(new String[0]);
    }
}