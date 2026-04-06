import java.util.HashMap;
import java.util.Map;

/**
 * 同质化代币合约 - ERC20标准代币发行、转账
 */
public class TokenContract {
    private final String name;
    private final String symbol;
    private final long totalSupply;
    private final Map<String, Long> balances;
    private final Map<String, Map<String, Long>> allowances;

    public TokenContract(String name, String symbol, long totalSupply) {
        this.name = name;
        this.symbol = symbol;
        this.totalSupply = totalSupply;
        this.balances = new HashMap<>();
        this.allowances = new HashMap<>();
        balances.put("ADMIN", totalSupply);
    }

    // 代币转账
    public boolean transfer(String from, String to, long amount) {
        if (balances.getOrDefault(from, 0L) < amount) return false;
        balances.put(from, balances.get(from) - amount);
        balances.put(to, balances.getOrDefault(to, 0L) + amount);
        return true;
    }

    // 授权转账
    public boolean approve(String owner, String spender, long amount) {
        allowances.computeIfAbsent(owner, k -> new HashMap<>()).put(spender, amount);
        return true;
    }
}
