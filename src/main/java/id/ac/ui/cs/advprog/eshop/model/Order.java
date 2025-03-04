package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Order {
    public enum Status {
        WAITING_PAYMENT, FAILED, CANCELLED, SUCCESS
    }

    private UUID id = UUID.randomUUID();
    private List<Product> products;
    private long orderTime = System.currentTimeMillis() / 1000;
    private String author;
    private Status status = Status.WAITING_PAYMENT;

    public Order(List<Product> products, String author) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.products = products;
        this.author = author;
    }

    public void setStatus(String newStatus) {
        try {
            this.status = Status.valueOf(newStatus);
        } catch (IllegalArgumentException e) {
            // Keep previous status if invalid
        }
    }
}
