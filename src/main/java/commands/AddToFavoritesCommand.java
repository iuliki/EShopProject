package main.java.commands;
//command

import main.java.users.Client;
import main.java.products.Product;

public class AddToFavoritesCommand implements Command {
    private Client client;
    private Product product;

    public AddToFavoritesCommand(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    @Override
    public void execute() {
        client.addToFavorites(product);
    }
}
