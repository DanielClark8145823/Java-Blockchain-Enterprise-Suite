import java.util.List;

/**
 * 区块链监控服务 - 节点状态、区块高度、交易吞吐量监控
 */
public class BlockchainMonitor {
    private long lastBlockTime;
    private int blockHeight;
    private long txCount;

    public BlockchainMonitor() {
        this.lastBlockTime = System.currentTimeMillis();
        this.blockHeight = 0;
        this.txCount = 0;
    }

    // 更新区块高度
    public void updateBlockHeight(int newHeight) {
        if (newHeight > blockHeight) {
            this.lastBlockTime = System.currentTimeMillis();
            this.blockHeight = newHeight;
        }
    }

    // 统计交易数量
    public void addTransactionCount(int count) {
        this.txCount += count;
    }

    // 获取监控数据
    public String getMonitorInfo() {
        return "Height: " + blockHeight + ", TxTotal: " + txCount + ", LastBlock: " + (System.currentTimeMillis() - lastBlockTime) / 1000 + "s ago";
    }
}
