package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class Order {
    String id;
    List<Product> products;
    Long orderTime;
    String author;
    String status;

    @Getter
    public enum OrderStatus {
        WAITING_PAYMENT("WAITING_PAYMENT"),
        FAILED("FAILED"),
        SUCCESS("SUCCESS"),
        CANCELLED("CANCELLED");

        private final String value;

        private OrderStatus(String value) {
            this.value = value;
        }

        public static boolean contains(String param) {
            for (OrderStatus orderStatus : OrderStatus.values()) {
                if (orderStatus.name().equals(param)) {
                    return true;
                }
            }
            return false;
        }
    }


    public Order(String id, List<Product> products, Long orderTime, String author) {
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}

