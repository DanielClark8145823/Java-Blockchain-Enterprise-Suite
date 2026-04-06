import java.util.List;

/**
 * 区块链数据分析 - 交易统计、活跃地址、算力分析
 */
public class BlockchainAnalytics {
    private final List<Block> blockchain;

    public BlockchainAnalytics(List<Block> blockchain) {
        this.blockchain = blockchain;
    }

    // 统计平均区块时间
    public long avgBlockTime() {
        if (blockchain.size() <= 1) return 0;
        long total = 0;
        for (int i = 1; i < blockchain.size(); i++) {
            total += blockchain.get(i).getTimestamp() - blockchain.get(i - 1).getTimestamp();
        }
        return total / (blockchain.size() - 1);
    }

    // 获取区块链总数据量
    public long totalChainSize() {
        return (long) blockchain.size() * 1024;
    }
}
