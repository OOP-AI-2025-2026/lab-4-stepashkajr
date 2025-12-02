package ua.opnu.java.inheritance.bill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountBill2 {
    private ua.opnu.java.inheritance.bill.GroceryBill groceryBill;
    private boolean regularCustomer;
    private int discountCount;
    private BigDecimal totalDiscount;
    private List<ua.opnu.java.inheritance.bill.Item> items;

    public DiscountBill2(ua.opnu.java.inheritance.bill.Employee clerk, boolean regularCustomer) {
        this.groceryBill = new ua.opnu.java.inheritance.bill.GroceryBill(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.totalDiscount = BigDecimal.ZERO;
        this.items = new ArrayList<>();
    }

    public void add(ua.opnu.java.inheritance.bill.Item item) {
        groceryBill.add(item);
        items.add(item);

        if (regularCustomer && item.getDiscount() > 0.0) {
            discountCount++;
            totalDiscount = totalDiscount.add(BigDecimal.valueOf(item.getDiscount()));
        }
    }

    public double getTotal() {
        if (regularCustomer) {
            BigDecimal totalWithoutDiscount = BigDecimal.valueOf(groceryBill.getTotal());
            BigDecimal result = totalWithoutDiscount.subtract(totalDiscount);
            return result.doubleValue();
        } else {
            return groceryBill.getTotal();
        }
    }

    public ua.opnu.java.inheritance.bill.Employee getClerk() {
        return groceryBill.getClerk();
    }

    public int getDiscountCount() {
        return discountCount;
    }

    public double getDiscountAmount() {
        return totalDiscount.doubleValue();
    }

    public double getDiscountPercent() {
        double totalWithoutDiscount = groceryBill.getTotal();
        if (totalWithoutDiscount == 0.0) {
            return 0.0;
        }

        // Використовуємо BigDecimal для точних обчислень
        BigDecimal totalWithoutDiscountBD = BigDecimal.valueOf(totalWithoutDiscount);
        BigDecimal totalDiscountBD = this.totalDiscount;

        // Формула: (totalDiscount * 100) / totalWithoutDiscount
        BigDecimal percent = totalDiscountBD
                .multiply(BigDecimal.valueOf(100))
                .divide(totalWithoutDiscountBD, 15, RoundingMode.HALF_UP);

        return percent.doubleValue();
    }

    // Додатковий метод для отримання загальної суми без знижки
    public double getTotalWithoutDiscount() {
        return groceryBill.getTotal();
    }

    // Додатковий метод для отримання списку товарів зі знижкою
    public List<ua.opnu.java.inheritance.bill.Item> getDiscountedItems() {
        List<ua.opnu.java.inheritance.bill.Item> discounted = new ArrayList<>();
        if (regularCustomer) {
            for (ua.opnu.java.inheritance.bill.Item item : items) {
                if (item.getDiscount() > 0.0) {
                    discounted.add(item);
                }
            }
        }
        return discounted;
    }

    // MAIN метод для демонстрації роботи класу
    public static void main(String[] args) {
        System.out.println("=== Демонстрація роботи класу DiscountBill2 (композиція) ===\n");

        // Створюємо співробітника
        ua.opnu.java.inheritance.bill.Employee clerk = new ua.opnu.java.inheritance.bill.Employee("Марія Іваненко");

        // Порівняння роботи DiscountBill2 для різних типів клієнтів
        System.out.println("1. Порівняння ПОСТІЙНОГО та ЗВИЧАЙНОГО клієнтів:");

        // Постійний клієнт
        DiscountBill2 regularCustomerBill = new DiscountBill2(clerk, true);
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Молоко", 25.0, 2.5));
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Хліб", 15.0, 1.5));
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Сир", 45.0, 4.5));

        // Звичайний клієнт
        DiscountBill2 normalCustomerBill = new DiscountBill2(clerk, false);
        normalCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Молоко", 25.0, 2.5));
        normalCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Хліб", 15.0, 1.5));
        normalCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Сир", 45.0, 4.5));

        System.out.println("Для ПОСТІЙНОГО клієнта:");
        System.out.println("  Кількість товарів зі знижкою: " + regularCustomerBill.getDiscountCount());
        System.out.println("  Загальна знижка: " + regularCustomerBill.getDiscountAmount() + " грн");
        System.out.println("  Повна вартість: " + regularCustomerBill.getTotalWithoutDiscount() + " грн");
        System.out.println("  Фінальна вартість: " + regularCustomerBill.getTotal() + " грн");
        System.out.println("  Відсоток знижки: " + String.format("%.2f", regularCustomerBill.getDiscountPercent()) + "%");

        System.out.println("\nДля ЗВИЧАЙНОГО клієнта:");
        System.out.println("  Кількість товарів зі знижкою: " + normalCustomerBill.getDiscountCount());
        System.out.println("  Загальна знижка: " + normalCustomerBill.getDiscountAmount() + " грн");
        System.out.println("  Фінальна вартість: " + normalCustomerBill.getTotal() + " грн");
        System.out.println("  Відсоток знижки: " + String.format("%.2f", normalCustomerBill.getDiscountPercent()) + "%");

        // Демонстрація економії
        System.out.println("\n2. Економія постійного клієнта:");
        double savings = normalCustomerBill.getTotal() - regularCustomerBill.getTotal();
        System.out.println("  Економія: " + savings + " грн");

        // Тестування з товарами без знижки
        System.out.println("\n3. Тестування з товарами без знижки:");
        DiscountBill2 noDiscountBill = new DiscountBill2(clerk, true);
        noDiscountBill.add(new ua.opnu.java.inheritance.bill.Item("Вода", 10.0, 0.0));
        noDiscountBill.add(new ua.opnu.java.inheritance.bill.Item("Сік", 20.0, 0.0));

        System.out.println("  Кількість товарів зі знижкою: " + noDiscountBill.getDiscountCount());
        System.out.println("  Загальна знижка: " + noDiscountBill.getDiscountAmount() + " грн");
        System.out.println("  Фінальна вартість: " + noDiscountBill.getTotal() + " грн");
        System.out.println("  Відсоток знижки: " + String.format("%.2f", noDiscountBill.getDiscountPercent()) + "%");

        // Тестування порожнього чеку
        System.out.println("\n4. Тестування порожнього чеку:");
        DiscountBill2 emptyBill = new DiscountBill2(clerk, true);
        System.out.println("  Кількість товарів зі знижкою: " + emptyBill.getDiscountCount());
        System.out.println("  Загальна знижка: " + emptyBill.getDiscountAmount() + " грн");
        System.out.println("  Фінальна вартість: " + emptyBill.getTotal() + " грн");
        System.out.println("  Відсоток знижки: " + String.format("%.2f", emptyBill.getDiscountPercent()) + "%");

        // Перевірка інформації про співробітника
        System.out.println("\n5. Інформація про співробітника:");
        System.out.println("  Співробітник: " + regularCustomerBill.getClerk().getName());

        System.out.println("\n=== Демонстрація завершена ===");
    }
}
