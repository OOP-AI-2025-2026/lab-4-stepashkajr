package ua.opnu.java.inheritance.point;

public class Point3D extends ua.opnu.java.inheritance.point.Point {
    private int z;

    // Конструктор без параметрів - точка (0,0,0)
    public Point3D() {
        super();
        this.z = 0;
    }

    // Конструктор з трьома координатами
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    // Конструктор копіювання
    public Point3D(Point3D p) {
        super(p);
        this.z = p.z;
    }

    // Новий метод для встановлення трьох координат
    public void setLocation(int x, int y, int z) {
        super.setLocation(x, y);
        this.z = z;
    }

    // Перевизначення методу setLocation з двома параметрами
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.z = 0; // z встановлюється в 0
    }

    // Новий метод для розрахунку відстані між двома точками 3D
    public double distance(Point3D p) {
        int dx = this.x - p.x;
        int dy = this.y - p.y;
        int dz = this.z - p.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    // Перевизначення методу distanceFromOrigin для 3D
    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Гетер для координати z
    public int getZ() {
        return z;
    }

    // Сетер для координати z
    public void setZ(int z) {
        this.z = z;
    }

    // Перевизначення методу toString для відображення трьох координат
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    // Перевизначення методу equals для порівняння трьох координат
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        Point3D point3D = (Point3D) obj;
        return z == point3D.z;
    }

    // Перевизначення методу distance для сумісності з Point
    // Якщо передається Point (не Point3D), то використовуємо 2D відстань
    @Override
    public double distance(ua.opnu.java.inheritance.point.Point p) {
        if (p instanceof Point3D) {
            return distance((Point3D) p);
        } else {
            // Для звичайної точки Point використовуємо 2D відстань
            int dx = this.x - p.x;
            int dy = this.y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // MAIN метод для демонстрації роботи класу
    public static void main(String[] args) {
        System.out.println("=== Демонстрація роботи класу Point3D ===\n");

        // Тестування конструкторів
        System.out.println("1. Тестування конструкторів:");
        Point3D p1 = new Point3D(); // (0,0,0)
        Point3D p2 = new Point3D(3, 4, 5);
        Point3D p3 = new Point3D(p2); // копія p2

        System.out.println("Point3D(): " + p1);
        System.out.println("Point3D(3, 4, 5): " + p2);
        System.out.println("Point3D(Point3D): " + p3);
        System.out.println("p2.equals(p3): " + p2.equals(p3));

        // Тестування методів setLocation
        System.out.println("\n2. Тестування методів setLocation:");
        Point3D p4 = new Point3D(1, 2, 3);
        System.out.println("Початкова точка: " + p4);

        p4.setLocation(10, 20, 30);
        System.out.println("Після setLocation(10, 20, 30): " + p4);

        p4.setLocation(5, 15);
        System.out.println("Після setLocation(5, 15): " + p4 + " (z має бути 0)");

        // Тестування відстаней
        System.out.println("\n3. Тестування відстаней:");
        Point3D origin = new Point3D();
        Point3D point1 = new Point3D(3, 0, 0);
        Point3D point2 = new Point3D(0, 4, 0);
        Point3D point3 = new Point3D(0, 0, 5);
        Point3D point4 = new Point3D(3, 4, 5);

        System.out.println("Відстань від " + origin + " до " + point1 + ": " + origin.distance(point1));
        System.out.println("Відстань від " + origin + " до " + point2 + ": " + origin.distance(point2));
        System.out.println("Відстань від " + origin + " до " + point3 + ": " + origin.distance(point3));
        System.out.println("Відстань від " + origin + " до " + point4 + ": " + origin.distance(point4));

        System.out.println("Відстань від початку координат для " + point4 + ": " + point4.distanceFromOrigin());

        // Перевірка правильності розрахунків
        double expectedDistance = Math.sqrt(3*3 + 4*4 + 5*5); // √(9+16+25) = √50 ≈ 7.07
        System.out.println("Очікувана відстань для (3,4,5): " + expectedDistance);
        System.out.println("Фактична відстань: " + point4.distanceFromOrigin());

        // Тестування сумісності з Point
        System.out.println("\n4. Тестування сумісності з класом Point:");
        Point3D p3d = new Point3D(2, 3, 4);
        ua.opnu.java.inheritance.point.Point p2d = new ua.opnu.java.inheritance.point.Point(2, 3);

        System.out.println("Point3D: " + p3d);
        System.out.println("Point: " + p2d);
        System.out.println("p3d.equals(p2d): " + p3d.equals(p2d) + " (має бути false)");
        System.out.println("p2d.equals(p3d): " + p2d.equals(p3d) + " (має бути false)");

        // Відстань між Point3D та Point
        System.out.println("Відстань між " + p3d + " і " + p2d + ": " + p3d.distance(p2d));

        // Тестування гетерів і сетерів
        System.out.println("\n5. Тестування гетерів і сетерів:");
        Point3D testPoint = new Point3D();
        System.out.println("Початкова точка: " + testPoint);

        testPoint.setX(10);
        testPoint.setY(20);
        testPoint.setZ(30);
        System.out.println("Після setX(10), setY(20), setZ(30): " + testPoint);
        System.out.println("getX(): " + testPoint.getX());
        System.out.println("getY(): " + testPoint.getY());
        System.out.println("getZ(): " + testPoint.getZ());

        // Тестування особливих випадків
        System.out.println("\n6. Тестування особливих випадків:");
        Point3D negativePoint = new Point3D(-1, -2, -3);
        System.out.println("Точка з від'ємними координатами: " + negativePoint);
        System.out.println("Відстань від початку: " + negativePoint.distanceFromOrigin());

        Point3D samePoint1 = new Point3D(5, 5, 5);
        Point3D samePoint2 = new Point3D(5, 5, 5);
        System.out.println("Відстань між двома однаковими точками: " + samePoint1.distance(samePoint2));

        System.out.println("\n=== Демонстрація завершена ===");
    }
}
