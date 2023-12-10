package main.java.mediator;

import main.java.cart.CartItem;
import main.java.cart.CartManager;
import main.java.commands.AddToCartCommand;
import main.java.products.Product;
import main.java.users.Client;

public class OrderMediator {
    public void placeOrder(CartManager cartManager, Product product, int quantity) {
        // Verificare stoc și plasare comandă
        if (product.getStock() >= quantity) {
            // Adăugăm produsul în coșul clientului
            cartManager.addToCart(product, quantity);

            // Actualizăm stocul produsului
            product.setStock(product.getStock() - quantity);

            // Adăugăm comanda în istoric
            AddToCartCommand addToCartCommand = new AddToCartCommand(product, quantity);
            cartManager.addCommandToHistory(addToCartCommand);

            System.out.println("Comandă plasată cu succes!");
        } else {
            System.out.println("Stoc insuficient pentru produsul ales.");
        }
    }

    // Alte metode pentru gestionarea procesului de plată, etc.
}
