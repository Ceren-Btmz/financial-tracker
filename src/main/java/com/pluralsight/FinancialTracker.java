package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FinancialTracker {

    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }

    public static void loadTransactions(String fileName) {
        // This method helps load all transactions within the transactions.csv file.
        // This displays each transaction with the date, time, description, vendor and amount separated by a barrier
        try {
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("new file created");
            } else {
                System.out.println("file already exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);

                transactions.add(transaction);
            }
            reader.close();

        } catch (IOException ex) {
            System.out.println("Error reading file " + fileName);
        }
    }

    private static void addDeposit(Scanner scanner) {
        // Collects date, time, description, vendor, and amount while storing this information in the transactions.csv file.
        System.out.println("Enter the date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        LocalDate formattedDate = LocalDate.parse(date, DATE_FORMATTER);

        System.out.println("Enter the time (HH:mm:ss): ");
        String time = scanner.nextLine();
        LocalTime formattedTime = LocalTime.parse(time, TIME_FORMATTER);

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the deposit amount (Must Be POSITIVE) : $ ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();
        if (deposit < 0) {
            System.out.println("ERROR: Invalid Input! Please try again!");
        }

        try {
            Transaction newDeposit = new Transaction(formattedDate, formattedTime, description, vendor, deposit);
            transactions.add(newDeposit);

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            String outputLine = newDeposit.getDate() + "|" + newDeposit.getTime() + "|" + newDeposit.getDescription() + "|" + newDeposit.getVendor() + "|" + newDeposit.getAmount();
            writer.write("\n" + outputLine);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error! Something went wrong!");
        }
    }

    private static void addPayment(Scanner scanner) {
        // Collects date, time, description, vendor, and amount while storing this information in the transactions.csv file.
        // The amount received will be stored as a negative here because it is a payment.
        System.out.println("Enter the date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        LocalDate formattedDate = LocalDate.parse(date, DATE_FORMATTER);

        System.out.println("Enter the time (HH:mm:ss): ");
        String time = scanner.nextLine();
        LocalTime formattedTime = LocalTime.parse(time, TIME_FORMATTER);

        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the payment amount (Must Be POSITIVE) : $ ");
        double payment = scanner.nextDouble();
        scanner.nextLine();
        if (payment < 0) {
            System.out.println("ERROR: Invalid Input! Please try again!");
        }
        payment = payment * -1;

        try {
            Transaction newPayment = new Transaction(formattedDate, formattedTime, description, vendor, payment);
            transactions.add(newPayment);

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            String outputLine = newPayment.getDate() + "|" + newPayment.getTime() + "|" + newPayment.getDescription() + "|" + newPayment.getVendor() + "|" +  newPayment.getAmount();
            writer.write("\n" + outputLine);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error! Something went wrong!");
        }
    }


    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayLedger() {
        // displays all transactions
        System.out.println("All Transactions: ");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }

    private static void displayDeposits() {
        // displays all transactions that are deposits.
        System.out.println("All Deposits: ");

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() >= 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void displayPayments() {
        // displays all transactions that are payments (negative values)
        System.out.println("All Payments: ");

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() <= 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Month to date search here
                    filterTransactionsByDate(LocalDate.now().withDayOfMonth(1), LocalDate.now());
                    break;
                case "2":
                    filterTransactionsByDate(LocalDate.now().withDayOfMonth(1).minusMonths(1), LocalDate.now().withDayOfMonth(1).minusDays(1));
                    break;
                case "3":
                    filterTransactionsByDate(LocalDate.now().withDayOfYear(1), LocalDate.now());
                    break;
                case "4":
                    filterTransactionsByDate(LocalDate.now().minusYears(1).withDayOfYear(1), LocalDate.now().minusYears(1).withMonth(12).withDayOfMonth(31));
                    break;
                case "5":
                    filterTransactionsByVendor();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
        // filters transactions by given date limitations: month to date, previous month, year to date, and previous year
           for (Transaction transaction : transactions) {
               if (transaction.getDate().isAfter(startDate.minusDays(1)) && transaction.getDate().isBefore(endDate.plusDays(1))) {
                   System.out.println(transaction);
                   return;
               }
           }
           System.out.println("There are no transactions in this timeline!");
    }
    private static void filterTransactionsByVendor() {
        // takes vendor information from user, to cross-reference and display any transactions under that vendor
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vendor: ");
        String vendorName = scanner.nextLine();

        try {
            for (Transaction transaction : transactions) {
                if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                    System.out.println(transaction);
                    return;
                }
            }
            System.out.println("There are no transactions under this vendor!");
        } catch (Exception ex) {
            System.out.println("Something went wrong! Please try again.");
        }
    }
}