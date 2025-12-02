package ua.opnu.java.inheritance.account;

public class MinMaxAccount extends ua.opnu.java.inheritance.account.BankingAccount {
    private int minBalance;
    private int maxBalance;

    public MinMaxAccount(ua.opnu.java.inheritance.account.Startup s) {
        super(s);
        int initialBalance = getBalance();
        this.minBalance = initialBalance;
        this.maxBalance = initialBalance;
    }

    @Override
    public void debit(ua.opnu.java.inheritance.account.Debit d) {
        // debit завжди додає гроші (використовуємо абсолютне значення)
        int amount = Math.abs(d.getAmount());
        super.debit(new ua.opnu.java.inheritance.account.Debit(amount));
        updateMinMax();
    }

    @Override
    public void credit(ua.opnu.java.inheritance.account.Credit c) {
        // credit завжди знімає гроші (використовуємо абсолютне значення)
        int amount = Math.abs(c.getAmount());
        super.credit(new ua.opnu.java.inheritance.account.Credit(amount));
        updateMinMax();
    }

    private void updateMinMax() {
        int currentBalance = getBalance();
        if (currentBalance < minBalance) {
            minBalance = currentBalance;
        }
        if (currentBalance > maxBalance) {
            maxBalance = currentBalance;
        }
    }

    public int getMin() {
        return minBalance;
    }

    public int getMax() {
        return maxBalance;
    }

    // MAIN метод для демонстрації роботи класу
    public static void main(String[] args) {
        System.out.println("=== Демонстрація роботи класу MinMaxAccount ===\n");

        // Створюємо рахунок з початковим балансом 1000 копійок (10.00 грн)
        Startup startup = new Startup(1000);
        MinMaxAccount account = new MinMaxAccount(startup);

        System.out.println("1. Початковий стан рахунку:");
        displayAccountInfo(account);

        // Поповнення рахунку
        System.out.println("\n2. Поповнення рахунку на 500 копійок (5.00 грн):");
        account.debit(new Debit(500));
        displayAccountInfo(account);

        // Зняття з рахунку
        System.out.println("\n3. Зняття з рахунку 300 копійок (3.00 грн):");
        account.credit(new Credit(300));
        displayAccountInfo(account);

        // Серія операцій
        System.out.println("\n4. Серія операцій:");

        account.debit(new Debit(1000)); // +10.00 грн
        System.out.println("Поповнення на 1000 копійок:");
        displayAccountInfo(account);

        account.credit(new Credit(800)); // -8.00 грн
        System.out.println("Зняття 800 копійок:");
        displayAccountInfo(account);

        account.credit(new Credit(500)); // -5.00 грн
        System.out.println("Зняття 500 копійок:");
        displayAccountInfo(account);

        account.debit(new Debit(200)); // +2.00 грн
        System.out.println("Поповнення на 200 копійок:");
        displayAccountInfo(account);

        // Додаткові тести
        System.out.println("\n5. Додаткові тести:");

        // Тест з тільки поповненнями
        Startup startup2 = new Startup(500);
        MinMaxAccount account2 = new MinMaxAccount(startup2);
        account2.debit(new Debit(300));
        account2.debit(new Debit(700));
        System.out.println("Рахунок з тільки поповненнями:");
        System.out.println("  Початковий баланс: 500 коп.");
        System.out.println("  Мінімальний: " + account2.getMin() + " коп.");
        System.out.println("  Максимальний: " + account2.getMax() + " коп.");
        System.out.println("  Поточний: " + account2.getBalance() + " коп.");

        // Тест з тільки зняттями
        Startup startup3 = new Startup(2000);
        MinMaxAccount account3 = new MinMaxAccount(startup3);
        account3.credit(new Credit(500));
        account3.credit(new Credit(800));
        System.out.println("Рахунок з тільки зняттями:");
        System.out.println("  Початковий баланс: 2000 коп.");
        System.out.println("  Мінімальний: " + account3.getMin() + " коп.");
        System.out.println("  Максимальний: " + account3.getMax() + " коп.");
        System.out.println("  Поточний: " + account3.getBalance() + " коп.");

        // Підсумки основного рахунку
        System.out.println("\n6. Фінальні результати основного рахунку:");
        System.out.println("Початковий баланс: 1000 копійок (" + formatMoney(1000) + " грн)");
        System.out.println("Мінімальний баланс за весь час: " + account.getMin() + " копійок (" + formatMoney(account.getMin()) + " грн)");
        System.out.println("Максимальний баланс за весь час: " + account.getMax() + " копійок (" + formatMoney(account.getMax()) + " грн)");
        System.out.println("Поточний баланс: " + account.getBalance() + " копійок (" + formatMoney(account.getBalance()) + " грн)");

        // Демонстрація наслідування
        System.out.println("\n7. Перевірка наслідування базових методів:");
        System.out.println("Метод getBalance() працює: " + (account.getBalance() == 1100));
        System.out.println("Метод equals() працює: " + account.equals(account));
        System.out.println("Метод toString() працює: " + (account.toString() != null));

        System.out.println("\n=== Демонстрація завершена ===");
    }

    private static void displayAccountInfo(MinMaxAccount account) {
        System.out.println("  Поточний баланс: " + account.getBalance() + " коп. (" + formatMoney(account.getBalance()) + " грн)");
        System.out.println("  Мінімальний баланс: " + account.getMin() + " коп. (" + formatMoney(account.getMin()) + " грн)");
        System.out.println("  Максимальний баланс: " + account.getMax() + " коп. (" + formatMoney(account.getMax()) + " грн)");
    }

    private static String formatMoney(int kopiyky) {
        return String.format("%.2f", kopiyky / 100.0);
    }
}
