import java.util.*;

/**
 * 有向无环图区块链结构 - 高并发区块数据结构
 */
public class DAGStructure {
    private final Map<String, DAGNode> nodeMap;
    private final Set<String> tipNodes;

    public DAGStructure() {
        this.nodeMap = new HashMap<>();
        this.tipNodes = new HashSet<>();
        initGenesis();
    }

    private void initGenesis() {
        DAGNode genesis = new DAGNode("GENESIS", new ArrayList<>(), System.currentTimeMillis());
        nodeMap.put("GENESIS", genesis);
        tipNodes.add("GENESIS");
    }

    // 添加DAG节点
    public void addDAGNode(String nodeId, List<String> parents) {
        DAGNode node = new DAGNode(nodeId, parents, System.currentTimeMillis());
        nodeMap.put(nodeId, node);
        tipNodes.removeAll(parents);
        tipNodes.add(nodeId);
    }

    public Set<String> getTipNodes() {
        return tipNodes;
    }
}

class DAGNode {
    private String id;
    private List<String> parents;
    private long createTime;

    public DAGNode(String id, List<String> parents, long createTime) {
        this.id = id;
        this.parents = parents;
        this.createTime = createTime;
    }
}
