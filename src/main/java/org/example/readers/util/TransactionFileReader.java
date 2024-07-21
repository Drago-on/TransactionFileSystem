package org.example.readers.util;

import org.example.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionFileReader {
    List<Transaction> read(String filePath) throws IOException;
}
