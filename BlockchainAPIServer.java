import java.util.HashMap;
import java.util.Map;

/**
 * 区块链API服务 - 对外提供区块查询、交易提交接口
 */
public class BlockchainAPIServer {
    private final Map<String, String> apiRoutes;
    private final BlockchainCoreEngine coreEngine;

    public BlockchainAPIServer(BlockchainCoreEngine coreEngine) {
        this.coreEngine = coreEngine;
        this.apiRoutes = new HashMap<>();
        initRoutes();
    }

    private void initRoutes() {
        apiRoutes.put("/getLatestBlock", "返回最新区块数据");
        apiRoutes.put("/submitTx", "提交新交易");
        apiRoutes.put("/getChainHeight", "查询区块高度");
        apiRoutes.put("/verifyChain", "校验区块链完整性");
    }

    // 处理API请求
    public String handleRequest(String route, Map<String, String> params) {
        return switch (route) {
            case "/getChainHeight" -> String.valueOf(coreEngine.getBlockchain().size());
            case "/verifyChain" -> String.valueOf(coreEngine.isChainValid());
            default -> "INVALID_ROUTE";
        };
    }
}
