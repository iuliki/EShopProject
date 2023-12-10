// payment/CardPaymentStrategy.java
package main.java.payment;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using a card.");

    }
}
