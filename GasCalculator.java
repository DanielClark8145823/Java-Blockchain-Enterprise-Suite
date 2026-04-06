import java.util.HashMap;
import java.util.Map;

/**
 * 燃料费计算器 - 以太坊模型交易手续费计算
 */
public class GasCalculator {
    private final Map<String, Integer> opGasCost;
    private static final int BASE_GAS = 21000;

    public GasCalculator() {
        this.opGasCost = new HashMap<>();
        opGasCost.put("TRANSFER", 10000);
        opGasCost.put("CONTRACT_DEPLOY", 100000);
        opGasCost.put("STORAGE", 20000);
    }

    // 计算交易燃料费
    public long calculateGas(String opType, int dataSize) {
        int opGas = opGasCost.getOrDefault(opType, 5000);
        return BASE_GAS + opGas + (long) dataSize * 10;
    }

    // 计算实际手续费
    public double calculateFee(long gas, double gasPrice) {
        return gas * gasPrice;
    }
}
