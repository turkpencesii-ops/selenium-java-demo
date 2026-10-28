package payment;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Tested
    PaymentService paymentService;

    @Injectable
    PaymentGateway gateway;

    @Test
    void successfulPaymentReturnsTrue() {
        new Expectations() {{
            gateway.charge("ACC-1", new BigDecimal("50.00"));
            result = true;
        }};

        assertTrue(paymentService.pay("ACC-1", new BigDecimal("50.00")));
    }

    @Test
    void negativeAmountIsRejectedWithoutCallingGateway() {
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.pay("ACC-1", new BigDecimal("-10.00")));

        new Verifications() {{
            gateway.charge(anyString, (BigDecimal) any);
            times = 0;
        }};
    }

    @Test
    void declinedPaymentReturnsFalse() {
        new Expectations() {{
            gateway.charge("ACC-1", new BigDecimal("50.00"));
            result = false;
        }};

        assertFalse(paymentService.pay("ACC-1", new BigDecimal("50.00")));
    }

}