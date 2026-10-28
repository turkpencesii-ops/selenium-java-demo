package payment;

import java.math.BigDecimal;

public interface PaymentGateway {

    boolean charge(String accountId, BigDecimal amount);
}