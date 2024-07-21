package org.example.readers;

import org.example.Transaction;
import org.example.TransactionListWrapper;
import org.example.readers.util.TransactionFileReader;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class YAMLTransactionFileReader implements TransactionFileReader {
    @Override
    public List<Transaction> read(String filePath) throws IOException {
        Yaml yaml = new Yaml();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            return yaml.loadAs(inputStream, TransactionListWrapper.class).getTransactions();
        }
    }
}
