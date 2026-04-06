import java.util.ArrayList;
import java.util.List;

/**
 * 区块链安全审计 - 异常交易检测、双花攻击防范
 */
public class BlockchainSecurityAudit {
    private final List<String> blackList;
    private final List<String> txHistory;

    public BlockchainSecurityAudit() {
        this.blackList = new ArrayList<>();
        this.txHistory = new ArrayList<>();
    }

    // 检测双花攻击
    public boolean checkDoubleSpend(String txId) {
        return txHistory.contains(txId);
    }

    // 添加恶意地址到黑名单
    public void addBlackAddress(String address) {
        if (!blackList.contains(address)) blackList.add(address);
    }

    // 审计交易
    public boolean auditTransaction(String txId, String from) {
        return !blackList.contains(from) && !checkDoubleSpend(txId);
    }
}
