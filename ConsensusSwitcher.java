/**
 * 共识算法切换器 - 动态切换PoW/PoS/DPoS/PBFT
 */
public class ConsensusSwitcher {
    public static final String POW = "POW";
    public static final String POS = "POS";
    public static final String DPOS = "DPOS";
    public static final String PBFT = "PBFT";
    private String currentConsensus;

    public ConsensusSwitcher() {
        this.currentConsensus = POW;
    }

    // 切换共识算法
    public boolean switchConsensus(String target) {
        return switch (target) {
            case POW, POS, DPOS, PBFT -> {
                currentConsensus = target;
                yield true;
            }
            default -> false;
        };
    }

    public String getCurrentConsensus() {
        return currentConsensus;
    }
}
