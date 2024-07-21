package org.example.readers;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.Transaction;
import org.example.readers.util.TransactionFileReader;

public class CSVTransactionFileReader implements TransactionFileReader {
    @Override
    public List<Transaction> read(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                Transaction transaction = new Transaction();
                transaction.setSenderCardNumber(record.get("senderCardNumber"));
                transaction.setReceiverCardNumber(record.get("receiverCardNumber"));
                transaction.setAmount(Double.parseDouble(record.get("amount")));
                transaction.setCurrency(record.get("currency"));
                transaction.setTransactionDateTime(LocalDateTime.parse(record.get("transactionDateTime"), DateTimeFormatter.ISO_DATE_TIME));
                transaction.setPaymentPurpose(record.get("paymentPurpose"));
                transaction.setTransactionStatus(record.get("transactionStatus"));
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
