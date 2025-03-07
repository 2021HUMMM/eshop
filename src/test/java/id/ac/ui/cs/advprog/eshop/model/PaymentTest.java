package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Product> products;
    private Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558f9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c26328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );
    }



    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "Bank Transfer",
                paymentData,
                order
        );
        assertEquals("Bank Transfer", payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }
    @Test
    void testCreatePaymentBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "Bank Transfer",
                paymentData,
                order
        );

        assertEquals("Bank Transfer", payment.getPaymentMethod());
    }
    @Test
    void testCreateInvalidPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "Bank Transfer",
                    paymentData,
                    order
            );
        });
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("bankName", "Niaga");
            paymentData.put("referenceCode", "12345");
            Payment payment = new Payment(
                    "chopin",
                    paymentData,
                    order
            );
        });
    }
    @Test
    void testSetPaymentStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "Bank Transfer",
                paymentData,
                order
        );
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testSetPaymentStatusRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "Voucher Code",
                paymentData,
                order
        );
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }
}

