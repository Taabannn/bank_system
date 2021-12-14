package ir.maktab58.view;

import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.service.BankService;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class BankSys {
    private BankService bankService = new BankService();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("***** Welcome to bank System *****\n" +
                    "1) login\n" +
                    "2) signup\n" +
                    "3) exit");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> login();
                    case "2" -> signup();
                    case "3" -> exit = true;
                    default -> throw BankSysException.builder()
                            .message("Choice must be an integer between 1 to 3.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void signup() {
        System.out.println("Please enter your name, family, national code, username, password.");
        String inputLine = scanner.nextLine().trim();
        int ownerId = bankService.registerOwnerInBank(inputLine);
        if (ownerId != 0) {
            System.out.println("You've registered successfully.");
            showOwnerMenu(ownerId);
        }
    }

    private void login() {
        System.out.println("Please enter your username and password.");
        String inputLine = scanner.nextLine().trim();
        int ownerId = bankService.checkOwnerIsExisted(inputLine);
        if (ownerId != 0) {
            System.out.println("You've logged in successfully!");
            showOwnerMenu(ownerId);
        } else {
            throw BankSysException.builder()
                    .message("Invalid username or password.")
                    .errorCode(400).build();
        }
    }

    private void showOwnerMenu(int id) {
        /*boolean exit = false;
        while (!exit) {
            System.out.println("***** Welcome *****\n" +
                    "1) login\n" +
                    "2) signup\n" +
                    "3) exit");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> //login();
                    case "2" -> signup();
                    case "3" -> exit = true;
                    default -> throw BankSysException.builder()
                            .message("Choice must be an integer between 1 to 3.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }*/
    }
}
