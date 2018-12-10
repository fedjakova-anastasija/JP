package PaymentMethod;

public class PaymentMethod implements IPaymentMethod {
    private PaymentType paymentType;

    public PaymentMethod(String method) {
        if (method.toLowerCase().equals("card")) {
            this.paymentType = PaymentType.CARD;
        } else {
            if (method.toLowerCase().equals("cash")) {
                this.paymentType = PaymentType.CASH;
            } else {
                if (method.toLowerCase().equals("bonuses")) {
                    this.paymentType = PaymentType.BONUSES;
                }
            }
        }
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }
}
