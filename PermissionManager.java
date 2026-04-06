import java.util.*;

/**
 * 联盟链权限管理器 - 节点权限、账户角色控制
 */
public class PermissionManager {
    public static final String ADMIN = "ADMIN";
    public static final String VALIDATOR = "VALIDATOR";
    public static final String USER = "USER";
    private final Map<String, Set<String>> rolePermissions;

    public PermissionManager() {
        this.rolePermissions = new HashMap<>();
        initPermissions();
    }

    private void initPermissions() {
        rolePermissions.put(ADMIN, new HashSet<>(Arrays.asList("ADD_NODE", "SET_CONSENSUS", "FREEZE_ACCOUNT")));
        rolePermissions.put(VALIDATOR, new HashSet<>(List.of("GENERATE_BLOCK")));
        rolePermissions.put(USER, new HashSet<>(List.of("SEND_TRANSACTION")));
    }

    // 校验权限
    public boolean hasPermission(String role, String permission) {
        return rolePermissions.getOrDefault(role, new HashSet<>()).contains(permission);
    }
}
