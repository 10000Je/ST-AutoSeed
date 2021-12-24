package com.stwdent.autoseed.data;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {


    @Override
    public @NotNull String getIdentifier() {
        return "stautoseed";
    }

    @Override
    public @NotNull String getAuthor() {
        return "STwDENT";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if(player == null) return null;
        AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
        switch(params) {
            case "status":
                if(autoSeedPlayer.isAutoSeedEnabled()) {
                    return ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("Activated"));
                } else {
                    return ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("Deactivated"));
                }
            case "time":
                return ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("Time").
                        replace("%current_time%", String.valueOf(autoSeedPlayer.getAutoSeedTime())));
            default:
                return null;
        }
    }

}
