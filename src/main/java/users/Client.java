package main.java.users;

import main.java.payment.PaymentStrategy;
import main.java.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private List<Product> cart;
    private List<Product> favorites;
    private PaymentStrategy paymentStrategy;
    // Constructor
    public Client(String name, String email) {
        super(name, email);  // Apelăm constructorul clasei părinte
        cart = new ArrayList<>();
        favorites = new ArrayList<>();
    }

    // Metode specifice pentru client
    public void clientSpecificMethod() {
        System.out.println("This is a method specific to clients.");
    }

    // Metode specifice pentru client (cumpărare, adăugare în lista de favorite, etc.)
    public void addToCart(Product product) {
        cart.add(product);
        System.out.println("Product added to the cart: " + product.getName());
    }

    public void addToFavorites(Product product) {
        favorites.add(product);
        System.out.println("Product added to favorites: " + product.getName());
    }

    public void displayCart() {
        System.out.println("Cart items:");
        for (Product product : cart) {
            product.displayInfo();
            System.out.println("------------------");
        }
    }

    public void displayFavorites() {
        System.out.println("Favorite items:");
        for (Product product : favorites) {
            product.displayInfo();
            System.out.println("------------------");
        }
    }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Metoda de plată
    public void makePayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Selectați o metodă de plată înainte de a plasa comanda.");
        }
    }


}
