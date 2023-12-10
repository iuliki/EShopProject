package main.java.users;


import main.java.products.Product;
import main.java.products.ProductBuilder;
import main.java.products.ProductStock;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private String name;
    private String email;
    private List<Product> products;

    private ProductStock productStock;

    public Seller(String name, String email) {
        super(name, email);
        this.products = new ArrayList<>();
        this.productStock = new ProductStock();
    }


    public void register() {
        System.out.println("Seller registered: " + name);
    }

    public void addProduct(String name, String category, int stock, double price) {
        ProductBuilder productBuilder = new ProductBuilder();
        Product product = productBuilder
                .setName(name)
                .setCategory(category)
                .setStock(stock)
                .setPrice(price)
                .build();

        products.add(product);
        productStock.addProduct(product);
        System.out.println("Product added: " + product.getName());
    }

    public void deleteProduct(Product product) {
        products.remove(product);
        System.out.println("Product deleted: " + product.getName());
    }

    public void configurePromotion(Product product, double discountPercent, String timeInterval) {

        System.out.println("Promotion configured for product: " + product.getName());
    }

    public void updateProduct(Product product, String newName, String newCategory, int newStock, double newPrice) {
        product.updateInfo(newName, newCategory, newStock, newPrice);
        System.out.println("Product updated: " + product.getName());
    }
    public List<Product> getProducts() {
        return products;
    }
    public void displayProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + ". " + products.get(i).getName());
        }
    }

    public ProductStock getProductStock() {
        return productStock;
    }

    public boolean isStockAvailable(int requestedStock) {
        ProductStock stock = getProductStock();
        return stock != null && requestedStock <= stock.getStock();
    }




}
