package ir.maktab58.view;

import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.models.Owner;
import ir.maktab58.models.factory.Account;
import ir.maktab58.service.BankService;

import java.util.List;
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
        boolean exit = false;
        while (!exit) {
            List<Account> ownerAccounts = bankService.getOwnerAccounts(id);
            if (ownerAccounts.size() == 0) {
                exit = showOwnerMenuWhenNoAccountIsAdded(id);
                continue;
            }
            ownerAccounts.forEach(System.out::println);
            System.out.println("***** OwnerMenu *****\n" +
                    "1) open an account\n" +
                    "2) choose an account\n" +
                    "3) exit from owner panel");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> openAnAccount(id);
                    case "2" -> chooseAnAccount(id);
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

    private void chooseAnAccount(int ownerId) {
        System.out.println("please enter your account number: ");
        String accountNumberStr = scanner.nextLine().trim();
        long accountNumber = Long.parseLong(accountNumberStr);
        int accountId = bankService.checkAccountExisted(accountNumber, ownerId);
        if (accountId == 0) {
            throw BankSysException.builder()
                    .message("the entered account number is wrong")
                    .errorCode(400).build();
        } else {
            showOwnerChoices(ownerId, accountId);
        }
    }

    private void showOwnerChoices(int ownerId, int accountId) {
        boolean back = false;
        while (!back) {
            System.out.println("***** Owner Choices *****\n" +
                    "1) update user or pass\n" +
                    "2) withdraw\n" +
                    "3) deposit\n" +
                    "4) card to card\n" +
                    "5) back");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> updateUserInfo(ownerId);
                    case "2" -> withdraw(accountId);
                    case "3" -> deposit(accountId);
                    case "4" -> cardToCard(accountId);
                    case "5" -> back = true;
                    default -> throw BankSysException.builder()
                            .message("Choice must be an integer between 1 to 5.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cardToCard(int accountId) {
        System.out.println("please enter the amount of transaction, cardNumber of receiver account: ");
        String inputLine = scanner.nextLine().trim();
        String[] tokens = inputLine.split(" ");
        String dischargeStr = tokens[0];
        long destCardNumber = Long.parseLong(tokens[1]);
        long discharge = Long.parseLong(dischargeStr);
        Owner cardNumberOwnerInfo = bankService.getCardNumberOwnerInfo(destCardNumber);
        System.out.println("des card Info: \n" +
                "owner: " + cardNumberOwnerInfo.getName() + " " + cardNumberOwnerInfo.getFamily() + "\n" +
                "card number: " + destCardNumber);
        completeCardToCardProcess(accountId, destCardNumber, discharge);
    }

    private void completeCardToCardProcess(int accountId, long destCardNumber, long discharge) {
        System.out.println("Do you want to continue?");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("yes")) {
            int result = bankService.cardToCardTransaction(accountId, discharge, destCardNumber);
            if (result == 0)
                System.out.println("please try again!");
            else
                System.out.println("cardToCard transaction has happened successfully!");
            return;
        }
        if (answer.equals("no")) {
            return;
        }
        throw BankSysException.builder()
                .message("your answer must be yes or no.")
                .errorCode(400).build();
    }

    private void updateUserInfo(int ownerId) {
        System.out.println("Do you like to update your user or pass?\n" +
                "1) username\n" +
                "2) password");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            System.out.println("please enter new username: ");
            String username = scanner.nextLine().trim();
            bankService.updateUserUserName(username, ownerId);
            System.out.println("your username is updated successfully.");
            return;
        }
        if (choice.equals("2")) {
            System.out.println("please enter new password: ");
            String password = scanner.nextLine().trim();
            bankService.updateUserPassword(password, ownerId);
            System.out.println("your password is updated successfully.");
            return;
        }
        throw BankSysException.builder()
                .message("your choice must be an integer between 1 and 2")
                .errorCode(400).build();
    }

    private void withdraw(int accountId) {
        System.out.println("please enter the amount of discharge: ");
        String dischargeStr = scanner.nextLine().trim();
        long discharge = Long.parseLong(dischargeStr);
        int result = bankService.withdrawTransaction(accountId, discharge);
        if (result == 0)
            System.out.println("please try again!");
        else
            System.out.println("withdraw transaction has happened successfully!");
    }

    private void deposit(int accountId) {
        System.out.println("please enter the amount of charge: ");
        String chargeStr = scanner.nextLine().trim();
        long charge = Long.parseLong(chargeStr);
        int result = bankService.depositTransaction(accountId, charge);
        if (result == 0)
            System.out.println("please try again!");
        else
            System.out.println("deposit transaction has happened successfully!");
    }

    private boolean showOwnerMenuWhenNoAccountIsAdded(int id) {
        boolean exit = false;
        System.out.println("There is no account to show...");
        System.out.println("***** OwnerMenu *****\n" +
                "1) open an account\n" +
                "2) exit from owner panel");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
                case "1" -> openAnAccount(id);
                case "2" -> exit = true;
                default -> throw BankSysException.builder()
                        .message("Choice must be an integer between 1 to 2.")
                        .errorCode(400).build();
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return exit;
    }

    private void openAnAccount(int id) {
        System.out.println("please enter type of account that you want to open, and also initial balance: ");
        String inputLine = scanner.nextLine().trim();
        int accountId = bankService.openNewAccountForThisOwner(id, inputLine);
        if (accountId != 0)
            System.out.println("your account has been created successfully.");
        else
            System.out.println("something went wrong. please try again.");
    }
}
