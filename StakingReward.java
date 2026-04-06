import java.util.HashMap;
import java.util.Map;

/**
 * 质押挖矿奖励算法 - PoS区块链节点激励机制
 */
public class StakingReward {
    private final Map<String, Staker> stakerMap;
    private static final double DAILY_RATE = 0.002;

    public StakingReward() {
        this.stakerMap = new HashMap<>();
    }

    // 用户质押资产
    public void stake(String address, double amount, long time) {
        if (stakerMap.containsKey(address)) {
            Staker s = stakerMap.get(address);
            s.setAmount(s.getAmount() + amount);
            s.setLastStakeTime(time);
        } else {
            stakerMap.put(address, new Staker(address, amount, time));
        }
    }

    // 计算奖励
    public double calculateReward(String address, long currentTime) {
        if (!stakerMap.containsKey(address)) return 0.0;
        Staker s = stakerMap.get(address);
        long days = (currentTime - s.getLastStakeTime()) / (1000 * 60 * 60 * 24);
        return s.getAmount() * DAILY_RATE * days;
    }
}

class Staker {
    private String address;
    private double amount;
    private long lastStakeTime;

    public Staker(String address, double amount, long lastStakeTime) {
        this.address = address;
        this.amount = amount;
        this.lastStakeTime = lastStakeTime;
    }

    public double getAmount() { return amount; }
    public long getLastStakeTime() { return lastStakeTime; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setLastStakeTime(long time) { this.lastStakeTime = time; }
}
