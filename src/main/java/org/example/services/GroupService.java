package org.example.services;

import java.util.*;

public class GroupService {
    private HashSet<String> uniqueStrings = new HashSet<>();
    private final List<String> strings = new ArrayList<>();
    private int maxGroup = 0;
    private List<List<String>> groups = new ArrayList<>();
    private final List<Integer> stringGroup = new ArrayList<>();
    private final List<Map<String, Integer>> columns = new ArrayList<>();

    public void addNewString(String s) {
        if (!s.contains("\"") && !uniqueStrings.contains(s)) {
            uniqueStrings.add(s);
            strings.add(s);
            String[] splitStr = s.split(";");
            Set<Integer> curGroups = new HashSet<>();
            for (int i = 0; i < columns.size() && i < splitStr.length; i++) {
                if (columns.get(i).containsKey(splitStr[i])) {
                    curGroups.add(stringGroup.get(columns.get(i).get(splitStr[i])));
                }
            }
            int groupNumber;
            if (curGroups.size() == 0) {
                groupNumber = maxGroup + 1;
                maxGroup++;
            } else {
                groupNumber = curGroups.stream().mapToInt(Integer::intValue).min().getAsInt();
                for (int i = 0; i < stringGroup.size(); i++) {
                    if (curGroups.contains(stringGroup.get(i))) {
                        stringGroup.set(i, groupNumber);
                    }
                }
            }
            stringGroup.add(groupNumber);
            for (int i = 0; i < splitStr.length; i++) {
                if (i >= columns.size()) {
                    columns.add(new HashMap<>());
                }
                if (!splitStr[i].isEmpty()) {
                    columns.get(i).compute(splitStr[i], (k, v) -> v == null ? strings.size() - 1 : v);
                }
            }
        }
    }

    public void formGroups() {
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < stringGroup.size(); i++) {
            map.compute(stringGroup.get(i), (k, v) -> v == null ? new ArrayList<>() : v);
            map.get(stringGroup.get(i)).add(strings.get(i));
        }
        groups = map.values().stream().sorted((list1, list2) -> list2.size() - list1.size()).toList();
    }

    public void printGroups() {
        for (int i = 0; i < groups.size(); i++) {
            System.out.printf("Группа %d: \n", i + 1);
            for (String s : groups.get(i)) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    public List<List<String>> getGroups() {
        return groups;
    }
}
