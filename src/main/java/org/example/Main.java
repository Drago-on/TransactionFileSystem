package org.example;

import org.example.readers.util.TransactionFileReader;
import org.example.readers.util.TransactionFileReaderFactory;
import org.example.writers.util.TransactionFileWriter;
import org.example.writers.util.TransactionFileWriterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filePath = null;
        boolean fileExists = false;

        while (!fileExists) {
            System.out.println("Enter path to transactions:");
            filePath = scanner.nextLine();
            fileExists = Files.exists(Paths.get(filePath));
            if (!fileExists) {
                System.out.println("File could not be found. Wanna create new? (yes/no):");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    List<Transaction> transactions = new ArrayList<>();
                    transactions.add(createTransaction(scanner));
                    System.out.println("Enter path for a new file:");
                    String savePath = scanner.nextLine();
                    saveTransactions(savePath, transactions);
                    System.out.println("Success!");
                    return;
                }
            }
        }

        try {
            TransactionFileReader fileReader = TransactionFileReaderFactory.getFileReader(filePath);

            List<Transaction> transactions = fileReader.read(filePath);

            printTransactions(transactions);

            editTransactions(scanner, transactions);

            System.out.println("Enter path to save the file:");
            String savePath = scanner.nextLine();
            TransactionFileWriter fileWriter = TransactionFileWriterFactory.getFileWriter(savePath);
            fileWriter.write(savePath, transactions);

            System.out.println("Saved successfully");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void printTransactions(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    private static void editTransactions(Scanner scanner, List<Transaction> transactions) {
        while (true) {
            System.out.println("Enter the transaction number to edit (or 0 to exit):");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index == -1) {
                break;
            }

            if (index >= 0 && index < transactions.size()) {
                Transaction transaction = transactions.get(index);
                System.out.println("Editing transaction: " + transaction);

                System.out.println("Enter new sender card nubmer (or leave it blank so save the previous one):");
                String senderCardNumber = scanner.nextLine();
                if (!senderCardNumber.isEmpty()) {
                    transaction.setSenderCardNumber(senderCardNumber);
                }

                System.out.println("Enter new receiver card number (or leave it blank so save the previous one):");
                String receiverCardNumber = scanner.nextLine();
                if (!receiverCardNumber.isEmpty()) {
                    transaction.setReceiverCardNumber(receiverCardNumber);
                }

                System.out.println("Enter new amount (or leave it blank so save the previous one):");
                String amount = scanner.nextLine();
                if (!amount.isEmpty()) {
                    try {
                        double newAmount = Double.parseDouble(amount);
                        if (newAmount < 0) {
                            System.out.println("Amount can't be negative. Try again");
                        } else {
                            transaction.setAmount(newAmount);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter valid data");
                    }
                }

                System.out.println("Enter new currency (or leave it blank so save the previous one):");
                String currency = scanner.nextLine();
                if (!currency.isEmpty()) {
                    transaction.setCurrency(currency);
                }

                System.out.println("Enter new payment purpose (or leave it blank so save the previous one):");
                String paymentPurpose = scanner.nextLine();
                if (!paymentPurpose.isEmpty()) {
                    transaction.setPaymentPurpose(paymentPurpose);
                }

                System.out.println("Enter new payment status (or leave it blank so save the previous one):");
                String transactionStatus = scanner.nextLine();
                if (!transactionStatus.isEmpty()) {
                    transaction.setTransactionStatus(transactionStatus);
                }

                if (transaction.getSenderCardNumber().equals(transaction.getReceiverCardNumber())) {
                    System.out.println("Both card numbers. Check your input.");
                } else {
                    System.out.println("Update Successfully completed.");
                }
            } else {
                System.out.println("Wring transaction number. Try again.");
            }
        }
    }

    private static Transaction createTransaction(Scanner scanner) {
        Transaction transaction = new Transaction();
        System.out.println("Creating a transaction");

        System.out.println("Card number of sender:");
        String senderCardNumber = scanner.nextLine();
        transaction.setSenderCardNumber(senderCardNumber);

        String receiverCardNumber;
        do {
            System.out.println("Card number of receiver:");
            receiverCardNumber = scanner.nextLine();
            if (receiverCardNumber.equals(senderCardNumber)) {
                System.out.println("Card numbers are the same. Try again.");
            }
        } while (receiverCardNumber.equals(senderCardNumber));
        transaction.setReceiverCardNumber(receiverCardNumber);

        double amount;
        while (true) {
            System.out.println("Amount of money:");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Your amount is negative. Try again");
                } else {
                    transaction.setAmount(amount);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter correct data.");
            }
        }

        System.out.println("Currency:");
        transaction.setCurrency(scanner.nextLine());

        transaction.setTransactionDateTime(LocalDateTime.now());

        System.out.println("Payment purpose:");
        transaction.setPaymentPurpose(scanner.nextLine());

        System.out.println("Payment status:");
        transaction.setTransactionStatus(scanner.nextLine());

        return transaction;
    }

    private static void saveTransactions(String filePath, List<Transaction> transactions) {
        try {
            TransactionFileWriter fileWriter = TransactionFileWriterFactory.getFileWriter(filePath);
            fileWriter.write(filePath, transactions);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}