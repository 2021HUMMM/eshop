package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PaymentServiceImpl implements PaymentService {

    private  PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String paymentMethod, Map<String, String> paymentData) {
        Payment payment = new Payment(paymentMethod, paymentData, order);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);
        if(payment.getStatus().equals("SUCCESS")){
            payment.getOrder().setStatus("SUCCESS");
        }else if(payment.getStatus().equals("REJECTED")){
            payment.getOrder().setStatus("FAILED");
        }
        return payment;

    }

    @Override
    public Payment getPayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            return payment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
