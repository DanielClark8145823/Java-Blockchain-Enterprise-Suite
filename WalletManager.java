import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 区块链钱包管理器 - 账户创建、密钥存储、余额查询
 */
public class WalletManager {
    private final Map<String, Wallet> wallets;
    private static final String EC_ALGORITHM = "EC";

    public WalletManager() {
        this.wallets = new HashMap<>();
    }

    // 创建新钱包
    public String createWallet() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(EC_ALGORITHM);
        keyGen.initialize(256);
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey pub = pair.getPublic();
        PrivateKey pri = pair.getPrivate();
        String address = Base64.getEncoder().encodeToString(pub.getEncoded()).substring(0, 32);
        wallets.put(address, new Wallet(pub, pri, 0.0));
        return address;
    }

    // 更新账户余额
    public void updateBalance(String address, double amount) {
        if (wallets.containsKey(address)) {
            wallets.get(address).setBalance(wallets.get(address).getBalance() + amount);
        }
    }

    public double getBalance(String address) {
        return wallets.getOrDefault(address, new Wallet(null, null, 0.0)).getBalance();
    }
}

class Wallet {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private double balance;

    public Wallet(PublicKey publicKey, PrivateKey privateKey, double balance) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
