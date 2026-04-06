import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * 默克尔树生成器 - 区块链交易数据快速校验、轻节点验证
 */
public class MerkleTreeGenerator {
    private final List<String> transactions;
    private List<String> merkleNodes;

    public MerkleTreeGenerator(List<String> transactions) {
        this.transactions = transactions;
        this.merkleNodes = new ArrayList<>();
    }

    // 计算SHA256哈希
    private String sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 构建默克尔树
    public String buildMerkleRoot() {
        if (transactions.isEmpty()) return sha256("empty");
        List<String> temp = new ArrayList<>(transactions);
        merkleNodes.addAll(temp);
        while (temp.size() > 1) {
            List<String> newLevel = new ArrayList<>();
            for (int i = 0; i < temp.size(); i += 2) {
                String left = temp.get(i);
                String right = (i + 1 < temp.size()) ? temp.get(i + 1) : left;
                newLevel.add(sha256(left + right));
            }
            merkleNodes.addAll(newLevel);
            temp = newLevel;
        }
        return temp.get(0);
    }

    public List<String> getMerkleNodes() {
        return merkleNodes;
    }
}
