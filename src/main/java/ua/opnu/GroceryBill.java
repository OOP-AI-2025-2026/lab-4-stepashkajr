package ua.opnu.java.inheritance.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GroceryBill {
    private ua.opnu.java.inheritance.bill.Employee clerk;
    private List<ua.opnu.java.inheritance.bill.Item> items;

    public GroceryBill(ua.opnu.java.inheritance.bill.Employee clerk) {
        this.clerk = clerk;
        this.items = new ArrayList<>();
    }

    public void add(ua.opnu.java.inheritance.bill.Item item) {
        items.add(item);
    }

    public double getTotal() {
        
        BigDecimal total = BigDecimal.ZERO;
        for (ua.opnu.java.inheritance.bill.Item item : items) {
            total = total.add(BigDecimal.valueOf(item.getPrice()));
        }
        return total.doubleValue();
    }

    public ua.opnu.java.inheritance.bill.Employee getClerk() {
        return clerk;
    }

    public List<ua.opnu.java.inheritance.bill.Item> getItems() {
        return new ArrayList<>(items);
    }
}
