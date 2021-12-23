package com.stwdent.autoseed.schedulers;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AutoSeedScheduler implements Runnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
            if(autoSeedPlayer.isAutoSeedEnabled()) {
                autoSeedPlayer.removeAutoSeedTime(1);
                if(autoSeedPlayer.getAutoSeedTime() <= 0) {
                    autoSeedPlayer.setAutoSeedStatus(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("NoTime")));
                }
            }
        }
    }

}
