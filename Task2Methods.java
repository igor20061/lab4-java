// Task2Methods.java
public class Task2Methods {
    // Метод для задания 2.3
    public static <T extends Point3D> void putPointToBox(Box<T> box) {
        // Создаем новую точку с произвольными координатами
        Point3D point = new Point3D(
                Math.random() * 10,
                Math.random() * 10,
                Math.random() * 10
        );
        try {
            box.put((T) point);
        } catch (ClassCastException e) {
            System.out.println("Ошибка приведения типа");
        }
    }
}
