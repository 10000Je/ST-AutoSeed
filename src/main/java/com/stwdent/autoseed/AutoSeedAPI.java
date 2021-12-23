package com.stwdent.autoseed;

import com.stwdent.autoseed.data.PlayerData;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AutoSeedAPI {

    public static AutoSeedPlayer getPlayer(Player player) {
        return new PlayerData(player);
    }

    public static AutoSeedPlayer getPlayer(OfflinePlayer player) {
        return new PlayerData(player);
    }

    public static AutoSeedPlayer getPlayer(UUID uuid) {
        return new PlayerData(Bukkit.getOfflinePlayer(uuid));
    }

}
