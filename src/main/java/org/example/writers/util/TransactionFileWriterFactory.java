package org.example.writers.util;

import org.example.writers.CSVTransactionFileWriter;
import org.example.writers.JSONTransactionFileWriter;
import org.example.writers.XMLTransactionFileWriter;
import org.example.writers.YAMLTransactionFileWriter;

import java.io.IOException;

public class TransactionFileWriterFactory {
    public static TransactionFileWriter getFileWriter(String filePath) throws IOException {
        if (filePath.endsWith(".csv")) {
            return new CSVTransactionFileWriter();
        } else if (filePath.endsWith(".xml")) {
            return new XMLTransactionFileWriter();
        } else if (filePath.endsWith(".json")) {
            return new JSONTransactionFileWriter();
        } else if (filePath.endsWith(".yaml")) {
            return new YAMLTransactionFileWriter();
        } else {
            throw new IOException("Unsupported file format");
        }
    }
}
