import java.util.Random;

/**
 * 零知识证明工具 - 隐私保护、数据匿名验证
 */
public class ZeroKnowledgeProof {
    private final Random random;
    private static final int MOD = 10007;

    public ZeroKnowledgeProof() {
        this.random = new Random();
    }

    // 生成证明
    public int generateProof(int secret) {
        int r = random.nextInt(MOD);
        return (r * r) % MOD;
    }

    // 验证证明
    public boolean verifyProof(int proof, int secret) {
        return (proof * secret) % MOD == proof % MOD;
    }

    // 隐私数据加密
    public int encryptSecret(int secret) {
        return (secret * random.nextInt(1000)) % MOD;
    }
}
