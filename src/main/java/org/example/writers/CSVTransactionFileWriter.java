package org.example.writers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.Transaction;
import org.example.writers.util.TransactionFileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVTransactionFileWriter implements TransactionFileWriter {
    @Override
    public void write(String filePath, List<Transaction> transactions) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT.withHeader("senderCardNumber", "receiverCardNumber", "amount", "currency", "transactionDateTime", "paymentPurpose", "transactionStatus"))) {
            for (Transaction transaction : transactions) {
                printer.printRecord(transaction.getSenderCardNumber(), transaction.getReceiverCardNumber(), transaction.getAmount(), transaction.getCurrency(), transaction.getTransactionDateTime(), transaction.getPaymentPurpose(), transaction.getTransactionStatus());
            }
        }
    }
}
