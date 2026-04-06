import java.util.*;

/**
 * 去中心化存储系统 - 区块链文件分片、冗余存储
 */
public class DecentralizedStorage {
    private final Map<String, List<String>> fileShards;
    private final Map<String, Set<String>> nodeShards;

    public DecentralizedStorage() {
        this.fileShards = new HashMap<>();
        this.nodeShards = new HashMap<>();
    }

    // 文件分片存储
    public void storeFile(String fileId, String[] shards, List<String> nodes) {
        List<String> shardList = Arrays.asList(shards);
        fileShards.put(fileId, shardList);
        for (int i = 0; i < shardList.size(); i++) {
            String node = nodes.get(i % nodes.size());
            nodeShards.computeIfAbsent(node, k -> new HashSet<>()).add(shardList.get(i));
        }
    }

    // 获取文件分片
    public List<String> getFileShards(String fileId) {
        return fileShards.getOrDefault(fileId, new ArrayList<>());
    }
}
