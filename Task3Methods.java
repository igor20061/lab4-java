// Task3Methods.java
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.BiConsumer;

public class Task3Methods {

    // 3.1 Функция (map)
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    // 3.2 Фильтр
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // 3.3 Сокращение (reduce)
    public static <T> T reduce(List<T> list, BinaryOperator<T> reducer) {
        if (list.isEmpty()) {
            return null;
        }

        T result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result = reducer.apply(result, list.get(i));
        }
        return result;
    }

    // Улучшенный reduce без null
    public static <T> T reduceSafe(List<T> list, BinaryOperator<T> reducer, T defaultValue) {
        if (list.isEmpty()) {
            return defaultValue;
        }

        T result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result = reducer.apply(result, list.get(i));
        }
        return result;
    }

    // 3.4 Коллекционирование - исправленная версия
    public static <T, P> P collect(
            List<T> list,
            Supplier<P> collectionFactory,
            BiConsumer<T, P> collector) {

        P result = collectionFactory.get();
        for (T item : list) {
            collector.accept(item, result);
        }
        return result;
    }
}