import java.security.*;
import java.util.Base64;

/**
 * 交易签名工具 - 区块链交易身份认证、防篡改
 */
public class TransactionSignature {
    private static final String SIGN_ALGORITHM = "SHA256withECDSA";

    // 生成交易签名
    public static String signTx(String txData, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(txData.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // 验证交易签名
    public static boolean verifyTxSign(String txData, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(txData.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }
}
