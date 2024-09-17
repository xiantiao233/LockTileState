package fun.xiantiao.lockTileState.utils;

import fun.xiantiao.lockTileState.LockTileState;
import fun.xiantiao.lockTileState.api.Lock;
import fun.xiantiao.lockTileState.pdt.UUIDDataType;
import org.bukkit.NamespacedKey;
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

    private NamespacedKey masterKey() {
        return new NamespacedKey(LockTileState.getInstance(),"lock-tileState-master");
    }
}
