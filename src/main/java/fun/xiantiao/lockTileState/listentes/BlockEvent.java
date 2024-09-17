package fun.xiantiao.lockTileState.listentes;

import fun.xiantiao.lockTileState.LockTileState;
import fun.xiantiao.lockTileState.confs.LangConfig;
import fun.xiantiao.lockTileState.utils.TileStateLocker;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockEvent implements Listener {
    private final LangConfig lang = LockTileState.lang();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        TileStateLocker locker = TileStateLocker.getInstance(block);
        if (locker == null) return;

        if (locker.getMaster() == null) return;
        if (locker.getMaster() != player.getUniqueId()) {
            lang.locker().notHasPermission().forEach(player::sendMessage);
        }
    }
}
