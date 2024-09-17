package fun.xiantiao.lockTileState;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockTileState extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand lt = getCommand("lt");
        if (lt != null) {
            lt.setExecutor(new LTCommand());
            lt.setTabCompleter(new LTCommand());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
