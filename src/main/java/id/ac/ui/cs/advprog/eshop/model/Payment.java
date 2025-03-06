package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import  lombok.Setter;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
public class Payment {
    String id;
    String paymentMethod;
    Map<String, String> paymentData;
    Order order;
    String status = "PENDING";

    public Payment(String paymentMethod, Map<String, String> paymentData, Order order) {
        this.id = UUID.randomUUID().toString();
        this.paymentMethod = paymentMethod;
        this.order = order;
        this.setPaymentData(paymentMethod,paymentData);
        this.setPaymentMethod(paymentMethod);
    }

    public void setPaymentMethod(String paymentMethod) {
        String[] paymentMethods = {"Bank Transfer", "Voucher Code"};
        if (Arrays.stream(paymentMethods).noneMatch(item -> (item.equals(paymentMethod)))) {
            throw new IllegalArgumentException();
        } else {
            this.paymentMethod = paymentMethod;
        }
    }

    public void setPaymentData(String paymentMethod, Map<String, String> paymentData) {
        if (paymentMethod.equals("Bank Transfer")) {
            if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException();
            }

            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");

            if (isEmpty(bankName) || isEmpty(referenceCode)) {
                this.status = "REJECTED";
            } else {
                this.paymentData = paymentData;
                this.status = "SUCCESS";
            }
        } else if (paymentMethod.equals("Voucher code")) {
            if (!paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException();
            }

            String voucherCode = paymentData.get("voucherCode");

            if (isValidVoucher(voucherCode)) {
                this.paymentData = paymentData;
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        }
    }

    private boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        long digitCount = voucherCode.chars().filter(Character::isDigit).count();
        return digitCount == 8;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }


    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
