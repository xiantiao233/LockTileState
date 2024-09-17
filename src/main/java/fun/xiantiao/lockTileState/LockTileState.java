package fun.xiantiao.lockTileState;

import fun.xiantiao.lockTileState.confs.LangConfig;
import fun.xiantiao.lockTileState.listentes.BlockEvent;
import fun.xiantiao.lockTileState.utils.ConfigManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LockTileState extends JavaPlugin {

    private static ConfigManager<LangConfig> langConfigManager;

    @Override
    public void onEnable() {
        PluginCommand lt = getCommand("lt");
        if (lt != null) {
            lt.setExecutor(new LTCommand());
            lt.setTabCompleter(new LTCommand());
        }

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockEvent(), this);
    }

    public void saveDefaultConfig() {
        langConfigManager = ConfigManager.create(getDataFolder().toPath(),"lang.yml", LangConfig.class);
    }

    public void reloadConfig() {
        langConfigManager.reloadConfig();
    }

    public static LangConfig lang() {
        return langConfigManager.getConfigData();
    }

    public static LockTileState getInstance() {
        return getPlugin(LockTileState.class);
    }
}
