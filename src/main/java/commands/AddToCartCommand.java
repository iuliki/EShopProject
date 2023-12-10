package main.java.commands;

import main.java.users.Client;
import main.java.products.Product;

//command
public class AddToCartCommand implements Command {
    private Client client;
    private Product product;

    public AddToCartCommand(Product product, int quantity) {
        this.client = client;
        this.product = product;
    }

    @Override
    public void execute() {
        client.addToCart(product);
    }
}
