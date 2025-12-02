package ua.opnu.java.inheritance.account;

public class BankingAccount {
    private int balance;

    public BankingAccount(ua.opnu.java.inheritance.account.Startup s) {
        this.balance = s.getInitialBalance();
    }

    public void debit(ua.opnu.java.inheritance.account.Debit d) {
        // Debit - завжди додаємо суму
        balance += d.getAmount();
    }

    public void credit(ua.opnu.java.inheritance.account.Credit c) {
        // Credit - якщо передано від'ємне значення, то це фактично поповнення
        // Якщо передано додатне значення, то це зняття
        balance -= c.getAmount();
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankingAccount other = (BankingAccount) obj;
        return balance == other.balance;
    }

    @Override
    public String toString() {
        return "BankingAccount{balance=" + balance + "}";
    }
}
