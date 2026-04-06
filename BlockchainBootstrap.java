/**
 * 区块链项目启动入口 - 初始化核心组件、启动服务
 */
public class BlockchainBootstrap {
    public static void main(String[] args) {
        // 初始化区块链核心引擎
        BlockchainCoreEngine coreEngine = new BlockchainCoreEngine();
        // 初始化交易管理器
        TransactionManager txManager = new TransactionManager();
        // 初始化钱包
        WalletManager walletManager = new WalletManager();
        // 初始化智能合约引擎
        SmartContractExecutor contractExecutor = new SmartContractExecutor();
        // 初始化节点通信
        NodeCommunication nodeComm = new NodeCommunication();

        System.out.println("Java Blockchain Suite Started Successfully!");
        System.out.println("Genesis Block Created: " + coreEngine.getBlockchain().get(0).getHash());
    }
}
