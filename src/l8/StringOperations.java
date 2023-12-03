package l8;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StringOperations {    private ArrayList<String> strings;
    private int maxSize;

    public StringOperations(int maxSize) {
        this.strings = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void addString(String str) {
        if (strings.size() >= maxSize) {
            strings.remove(0);
        }
        strings.add(str);
    }

    public void removeString(String str) {
        strings.remove(str);
    }

    public int countOccurrences(String str) {
        int count = 0;
        for (String s : strings) {
            if (s.equals(str)) {
                count++;
            }
        }
        return count;
    }

    public void exportToXml(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println("<strings>");
            for (String str : strings) {
                writer.println("\t<string>" + str + "</string>");
            }
            writer.println("</strings>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reverseStrings() {
        Collections.reverse(strings);
    }

    public Map<Character, Integer> getCharacterStatistics() {
        Map<Character, Integer> statistics = new HashMap<>();
        for (String str : strings) {
            for (char c : str.toCharArray()) {
                if (statistics.containsKey(c)) {
                    statistics.put(c, statistics.get(c) + 1);
                } else {
                    statistics.put(c, 1);
                }
            }
        }
        return statistics;
    }

    public ArrayList<String> searchSubstring(String substring) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : strings) {
            if (str.contains(substring)) {
                result.add(str);
            }
        }
        return result;
    }

    public int compareInnerObjects(int firstIndex, int secondIndex) {
        return strings.get(firstIndex).compareTo(strings.get(secondIndex));
    }

    public ArrayList<Integer> getLengthStatistics() {
        ArrayList<Integer> lengths = new ArrayList<>();
        for (String str : strings) {
            lengths.add(str.length());
        }
        Collections.sort(lengths);
        return lengths;
    }

    public static void main(String[] args) {
        StringOperations stringOperations = new StringOperations(5);
        stringOperations.addString("Hello");
        stringOperations.addString("World");
        stringOperations.addString("Java");
        stringOperations.addString("Programming");
        stringOperations.addString("Language");

        System.out.println("Count of 'Hello': " + stringOperations.countOccurrences("Hello"));

        stringOperations.exportToXml("strings.xml");

        System.out.println("Original strings: " + stringOperations.strings);
        stringOperations.reverseStrings();
        System.out.println("Reversed strings: " + stringOperations.strings);

        Map<Character, Integer> characterStatistics = stringOperations.getCharacterStatistics();
        System.out.println("Character statistics: " + characterStatistics);

        ArrayList<String> substrings = stringOperations.searchSubstring("Pro");
        System.out.println("Strings containing 'Pro': " + substrings);

        int compareResult = stringOperations.compareInnerObjects(0, 1);
        System.out.println("Comparison result: " + compareResult);

        ArrayList<Integer> lengthStatistics = stringOperations.getLengthStatistics();
        System.out.println("Length statistics: " + lengthStatistics);
    }
}
