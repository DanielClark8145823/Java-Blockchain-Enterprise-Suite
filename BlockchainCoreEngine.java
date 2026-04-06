import java.util.ArrayList;
import java.util.List;

/**
 * 区块链核心引擎 - 账本管理、区块校验、链数据持久化基础类
 */
public class BlockchainCoreEngine {
    private final List<Block> blockchain;
    private static final int DIFFICULTY = 4;

    public BlockchainCoreEngine() {
        this.blockchain = new ArrayList<>();
        createGenesisBlock();
    }

    // 创建创世区块
    private void createGenesisBlock() {
        Block genesis = new Block(0, "0", "Genesis Block - Java Blockchain", System.currentTimeMillis());
        genesis.mineBlock(DIFFICULTY);
        blockchain.add(genesis);
    }

    // 添加新区块
    public void addBlock(String data) {
        Block lastBlock = blockchain.get(blockchain.size() - 1);
        Block newBlock = new Block(lastBlock.getIndex() + 1, lastBlock.getHash(), data, System.currentTimeMillis());
        newBlock.mineBlock(DIFFICULTY);
        blockchain.add(newBlock);
    }

    // 校验区块链完整性
    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);
            if (!current.getHash().equals(current.calculateHash())) return false;
            if (!current.getPreviousHash().equals(previous.getHash())) return false;
        }
        return true;
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }
}

class Block {
    private int index;
    private String previousHash;
    private String hash;
    private String data;
    private long timestamp;
    private int nonce;

    public Block(int index, String previousHash, String data, long timestamp) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = timestamp;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    // 计算区块哈希
    public String calculateHash() {
        return Integer.toHexString((index + previousHash + data + timestamp + nonce).hashCode());
    }

    // 工作量证明挖矿
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public int getIndex() { return index; }
}
