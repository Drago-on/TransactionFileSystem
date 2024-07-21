package org.example.writers;

import org.example.Transaction;
import org.example.TransactionListWrapper;
import org.example.writers.util.TransactionFileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLTransactionFileWriter implements TransactionFileWriter {
    @Override
    public void write(String filePath, List<Transaction> transactions) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            TransactionListWrapper wrapper = new TransactionListWrapper();
            wrapper.setTransactions(transactions);
            marshaller.marshal(wrapper, new File(filePath));
        } catch (JAXBException e) {
            throw new IOException("Error writing XML file", e);
        }
    }
}
