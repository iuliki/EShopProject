package main.java;

import main.java.handlers.ClientHandler;
import main.java.handlers.SellerHandler;
import main.java.handlers.UserHandlerStrategy;
import main.java.products.ProductStock;
import main.java.users.Client;
import main.java.users.Seller;
import main.java.users.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserHandlerStrategy handlerStrategy = null;
        ProductStock productStock = new ProductStock();

        while (true) {
            System.out.println("Bine ați venit! Selectați tipul de utilizator:");
            System.out.println("1. Client");
            System.out.println("2. Vânzător");
            System.out.println("3. Exit");
            System.out.print("Alegeți opțiunea: ");


            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    handlerStrategy = new ClientHandler(productStock);
                    break;
                case 2:
                    handlerStrategy = new SellerHandler(productStock);
                    break;
                case 3:
                    System.out.println("La revedere!");
                    return;  // Ieșire din program
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
                    break;
            }


            // Introducerea informațiilor comune pentru toți utilizatorii
            System.out.print("Introduceți numele utilizatorului: ");
            scanner.nextLine(); // Consumă linia anterioară
            String name = scanner.nextLine();

            System.out.print("Introduceți adresa de email a utilizatorului: ");
            String email = scanner.nextLine();

            User user;
            if (option == 1) {
                user = new Client(name, email);
            } else {
                user = new Seller(name, email);
            }

            // Gestionarea utilizatorului folosind strategia corespunzătoare
            handlerStrategy.handleUser(user);

            // Afișare finală
            System.out.println("Operațiune finalizată.");

            Client client = new Client("NumeClient", "email@client.com");

        }
    }
}
