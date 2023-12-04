package l8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArrayListProgram {

    private static final int STATIC_SIZE = 5; // статический размер коллекции

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавление и удаление объектов");
            System.out.println("2. Поиск одинаковых элементов с подсчетом совпадений");
            System.out.println("3. Выгрузка в XML-файл");
            System.out.println("4. Реверс всех строк");
            System.out.println("5. Статистика по всем символам");
            System.out.println("6. Поиск подстроки");
            System.out.println("7. Расширить функциональность ArrayList");
            System.out.println("8. Посчитать длины строк");
            System.out.println("9. Добавление в динамическую коллекцию");

            System.out.println("Выберите действие (введите номер пункта): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToArrayList(arrayList, scanner);
                    removeFromArrayList(arrayList, scanner);
                    break;
                case 2:
                    findDuplicates(arrayList);
                    break;
                case 3:
                    saveToXmlFile(arrayList);
                    break;
                case 4:
                    reverseStrings(arrayList);
                    break;
                case 5:
                    countCharacters(arrayList);
                    break;
                case 6:
                    searchSubstring(arrayList, scanner);
                    break;
                case 7:
                    compareInnerObjects(arrayList, scanner);
                    break;
                case 8:
                    countStringLengths(arrayList);
                    break;
                case 9:
                    addToDynamicArrayList(arrayList, scanner);
                    break;
                default:
                    System.out.println("Недопустимый выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void addToArrayList(ArrayList<String> arrayList, Scanner scanner) {
        System.out.println("Введите строку для добавления: ");
        String input = scanner.next();
        arrayList.add(input);
        System.out.println("Строка добавлена в коллекцию.");
    }

    private static void removeFromArrayList(ArrayList<String> arrayList, Scanner scanner) {
        System.out.println("Введите строку для удаления: ");
        String input = scanner.next();
        if (arrayList.remove(input)) {
            System.out.println("Строка удалена из коллекции.");
        } else {
            System.out.println("Строка не найдена в коллекции.");
        }
    }

    private static void findDuplicates(ArrayList<String> arrayList) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String str : arrayList) {
            countMap.put(str, countMap.getOrDefault(str, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                String str = entry.getKey();
                int count = entry.getValue();
                System.out.println("Строка \"" + str + "\" встречается " + count + " раз(а).");
            }
        }
    }

    private static void saveToXmlFile(ArrayList<String> arrayList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.xml"))) {
            writer.write("<strings>");
            for (String str : arrayList) {
                writer.write("<string>" + str + "</string>");
            }
            writer.write("</strings>");
            System.out.println("Коллекция успешно сохранена в XML-файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл.");
        }
    }

    private static void reverseStrings(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String reversed = new StringBuilder(arrayList.get(i)).reverse().toString();
            arrayList.set(i, reversed);
        }
        System.out.println("Все строки в коллекции были реверсированы.");
        System.out.println("Новые значения: " + arrayList);
    }

    private static void countCharacters(ArrayList<String> arrayList) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (String str : arrayList) {
            for (char c : str.toCharArray()) {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
        }

        System.out.println("Статистика по символам:");
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            System.out.println("'" + c + "': " + count);
        }
    }

    private static void searchSubstring(ArrayList<String> arrayList, Scanner scanner) {
        System.out.println("Введите подстроку для поиска: ");
        String substring = scanner.next();

        List<String> matchingStrings = new ArrayList<>();
        for (String str : arrayList) {
            if (str.contains(substring)) {
                matchingStrings.add(str);
            }
        }

        System.out.println("Строки, содержащие подстроку \"" + substring + "\":");
        System.out.println(matchingStrings);
    }

    private static void compareInnerObjects(ArrayList<String> arrayList, Scanner scanner) {
        System.out.println("Введите индексы двух строк для сравнения: ");
        int firstIndex = scanner.nextInt();
        int secondIndex = scanner.nextInt();

        if (firstIndex >= 0 && firstIndex < arrayList.size() && secondIndex >= 0 && secondIndex < arrayList.size()) {
            String firstString = arrayList.get(firstIndex);
            String secondString = arrayList.get(secondIndex);

            int result = compareStringsIgnoreDuplicates(firstString, secondString);
            System.out.println("Результат сравнения: " + result);
        } else {
            System.out.println("Некорректные индексы строк.");
        }
    }

    private static int compareStringsIgnoreDuplicates(String str1, String str2) {
        Set<Character> set1 = new HashSet<>();
        for (char c : str1.toCharArray()) {
            set1.add(c);
        }

        int count = 0;
        for (char c : str2.toCharArray()) {
            if (!set1.contains(c)) {
                count++;
            }
        }

        return count;
    }

    private static void countStringLengths(ArrayList<String> arrayList) {
        List<Integer> lengths = new ArrayList<>();
        for (String str : arrayList) {
            lengths.add(str.length());
        }
        Collections.sort(lengths);

        System.out.println("Длины строк в коллекции (в порядке возрастания):");
        for (int length : lengths) {
            System.out.println(length);
        }
    }

    private static void addToDynamicArrayList(ArrayList<String> arrayList, Scanner scanner) {
        if (arrayList.size() >= STATIC_SIZE) {
            arrayList.remove(0);
        }

        System.out.println("Введите строку для добавления: ");
        String input = scanner.next();
        arrayList.add(input);
        System.out.println("Строка добавлена в коллекцию.");
    }
}