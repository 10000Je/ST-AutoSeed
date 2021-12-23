package com.stwdent.autoseed;

import com.stwdent.autoseed.commands.AdminCommand;
import com.stwdent.autoseed.commands.UserCommand;
import com.stwdent.autoseed.data.MainData;
import com.stwdent.autoseed.data.PlayerData;
import com.stwdent.autoseed.listeners.BlockBreakListener;
import com.stwdent.autoseed.listeners.ItemUseListener;
import com.stwdent.autoseed.schedulers.AutoSeedScheduler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoSeedCore extends JavaPlugin {

    public static FileConfiguration cf;
    public static AutoSeedCore instance;

    @Override
    public void onEnable() {
        instance = this;
        if(!dependCheck()) return;
        saveDefaultConfig();
        cf = getConfig();
        setCommandExecutors();
        setCommandTabCompleter();
        registerEvents();
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, new AutoSeedScheduler(), 0, 20);
        Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + getDescription().getVersion() + " §a플러그인이 활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(instance);
        MainData.save();
        PlayerData.save();
        Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + getDescription().getVersion() + " §c플러그인이 비활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    public void setCommandExecutors() {
        getCommand("자동심기").setExecutor(new UserCommand());
        getCommand("자동심기시간").setExecutor(new UserCommand());
        getCommand("자동심기관리").setExecutor(new AdminCommand());
    }

    public void setCommandTabCompleter() {
        getCommand("자동심기관리").setTabCompleter(new AdminCommand());
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), instance);
        Bukkit.getPluginManager().registerEvents(new ItemUseListener(), instance);
    }

    public boolean dependCheck() {
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getPluginManager().disablePlugin(instance);
            Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + getDescription().getVersion() + " §fPlaceholderAPI §c플러그인이 존재하지 않습니다.");
            return false;
        }
        return true;
    }

}
