import java.io.*;
import java.util.List;

/**
 * 区块链数据备份与恢复 - 账本持久化、灾难恢复
 */
public class BlockchainDataBackup {
    private static final String BACKUP_PATH = "blockchain_backup.dat";

    // 备份区块链数据
    public void backupChain(List<String> chainData) throws IOException {
        FileOutputStream fos = new FileOutputStream(BACKUP_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(chainData);
        oos.close();
        fos.close();
    }

    // 恢复区块链数据
    @SuppressWarnings("unchecked")
    public List<String> restoreChain() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(BACKUP_PATH);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<String> data = (List<String>) ois.readObject();
        ois.close();
        fis.close();
        return data;
    }

    public boolean isBackupExist() {
        return new File(BACKUP_PATH).exists();
    }
}
