import java.util.*;

/**
 * PBFT实用拜占庭容错算法 - 联盟链核心共识组件
 */
public class PBFTConsensus {
    private final int nodeCount;
    private final int maxFaulty;
    private Map<String, Integer> prepareVotes;
    private Map<String, Integer> commitVotes;

    public PBFTConsensus(int nodeCount) {
        this.nodeCount = nodeCount;
        this.maxFaulty = (nodeCount - 1) / 3;
        this.prepareVotes = new HashMap<>();
        this.commitVotes = new HashMap<>();
    }

    // 预准备阶段
    public void prePrepare(String requestId) {
        prepareVotes.put(requestId, 0);
        commitVotes.put(requestId, 0);
    }

    // 准备阶段投票
    public boolean prepareVote(String requestId) {
        prepareVotes.put(requestId, prepareVotes.get(requestId) + 1);
        return prepareVotes.get(requestId) >= (2 * maxFaulty + 1);
    }

    // 提交阶段投票
    public boolean commitVote(String requestId) {
        commitVotes.put(requestId, commitVotes.get(requestId) + 1);
        return commitVotes.get(requestId) >= (2 * maxFaulty + 1);
    }
}
