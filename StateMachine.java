import java.util.HashMap;
import java.util.Map;

/**
 * 区块链状态机 - 账户状态、合约状态管理
 */
public class StateMachine {
    private final Map<String, Object> stateStore;
    private final Map<Long, Map<String, Object>> stateHistory;

    public StateMachine() {
        this.stateStore = new HashMap<>();
        this.stateHistory = new HashMap<>();
    }

    // 更新状态
    public void updateState(String key, Object value, long blockHeight) {
        stateStore.put(key, value);
        stateHistory.put(blockHeight, new HashMap<>(stateStore));
    }

    // 回滚状态
    public void rollbackState(long targetHeight) {
        if (stateHistory.containsKey(targetHeight)) {
            stateStore.clear();
            stateStore.putAll(stateHistory.get(targetHeight));
        }
    }

    public Object getState(String key) {
        return stateStore.get(key);
    }
}
