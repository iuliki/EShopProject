// payment/LoanPaymentStrategy.java
package main.java.payment;

public class LoanPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using a loan.");
        // Adăugați logica specifică plății cu împrumut
    }
}
