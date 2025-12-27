// Main.java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №4 ===");
        System.out.println("=== Вариант 5 ===");

        // ========== Задание 1 ==========
        System.out.println("\n--- Задание 1 ---");

        // 1.1 Обобщенная коробка
        System.out.println("\n1.1 Обобщенная коробка:");
        Box<Integer> intBox = new Box<>();
        System.out.println("Создана коробка: " + intBox);

        intBox.put(3);
        System.out.println("Положили число 3: " + intBox);

        Integer value = intBox.take();
        System.out.println("Извлекли значение: " + value);
        System.out.println("Коробка после извлечения: " + intBox);

        // Проверка исключения
        try {
            intBox.put(5);
            intBox.put(10); // Должно вызвать исключение
        } catch (IllegalStateException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        // 1.2 Без null
        System.out.println("\n1.2 Без null:");

        Storage<Integer> nullIntStorage = new Storage<>(null, 0);
        System.out.println("Хранилище null (альтернатива 0): " + nullIntStorage.get());

        Storage<Integer> intStorage = new Storage<>(99, -1);
        System.out.println("Хранилище 99 (альтернатива -1): " + intStorage.get());

        Storage<String> nullStringStorage = new Storage<>(null, "default");
        System.out.println("Хранилище null (альтернатива 'default'): " + nullStringStorage.get());

        Storage<String> stringStorage = new Storage<>("hello", "hello world");
        System.out.println("Хранилище 'hello' (альтернатива 'hello world'): " + stringStorage.get());

        // ========== Задание 2 ==========
        System.out.println("\n--- Задание 2 ---");

        // 2.3 Начало отсчета
        System.out.println("\n2.3 Начало отсчета:");

        Box<Point3D> pointBox = new Box<>();
        System.out.println("Создана пустая коробка для точек: " + pointBox);

        Task2Methods.putPointToBox(pointBox);
        System.out.println("Положили точку в коробку: " + pointBox);

        Point3D point = pointBox.take();
        System.out.println("Извлекли точку: " + point);

        // ========== Задание 3 ==========
        System.out.println("\n--- Задание 3 ---");

        // 3.1 Функция
        System.out.println("\n3.1 Функция (map):");

        // 1. Строки -> их длины
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        List<Integer> lengths = Task3Methods.map(strings, s -> s.length());
        System.out.println("Строки: " + strings);
        System.out.println("Длины строк: " + lengths);

        // 2. Числа -> абсолютные значения
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        List<Integer> absNumbers = Task3Methods.map(numbers, n -> Math.abs(n));
        System.out.println("Числа: " + numbers);
        System.out.println("Абсолютные значения: " + absNumbers);

        // 3. Массивы -> максимумы
        List<int[]> arrays = Arrays.asList(
                new int[]{1, 2, 3},
                new int[]{-1, 5, 0},
                new int[]{10, -2, 8}
        );
        List<Integer> maxValues = Task3Methods.map(arrays, arr -> {
            int max = arr[0];
            for (int val : arr) {
                if (val > max) max = val;
            }
            return max;
        });
        System.out.println("Массивы: " + Arrays.deepToString(arrays.toArray()));
        System.out.println("Максимумы: " + maxValues);

        // 3.2 Фильтр
        System.out.println("\n3.2 Фильтр:");

        // 1. Строки длиной >= 3
        List<String> filteredStrings = Task3Methods.filter(strings, s -> s.length() >= 3);
        System.out.println("Строки: " + strings);
        System.out.println("Строки длиной >= 3: " + filteredStrings);

        // 2. Положительные числа
        List<Integer> positiveNumbers = Task3Methods.filter(numbers, n -> n > 0);
        System.out.println("Числа: " + numbers);
        System.out.println("Положительные числа: " + positiveNumbers);

        // 3. Массивы без положительных элементов
        List<int[]> arraysWithoutPositive = Task3Methods.filter(arrays, arr -> {
            for (int val : arr) {
                if (val > 0) return false;
            }
            return true;
        });
        System.out.println("Массивы: " + Arrays.deepToString(arrays.toArray()));
        System.out.println("Массивы без положительных: " + arraysWithoutPositive.size() + " шт.");

        // 3.3 Сокращение
        System.out.println("\n3.3 Сокращение (reduce):");

        // 1. Конкатенация строк
        String concatenated = Task3Methods.reduce(strings, (a, b) -> a + b);
        System.out.println("Строки: " + strings);
        System.out.println("Объединенная строка: " + concatenated);

        // 2. Сумма чисел
        Integer sum = Task3Methods.reduce(numbers, (a, b) -> a + b);
        System.out.println("Числа: " + numbers);
        System.out.println("Сумма: " + sum);

        // 3. Общее количество элементов во вложенных списках
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        // Сначала преобразуем каждый список в его длину
        List<Integer> listLengths = Task3Methods.map(nestedLists, List::size);
        // Затем суммируем длины
        Integer totalElements = Task3Methods.reduce(listLengths, (a, b) -> a + b);
        System.out.println("Вложенные списки: " + nestedLists);
        System.out.println("Общее количество элементов: " + totalElements);

        // 3.4 Коллекционирование
        System.out.println("\n3.4 Коллекционирование:");

        // 1. Разделение на положительные и отрицательные
        Map<String, List<Integer>> partitioned = Task3Methods.collect(
                numbers,
                () -> {
                    Map<String, List<Integer>> map = new HashMap<>();
                    map.put("positive", new ArrayList<>());
                    map.put("negative", new ArrayList<>());
                    return map;
                },
                (item, collection) -> {
                    if (item > 0) {
                        collection.get("positive").add(item);
                    } else {
                        collection.get("negative").add(item);
                    }
                }
        );
        System.out.println("Числа: " + numbers);
        System.out.println("Разделенные числа: " + partitioned);

        // 2. Группировка строк по длине
        List<String> strings2 = Arrays.asList("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> groupedByLength = Task3Methods.collect(
                strings2,
                HashMap::new,
                (item, collection) -> {
                    int length = item.length();
                    collection.computeIfAbsent(length, k -> new ArrayList<>()).add(item);
                }
        );
        System.out.println("Строки: " + strings2);
        System.out.println("Сгруппированные по длине: " + groupedByLength);

        // В Main.java замените последний блок с уникальными строками:

// 3. Уникальные строки (используем Set)
        List<String> strings3 = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniqueStrings = Task3Methods.collect(
                strings3,
                HashSet::new,
                (item, collection) -> collection.add(item)  // ← ИСПРАВЛЕНО!
        );
        System.out.println("Строки: " + strings3);
        System.out.println("Уникальные строки: " + uniqueStrings);

        scanner.close();
        System.out.println("\n=== Программа завершена ===");
    }
}
