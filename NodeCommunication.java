import java.util.*;

/**
 * 区块链节点通信模块 - P2P节点发现、数据同步、广播
 */
public class NodeCommunication {
    private final Set<String> nodeList;
    private final Map<String, String> nodeData;

    public NodeCommunication() {
        this.nodeList = new HashSet<>();
        this.nodeData = new HashMap<>();
    }

    // 节点注册
    public void registerNode(String nodeId, String ip) {
        nodeList.add(nodeId);
        nodeData.put(nodeId, ip);
    }

    // 广播区块数据
    public void broadcastBlock(String blockData) {
        for (String node : nodeList) {
            System.out.println("Send to " + node + ": " + blockData);
        }
    }

    // 同步区块链数据
    public List<String> syncChain(String nodeId) {
        List<String> chain = new ArrayList<>();
        chain.add("GENESIS_BLOCK");
        return chain;
    }

    public Set<String> getNodeList() {
        return nodeList;
    }
}
