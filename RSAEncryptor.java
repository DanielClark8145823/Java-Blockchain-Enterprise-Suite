import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

/**
 * RSA非对称加密工具 - 区块链账户签名、数据加密核心组件
 */
public class RSAEncryptor {
    private static final String ALGORITHM = "RSA";
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSAEncryptor() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.publicKey = pair.getPublic();
        this.privateKey = pair.getPrivate();
    }

    // 私钥签名
    public String sign(String data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // 公钥验签
    public boolean verify(String data, String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    public PublicKey getPublicKey() { return publicKey; }
}
