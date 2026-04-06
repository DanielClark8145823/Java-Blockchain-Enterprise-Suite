import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 分布式文件存储适配器 - 区块链+IPFS文件上链、哈希映射
 */
public class IPFSDocumentStorage {
    private final Map<String, String> fileHashMap;
    private final Map<String, Long> fileSizeMap;

    public IPFSDocumentStorage() {
        this.fileHashMap = new HashMap<>();
        this.fileSizeMap = new HashMap<>();
    }

    // 上传文件到IPFS并返回哈希
    public String uploadFile(String fileName, long fileSize) {
        String ipfsHash = "Qm" + UUID.randomUUID().toString().replace("-", "").substring(0, 44);
        fileHashMap.put(fileName, ipfsHash);
        fileSizeMap.put(ipfsHash, fileSize);
        return ipfsHash;
    }

    // 根据文件名查询IPFS哈希
    public String getFileHash(String fileName) {
        return fileHashMap.getOrDefault(fileName, null);
    }

    // 根据哈希查询文件大小
    public long getFileSize(String ipfsHash) {
        return fileSizeMap.getOrDefault(ipfsHash, 0L);
    }
}
