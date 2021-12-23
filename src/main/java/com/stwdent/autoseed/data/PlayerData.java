package com.stwdent.autoseed.data;

import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerData implements AutoSeedPlayer {

    public OfflinePlayer autoSeedPlayer;
    public static File playerDataFile;
    public static YamlConfiguration playerData;

    static {
        playerDataFile = new File("plugins/" + AutoSeedCore.instance.getName() + "/playerData.yml");
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public static void save() {
        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §ev" + AutoSeedCore.instance.getDescription().getVersion() + " §c데이터를 저장하는 과정에서 오류가 발생했습니다.");
        }
    }

    public PlayerData(OfflinePlayer player) {
        this.autoSeedPlayer = player;
    }

    @Override
    public OfflinePlayer getBukkitPlayer() {
        return this.autoSeedPlayer;
    }


    @Override
    public void setAutoSeedTime(int time) {
        playerData.set(this.autoSeedPlayer.getUniqueId() + ".TIME", time);
    }

    @Override
    public void removeAutoSeedTime(int time) {
        setAutoSeedTime(Math.max(getAutoSeedTime() - time, 0));
    }

    @Override
    public void addAutoSeedTime(int time) {
        setAutoSeedTime(getAutoSeedTime() + time);
    }

    @Override
    public int getAutoSeedTime() {
        return playerData.getInt(this.autoSeedPlayer.getUniqueId() + ".TIME", 0);
    }

    @Override
    public void setAutoSeedStatus(boolean status) {
        playerData.set(this.autoSeedPlayer.getUniqueId() + ".STATUS", status);
    }

    @Override
    public boolean isAutoSeedEnabled() {
        return playerData.getBoolean(this.autoSeedPlayer.getUniqueId() + ".STATUS", false);
    }
}
