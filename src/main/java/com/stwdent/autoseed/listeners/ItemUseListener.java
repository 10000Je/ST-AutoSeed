package com.stwdent.autoseed.listeners;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemUseListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
            ItemStack currentHeldItem = player.getInventory().getItemInMainHand();
            if(!currentHeldItem.hasItemMeta()) return; if(!currentHeldItem.getItemMeta().hasLocalizedName()) return;
            String itemData = currentHeldItem.getItemMeta().getLocalizedName();
            if(!itemData.contains("ST_AutoSeed_Item_")) return;
            int addedTime = Integer.parseInt(itemData.replace("ST_AutoSeed_Item_", ""));
            currentHeldItem.setAmount(currentHeldItem.getAmount() - 1);
            autoSeedPlayer.addAutoSeedTime(addedTime);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    PlaceholderAPI.setPlaceholders(player,AutoSeedCore.cf.getString("TimeAdded").replace("%added_time%", String.valueOf(addedTime)))));
        }
    }


}
