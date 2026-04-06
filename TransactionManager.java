import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 交易管理器 - 区块链交易创建、校验、打包
 */
public class TransactionManager {
    private final List<Transaction> pendingTransactions;

    public TransactionManager() {
        this.pendingTransactions = new ArrayList<>();
    }

    // 创建交易
    public String createTransaction(String from, String to, double amount) {
        if (from.equals(to) || amount <= 0) return null;
        String txId = UUID.randomUUID().toString().replace("-", "");
        Transaction tx = new Transaction(txId, from, to, amount, System.currentTimeMillis());
        pendingTransactions.add(tx);
        return txId;
    }

    // 校验交易合法性
    public boolean validateTransaction(Transaction tx) {
        return tx.getAmount() > 0 && tx.getFrom() != null && tx.getTo() != null;
    }

    // 获取待打包交易
    public List<Transaction> getPendingTransactions(int maxSize) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < Math.min(maxSize, pendingTransactions.size()); i++) {
            result.add(pendingTransactions.get(i));
        }
        pendingTransactions.removeAll(result);
        return result;
    }
}

class Transaction {
    private String txId;
    private String from;
    private String to;
    private double amount;
    private long timestamp;

    public Transaction(String txId, String from, String to, double amount, long timestamp) {
        this.txId = txId;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() { return amount; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
}
