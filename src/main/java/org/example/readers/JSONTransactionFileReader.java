package org.example.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Transaction;
import org.example.readers.util.TransactionFileReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONTransactionFileReader implements TransactionFileReader {
    @Override
    public List<Transaction> read(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Transaction[] transactions = mapper.readValue(new File(filePath), Transaction[].class);
        return Arrays.asList(transactions);
    }
}
