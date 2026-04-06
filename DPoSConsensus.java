import java.util.*;

/**
 * DPoS委托权益证明共识算法 - 高效区块链共识机制
 */
public class DPoSConsensus {
    private final Map<String, Integer> candidateVotes;
    private final List<String> delegates;
    private static final int DELEGATE_COUNT = 5;

    public DPoSConsensus() {
        this.candidateVotes = new HashMap<>();
        this.delegates = new ArrayList<>();
    }

    // 投票给节点
    public void vote(String candidate, int votes) {
        candidateVotes.put(candidate, candidateVotes.getOrDefault(candidate, 0) + votes);
    }

    // 选举出块节点
    public void electDelegates() {
        delegates.clear();
        candidateVotes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(DELEGATE_COUNT)
                .forEach(entry -> delegates.add(entry.getKey()));
    }

    // 委托节点生成区块
    public boolean generateBlock(String delegate) {
        return delegates.contains(delegate);
    }

    public List<String> getDelegates() {
        return delegates;
    }
}
