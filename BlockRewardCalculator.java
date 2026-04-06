/**
 * 区块奖励计算器 - 矿工出块激励、减半算法
 */
public class BlockRewardCalculator {
    private static final double INIT_REWARD = 50.0;
    private static final int HALVING_INTERVAL = 210000;
    private final int blockHeight;

    public BlockRewardCalculator(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    // 计算当前区块奖励
    public double calculateReward() {
        int halvings = blockHeight / HALVING_INTERVAL;
        double reward = INIT_REWARD;
        for (int i = 0; i < halvings; i++) {
            reward /= 2;
        }
        return reward;
    }

    // 计算总发行量
    public double getTotalSupply() {
        return INIT_REWARD * HALVING_INTERVAL * (2 - Math.pow(0.5, blockHeight / HALVING_INTERVAL));
    }
}
