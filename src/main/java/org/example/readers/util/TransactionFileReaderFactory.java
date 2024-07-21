package org.example.readers.util;

import org.example.readers.CSVTransactionFileReader;
import org.example.readers.JSONTransactionFileReader;
import org.example.readers.XMLTransactionFileReader;
import org.example.readers.YAMLTransactionFileReader;

import java.io.IOException;

public class TransactionFileReaderFactory {
    public static TransactionFileReader getFileReader(String filePath) throws IOException {
        if (filePath.endsWith(".csv")) {
            return new CSVTransactionFileReader();
        } else if (filePath.endsWith(".xml")) {
            return new XMLTransactionFileReader();
        } else if (filePath.endsWith(".json")) {
            return new JSONTransactionFileReader();
        } else if (filePath.endsWith(".yaml")) {
            return new YAMLTransactionFileReader();
        } else {
            throw new IOException("Unsupported file format");
        }
    }
}