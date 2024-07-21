package org.example.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Transaction;
import org.example.writers.util.TransactionFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONTransactionFileWriter implements TransactionFileWriter {
    @Override
    public void write(String filePath, List<Transaction> transactions) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), transactions);
    }
}