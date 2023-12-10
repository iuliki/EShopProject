package main.java.products;

public class Product {
    private String name;
    private String category;
    private int stock;
    private double price;


    public Product(String name, String category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }


    public void displayInfo() {
        System.out.println("Product: " + name);
        System.out.println("Category: " + category);
        System.out.println("Stock: " + stock);
        System.out.println("Price: " + price);
    }


    public String getName() {
        return name;
    }


    public void updateInfo(String newName, String newCategory, int newStock, double newPrice) {
        this.name = newName;
        this.category = newCategory;
        this.stock = newStock;
        this.price = newPrice;
        System.out.println("Product information updated.");
    }
    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
    public void buy(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
            System.out.println("Purchase successful. Remaining stock: " + stock);
        } else {
            System.out.println("Not enough stock available.");
        }
    }

    public void setStock(int newStock) {
        this.stock = newStock;
    }

    public Product getProduct() {
        return this;
    }


    public int getQuantity() {
        return stock;
    }
}
