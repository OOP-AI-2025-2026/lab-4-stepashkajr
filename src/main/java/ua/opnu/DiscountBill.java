package ua.opnu.java.inheritance.bill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountBill extends ua.opnu.java.inheritance.bill.GroceryBill {
    private boolean regularCustomer;
    private int discountCount;
    private double totalDiscount;
    private List<ua.opnu.java.inheritance.bill.Item> items;

    public DiscountBill(ua.opnu.java.inheritance.bill.Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.totalDiscount = 0.0;
        this.items = new ArrayList<>();
    }

    @Override
    public void add(ua.opnu.java.inheritance.bill.Item item) {
        super.add(item);
        items.add(item);

        if (regularCustomer && item.getDiscount() > 0.0) {
            discountCount++;
            totalDiscount += item.getDiscount();
        }
    }

    @Override
    public double getTotal() {
        if (regularCustomer) {
            // Використовуємо BigDecimal для точних обчислень
            BigDecimal totalWithoutDiscount = BigDecimal.valueOf(super.getTotal());
            BigDecimal discount = BigDecimal.valueOf(totalDiscount);
            BigDecimal result = totalWithoutDiscount.subtract(discount);
            return result.doubleValue();
        } else {
            return super.getTotal();
        }
    }

    public int getDiscountCount() {
        return discountCount;
    }

    public double getDiscountAmount() {
        return totalDiscount;
    }

    public double getDiscountPercent() {
        double totalWithoutDiscount = super.getTotal();
        if (totalWithoutDiscount == 0.0) {
            return 0.0;
        }
        double totalWithDiscount = getTotal();

        // Використовуємо BigDecimal для точного розрахунку відсотка
        BigDecimal totalWithoutDiscountBD = BigDecimal.valueOf(totalWithoutDiscount);
        BigDecimal totalWithDiscountBD = BigDecimal.valueOf(totalWithDiscount);

        // Формула: 100 - (totalWithDiscount * 100) / totalWithoutDiscount
        BigDecimal percent = BigDecimal.valueOf(100)
                .subtract(
                        totalWithDiscountBD
                                .multiply(BigDecimal.valueOf(100))
                                .divide(totalWithoutDiscountBD, 15, RoundingMode.HALF_UP)
                );

        return percent.doubleValue();
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
        System.out.println("=== Демонстрація роботи класу DiscountBill ===\n");

        // Створюємо співробітника
        ua.opnu.java.inheritance.bill.Employee clerk = new ua.opnu.java.inheritance.bill.Employee("Іван Петренко");

        // Тестування для постійного клієнта
        System.out.println("1. Тестування для ПОСТІЙНОГО клієнта:");
        DiscountBill regularBill = new DiscountBill(clerk, true);

        // Додаємо товари з різними знижками
        regularBill.add(new ua.opnu.java.inheritance.bill.Item("Молоко", 25.0, 2.5));
        regularBill.add(new ua.opnu.java.inheritance.bill.Item("Хліб", 15.0, 1.5));
        regularBill.add(new ua.opnu.java.inheritance.bill.Item("Вода", 10.0, 0.0));
        regularBill.add(new ua.opnu.java.inheritance.bill.Item("Сир", 45.0, 4.5));

        // Виводимо результати
        System.out.println("Співробітник: " + regularBill.getClerk().getName());
        System.out.println("Кількість товарів зі знижкою: " + regularBill.getDiscountCount());
        System.out.println("Загальна знижка: " + regularBill.getDiscountAmount() + " грн");
        System.out.println("Повна вартість: " + regularBill.getTotal() + " грн");
        System.out.println("Відсоток знижки: " + String.format("%.2f", regularBill.getDiscountPercent()) + "%");

        // Розрахунок для перевірки
        double fullPrice = 25.0 + 15.0 + 10.0 + 45.0; // 95 грн
        double discount = 2.5 + 1.5 + 4.5; // 8.5 грн
        double finalPrice = fullPrice - discount; // 86.5 грн
        double discountPercent = 100 - (finalPrice * 100) / fullPrice; // 8.95%

        System.out.println("\nПеревірка розрахунків:");
        System.out.println("Повна вартість: " + fullPrice + " грн");
        System.out.println("Знижка: " + discount + " грн");
        System.out.println("Фінальна вартість: " + finalPrice + " грн");
        System.out.println("Відсоток знижки: " + String.format("%.2f", discountPercent) + "%");

        // Тестування для звичайного клієнта
        System.out.println("\n2. Тестування для ЗВИЧАЙНОГО клієнта:");
        DiscountBill regularCustomerBill = new DiscountBill(clerk, false);

        // Додаємо ті ж самі товари
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Молоко", 25.0, 2.5));
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Хліб", 15.0, 1.5));
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Вода", 10.0, 0.0));
        regularCustomerBill.add(new ua.opnu.java.inheritance.bill.Item("Сир", 45.0, 4.5));

        // Виводимо результати
        System.out.println("Кількість товарів зі знижкою: " + regularCustomerBill.getDiscountCount());
        System.out.println("Загальна знижка: " + regularCustomerBill.getDiscountAmount() + " грн");
        System.out.println("Повна вартість: " + regularCustomerBill.getTotal() + " грн");
        System.out.println("Відсоток знижки: " + String.format("%.2f", regularCustomerBill.getDiscountPercent()) + "%");

        // Демонстрація різниці
        System.out.println("\n3. Порівняння:");
        System.out.println("Постійний клієнт заплатив: " + regularBill.getTotal() + " грн");
        System.out.println("Звичайний клієнт заплатив: " + regularCustomerBill.getTotal() + " грн");
        System.out.println("Економія: " + (regularCustomerBill.getTotal() - regularBill.getTotal()) + " грн");

        // Тестування з одним товаром
        System.out.println("\n4. Тестування з одним товаром зі знижкою:");
        DiscountBill singleItemBill = new DiscountBill(clerk, true);
        singleItemBill.add(new ua.opnu.java.inheritance.bill.Item("Ноутбук", 15000.0, 1500.0));

        System.out.println("Товар: Ноутбук");
        System.out.println("Ціна: 15000.0 грн");
        System.out.println("Знижка: 1500.0 грн");
        System.out.println("Кількість товарів зі знижкою: " + singleItemBill.getDiscountCount());
        System.out.println("Загальна знижка: " + singleItemBill.getDiscountAmount() + " грн");
        System.out.println("Фінальна вартість: " + singleItemBill.getTotal() + " грн");
        System.out.println("Відсоток знижки: " + String.format("%.2f", singleItemBill.getDiscountPercent()) + "%");

        // Тестування порожнього чеку
        System.out.println("\n5. Тестування порожнього чеку:");
        DiscountBill emptyBill = new DiscountBill(clerk, true);
        System.out.println("Кількість товарів зі знижкою: " + emptyBill.getDiscountCount());
        System.out.println("Загальна знижка: " + emptyBill.getDiscountAmount() + " грн");
        System.out.println("Фінальна вартість: " + emptyBill.getTotal() + " грн");
        System.out.println("Відсоток знижки: " + String.format("%.2f", emptyBill.getDiscountPercent()) + "%");

        System.out.println("\n=== Демонстрація завершена ===");
    }
}
