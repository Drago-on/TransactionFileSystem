package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "transactions")
public class TransactionListWrapper {
    private List<Transaction> transactions;

    public TransactionListWrapper() {
    }

    public TransactionListWrapper(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @XmlElement(name = "transaction")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
