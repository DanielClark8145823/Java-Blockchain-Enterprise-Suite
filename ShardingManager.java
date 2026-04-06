import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 区块链分片管理器 - 水平扩容、交易分片处理
 */
public class ShardingManager {
    private final Map<String, Shard> shardMap;
    private static final int SHARD_COUNT = 4;

    public ShardingManager() {
        this.shardMap = new HashMap<>();
        initShards();
    }

    private void initShards() {
        for (int i = 0; i < SHARD_COUNT; i++) {
            String shardId = "SHARD_" + i;
            shardMap.put(shardId, new Shard(shardId, new HashMap<>()));
        }
    }

    // 分配交易到分片
    public String assignTransaction(String txId) {
        int hash = txId.hashCode() % SHARD_COUNT;
        hash = hash < 0 ? -hash : hash;
        return "SHARD_" + hash;
    }

    public Map<String, Shard> getShardMap() {
        return shardMap;
    }
}

class Shard {
    private String shardId;
    private Map<String, String> txMap;

    public Shard(String shardId, Map<String, String> txMap) {
        this.shardId = shardId;
        this.txMap = txMap;
    }
}
