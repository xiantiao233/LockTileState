package fun.xiantiao.lockTileState.api;

import org.bukkit.block.TileState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Lock {
    @NotNull TileState getTileState();

    @Nullable UUID getMaster();
    void setMaster(UUID master);

    void update();
}
