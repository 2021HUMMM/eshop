package id.ac.ui.cs.advprog.eshop.model;
import lombok.Builder;
import lombok.Getter;
import  lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String paymentMethod;
    Map<String, String> paymentData;
    String orderId;

    @Setter
    String status;

    public Payment(String id, String paymentMethod, Map<String, String> paymentData, String orderId) {}

}
