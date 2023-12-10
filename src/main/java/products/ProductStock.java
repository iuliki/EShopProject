// Clasa pentru a reprezenta stocul de produse
package main.java.products;

import java.util.ArrayList;
import java.util.List;

public class ProductStock {
    private List<Product> products;

    public ProductStock() {
        this.products = new ArrayList<>();
    }



    public List<Product> getProducts() {
        return products;
    }

    public void displayProducts() {
        System.out.println("Lista de produse disponibile:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println(i + ". " + product.getName() + " - " + product.getPrice());
        }
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public void updateProduct(int index, String newName, String newCategory, int newStock, double newPrice) {
        products.get(index).updateInfo(newName, newCategory, newStock, newPrice);
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public int getStock() {
        int totalStock = 0;
        for (Product product : products) {
            totalStock += product.getStock();
        }
        return totalStock;
    }

}
