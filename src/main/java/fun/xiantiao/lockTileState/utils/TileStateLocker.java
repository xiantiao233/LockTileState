package fun.xiantiao.lockTileState.utils;

import fun.xiantiao.lockTileState.LockTileState;
import fun.xiantiao.lockTileState.api.Lock;
import fun.xiantiao.lockTileState.pdt.UUIDDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TileStateLocker implements Lock {
    private final @NotNull TileState tileState;
    private final @NotNull PersistentDataContainer container;
    private final @NotNull UUIDDataType uuidDataType;

    private TileStateLocker(@NotNull TileState tileState) {
        this.tileState = tileState;
        this.container = tileState.getPersistentDataContainer();
        this.uuidDataType = new UUIDDataType();
    }

    public static TileStateLocker getInstance(Block block) {
        if (block.getState() instanceof TileState tileState) {
            return new TileStateLocker(tileState);
        }
        return null;
    }

    @Override
    public @NotNull TileState getTileState() {
        return tileState;
    }

    @Override
    public UUID getMaster() {
        return container.get(masterKey(), uuidDataType);
    }

    @Override
    public void setMaster(UUID master) {
        container.set(masterKey(), uuidDataType, master);
    }

    @Override
    public void update() {
        PersistentDataContainer container = tileState.getPersistentDataContainer();
        UUID master = getMaster();
        if (master == null) return;
        container.set(masterKey(), uuidDataType, master);
        tileState.update();
    }

    private NamespacedKey masterKey() {
        return new NamespacedKey(LockTileState.getInstance(),"lock-tileState-master");
    }
}
