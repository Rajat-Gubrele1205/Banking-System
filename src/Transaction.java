import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class Transaction {
    private String transactionType;
    private double amount;
    private LocalDate timestamp;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDate.now();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }
}