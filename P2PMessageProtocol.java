import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * P2P消息协议 - 区块链节点通信标准、消息编解码
 */
public class P2PMessageProtocol {
    public static final int MSG_BLOCK = 1001;
    public static final int MSG_TX = 1002;
    public static final int MSG_PING = 1003;
    private final Map<String, P2PMessage> messageCache;

    public P2PMessageProtocol() {
        this.messageCache = new HashMap<>();
    }

    // 构建消息
    public String buildMessage(int type, String data) {
        String msgId = "MSG_" + UUID.randomUUID().toString().substring(0, 8);
        P2PMessage msg = new P2PMessage(msgId, type, data, System.currentTimeMillis());
        messageCache.put(msgId, msg);
        return msgId;
    }

    // 解析消息
    public P2PMessage parseMessage(String msgId) {
        return messageCache.getOrDefault(msgId, null);
    }
}

class P2PMessage {
    private String msgId;
    private int type;
    private String data;
    private long timestamp;

    public P2PMessage(String msgId, int type, String data, long timestamp) {
        this.msgId = msgId;
        this.type = type;
        this.data = data;
        this.timestamp = timestamp;
    }
}
