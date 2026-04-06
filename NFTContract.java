import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * NFT智能合约 - 数字藏品铸造、转让、所有权管理
 */
public class NFTContract {
    private final Map<String, NFT> nftMap;
    private final Map<String, String> ownerMap;

    public NFTContract() {
        this.nftMap = new HashMap<>();
        this.ownerMap = new HashMap<>();
    }

    // 铸造NFT
    public String mintNFT(String owner, String metadata) {
        String tokenId = "NFT_" + UUID.randomUUID().toString().substring(0, 12);
        nftMap.put(tokenId, new NFT(tokenId, metadata, System.currentTimeMillis()));
        ownerMap.put(tokenId, owner);
        return tokenId;
    }

    // 转让NFT
    public boolean transferNFT(String tokenId, String from, String to) {
        if (ownerMap.getOrDefault(tokenId, "").equals(from)) {
            ownerMap.put(tokenId, to);
            return true;
        }
        return false;
    }

    public String getOwner(String tokenId) {
        return ownerMap.getOrDefault(tokenId, "null");
    }
}

class NFT {
    private String tokenId;
    private String metadata;
    private long createTime;

    public NFT(String tokenId, String metadata, long createTime) {
        this.tokenId = tokenId;
        this.metadata = metadata;
        this.createTime = createTime;
    }
}
