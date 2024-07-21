package org.example;

import java.time.LocalDateTime;

public class Transaction {
    private String senderCardNumber;
    private String receiverCardNumber;
    private double amount;
    private String currency;
    private LocalDateTime transactionDateTime;
    private String paymentPurpose;
    private String transactionStatus;

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getReceiverCardNumber() {
        return receiverCardNumber;
    }

    public void setReceiverCardNumber(String receiverCardNumber) {
        this.receiverCardNumber = receiverCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderCardNumber='" + senderCardNumber + '\'' +
                ", receiverCardNumber='" + receiverCardNumber + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", transactionDateTime=" + transactionDateTime +
                ", paymentPurpose='" + paymentPurpose + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                '}';
    }
}