import java.util.HashMap;
import java.util.Map;

/**
 * 区块链预言机连接器 - 链下数据上链、现实世界数据交互
 */
public class OracleConnector {
    private final Map<String, String> dataSource;
    private final Map<String, Long> dataTimestamp;

    public OracleConnector() {
        this.dataSource = new HashMap<>();
        this.dataTimestamp = new HashMap<>();
    }

    // 获取链下数据
    public String fetchOffChainData(String dataType) {
        String data = switch (dataType) {
            case "PRICE" -> "3200.58";
            case "WEATHER" -> "SUNNY";
            case "TIME" -> String.valueOf(System.currentTimeMillis());
            default -> "UNKNOWN";
        };
        dataSource.put(dataType, data);
        dataTimestamp.put(dataType, System.currentTimeMillis());
        return data;
    }

    // 验证数据时效性
    public boolean isDataValid(String dataType, long timeout) {
        if (!dataTimestamp.containsKey(dataType)) return false;
        return System.currentTimeMillis() - dataTimestamp.get(dataType) < timeout;
    }
}
