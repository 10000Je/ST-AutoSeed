package com.stuudent.autoseed.listeners;

import com.stuudent.autoseed.AutoSeedCore;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        if(e.isCancelled()) return;
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
            Bukkit.getScheduler().scheduleSyncDelayedTask(AutoSeedCore.instance, () -> {
                if(!e.getBlock().getType().equals(Material.AIR)) return;
                Block north = e.getBlock().getRelative(BlockFace.NORTH); Block south = e.getBlock().getRelative(BlockFace.SOUTH);
                Block east = e.getBlock().getRelative(BlockFace.EAST); Block west = e.getBlock().getRelative(BlockFace.WEST);
                Tree jungleTree = new Tree(TreeSpecies.JUNGLE);
                if(north.getState().getData().equals(jungleTree)) {
                    CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, BlockFace.NORTH);
                    e.getBlock().setType(Material.COCOA);
                    BlockState blockState = e.getBlock().getState();
                    blockState.setData(cocoaPlant);
                    blockState.update();
                } else if(south.getState().getData().equals(jungleTree)) {
                    CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, BlockFace.SOUTH);
                    e.getBlock().setType(Material.COCOA);
                    BlockState blockState = e.getBlock().getState();
                    blockState.setData(cocoaPlant);
                    blockState.update();
                } else if(east.getState().getData().equals(jungleTree)) {
                    CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, BlockFace.EAST);
                    e.getBlock().setType(Material.COCOA);
                    BlockState blockState = e.getBlock().getState();
                    blockState.setData(cocoaPlant);
                    blockState.update();
                } else if(west.getState().getData().equals(jungleTree)) {
                    CocoaPlant cocoaPlant = new CocoaPlant(CocoaPlant.CocoaPlantSize.SMALL, BlockFace.WEST);
                    e.getBlock().setType(Material.COCOA);
                    BlockState blockState = e.getBlock().getState();
                    blockState.setData(cocoaPlant);
                    blockState.update();
                }
            }, 20);
        }
    }

}
