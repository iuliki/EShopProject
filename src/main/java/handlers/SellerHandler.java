package main.java.handlers;

import main.java.cart.CartManager;
import main.java.products.Product;
import main.java.products.ProductStock;
import main.java.users.Seller;
import main.java.users.User;

import java.util.Scanner;

public class SellerHandler implements UserHandlerStrategy {
    private ProductStock productStock;
    CartManager cartManager = CartManager.getInstance();

    public SellerHandler(ProductStock productStock) {
        this.productStock = productStock;
    }

    @Override
    public void handleUser(User user) {
        Seller seller = (Seller) user;
        seller.displayInfo();

        Scanner scanner = new Scanner(System.in);

        // Gestionarea operațiunilor unui vânzător
        while (true) {
            System.out.println("Selectați o operațiune:");
            System.out.println("1. Adăugare Produse");
            System.out.println("2. Ștergere Produse");
            System.out.println("3. Configurare Promoții");
            System.out.println("4. Exit");

            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    addProducts(seller);
                    break;
                case 2:
                    deleteProducts(seller);
                    break;
                case 3:
                    configurePromotions(seller);
                    break;
                case 4:
                    System.out.println("Ați ieșit ca și vânzător.");
                    return;
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
    }

    private void addProducts(Seller seller) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Introduceți numele produsului (sau \"exit\" pentru a ieși):");

            // Consumă newline-ul rămas
            scanner.nextLine();

            String productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Introduceți categoria produsului: ");
            String productCategory = scanner.nextLine();
            System.out.print("Introduceți stocul produsului: ");
            int productStockValue = scanner.nextInt();
            System.out.print("Introduceți prețul produsului: ");
            double productPrice = scanner.nextDouble();

            Product newProduct = new Product(productName, productCategory, productStockValue, productPrice);
            seller.addProduct(productName, productCategory, productStockValue, productPrice);
            productStock.addProduct(newProduct);

            System.out.println("Produs adăugat: " + newProduct.getName());
        }
    }

    private void deleteProducts(Seller seller) {
        // Implementează ștergerea produselor
        System.out.println("Funcționalitatea de ștergere a produselor va fi implementată aici.");
    }

    private void configurePromotions(Seller seller) {
        // Implementează configurarea promoțiilor
        System.out.println("Funcționalitatea de configurare a promoțiilor va fi implementată aici.");
    }
}
