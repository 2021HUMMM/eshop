package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Order order;
    private Payment payment;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("123", products, 1708560000L, "Safina Sudrajat", "PENDING");
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");
        payment = new Payment("Bank Transfer", paymentData, order);
    }

    @Test
    void testAddPayment() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "Bank Transfer", payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(payment.getOrder().getId(), result.getOrder().getId());
    }

    @Test
    void testSetStatusSuccess() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        payment.setStatus("SUCCESS");

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusRejected() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        payment.setStatus("REJECTED");

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "INVALID"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentFound() {
        doReturn(payment).when(paymentRepository).findById("pay123");

        Payment result = paymentService.getPayment("pay123");

        assertNotNull(result);
        assertEquals("pay123", result.getId());
    }

    @Test
    void testGetPaymentNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid_id");

        assertThrows(NoSuchElementException.class, () -> paymentService.getPayment("invalid_id"));
    }

    @Test
    void testGetAllPayments() {
        paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
    }
}

