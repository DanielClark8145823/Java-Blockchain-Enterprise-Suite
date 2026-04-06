import java.util.*;

/**
 * 多签钱包 - 区块链资产多人共管、授权交易
 */
public class MultiSignatureWallet {
    private final List<String> owners;
    private final int requiredSigns;
    private final Map<String, Set<String>> txSignatures;

    public MultiSignatureWallet(List<String> owners, int requiredSigns) {
        this.owners = owners;
        this.requiredSigns = requiredSigns;
        this.txSignatures = new HashMap<>();
    }

    // 创建多签交易
    public String createMultiTx(String txData) {
        String txId = "MULTI_" + System.currentTimeMillis();
        txSignatures.put(txId, new HashSet<>());
        return txId;
    }

    // 签名交易
    public boolean signTx(String txId, String owner) {
        if (!owners.contains(owner) || !txSignatures.containsKey(txId)) return false;
        txSignatures.get(txId).add(owner);
        return true;
    }

    // 校验是否满足签名条件
    public boolean isTxApproved(String txId) {
        return txSignatures.getOrDefault(txId, new HashSet<>()).size() >= requiredSigns;
    }
}
