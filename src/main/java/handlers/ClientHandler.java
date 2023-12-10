package main.java.handlers;

import main.java.cart.CartManager;
import main.java.products.Product;
import main.java.products.ProductStock;
import main.java.users.Client;
import main.java.users.User;
import main.java.payment.*;

import java.util.Scanner;

public class ClientHandler implements UserHandlerStrategy {
    private ProductStock productStock;

    public ClientHandler(ProductStock productStock) {
        this.productStock = productStock;
    }

    @Override
    public void handleUser(User user) {
        Client client = (Client) user;
        client.displayInfo();

        CartManager cartManager = CartManager.getInstance();

        // Verificare dacă există produse disponibile
        if (productStock.getProducts().isEmpty()) {
            System.out.println("Nu avem produse disponibile. Ieșire din client.");
            return;
        }

        // Afișare produse disponibile
        productStock.displayProducts();

        // Adăugare produse în coș
        System.out.println("Adăugați produse în coș (introduceți indexul produsului sau -1 pentru a opri):");
        Scanner scanner = new Scanner(System.in);
        int productIndex;

        do {
            productIndex = scanner.nextInt();

            if (productIndex >= 0 && productIndex < productStock.getProducts().size()) {
                Product selectedProduct = productStock.getProduct(productIndex);

                // Solicităm cantitatea
                System.out.print("Introduceți cantitatea produsului \"" + selectedProduct.getName() + "\": ");
                int quantity = scanner.nextInt();

                // Adăugăm produsul în coș cu cantitatea specificată
                cartManager.addToCart(selectedProduct, quantity);
            } else if (productIndex != -1) {
                System.out.println("Index invalid. Încercați din nou.");
            }
        } while (productIndex != -1);


        // Afișare coș
        cartManager.displayCart();

        // Finalizare comandă
        cartManager.completeOrder();

        // Setare strategie de plată în funcție de alegerea utilizatorului
        System.out.println("Selectați metoda de plată:");
        System.out.println("1. Cash Payment");
        System.out.println("2. Card Payment");
        System.out.println("3. Loan Payment");
        int paymentOption = scanner.nextInt();

        switch (paymentOption) {
            case 1:
                client.setPaymentStrategy(new CashPaymentStrategy());
                break;
            case 2:
                client.setPaymentStrategy(new CardPaymentStrategy());
                break;
            case 3:
                client.setPaymentStrategy(new LoanPaymentStrategy());
                break;
            default:
                System.out.println("Opțiune invalidă. Folosind Cash Payment implicit.");
                client.setPaymentStrategy(new CashPaymentStrategy());
                break;
        }

        // Plata folosind strategia selectată
        System.out.println("Introduceți suma de plată:");
        double paymentAmount = scanner.nextDouble();
        client.makePayment(paymentAmount);

        System.out.println("Ați ieșit ca și client.");
    }
}
