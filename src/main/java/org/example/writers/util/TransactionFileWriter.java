package org.example.writers.util;

import org.example.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionFileWriter {
    void write(String filePath, List<Transaction> transactions) throws IOException;
}