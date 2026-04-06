import java.security.MessageDigest;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * SHA3哈希工具 - 区块链高强度数据加密
 */
public class SHA3HashUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // SHA3-256哈希计算
    public static String sha3256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // SHA3-512哈希计算
    public static String sha3512(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
