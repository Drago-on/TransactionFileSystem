package org.example.readers;

import org.example.Transaction;
import org.example.TransactionListWrapper;
import org.example.readers.util.TransactionFileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLTransactionFileReader implements TransactionFileReader {
    @Override
    public List<Transaction> read(String filePath) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TransactionListWrapper wrapper = (TransactionListWrapper) unmarshaller.unmarshal(new File(filePath));
            return wrapper.getTransactions();
        } catch (JAXBException e) {
            throw new IOException("Error reading XML file", e);
        }
    }
}