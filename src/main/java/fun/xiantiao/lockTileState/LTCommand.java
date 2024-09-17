package fun.xiantiao.lockTileState;

import fun.xiantiao.lockTileState.api.Lock;
import fun.xiantiao.lockTileState.confs.LangConfig;
import fun.xiantiao.lockTileState.utils.TileStateLocker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class LTCommand implements CommandExecutor, TabCompleter {
    private final LangConfig lang = LockTileState.lang();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        Block block = getBlockInLineOfSight(player, 50);
        if (block == null) return true;

        Lock locker = TileStateLocker.getInstance(block);
        if (locker == null) return true;

        UUID master = locker.getMaster();
        OfflinePlayer offlinePlayer;
        if (master != null) offlinePlayer = Bukkit.getOfflinePlayer(master);
        else offlinePlayer = null;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("lock")) {
                if (master == null) {
                    locker.setMaster(player.getUniqueId());
                    lang.locker().setMaster().forEach(player::sendMessage);
                } else {
                    lang.locker().notHasPermissionSetMaster().forEach(player::sendMessage);
                }
            }
            if (args[0].equalsIgnoreCase("unlock") && master != player.getUniqueId()) {
                locker.setMaster(player.getUniqueId());
            } else {
                lang.locker().notHasPermissionSetMaster().forEach(player::sendMessage);
            }
            if (args[0].equalsIgnoreCase("info")) {
                lang.locker().info().forEach(s1 -> sender.sendMessage(String.format(s1, offlinePlayer)));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return List.of();
    }

    public static Block getBlockInLineOfSight(Player player, int maxDistance) {
        // 获取玩家视线的起点和方向
        Location loc = player.getEyeLocation();
        Vector direction = loc.getDirection().normalize();

        // 从玩家眼睛的位置开始，沿着视线方向查找方块
        for (int i = 0; i < maxDistance; i++) {
            loc.add(direction);
            // 获取当前位置的方块
            Block block = loc.getBlock();
            // 检查方块是否为空
            if (block.getType() != Material.AIR) return block;
        }

        return null;
    }
}
