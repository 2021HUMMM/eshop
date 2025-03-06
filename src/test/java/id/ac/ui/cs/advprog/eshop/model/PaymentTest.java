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

    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb1396d79b",
                "Bank Transfer",
                paymentData,
                "tanzania"
        );
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("Bank Transfer", payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("tanzania", payment.getOrderId());
    }
    @Test
    void testCreatePaymentBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb1396d79b",
                "Bank Transfer",
                paymentData,
                "tanzania"
        );

        assertEquals("Bank Transfer", payment.getPaymentMethod());
    }
    @Test
    void testCreateInvalidPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "13652556-012a-4c07-b546-54eb1396d79b",
                    "Bank Transfer",
                    paymentData,
                    "tanzania"
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
                    "13652556-012a-4c07-b546-54eb1396d79b",
                    "chopin",
                    paymentData,
                    "tanzania"
            );
        });
    }
    @Test
    void testSetPaymentStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Niaga");
        paymentData.put("referenceCode", "12345");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb1396d79b",
                "Bank Transfer",
                paymentData,
                "tanzania"
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
                "13652556-012a-4c07-b546-54eb1396d79b",
                "Voucher Code",
                paymentData,
                "tanzania"
        );
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }

}

