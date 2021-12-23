package com.stwdent.autoseed.commands;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UserCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(cmd.getName().equals("자동심기")) {
            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
            if (autoSeedPlayer.isAutoSeedEnabled()) {
                autoSeedPlayer.setAutoSeedStatus(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("AutoSeedOFF")));
            } else {
                if (autoSeedPlayer.getAutoSeedTime() <= 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("NoTime")));
                    return false;
                }
                autoSeedPlayer.setAutoSeedStatus(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AutoSeedCore.cf.getString("AutoSeedON")));
            }
            return false;
        }
        if(cmd.getName().equals("자동심기시간")) {
            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AutoSeedCore.cf.getString("CurrentTime").
                    replace("%current_time%", String.valueOf(autoSeedPlayer.getAutoSeedTime())))));
            return false;
        }
        return false;
    }

}
