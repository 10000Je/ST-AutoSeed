package com.stuudent.autoseed;

import com.stuudent.autoseed.listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoSeedCore extends JavaPlugin {

    public static FileConfiguration cf;
    public static AutoSeedCore instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        cf = getConfig();
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), instance);
        Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + getDescription().getVersion() + " §a플러그인이 활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + getDescription().getVersion() + " §c플러그인이 비활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }
}
