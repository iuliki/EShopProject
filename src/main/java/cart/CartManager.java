package main.java.cart;
import main.java.commands.Command;
import main.java.mediator.OrderMediator;
import main.java.products.Product;
import main.java.commands.AddToCartCommand;

//in clasa asta vom face tot ce tine de de operatiunile de cumparaturi
//folosim singleton

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartManager  {
    // Membru static pentru instanța unică
    private static CartManager instance;

    // Lista de produse în coș
    private List<Product> cartItems;
    private List<Command> commandHistory;
    private OrderMediator mediator;

    // Constructor privat pentru a preveni instantierea directă
    public CartManager() {
        cartItems = new ArrayList<>();
        commandHistory = new ArrayList<>();
        this.mediator = mediator;
    }

    // Metodă pentru a obține instanța unică
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }



    public static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    // Metodă pentru adăugarea unui produs în coș
    public void addToCart(Product product, int quantity) {
        // Verifică dacă cantitatea introdusă este mai mică sau egală cu stocul disponibil
        if (quantity > product.getStock()) {
            System.out.println("Cantitatea introdusă este mai mare decât stocul disponibil.");
            displayOptions();
            return;
        }

        // Adaugă produsul în coș
        cartItems.add(product);

        // Actualizează stocul produsului după adăugare în coș
        product.setStock(product.getStock() - quantity);

        // Afișează opțiunile după adăugarea produsului în coș
        displayOptions();
    }
    // Alte metode pentru gestionarea coșului de cumpărături
    // ...
    public void displayCart() {
        System.out.println("Cart items:");
        double totalCartPrice = 0;

        for (Product product : cartItems) {
            int quantity = getProductQuantity(product);

            // Restul codului pentru afișarea informațiilor
            product.displayInfo();
            System.out.println("Quantity: " + quantity);
            System.out.println("------------------");

            // Calculăm totalul pentru fiecare produs în coș
            totalCartPrice += product.getPrice() * quantity;
        }

        // Afișăm totalul coșului
        System.out.println("Total cart price: " + totalCartPrice);
    }


    // Metodă pentru finalizarea comenzii
    public void completeOrder() {
        System.out.println("Order completed. Thank you for shopping!");
        cartItems.clear();  // Curățare coș după finalizarea comenzii
    }
    public void displayTotalPrice() {
        double totalPrice = calculateTotalPrice();
        System.out.println("Pret total: " + totalPrice);
    }
    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    private void displayOptions() {
        System.out.println("Selectați o opțiune:");
        System.out.println("1. Adăugare alt produs în coș");
        System.out.println("2. Vezi coșul");
        System.out.println("3. Finalizează comanda");

        Scanner scanner = new Scanner(System.in);
        int option;

        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Opțiune invalidă. Încercați din nou.");
            displayOptions();
            return;
        }

        switch (option) {
            case 1:
                // Adăugare alt produs în coș
                break;
            case 2:
                // Vezi coșul
                displayCart();
                break;
            case 3:
                // Finalizează comanda
                completeOrder();
                break;
            default:
                System.out.println("Opțiune invalidă. Încercați din nou.");
                displayOptions();
        }
    }
    public void displayTotalCartPrice() {
        double totalCartPrice = calculateTotalCartPrice();
        System.out.println("Total cart price: " + totalCartPrice);
    }

    // Metodă pentru a calcula totalul coșului
    private double calculateTotalCartPrice() {
        double totalCartPrice = 0;

        for (Product cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Calculăm totalul pentru fiecare produs în coș
            totalCartPrice += product.getPrice() * quantity;
        }

        return totalCartPrice;
    }
    public void placeOrder(Product product, int quantity) {
        mediator.placeOrder(this, product, quantity);
        displayCart();  // Afisam coșul după plasarea comenzii
    }
    private int getProductQuantity(Product product) {
        int quantity = 0;

        for (Product cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                quantity = cartItem.getQuantity();
                break;
            }
        }

        return quantity;
    }
    public void addCommandToHistory(Command command) {
        commandHistory.add(command);
    }
}
