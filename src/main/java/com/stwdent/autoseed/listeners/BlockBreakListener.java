package com.stwdent.autoseed.listeners;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        if(e.isCancelled()) return;
        Player player = e.getPlayer();
        AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(player);
        if(!autoSeedPlayer.isAutoSeedEnabled()) return;
        if(e.getBlock().getType().equals(Material.CROPS)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Crops crops = new Crops(CropState.SEEDED);
                e.getBlock().setType(Material.CROPS);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(crops);
                blockState.update();
            }, 20);
            return;
        }
        if(e.getBlock().getType().equals(Material.CARROT)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Crops crops = new Crops(CropState.SEEDED);
                e.getBlock().setType(Material.CARROT);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(crops);
                blockState.update();
            }, 20);
            return;
        }
        if(e.getBlock().getType().equals(Material.POTATO)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Crops crops = new Crops(CropState.SEEDED);
                e.getBlock().setType(Material.POTATO);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(crops);
                blockState.update();
            }, 20);
            return;
        }
        if(e.getBlock().getType().equals(Material.BEETROOT_BLOCK)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Crops crops = new Crops(CropState.SEEDED);
                e.getBlock().setType(Material.BEETROOT_BLOCK);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(crops);
                blockState.update();
            }, 20);
            return;
        }
        if(e.getBlock().getType().equals(Material.NETHER_WARTS)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Crops crops = new Crops(CropState.SEEDED);
                e.getBlock().setType(Material.BEETROOT_BLOCK);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(crops);
                blockState.update();
            }, 20);
            return;
        }
        if(e.getBlock().getType().equals(Material.COCOA)) {
            CocoaPlant blockData = (CocoaPlant) e.getBlock().getState().getData();
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, blockData.getFacing());
                e.getBlock().setType(Material.COCOA);
                BlockState blockState = e.getBlock().getState();
                blockState.setData(cocoaPlant);
                blockState.update();
            }, 20);
        }
    }

}
